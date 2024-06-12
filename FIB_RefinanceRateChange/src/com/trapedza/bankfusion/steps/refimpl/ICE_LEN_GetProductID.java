
package com.trapedza.bankfusion.steps.refimpl;

import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;
import java.util.Map;
import com.trapedza.bankfusion.core.BankFusionException;

/**
* 
* DO NOT CHANGE MANUALLY - THIS IS AUTOMATICALLY GENERATED CODE.<br>
* This will be overwritten by any subsequent code-generation.
*
*/
public interface ICE_LEN_GetProductID extends com.trapedza.bankfusion.servercommon.steps.refimpl.Processable {
	public static final String IN_accountNum = "accountNum";
	public static final String OUT_isKFWRefinanceProd = "isKFWRefinanceProd";

	public void process(BankFusionEnvironment env) throws BankFusionException;

	public String getF_IN_accountNum();

	public void setF_IN_accountNum(String param);

	@SuppressWarnings("rawtypes")
	public Map getInDataMap();

	public Boolean isF_OUT_isKFWRefinanceProd();

	public void setF_OUT_isKFWRefinanceProd(Boolean param);

	@SuppressWarnings("rawtypes")
	public Map getOutDataMap();
}