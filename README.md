# custom-expensive-scenario-notifier-plainjava

CLM, since 6.0.5 has capabilities for logging/registering expensive scenarios. These are scenarios such as compare workspace or plan loading or other scenarios that can be resource intensive and may have impact on performance. CLM provides MBeans and the repository debug capability to access statistics about expensive scenarios. this data can be collected on a regular basis and logged to better understand how the system behaves. 

This capability also allows to register custom expensive scenarios. For example it would be possible to log information about the start,end, amount and average duration of a custom automation or extension. The available API allows to register scenarios ba name, their start and stop. This information is exposed in monitoring

This repository contains code to do this using the Rational Team Concert Plain Java Client Libraries.

Documentation under construction.

	Syntax : [publicURI] [userId] [password] start [scenarionName]
	Syntax : [publicURI] [userId] [password] stop [scenarionName]
  
	Example: https://clm.example.com:9443/ccm/ ADMIN ****** start MyCustomExpensiveScenario
	Example: https://clm.example.com:9443/ccm/ ADMIN ****** stop MyCustomExpensiveScenario

It might be necessary to change the login process to log into applications other than ccm. 
