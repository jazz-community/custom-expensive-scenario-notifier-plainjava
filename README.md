# custom-expensive-scenario-notifier-plainjava

Version 1.2

CLM, since 6.0.5 has capabilities for logging/registering expensive scenarios. These are scenarios such as compare workspace or plan loading or other scenarios that can be resource intensive and may have impact on performance. CLM provides MBeans and the repository debug capability to access statistics about expensive scenarios. This data can be collected on a regular basis and logged to better understand how the system behaves. The scenario information is available using the monitoring capabilities. See [CLM Monitoring](https://jazz.net/library/article/91590) section **6. Resource-Intensive Scenarios Summary bean**. Another, simpler approach, is to access the ICounterContentService using the URL `https://<server>:<port>/<app>/service/com.ibm.team.repository.service.internal.counters.ICounterContentService` and then look into the information in the sections **scenarios** and **scenariosSummary**. 

The capability also allows to register custom resource-intensive scenarios. It is possibe to register the start and end time of a custom resource-intensive scenario. See [Register Custom Scripts as a Resource-intensive Scenario](https://jazz.net/wiki/bin/view/Deployment/CreateCustomScenarios) for more details on the background of resource-intensive scenarios.
See also [Registering Custom Resource Intensive Scenarios to CLM Applications](https://rsjazz.wordpress.com/2019/03/07/registering-custom-resource-intensive-scenarios-to-clm-applications/) for more background information. 

The available API allows to register custom scenarios by name using a start and a stop command.

**IMPORTANT: This code should not be used as a command line option.** 
Use this code only in the context of RTC Plain Java Client Libraries or RTC SDK based automation or extensions e.g.
  * Automation based on RTC Plain Java Client Libraries, such as the Work Item Command Line
  * Extensions to the RTC JBE and Buildsystem Toolkit or the RTC SCM command line
  * RTC Eclipse client extensions, RTC Server extensions

This repository contains code to do this using the Rational Team Concert Plain Java Client Libraries.

For debugging this example code, use the following syntax:

Parameter Syntax:
```bash
[publicURI] [userId] [password] start [scenarionName]
[publicURI] [userId] [password] stop [scenarionName]
```

Example start Custom Scenario MyCustomExpensiveScenario:
```bash
https://clm.example.com:9443/ccm/ ADMIN ****** start MyCustomExpensiveScenario
```

Example start Custom Scenario MyCustomExpensiveScenario:
```bash
https://clm.example.com:9443/ccm/ ADMIN ****** stop MyCustomExpensiveScenario
```

There are two implementations of the service interface. 

* One implementation returns the scenario information created when starting a custom scenario instance as a string. This string can be used to stop the scenario counter in the stop command.
* The second implementation persists the information in a file. This interface is used in the main command.  
