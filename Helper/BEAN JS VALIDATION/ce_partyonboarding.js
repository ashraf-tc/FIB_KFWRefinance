define("app/fib/ce_partyonboarding", [
    "uxp/utility",
    "uxp/api",
    "dijit/registry",
    "dojo/dom",
], function (utility, api, registry, dom) {
    var ce_partyonboarding = {};
    ce_partyonboarding.process = function (formid) {
    	
        var formTitleElement = document.getElementById(formid + "formtitle");
        var screenname = formTitleElement.getAttribute("screen_name");
        var partyType = "";
		var onboardingPartyType = ""; 
        
        if("PT_PAM_CreateQuickPersonalParty" === screenname ){
        	partyType = "1062";
			onboardingPartyType = "quick";
        } else if("PT_PAM_CreateQuickEnterpriseParty" === screenname){
        	partyType = "1063";
			onboardingPartyType = "quick";
        } else if("PT_PAM_CreatePersonalParty" === screenname ){
        	partyType = "1062";
			onboardingPartyType = "full";
        } else if("PT_PAM_AddEnterpriseDetails" === screenname){
        	partyType = "1063";
			onboardingPartyType = "full";
        }
		
		var rowDataMap = {};
		rowDataMap["03partyType"] = partyType;
		rowDataMap["03formId"] = formid;
		rowDataMap["03type"] = onboardingPartyType;
		var reqItem = utility.getRequestMessage();
		reqItem.requestBody = rowDataMap;
		reqItem.requestBody.isDialog = true;
		reqItem.requestBody.saction = "subProcessStart";
		reqItem.requestBody.mfname = "CE_PTY_GeneratePartyID_SRV"; 
		reqItem.requestBody.sname = "CE_PTY_GeneratePartyID_SRV"; 
		reqItem.requestBody.sdn = "CE_PTY_GeneratePartyID_SRV"; 
		reqItem.requestBody.sid = "";
		reqItem.requestBody.ignoreResponse = true;
		reqItem["suppressEvents"] = true;
		
		var responseMapping = {};
        responseMapping["responseData"] = "{\"responseData\":\"\"\}";
		responseMapping["formId"] = "{\"formId\":\"\"\}";
		responseMapping["partyType"] = "{\"partyType\":\"\"\}";
		responseMapping["type"] = "{\"type\":\"\"\}";
        reqItem.requestBody.expectedValues = JSON.stringify(responseMapping);
		
		console.log("****** Invoking MF service for the generating Party ID ******");
		var cbargs = []; 
		require(["uxp/core" ], function(core){
				core.invokeService(document, reqItem, "", handleResponse, cbargs); //handleResponse - callback function
		});
		
    };
	
	function handleResponse(response) {
		console.log(" Response Handler : " + response);
		if (response.uxsd) {
            var responseData = JSON.parse(response.uxsd)["responseData"];
			var formId = JSON.parse(response.uxsd)["formId"];
			var partyType = JSON.parse(response.uxsd)["partyType"];
			var onboardingPartyType = JSON.parse(response.uxsd)["type"];
			console.log("****** Generated Party ID is " + responseData + " ******");
			 if("quick" === onboardingPartyType){
				if("1062" === partyType ){
				api.setControlAttribute(formId , "createPersonalPartyRq:createPersonPartyInput:partyBasicDtls:partyId" + formId, responseData,'value'); 
				api.disableEnableControl("", "createPersonalPartyRq:createPersonPartyInput:partyBasicDtls:partyId" + formId, true);
			 } else if("1063" === partyType ){
				api.setControlAttribute(formId , "CreateEntPartyBasicDtlsRq:createEntPartyBasicDtlsInput:partyBasicDtls:partyId" + formId, responseData,'value'); 
				api.disableEnableControl("", "CreateEntPartyBasicDtlsRq:createEntPartyBasicDtlsInput:partyBasicDtls:partyId" + formId, true);
			 } else {
				 console.log("****** Invalid Party Type ******");
			 }
			} else if ("full" === onboardingPartyType){
				if("1062" === partyType ){
				api.setControlAttribute(formId , "createPersonalPartyRq:createPersonPartyInput:partyBasicDtls:partyId" + formId, responseData,'value'); 
				api.disableEnableControl("", "createPersonalPartyRq:createPersonPartyInput:partyBasicDtls:partyId" + formId, true);
			 } else if("1063" === partyType ){
				api.setControlAttribute(formId , "CreateEntPartyBasicDtlsRq:createEntPartyBasicDtlsInput:partyBasicDtls:partyId" + formId, responseData,'value'); 
				api.disableEnableControl("", "CreateEntPartyBasicDtlsRq:createEntPartyBasicDtlsInput:partyBasicDtls:partyId" + formId, true);
			 } else {
				 console.log("****** Invalid Party Type ******");
			 }
			}

				 
			 
        }
	}
	
	return ce_partyonboarding;
});