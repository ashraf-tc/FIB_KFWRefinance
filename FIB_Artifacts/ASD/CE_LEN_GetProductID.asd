<?xml version="1.0" encoding="ASCII"?>
<bankfusion_activityStep:ActivityStep xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:bankfusion_activityStep="http://www.misys.com/bankfusion/activityStep" artefactId="CE_LEN_GetProductID" name="CE_LEN_GetProductID" description="To get the productID of given Account Number to validate the KFW UDF" versionId="3.2.2" namespace="com.fbp.customextn" accessModifier="public" codeProvider="" editedDate="2024-06-12T12:42:45.987+0530" stepId="0" superResourceID="" wrapperType="ActivityStep" implementationClass="com.ce.fatom.GetProductId">
  <inputTags name="accountNum" publishedType="accountNum" baseAttribute="ATTR_STRING" description="" displayName="accountNum" dataType="STRING" lookupKey="ATTR_STRING" preservedName="bf:ATTR_STRING">
    <value classType="java.lang.String" value=""/>
    <defaultValue classType="java.lang.String" value=""/>
  </inputTags>
  <outputTags name="isKFWRefinanceProd" publishedType="isKFWRefinanceProd" baseAttribute="ATTR_BOOLEAN" description="" displayName="isKFWRefinanceProd" dataType="BOOLEAN" lookupKey="ATTR_BOOLEAN" preservedName="bf:ATTR_BOOLEAN">
    <value classType="java.lang.Boolean" value="false"/>
    <defaultValue classType="java.lang.Boolean" value="false"/>
  </outputTags>
</bankfusion_activityStep:ActivityStep>
