
package com.trapedza.bankfusion.bo.refimpl;

import java.math.BigDecimal;

public interface IBOCE_LEN_RefinanceRateChangeTag extends com.trapedza.bankfusion.core.SimplePersistentObject {
	public static final String BONAME = "CE_LEN_RefinanceRateChangeTag";
	public static final String Status = "f_Status";
	public static final String IsReversed = "f_IsReversed";
	public static final String margin = "f_margin";
	public static final String RowSeq = "f_RowSeq";
	public static final String BaseCode = "f_BaseCode";
	public static final String FiexedRate = "f_FiexedRate";
	public static final String AccountID = "boID";
	public static final String VERSIONNUM = "versionNum";

	public String getF_Status();

	public void setF_Status(String param);

	public boolean isF_IsReversed();

	public void setF_IsReversed(boolean param);

	public BigDecimal getF_margin();

	public void setF_margin(BigDecimal param);

	public int getF_RowSeq();

	public void setF_RowSeq(int param);

	public String getF_BaseCode();

	public void setF_BaseCode(String param);

	public BigDecimal getF_FiexedRate();

	public void setF_FiexedRate(BigDecimal param);

}