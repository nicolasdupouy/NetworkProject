package com.ndu.main;

import com.ndu.client.Client;
import com.ndu.common.constants.CommonConstants;
import com.ndu.common.exception.TechnicalException;

public class MainClient {
	
	public static void main(String[] argv) {
		Client client;
		try {
			client = new Client(argv);
			client.launchClient();
		} catch (TechnicalException e) {
			System.out.println(e.getMessage());
			System.exit(CommonConstants.ERROR_RETURN_CODE);
		}
	}
}
