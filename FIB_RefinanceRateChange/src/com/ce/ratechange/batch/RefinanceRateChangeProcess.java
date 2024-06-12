package com.ce.ratechange.batch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.misys.cbs.common.util.log.CBSLogger;
import com.misys.fbe.common.util.CommonUtil;
import com.trapedza.bankfusion.batch.fatom.AbstractPersistableFatomContext;
import com.trapedza.bankfusion.batch.process.AbstractBatchProcess;
import com.trapedza.bankfusion.batch.process.AbstractProcessAccumulator;
import com.trapedza.bankfusion.bo.refimpl.IBOCE_LEN_KfwRefinanceRateHistory;
import com.trapedza.bankfusion.bo.refimpl.IBOCE_LEN_RefinanceRateChangeTag;
import com.trapedza.bankfusion.bo.refimpl.IBOCreditInterestFeature;
import com.trapedza.bankfusion.bo.refimpl.IBOInterestBaseCode;
import com.trapedza.bankfusion.bo.refimpl.IBOUB_CMN_BatchProcessLog;
import com.trapedza.bankfusion.bo.refimpl.IBOUDFEXTAttributeCollectionFeature;
import com.trapedza.bankfusion.core.SystemInformationManager;
import com.trapedza.bankfusion.persistence.core.IPersistenceObjectsFactory;
import com.trapedza.bankfusion.servercommon.core.BankFusionThreadLocal;
import com.trapedza.bankfusion.utils.GUIDGen;

public class RefinanceRateChangeProcess extends AbstractBatchProcess {
	private static final String UDF_INTERPOLATEDRATE = "UDF_INTERPOLATEDRATE";
	IPersistenceObjectsFactory factory;
	private RefinanceRateChangeAccumulator accumulator;
	private RefinanceRateChangeContext context;

	private CBSLogger LOGGER = new CBSLogger(RefinanceRateChangeProcess.class.getName());

	public RefinanceRateChangeProcess(AbstractPersistableFatomContext context) {
		super(context);
		BankFusionThreadLocal.getBankFusionEnvironment();
		this.context = (RefinanceRateChangeContext) context;
	}

	@Override
	public AbstractProcessAccumulator getAccumulator() {
		return accumulator;
	}

	@Override
	public void init() {
		initialiseAccumulator();
	}

