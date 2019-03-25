/*******************************************************************************
 * Copyright (c) 2015-2019 IBM Corporation
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 * 
 *******************************************************************************/
package com.ibm.js.team.monitoring.custom.expensivescenario;

import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.ibm.js.team.monitoring.custom.service.expensivescenario.RegisterScenarioExample;
import com.ibm.team.repository.client.ITeamRepository;
import com.ibm.team.repository.client.ITeamRepository.ILoginHandler;
import com.ibm.team.repository.client.ITeamRepository.ILoginHandler.ILoginInfo;
import com.ibm.team.repository.client.TeamPlatform;
import com.ibm.team.repository.common.TeamRepositoryException;

public class ExpensiveScenarioNotifier {

	private static class LoginHandler implements ILoginHandler, ILoginInfo {

		private static String fPassword;
		private static String fUserId;

		public LoginHandler(final String userId, final String password) {
			LoginHandler.fUserId = userId;
			LoginHandler.fPassword = password;
		}

		@Override
		public ILoginInfo challenge(final ITeamRepository repository) {
			return this;
		}

		@Override
		public String getPassword() {
			return LoginHandler.fPassword;
		}

		@Override
		public String getUserId() {
			return LoginHandler.fUserId;
		}
	}

	public static void main(String[] args) {

		boolean result = false;
		TeamPlatform.startup();
		try {
			result = run(args);
		} catch (TeamRepositoryException e) {
			System.out.println("TeamRepositoryException: " + e.getMessage());
			e.printStackTrace();
		} catch (URISyntaxException e) {
			System.out.println("URISyntaxException: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			TeamPlatform.shutdown();
		}
		if (!result) {
			System.out.println("Failed.");
			System.exit(1);
		}
		System.out.println("Success.");
	}

	private static boolean run(String[] args) throws Exception {

		if (args.length != 5) {
			System.out
					.println("Usage: esn [publicURI] [userId] [password] start [scenarionName]");
			System.out.println("Usage: esn [publicURI] [userId] [password] stop [scenarionName]");
			return false;
		}

		final String publicURI = args[0];
		final String userId = args[1];
		final String password = args[2];
		final String startStop = args[3];
		final String scenarioName = args[4];

		// log in to platform
		ITeamRepository teamRepository = TeamPlatform.getTeamRepositoryService().getTeamRepository(publicURI);
		IProgressMonitor monitor = new NullProgressMonitor();
		teamRepository.registerLoginHandler(new LoginHandler(userId, password));
		teamRepository.login(monitor);		
		RegisterScenarioExample.runService(teamRepository, teamRepository.getRepositoryURI(), scenarioName, startStop);
		teamRepository.logout();
		return true;
	}

}
