define("app/fib/ce_kfwvalidateinflowdate", [
    "uxp/utility",
    "uxp/api",
    "dijit/registry",
    "dojo/dom",
], function (utility, api, registry, dom) {
    var ce_kfwvalidateinflowdate= {};
    ce_kfwvalidateinflowdate.process = function (formid) {
    	
		var rowDataMap = {};
		rowDataMap["03UDF_INFLOWDATE"] = UDF_INFLOWDATE;
		var reqItem = utility.getRequestMessage();
		reqItem.requestBody = rowDataMap;
		reqItem.requestBody.isDialog = true;
		reqItem.requestBody.saction = "subProcessStart";
		reqItem.requestBody.mfname = "CE_LEN_ValidateKFWDetails_SRV"; 
		reqItem.requestBody.sname = "CE_LEN_ValidateKFWDetails_SRV"; 
		reqItem.requestBody.sdn = "CE_LEN_ValidateKFWDetails_SRV"; 
		reqItem.requestBody.sid = "";
		reqItem.requestBody.ignoreResponse = true;
		reqItem["suppressEvents"] = true;
		
		var responseMapping = {};
        responseMapping["responseData"] = "{\"responseData\":\"\"\}";
		responseMapping["UDF_INFLOWDATE"] = "{\"UDF_INFLOWDATE\":\"\"\}";
        reqItem.requestBody.expectedValues = JSON.stringify(responseMapping);
		
		console.log("Invoking MF service for validating value of UDF_INFLOWDATE");
		var cbargs = []; 
		require(["uxp/core" ], function(core){
				core.invokeService(document, reqItem, "", handleResponse, cbargs); //handleResponse - callback function
		});
		
    };
	
	function handleResponse(response) {
		
	}
	
	return ce_kfwvalidateinflowdate;
});