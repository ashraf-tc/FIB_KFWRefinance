
package com.trapedza.bankfusion.bo.refimpl;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface IBOCE_LEN_KfwRefinanceRateHistory extends com.trapedza.bankfusion.core.SimplePersistentObject {
	public static final String BONAME = "CE_LEN_KfwRefinanceRateHistory";
	public static final String IsReversed = "f_IsReversed";
	public static final String RECCREATEDON = "f_RECCREATEDON";
	public static final String AccountID = "boID";
	public static final String RECLASTMODIFIEDDATE = "f_RECLASTMODIFIEDDATE";
	public static final String RECCREATEDBY = "f_RECCREATEDBY";
	public static final String RECAPPROVEDDATE = "f_RECAPPROVEDDATE";
	public static final String VERSIONNUM = "versionNum";
	public static final String Margin = "f_Margin";
	public static final String RECSYSDATE = "f_RECSYSDATE";
	public static final String BaseCode = "f_BaseCode";
	public static final String FixedRate = "f_FixedRate";
	public static final String RECAPPROVEDBY = "f_RECAPPROVEDBY";
	public static final String RECLASTMODIFIEDBY = "f_RECLASTMODIFIEDBY";

	public boolean isF_IsReversed();

	public void setF_IsReversed(boolean param);

	public Timestamp getF_RECCREATEDON();

	public void setF_RECCREATEDON(Timestamp param);

	public Timestamp getF_RECLASTMODIFIEDDATE();

	public void setF_RECLASTMODIFIEDDATE(Timestamp param);

	public String getF_RECCREATEDBY();

	public void setF_RECCREATEDBY(String param);

	public Timestamp getF_RECAPPROVEDDATE();

	public void setF_RECAPPROVEDDATE(Timestamp param);

	public BigDecimal getF_Margin();

	public void setF_Margin(BigDecimal param);

	public Timestamp getF_RECSYSDATE();

	public void setF_RECSYSDATE(Timestamp param);

	public String getF_BaseCode();

	public void setF_BaseCode(String param);

	public BigDecimal getF_FixedRate();

	public void setF_FixedRate(BigDecimal param);

	public String getF_RECAPPROVEDBY();

	public void setF_RECAPPROVEDBY(String param);

	public String getF_RECLASTMODIFIEDBY();

	public void setF_RECLASTMODIFIEDBY(String param);

}