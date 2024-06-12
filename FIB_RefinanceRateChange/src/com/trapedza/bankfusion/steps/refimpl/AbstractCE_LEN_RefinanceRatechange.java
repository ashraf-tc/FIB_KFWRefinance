
package com.trapedza.bankfusion.steps.refimpl;

import com.trapedza.bankfusion.core.CommonConstants;
import java.util.HashMap;
import bf.com.misys.bankfusion.attributes.UserDefinedFields;
import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;
import java.util.ArrayList;
import java.util.Map;

/**
* 
* DO NOT CHANGE MANUALLY - THIS IS AUTOMATICALLY GENERATED CODE.<br>
* This will be overwritten by any subsequent code-generation.
*
*/
public abstract class AbstractCE_LEN_RefinanceRatechange extends com.trapedza.bankfusion.batch.fatom.AbstractBatchFatom
		implements ICE_LEN_RefinanceRatechange {
	/**
	 * @deprecated use no-argument constructor!
	 */
	public AbstractCE_LEN_RefinanceRatechange(BankFusionEnvironment env) {
	}

	public AbstractCE_LEN_RefinanceRatechange() {
	}

	private Boolean f_IN_DO_NOT_PARK = Boolean.FALSE;

	private Boolean f_IN_PARK_ON_UI = Boolean.FALSE;

	private Boolean f_IN_SYNCHRONISED = Boolean.FALSE;
	private ArrayList<String> udfBoNames = new ArrayList<String>();
	@SuppressWarnings("rawtypes")
	private HashMap udfStateData = new HashMap();

	private String f_OUT_StatusMsg = CommonConstants.EMPTY_STRING;

	private Boolean f_OUT_BATCH_STATUS = Boolean.FALSE;

	public void nullProcess() {
	}

	public Boolean isF_IN_DO_NOT_PARK() {
		return f_IN_DO_NOT_PARK;
	}

	public void setF_IN_DO_NOT_PARK(Boolean param) {
		f_IN_DO_NOT_PARK = param;
	}

	public Boolean isF_IN_PARK_ON_UI() {
		return f_IN_PARK_ON_UI;
	}

	public void setF_IN_PARK_ON_UI(Boolean param) {
		f_IN_PARK_ON_UI = param;
	}

	public Boolean isF_IN_SYNCHRONISED() {
		return f_IN_SYNCHRONISED;
	}

	public void setF_IN_SYNCHRONISED(Boolean param) {
		f_IN_SYNCHRONISED = param;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getInDataMap() {
		Map dataInMap = new HashMap();
		dataInMap.put(IN_DO_NOT_PARK, f_IN_DO_NOT_PARK);
		dataInMap.put(IN_PARK_ON_UI, f_IN_PARK_ON_UI);
		dataInMap.put(IN_SYNCHRONISED, f_IN_SYNCHRONISED);
		return dataInMap;
	}

	public String getF_OUT_StatusMsg() {
		return f_OUT_StatusMsg;
	}

	public void setF_OUT_StatusMsg(String param) {
		f_OUT_StatusMsg = param;
	}

	@SuppressWarnings("unchecked")
	public void setUDFData(String boName, UserDefinedFields fields) {
		if (!udfBoNames.contains(boName.toUpperCase())) {
			udfBoNames.add(boName.toUpperCase());
		}
		String udfKey = boName.toUpperCase() + CommonConstants.CUSTOM_PROP;
		udfStateData.put(udfKey, fields);
	}

	public Boolean isF_OUT_BATCH_STATUS() {
		return f_OUT_BATCH_STATUS;
	}

	public void setF_OUT_BATCH_STATUS(Boolean param) {
		f_OUT_BATCH_STATUS = param;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getOutDataMap() {
		Map dataOutMap = new HashMap();
		dataOutMap.put(OUT_StatusMsg, f_OUT_StatusMsg);
		dataOutMap.put(CommonConstants.ACTIVITYSTEP_UDF_BONAMES, udfBoNames);
		dataOutMap.put(CommonConstants.ACTIVITYSTEP_UDF_STATE_DATA, udfStateData);
		dataOutMap.put(OUT_BATCH_STATUS, f_OUT_BATCH_STATUS);
		return dataOutMap;
	}
}