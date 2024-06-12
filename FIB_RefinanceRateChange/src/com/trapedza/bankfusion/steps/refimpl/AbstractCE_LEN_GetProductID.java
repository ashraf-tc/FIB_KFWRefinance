
package com.trapedza.bankfusion.steps.refimpl;

import com.trapedza.bankfusion.core.CommonConstants;
import java.util.HashMap;
import bf.com.misys.bankfusion.attributes.UserDefinedFields;
import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;
import java.util.ArrayList;
import java.util.Map;
import com.trapedza.bankfusion.core.BankFusionException;

/**
* 
* DO NOT CHANGE MANUALLY - THIS IS AUTOMATICALLY GENERATED CODE.<br>
* This will be overwritten by any subsequent code-generation.
*
*/
@SuppressWarnings("serial")
public abstract class AbstractCE_LEN_GetProductID implements ICE_LEN_GetProductID {
	/**
	 * @deprecated use no-argument constructor!
	 */
	public AbstractCE_LEN_GetProductID(BankFusionEnvironment env) {
	}

	public AbstractCE_LEN_GetProductID() {
	}

	private String f_IN_accountNum = CommonConstants.EMPTY_STRING;
	private ArrayList<String> udfBoNames = new ArrayList<String>();
	@SuppressWarnings("rawtypes")
	private HashMap udfStateData = new HashMap();

	private Boolean f_OUT_isKFWRefinanceProd = Boolean.FALSE;

	public void process(BankFusionEnvironment env) throws BankFusionException {
	}

	public String getF_IN_accountNum() {
		return f_IN_accountNum;
	}

	public void setF_IN_accountNum(String param) {
		f_IN_accountNum = param;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getInDataMap() {
		Map dataInMap = new HashMap();
		dataInMap.put(IN_accountNum, f_IN_accountNum);
		return dataInMap;
	}

	public Boolean isF_OUT_isKFWRefinanceProd() {
		return f_OUT_isKFWRefinanceProd;
	}

	public void setF_OUT_isKFWRefinanceProd(Boolean param) {
		f_OUT_isKFWRefinanceProd = param;
	}

	@SuppressWarnings("unchecked")
	public void setUDFData(String boName, UserDefinedFields fields) {
		if (!udfBoNames.contains(boName.toUpperCase())) {
			udfBoNames.add(boName.toUpperCase());
		}
		String udfKey = boName.toUpperCase() + CommonConstants.CUSTOM_PROP;
		udfStateData.put(udfKey, fields);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getOutDataMap() {
		Map dataOutMap = new HashMap();
		dataOutMap.put(OUT_isKFWRefinanceProd, f_OUT_isKFWRefinanceProd);
		dataOutMap.put(CommonConstants.ACTIVITYSTEP_UDF_BONAMES, udfBoNames);
		dataOutMap.put(CommonConstants.ACTIVITYSTEP_UDF_STATE_DATA, udfStateData);
		return dataOutMap;
	}
}