package com.ce.ratechange.batch;

import com.trapedza.bankfusion.batch.fatom.AbstractPersistableFatomContext;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings(value = { "rawtypes", "serial" })
public class RefinanceRateChangeContext extends AbstractPersistableFatomContext {

  private String batchName = "RefinanceRateChange";
  private String processName = "com.ce.ratechange.batch.RefinanceRateChangeProcess";
  
  public boolean isMultiNodeSupported() {
    return false;
  }
  
  public Object[] getAdditionalProcessParams() {
    return new Object[1];
  }
  
  public String getBatchProcessName() {
    return batchName;
  }
  
  public String getProcessClassName() {
    return processName;
  }
  
  public Map getInputTagDataMap() {
    return new HashMap<>();
  }
  
public Map getOutputTagDataMap() {
    return new HashMap<>();
  }
  
  public void setAdditionalProcessParams(Object[] arg0) {}
  
  public void setBatchProcessName(String arg0) {}
  
  public void setInputTagDataMap(Map arg0) {}
  
  public void setOutputTagDataMap(Map arg0) {}
}
