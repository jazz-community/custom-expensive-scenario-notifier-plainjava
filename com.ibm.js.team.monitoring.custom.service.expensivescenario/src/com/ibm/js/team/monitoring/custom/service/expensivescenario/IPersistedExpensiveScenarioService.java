/*******************************************************************************
 * Copyright (c) 2015-2019 IBM Corporation
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 * 
 *******************************************************************************/
package com.ibm.js.team.monitoring.custom.service.expensivescenario;

public interface IPersistedExpensiveScenarioService {

	/**
	 * Start the custom expensive scenario.
	 * Store the request response data in a file to persist it.
	 *  
	 * @throws Exception
	 */
	void start() throws Exception;

	/**
	 * Stop the custom expensive scenario.
	 * Read the request response data from a file that persists it.
	 * 
	 * @throws Exception
	 */
	void stop() throws Exception;

}