package com.ndu.main;

import com.ndu.common.exception.TechnicalException;
import com.ndu.server.Server;
import com.ndu.server.ServerImpl;

public class MainServer {
	
	public static void main(String[] argv) {
		
		try {
			Server server = new ServerImpl(argv);
			server.launchServer();
		} catch (TechnicalException e) {
			e.printStackTrace();
		}
	}
}
