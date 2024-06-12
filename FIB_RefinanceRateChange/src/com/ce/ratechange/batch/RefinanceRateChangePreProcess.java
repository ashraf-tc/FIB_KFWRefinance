package com.ce.ratechange.batch;

import com.misys.cbs.common.util.log.CBSLogger;
import com.trapedza.bankfusion.batch.fatom.AbstractFatomContext;
import com.trapedza.bankfusion.batch.process.IBatchPreProcess;
import com.trapedza.bankfusion.batch.process.PreProcessException;
import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;

@SuppressWarnings("deprecation")
public class RefinanceRateChangePreProcess implements IBatchPreProcess{
	 private static CBSLogger LOGGER = new CBSLogger(RefinanceRateChangePreProcess.class.getName());
	  
	@Override
	public void init(BankFusionEnvironment arg0)throws PreProcessException {	}

	@Override
	public void process(AbstractFatomContext arg0) {
		LOGGER.info("RefinanceRateChangePreProcess()","Inside RefinanceRateChangePreProcess ");
		
	}

}
