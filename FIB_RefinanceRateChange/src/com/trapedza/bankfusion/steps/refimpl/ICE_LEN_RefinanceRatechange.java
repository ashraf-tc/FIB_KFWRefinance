
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
public interface ICE_LEN_RefinanceRatechange {
	public static final String IN_DO_NOT_PARK = "DO_NOT_PARK";
	public static final String IN_PARK_ON_UI = "PARK_ON_UI";
	public static final String IN_SYNCHRONISED = "SYNCHRONISED";
	public static final String OUT_StatusMsg = "StatusMsg";
	public static final String OUT_BATCH_STATUS = "BATCH_STATUS";

	public void process(BankFusionEnvironment env) throws BankFusionException;

	public Boolean isF_IN_DO_NOT_PARK();

	public void setF_IN_DO_NOT_PARK(Boolean param);

	public Boolean isF_IN_PARK_ON_UI();

	public void setF_IN_PARK_ON_UI(Boolean param);

	public Boolean isF_IN_SYNCHRONISED();

	public void setF_IN_SYNCHRONISED(Boolean param);

	@SuppressWarnings("rawtypes")
	public Map getInDataMap();

	public String getF_OUT_StatusMsg();

	public void setF_OUT_StatusMsg(String param);

	public Boolean isF_OUT_BATCH_STATUS();

	public void setF_OUT_BATCH_STATUS(Boolean param);

	@SuppressWarnings("rawtypes")
	public Map getOutDataMap();

	public void setStepName(String name);
}