package com.ce.ratechange.batch;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import com.misys.cbs.common.util.log.CBSLogger;
import com.trapedza.bankfusion.batch.fatom.AbstractFatomContext;
import com.trapedza.bankfusion.batch.services.BatchService;
import com.trapedza.bankfusion.bo.refimpl.IBOCE_LEN_RefinanceRateChangeTag;
import com.trapedza.bankfusion.core.SystemInformationManager;
import com.trapedza.bankfusion.persistence.core.IPersistenceObjectsFactory;
import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;
import com.trapedza.bankfusion.servercommon.core.BankFusionThreadLocal;
import com.trapedza.bankfusion.servercommon.services.IServiceManager;
import com.trapedza.bankfusion.servercommon.services.ServiceManager;
import com.trapedza.bankfusion.servercommon.services.ServiceManagerFactory;
import com.trapedza.bankfusion.steps.refimpl.AbstractCE_LEN_RefinanceRatechange;

@SuppressWarnings("unused")
public class RefinanceRateChangeFatom extends AbstractCE_LEN_RefinanceRatechange {
	private static String CLASS_NAME = RefinanceRateChangeFatom.class.getName();
	private static CBSLogger LOGGER = new CBSLogger(CLASS_NAME);
	private RefinanceRateChangeContext Context;
	private IPersistenceObjectsFactory factory;

	protected AbstractFatomContext getFatomContext() {
		return (AbstractFatomContext) new RefinanceRateChangeContext();
	}

	@SuppressWarnings("deprecation")
	public RefinanceRateChangeFatom(BankFusionEnvironment env) {
		super(env);
	}

	protected void processBatch(BankFusionEnvironment env, AbstractFatomContext context) {
		this.Context = (RefinanceRateChangeContext) context;
		try {
			tagTableInsert(env);
			boolean status = false;
			IServiceManager sm = ServiceManagerFactory.getInstance().getServiceManager();
			BatchService service = (BatchService) sm.getServiceForName(ServiceManager.BATCH_SERVICE);
			if (service.runBatch(env, (AbstractFatomContext) this.Context)) {
				status = Boolean.TRUE;
			} else {
				status = Boolean.FALSE;
			}
		} catch (Exception e) {
			e.getStackTrace();
			LOGGER.info("RefinanceRateChangeFatom()", "Batch fatom Failed - " + e.getMessage());
		}
	}

