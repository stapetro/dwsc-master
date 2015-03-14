# Introduction #

This document aims to provide a basic description of how what steps need to be performed in order to register and activate the YaniBpelExtension for use in WSO2 BPS.


# Details #
1. Copy the YaniBpelExtension.jar file to the following directory 

<wso2\_install\_dir>

\repository\components\lib
2. Open the bps.xml file in the following directory 

<wso2\_install\_dir>

\repository\conf
3. Register the YaniBpelExtension in the bps.xml file by updating the 

&lt;tns:ExtensionBundles&gt;

 element
Your updated element would look like:
<!-- BPEL Extensions -->
> 

&lt;tns:ExtensionBundles&gt;


> > 

&lt;tns:runtimes&gt;


> > > 

&lt;tns:runtime class="bg.unisofia.fmi.dwsc.bpel.extension.YaniWsComposerBundle"/&gt;



> > 

&lt;/tns:runtimes&gt;


> > <!--

&lt;tns:filters&gt;


> > > 

&lt;tns:filter class="org.wso2.bps.SampleCorrelationFilter"/&gt;



> > 

&lt;/tns:filters&gt;

-->

> 

&lt;/tns:ExtensionBundles&gt;

