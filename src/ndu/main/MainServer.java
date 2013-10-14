package ndu.main;

import java.io.IOException;

import ndu.common.exception.ParameterException;
import ndu.common.exception.PasswordListException;
import ndu.server.Server;

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
