package ndu.server;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


import ndu.common.constants.CommonConstants;
import ndu.common.exception.ParameterException;
import ndu.common.exception.PasswordListException;
import ndu.common.message.RequestMessage;
import ndu.common.message.ResponseMessage;
import ndu.common.message.SupportedOrders;
import ndu.common.utils.LoginPasswordListManagement;
import ndu.common.utils.ParametersManagement;
import ndu.server.business.ServerBusinessManager;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

