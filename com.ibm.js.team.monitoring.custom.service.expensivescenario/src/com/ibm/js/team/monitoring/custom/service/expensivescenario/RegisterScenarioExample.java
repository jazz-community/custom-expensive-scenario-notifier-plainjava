/*******************************************************************************
 * Copyright (c) 2015-2019 IBM Corporation
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 * 
 *******************************************************************************/
package com.ibm.js.team.monitoring.custom.service.expensivescenario;

import java.net.URISyntaxException;

import com.ibm.team.repository.client.ITeamRepository;

public class RegisterScenarioExample {

	/**
	 * Examples of how to use the services
	 * 
	 * @param teamRepository
	 * @param scenarioName
	 * @throws URISyntaxException
	 * @throws Exception
	 */
	public static void serviceWorkflow(ITeamRepository teamRepository, final String scenarioName)
			throws URISyntaxException, Exception {

		// Simple scenario persisted in String
		IExpensiveScenarioService service = new ExpensiveScenarioService(teamRepository, scenarioName);
		String scenarioInstance = service.start();
		//
		// TODO: Your code goes here
		//
		service.stop(scenarioInstance);

		// Example with file persistence
		IPersistedExpensiveScenarioService persistedService = new FilePersitentExpensiveScenarioService(
				new ExpensiveScenarioService(teamRepository, scenarioName));
		persistedService.start();
		//
		// TODO: Your code goes here
		//
		persistedService.stop();
	}

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

		IExpensiveScenarioService service = new ExpensiveScenarioService(teamRepository, publicURI, scenarioName);

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
