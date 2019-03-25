package com.ibm.js.team.monitoring.custom.service.expensivescenario;

import java.net.URISyntaxException;

import com.ibm.team.repository.client.ITeamRepository;

public class RegisterScenarioExample {

	/**
	 * @param teamRepository
	 * @param publicURI
	 * @param scenarioName
	 * @param startStop
	 * @throws URISyntaxException
	 * @throws Exception
	 */
	public static void runService(ITeamRepository teamRepository, final String publicURI, final String scenarioName,
			final String startStop) throws URISyntaxException, Exception {
		IExpensiveScenarioService service= new ExpensiveScenarioService(teamRepository, publicURI, scenarioName);
	
		if (ExpensiveScenarioService.START_COMMAND.equals(startStop)) {
			System.out.println("Start Scenario...");			
			IPersistedExpensiveScenarioService expensiveScenario = new FilePersitentExpensiveScenarioService(service);
			expensiveScenario.start();
		} else if (ExpensiveScenarioService.STOP_COMMAND.equals(startStop)) {
			System.out.println("Stop Scenario...");
			IPersistedExpensiveScenarioService expensiveScenario = new FilePersitentExpensiveScenarioService(service);
			expensiveScenario.stop();
		} else if (ExpensiveScenarioService.DEBUG_FILE_COMMAND.equals(startStop)) {
			System.out.println("Start and Stop Scenario File Persisted...");
			IPersistedExpensiveScenarioService expensiveScenario = new FilePersitentExpensiveScenarioService(service);
			expensiveScenario.start();
			expensiveScenario.stop();
		} else if (ExpensiveScenarioService.DEBUG_NOFILE_COMMAND.equals(startStop)) {
			System.out.println("Start and Stop Scenario Not Persisted...");
			String startRequestResponse = service.start();
			service.stop(startRequestResponse);
		} else {
			throw new Exception("Unknown Command Exceptioen '" + startStop + "'");
		}
	}

}
