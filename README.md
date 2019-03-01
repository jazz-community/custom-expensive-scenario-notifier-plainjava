# custom-expensive-scenario-notifier-plainjava
CLM allows logging of custom expensive scenarios. This repository contains code to do this using the Rational Team Concert Plain Java Client Libraries.

Documentation under construction.

	Syntax : [publicURI] [userId] [password] start [scenarionName]
	Syntax : [publicURI] [userId] [password] stop [scenarionName]
  
	Example: https://clm.example.com:9443/ccm/ ADMIN ****** -scenarioName start MyCustomExpensiveScenario
	Example: https://clm.example.com:9443/ccm/ ADMIN ****** -scenarioName stop MyCustomExpensiveScenario

It might be necessary to change the login process to log into applications other than ccm. 