	@Override
	protected void initialiseAccumulator() {
		Object[] acc = new Object[0];
		accumulator = new RefinanceRateChangeAccumulator(acc);

	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@Override
	public AbstractProcessAccumulator process(int pageToProcess) {
		LOGGER.info("process()", "Testbatchprocess started");
		this.context = (RefinanceRateChangeContext) context;
		pagingData.setCurrentPageNumber(pageToProcess);

		int pageSize = this.context.getPageSize();
		int fromValue = ((pageToProcess - 1) * pageSize) + 1;
		int toValue = (pageToProcess * pageSize);
		LOGGER.info("Process", "\n" + fromValue + " ----- " + toValue);
		factory = BankFusionThreadLocal.getPersistanceFactory();
		String query = " WHERE " + IBOCE_LEN_RefinanceRateChangeTag.RowSeq + " BETWEEN ? AND ? ";
		ArrayList<Integer> parameters = new ArrayList<>();
		parameters.add(fromValue);
		parameters.add(toValue);
		List reFinanceTagRecords = BankFusionThreadLocal.getPersistanceFactory()
				.findByQuery(IBOCE_LEN_RefinanceRateChangeTag.BONAME, query, parameters, null);

		if (CommonUtil.checkIfNotNullOrEmpty(reFinanceTagRecords)) {
			BankFusionThreadLocal.setCurrentPageRecordIDs(reFinanceTagRecords);
			for (IBOCE_LEN_RefinanceRateChangeTag reFinanceTag : (List<IBOCE_LEN_RefinanceRateChangeTag>) reFinanceTagRecords) {
				try {
					handleConditionalProcessing(reFinanceTag);
				} catch (Exception e) {
					e.printStackTrace();
					createLogMessage(e.getMessage(), reFinanceTag);
					reFinanceTag.setF_Status("E");
				}
			}
		}

		factory = BankFusionThreadLocal.getPersistanceFactory();
		return accumulator;
	}

	private void createLogMessage(String message, IBOCE_LEN_RefinanceRateChangeTag reFinanceTag) {
	        IBOUB_CMN_BatchProcessLog batchException = (IBOUB_CMN_BatchProcessLog) factory.getStatelessNewInstance(IBOUB_CMN_BatchProcessLog.BONAME);
	        batchException.setBoID(GUIDGen.getNewGUID());
	        batchException.setF_PROCESSNAME(this.context.getBatchProcessName());
	        batchException.setF_RUNDATETIME(SystemInformationManager.getInstance().getBFBusinessDateTime());
	        batchException.setF_RECORDID(reFinanceTag.getBoID());
	        boolean isReversed = reFinanceTag.isF_IsReversed();
	        message = isReversed == true ? ("While Updating Credit Interest : " + message) : ("While Reverting value of Basecode and margin from History" + message); 
	        batchException.setF_ERRORMESSAGE(message);
	        batchException.setF_STATUS("E");
	        factory.create(IBOUB_CMN_BatchProcessLog.BONAME, batchException);
	}

	private void handleConditionalProcessing(IBOCE_LEN_RefinanceRateChangeTag reFinanceTag) {
		if (!reFinanceTag.isF_IsReversed()) {
			IBOUDFEXTAttributeCollectionFeature hostExtn = (IBOUDFEXTAttributeCollectionFeature) factory
					.findByPrimaryKey(IBOUDFEXTAttributeCollectionFeature.BONAME, reFinanceTag.getBoID(),
							Boolean.TRUE);
			BigDecimal interpolatedRate = new BigDecimal(0);
			if (null != hostExtn.getValueOfCustomField(UDF_INTERPOLATEDRATE)) {
				interpolatedRate = (BigDecimal) hostExtn.getValueOfCustomField(UDF_INTERPOLATEDRATE);
			}
			updateCreditInterestRate(reFinanceTag.getBoID(), interpolatedRate);
			reFinanceTag.setF_Status("P");

		} else {
			IBOCE_LEN_KfwRefinanceRateHistory kfwRefinanceRateHistory = (IBOCE_LEN_KfwRefinanceRateHistory) BankFusionThreadLocal
					.getPersistanceFactory().findByPrimaryKey(IBOCE_LEN_KfwRefinanceRateHistory.BONAME,
							reFinanceTag.getBoID(), Boolean.TRUE);
			revertValueOfRateFromHistory(reFinanceTag.getBoID(), kfwRefinanceRateHistory.getF_BaseCode(),
					kfwRefinanceRateHistory.getF_Margin());
			kfwRefinanceRateHistory.setF_IsReversed(true);
			kfwRefinanceRateHistory.setF_RECLASTMODIFIEDDATE(SystemInformationManager.getInstance().getBFBusinessDateTime());
			reFinanceTag.setF_Status("P");
		}
	}

	private void updateCreditInterestRate(String accountID, BigDecimal creditInterestRate) {
		IBOCreditInterestFeature iboCreditInterestFeature = (IBOCreditInterestFeature) BankFusionThreadLocal
				.getPersistanceFactory().findByPrimaryKey(IBOCreditInterestFeature.BONAME, accountID, Boolean.TRUE);
		iboCreditInterestFeature.setF_CREDITINTERESTRATE(creditInterestRate);
		iboCreditInterestFeature.setF_CREDITBASECODE(null);
		iboCreditInterestFeature.setF_CREDITINTERESTMARGIN(BigDecimal.ZERO);
		LOGGER.info("updateCreditInterestRate()", "CreditInterestRate is Updated with the value of Interpolated Rate");
		
	}

	private void revertValueOfRateFromHistory(String accountID, String baseCode, BigDecimal margin) {
		IBOCreditInterestFeature iboCreditInterestFeature = (IBOCreditInterestFeature) BankFusionThreadLocal
				.getPersistanceFactory().findByPrimaryKey(IBOCreditInterestFeature.BONAME, accountID, Boolean.TRUE);
		iboCreditInterestFeature.setF_CREDITINTERESTRATE(getBaseCode(baseCode));
		iboCreditInterestFeature.setF_CREDITBASECODE(baseCode);
		iboCreditInterestFeature.setF_CREDITINTERESTMARGIN(margin);
		LOGGER.info("revertValueOfRateFromHistory()","Base Code and Margin is reverted from Refinance Rate Change History Table");
	}

	private BigDecimal getBaseCode(String baseCode) {
		BigDecimal baseCodeRate = BigDecimal.ZERO;
		IBOInterestBaseCode interestBaseCodeDtl = (IBOInterestBaseCode)BankFusionThreadLocal.getPersistanceFactory().findByPrimaryKey(IBOInterestBaseCode.BONAME, baseCode, true);
		if(null!=interestBaseCodeDtl ) {
			baseCodeRate = interestBaseCodeDtl.getF_INTRATE();
			LOGGER.info("getBaseCode()","Base Code Rate fetched Rate value - " +baseCodeRate+" Base Code - "+ baseCode);
		}else {			
			LOGGER.info("getBaseCode()","Base Code not found in the Table - BaseCode - "+baseCode);
		}
		return baseCodeRate;
	}

}