	private void tagTableInsert(BankFusionEnvironment env) {
		try {
			Connection connection;
			connection = BankFusionThreadLocal.getPersistanceFactory().getJDBCConnection();
	    	String currentDateAsString = "TO_DATE('"+SystemInformationManager.getInstance().getBFBusinessDateAsString() +"','YYYY-MM-DD')";
	    	String timeStamp = "TO_DATE('"+SystemInformationManager.getInstance().getBFBusinessDateTimeAsString() +"','YYYY-MM-DD HH24:MI:SS')";
			String userId = "'" + BankFusionThreadLocal.getUserId()+"'";

			BankFusionThreadLocal.getPersistanceFactory().bulkDeleteAll(IBOCE_LEN_RefinanceRateChangeTag.BONAME);

			String tagInsert = "INSERT INTO CUSTOMEXTN.CETB_REFINANCERATECHANGETAG (ROWSEQ, ACCOUNTIDPK, FIEXEDRATE, BASECODE, MARGIN, ISREVERSED, VERSIONNUM) "
		            + "SELECT ROW_NUMBER() OVER (ORDER BY SEQNUMBER), ACCOUNTID AS ACCOUNTID, CREDITINTERESTRATE AS CREDITINTERESTRATE, CREDITBASECODE AS CREDITBASECODE, CREDITINTERESTMARGIN AS CREDITINTERESTMARGIN, ISREVERSED AS ISREVERSED, VERSIONNUM AS VERSIONNUM FROM "
		            + "(SELECT ROW_NUMBER() OVER (ORDER BY ACC.ACCOUNTID) AS SEQNUMBER, "
		            + "ACC.ACCOUNTID AS ACCOUNTID, CINT.CREDITINTERESTRATE AS CREDITINTERESTRATE , CINT.CREDITBASECODE AS CREDITBASECODE, CINT.CREDITINTERESTMARGIN AS CREDITINTERESTMARGIN, 'N' AS ISREVERSED, 0 AS VERSIONNUM "
		            + "FROM WASADMIN.ACCOUNT ACC "
		            + "JOIN WASADMIN.CREDITINTEREST CINT ON ACC.ACCOUNTID = CINT.ACCOUNTID "
		            + "JOIN WASADMIN.UBTB_ACCOUNT_UD ACC_UD ON ACC_UD.ACCOUNTID = ACC.ACCOUNTID "
		            + "WHERE ACC_UD.UDF_INFLOWDATE = "+currentDateAsString+" AND ACC_UD.UDF_INTERPOLATEDRATE IS NOT NULL "
		            + "AND NOT EXISTS (SELECT 1 FROM CUSTOMEXTN.CETB_KFWREFINANCERATEHISTORY HIS WHERE HIS.ACCOUNTIDPK = ACC.ACCOUNTID) AND CINT.CREDITBASECODE IS NOT NULL " 
		            + "UNION ALL SELECT ROW_NUMBER() OVER (ORDER BY HIS.ACCOUNTIDPK) AS SEQNUMBER, " 
		            + "HIS.ACCOUNTIDPK, HIS.BASERATE , HIS.BASECODE, HIS.MARGIN, 'Y', 0 " 
		            + "FROM CUSTOMEXTN.CETB_KFWREFINANCERATEHISTORY HIS "
		            + "JOIN WASADMIN.CREDITINTEREST CINT ON HIS.ACCOUNTIDPK = CINT.ACCOUNTID " 
		            + "JOIN WASADMIN.ACCOUNT ACC ON ACC.ACCOUNTID = CINT.ACCOUNTID "
		            + "WHERE HIS.ISREVERSED = 'N' "
		            + "AND TRUNC(CINT.LASTINTERESTAPPLICATIONDATE) = "+currentDateAsString + ")";//+" AND TRUNC(CINT.LASTINTERESTAPPLICATIONDATE) != TO_DATE('1970-01-01','YYYY-MM-DD'))";

			String historyInsertQuery = "INSERT INTO CUSTOMEXTN.CETB_KFWREFINANCERATEHISTORY " 
					+ "(ISREVERSED, RECCREATEDON, ACCOUNTIDPK, RECLASTMODIFIEDDATE, RECCREATEDBY, VERSIONNUM, MARGIN, RECSYSDATE, BASECODE, BASERATE, RECAPPROVEDBY, RECLASTMODIFIEDBY) "
					+ "SELECT 'N'as ISREVERSED, "+timeStamp+" as RECCREATEDON, TAG.ACCOUNTIDPK as ACCOUNTIDPK, "+timeStamp+" as RECLASTMODIFIEDDATE, "+userId+" as RECCREATEDBY, 0 as VERSIONNUM, TAG.MARGIN as MARGIN, "+timeStamp+" as RECSYSDATE, TAG.BASECODE as BASECODE, TAG.FIEXEDRATE as FIXEDRATE, NULL, NULL "
					+ "FROM CUSTOMEXTN.CETB_REFINANCERATECHANGETAG TAG WHERE TAG.ISREVERSED != 'Y' ";

			PreparedStatement ps1 = connection.prepareStatement(tagInsert);
			int insertCount = ps1.executeUpdate();
			BankFusionThreadLocal.getPersistanceFactory().commitTransaction();
			BankFusionThreadLocal.getPersistanceFactory().beginTransaction();
			LOGGER.info("tagTableInsert()", "Inserted successfully Count - " + insertCount);

			connection = BankFusionThreadLocal.getPersistanceFactory().getJDBCConnection();
			PreparedStatement ps2 = connection.prepareStatement(historyInsertQuery);
			insertCount = ps2.executeUpdate();
			BankFusionThreadLocal.getPersistanceFactory().commitTransaction();
			BankFusionThreadLocal.getPersistanceFactory().beginTransaction();
			LOGGER.info("HistoryTableInsert", "Inserted Successfully Count - " + insertCount);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void setOutputTags(AbstractFatomContext arg0) {

	}
}
