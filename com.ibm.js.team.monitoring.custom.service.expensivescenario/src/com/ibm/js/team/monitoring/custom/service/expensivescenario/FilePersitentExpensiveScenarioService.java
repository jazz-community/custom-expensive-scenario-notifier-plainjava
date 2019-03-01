/*******************************************************************************
 * Copyright (c) 2015-2019 IBM Corporation
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 * 
 *******************************************************************************/
package com.ibm.js.team.monitoring.custom.service.expensivescenario;

import java.io.FileWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Service to execute a start and stop for an expensive scenario. 
 * The information to stop the expensive scenario is persisted as a file.
 *
 */
public class FilePersitentExpensiveScenarioService implements IPersistedExpensiveScenarioService {

	public static final String SCENARIO_RESPONSE_FILE_EXTENSION = ".json";
	public static final String SCENARIO_RESPONSE_FILE_PATH_ROOT_FOLDER = "./";

	private IExpensiveScenarioService fExpensiveScenarioService=null;

	/**
	 * Start and stop expensive scenario counter are performed persisting the
	 * scenario counter in a file or pass it as string. See option
	 * persistStartAsFile.
	 * 
	 * @param teamRepository     Team Repository
	 * @param publicURI          Public URI of the target CLM server
	 * @param scenarioName       the name of the scenario
	 * @throws URISyntaxException
	 */
	public FilePersitentExpensiveScenarioService(IExpensiveScenarioService expensiveScenarioService) throws URISyntaxException {
		if(expensiveScenarioService==null)
			throw new NullPointerException("Expensive Scenario Service can not be null");
		fExpensiveScenarioService = expensiveScenarioService;
	}


	/* (non-Javadoc)
	 * @see com.ibm.js.team.monitoring.custom.service.expensivescenario.IPersistedExpensiveScenarioService#start()
	 */
	@Override
	public void start() throws Exception {
		String responseString = fExpensiveScenarioService.start();
		FileWriter fileWriter = new FileWriter(getScenarioResponseFileName());
		fileWriter.write(responseString);
		fileWriter.close();
	}

	/* (non-Javadoc)
	 * @see com.ibm.js.team.monitoring.custom.service.expensivescenario.IPersistedExpensiveScenarioService#stop()
	 */
	@Override
	public void stop()
			throws Exception {
		Path path = Paths.get(getScenarioResponseFileName());
		String startRequestResponse = new String(Files.readAllBytes(path));
		Files.delete(path);
		fExpensiveScenarioService.stop(startRequestResponse);
	}

	/**
	 * @return the name of the file to contain the request respones information
	 * @throws Exception 
	 */
	private String getScenarioResponseFileName() throws Exception {
		return SCENARIO_RESPONSE_FILE_PATH_ROOT_FOLDER + fExpensiveScenarioService.getScenarioName() + SCENARIO_RESPONSE_FILE_EXTENSION;
	}
}
