package com.ce.ratechange.batch;

import com.trapedza.bankfusion.batch.fatom.AbstractFatomContext;
import com.trapedza.bankfusion.batch.process.AbstractProcessAccumulator;
import com.trapedza.bankfusion.batch.process.IBatchPostProcess;
import com.trapedza.bankfusion.batch.process.engine.IBatchStatus;
import com.trapedza.bankfusion.servercommon.commands.BankFusionEnvironment;
import com.misys.cbs.common.util.log.CBSLogger;

public class RefinanceRateChangePostProcess implements IBatchPostProcess {
	private IBatchStatus status;	
	private static CBSLogger LOGGER = new CBSLogger(RefinanceRateChangePreProcess.class.getName());
	
	@Override
	public void init(BankFusionEnvironment arg0, AbstractFatomContext arg1, IBatchStatus arg2) {
		 this.status = arg2;		
	}

	@Override
	public IBatchStatus process(AbstractProcessAccumulator arg0) {
			LOGGER.info("RefinanceRateChangePostProcess()","RefinanceRateChangePostProcess - Inside post process");
		    this.status.setStatus(true);
		    return this.status;
	}

}
