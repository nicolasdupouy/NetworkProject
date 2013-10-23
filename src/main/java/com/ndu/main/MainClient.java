package com.ndu.main;

import java.io.IOException;
import java.net.ConnectException;

import com.ndu.client.Client;
import com.ndu.common.communication.CommunicationException;
import com.ndu.common.constants.CommonConstants;
import com.ndu.common.exception.ExitException;
import com.ndu.common.exception.ParameterException;

public class MainClient {

	public static void main(String [] argv) {
		Client client;
		try {
			client = new Client(argv);
			client.launchClient();
		} catch (ExitException e) {
			System.out.println(e.getMessage());
			System.exit(CommonConstants.RETURN_CODE_OK);
		} catch (ParameterException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(CommonConstants.ERROR_RETURN_CODE);
		} catch(ConnectException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(CommonConstants.ERROR_RETURN_CODE);
		} catch (CommunicationException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(CommonConstants.ERROR_RETURN_CODE);

		} catch(IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(CommonConstants.ERROR_RETURN_CODE);
		}
	}
}
