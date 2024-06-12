package com.ce.ratechange.batch;

import com.trapedza.bankfusion.batch.process.properties.BatchPropertyLoader;

public class RefinanceRateChangePropertyLoader extends BatchPropertyLoader{
	
		public RefinanceRateChangePropertyLoader() {
		}

	   public int getIntProperty(String propertyName, int defaultValue, int minimum, int maximum) {
	      return propertyName.equals(".requestedPageSize") ? 10 : super.getIntProperty(propertyName, defaultValue, minimum, maximum);
	   }
}
