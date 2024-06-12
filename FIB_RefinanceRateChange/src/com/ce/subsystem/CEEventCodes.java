package com.ce.subsystem;

import com.trapedza.bankfusion.core.CommonConstants;
import com.trapedza.bankfusion.utils.AbstractEventCodes;

public class CEEventCodes extends AbstractEventCodes {

	private static final int SUB_SYSTEM_ID = 800;

	public CEEventCodes() {
		subsystemId = SUB_SYSTEM_ID;
		baseName = CommonConstants.EMPTY_STRING;
	}

}
