package com.ce.fatom;

import com.misys.cbs.common.util.log.CBSLogger;
import com.trapedza.bankfusion.bo.refimpl.IBOAccount;
import com.trapedza.bankfusion.core.BankFusionException;
import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;
import com.trapedza.bankfusion.servercommon.core.BankFusionThreadLocal;
import com.trapedza.bankfusion.steps.refimpl.AbstractCE_LEN_GetProductID;

@SuppressWarnings("serial")
public class GetProductId extends AbstractCE_LEN_GetProductID  {
	private static String CLASS_NAME = GetProductId.class.getName();
	private static CBSLogger LOGGER = new CBSLogger(CLASS_NAME);
	@Override
	public void process(BankFusionEnvironment env) throws BankFusionException {
		// TODO Auto-generated method stub
		super.process(env);
		String accountNum = getF_IN_accountNum();
		IBOAccount account = (IBOAccount) BankFusionThreadLocal.getPersistanceFactory().findByPrimaryKey(IBOAccount.BONAME, accountNum, true);
		String productId = account.getF_PRODUCTID();
		if(productId.equals("kfwrefinance")) {
			setF_OUT_isKFWRefinanceProd(true);
			LOGGER.info("GetProductID()", "ProductID for the given accountNum: "+accountNum+" is kfwrefinancr");
		}
		else {
			setF_OUT_isKFWRefinanceProd(false);
			LOGGER.info("GetProductID()", "ProductID for the given accountNum: "+accountNum+" is "+productId);
		}
	}



	@SuppressWarnings("deprecation")
	public GetProductId(BankFusionEnvironment env) {
		super(env);
	}



	public GetProductId() {
		super();
	}
	
	
}
