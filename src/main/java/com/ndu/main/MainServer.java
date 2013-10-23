package com.ndu.main;

import java.io.IOException;

import com.ndu.common.exception.ParameterException;
import com.ndu.common.exception.PasswordListException;
import com.ndu.server.Server;

public class MainServer {

	public static void main(String [] argv) {
		
		try {
			Server server = new Server(argv);
			server.launchServer();
		} catch (ParameterException e) {
			e.printStackTrace();
		} catch (PasswordListException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
