package com.ndu.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.ndu.common.exception.ParameterException;
import com.ndu.common.exception.PasswordListException;
import com.ndu.common.utils.LoginPasswordListManagement;
import com.ndu.common.utils.ParametersManagement;

public class Server {
	
	private ServerSocket ss;
	
	/**
	 * Initialize the server.
	 * Listen to the port 40000 (IP address: 127.0.0.1)
	 */
	public Server(String [] argv) throws ParameterException, PasswordListException, IOException {

		ParametersManagement.initInstance(true, argv);
		LoginPasswordListManagement.initInstance(ParametersManagement.getInstance().getPasswordFile());
		ss = new ServerSocket(ParametersManagement.getInstance().getPort());
	}

	/**
	 * Wait for new clients and then create a thread for each one of them.
	 * The class where they are taken care of is an inner-class (ClientHandler) of this Server class.
	 */
	public void launchServer() {
		// Initialize the clients list.
		
		
		
		try {
			while (true) {
				// Wait for new clients.
				Socket s = ss.accept();
				
				// Manage each client in a separate thread.
				Thread t = new Thread(new ClientHandler(s));
				// launch the thread.
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

