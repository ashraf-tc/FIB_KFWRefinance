package com.ce.ratechange.batch;

import com.trapedza.bankfusion.batch.process.AbstractProcessAccumulator;

@SuppressWarnings("serial")
public class RefinanceRateChangeAccumulator  extends AbstractProcessAccumulator {

	public RefinanceRateChangeAccumulator(Object[] args) {
	    super(args);
	  }
	
	  public Object[] getMergedTotals() {
	    Object[] mergedTotals = new Object[5];
	    return mergedTotals;
	  }
	  
	  public Object[] getProcessWorkerTotals() {
	    Object[] mergedTotals = new Object[5];
	    return mergedTotals;
	  }
	  
	  public void mergeAccumulatedTotals(Object[] arg0) {}
	  
	@Override
	public void addAccumulatorForMerging(AbstractProcessAccumulator arg0) {
			
	}
	@Override
	public void acceptChanges() {
		
	}

	@Override
	public void accumulateTotals(Object[] arg0) {
		
	}

	@Override
	public void restoreState() {

		
	}

	@Override
	public void storeState() {
	
		
	}
	}
