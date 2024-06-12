<?xml version="1.0" encoding="ASCII"?>
<bankfusion_activityStep:ActivityStep xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:bankfusion_activityStep="http://www.misys.com/bankfusion/activityStep" artefactId="CE_LEN_RefinanceRatechange" name="CE_LEN_RefinanceRatechange" description="Refinance Rate Change EOD" versionId="3.2.2" namespace="com.fbp.customextn" accessModifier="public" codeProvider="com.trapedza.bpm.codegeneration.providers.BatchStepProvider" editedDate="2024-06-10T13:15:29.399+0530" stepId="0" superResourceID="" wrapperType="ActivityStep" implementationClass="com.ce.ratechange.batch.RefinanceRateChangeFatom">
  <inputTags name="DO_NOT_PARK" publishedType="DO_NOT_PARK" baseAttribute="ATTR_BOOLEAN" description="" displayName="DO_NOT_PARK" dataType="BOOLEAN" lookupKey="ATTR_BOOLEAN" preservedName="bf:ATTR_BOOLEAN">
    <value classType="java.lang.Boolean" value="false"/>
    <defaultValue classType="java.lang.Boolean" value="false"/>
  </inputTags>
  <inputTags name="PARK_ON_UI" publishedType="PARK_ON_UI" baseAttribute="ATTR_BOOLEAN" description="" displayName="PARK_ON_UI" dataType="BOOLEAN" lookupKey="ATTR_BOOLEAN" preservedName="bf:ATTR_BOOLEAN">
    <value classType="java.lang.Boolean" value="false"/>
    <defaultValue classType="java.lang.Boolean" value="false"/>
  </inputTags>
  <inputTags name="SYNCHRONISED" publishedType="SYNCHRONISED" baseAttribute="ATTR_BOOLEAN" description="" displayName="SYNCHRONISED" dataType="BOOLEAN" lookupKey="ATTR_BOOLEAN" preservedName="bf:ATTR_BOOLEAN">
    <value classType="java.lang.Boolean" value="false"/>
    <defaultValue classType="java.lang.Boolean" value="false"/>
  </inputTags>
  <outputTags name="StatusMsg" publishedType="StatusMsg" baseAttribute="ATTR_STRING" description="" displayName="StatusMsg" dataType="STRING" lookupKey="ATTR_STRING" preservedName="bf:ATTR_STRING">
    <value classType="java.lang.String" value=""/>
    <defaultValue classType="java.lang.String" value=""/>
  </outputTags>
  <outputTags name="BATCH_STATUS" publishedType="BATCH_STATUS" baseAttribute="ATTR_BOOLEAN" description="" displayName="BATCH_STATUS" dataType="BOOLEAN" lookupKey="ATTR_BOOLEAN" preservedName="bf:ATTR_BOOLEAN">
    <value classType="java.lang.Boolean" value="false"/>
    <defaultValue classType="java.lang.Boolean" value="false"/>
  </outputTags>
</bankfusion_activityStep:ActivityStep>
