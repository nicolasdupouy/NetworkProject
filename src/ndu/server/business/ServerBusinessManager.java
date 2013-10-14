package ndu.server.business;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


import ndu.common.communication.CommunicationChannelAbstract;
import ndu.common.communication.CommunicationException;
import ndu.common.constants.CommonConstants;
import ndu.common.exception.PasswordListException;
import ndu.common.message.RequestMessage;
import ndu.common.message.ResponseMessage;
import ndu.common.message.SupportedOrders;
import ndu.common.utils.LoginPasswordListManagement;
import ndu.server.ClientPOJO;
import ndu.server.Server;
import ndu.server.constants.ServerConstants;

public class ServerBusinessManager {

	//private HashMap<String,String> usersPasswordMap;
	private HashMap<Integer,ClientPOJO> clientList;
	//private Server server;
	private CommunicationChannelAbstract communicationChannel;
	
	public ServerBusinessManager(/*Server server,*/ CommunicationChannelAbstract communicationChannel) {
		//this.server = server;
		this.communicationChannel = communicationChannel;
		this.clientList = new HashMap<Integer,ClientPOJO>();
	}
	
	public void displayNewClientConnectionInformations(Socket s) throws CommunicationException {
		// display new incomer
		communicationChannel.writeToTerminal("Server: receive a request from addr=/" 
						+ s.getInetAddress().getHostAddress() 
						+ "(" + s.getInetAddress().getCanonicalHostName() + ")" 
						+ ",port=" + s.getPort()
						+ " ==> TO  SERVER("
						+ "localAddress=" + s.getLocalAddress()
						+ ",localPort=" + s.getLocalPort()
						+ ")");
	}
	
	public void addToClientList(Socket s) throws CommunicationException {
		
		displayNewClientConnectionInformations(s);
		
		ClientPOJO clientPOJO = new ClientPOJO();
		clientPOJO.setInetAddress(s.getInetAddress());
		clientPOJO.setPort(s.getPort());
		
		clientList.put(s.hashCode(), clientPOJO);
	}
	
	public boolean removeFromClientList(Socket clientSocket) throws CommunicationException {
		if (clientList.containsKey(clientSocket.hashCode())) {
			ClientPOJO clientPOJO = clientList.remove(clientSocket.hashCode());
			communicationChannel.writeToTerminal(ServerConstants.COMMUNICATION_CLIENT_DISCONNECTED + clientPOJO);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param clientHashCode
	 * @param orderMessage
	 * @return
	 */
	public ResponseMessage manageCorrespondantMessage(int clientHashCode, RequestMessage orderMessage) {
		String message = null;
		
		// Login
		if (SupportedOrders.isLoginType(orderMessage.getActionType())) {
			
			if (login(clientHashCode, orderMessage)) {
				message = CommonConstants.S_OK;
			}
			else {
				message = CommonConstants.S_KO;
			}
		}
		
		// Logout
		else if (SupportedOrders.isLogoutType(orderMessage.getActionType())) {
			
			if (logout(clientHashCode)) {
				message = CommonConstants.S_OK;
			}
			else {
				message = CommonConstants.S_KO;
			}
		}
		
		// List clients
		else if (SupportedOrders.isListClients(orderMessage.getActionType())) {
			message = listClients();
		}
		
		//
		else {
			message = CommonConstants.S_WRONG_OPERATION;
		}
		
		return new ResponseMessage(message);
	}
	
	
	/**
	 * 
	 * @param clientHashCode
	 * @param orderMessage
	 * @return
	 */
	private synchronized boolean login(int clientHashCode, RequestMessage orderMessage) {
		//"the user does not exist"
		//"the password is incorrect"
		//"the user had login already"
		//==> add
		//addClient(message.getSenderID());

		try {
			ArrayList<String> parameters = orderMessage.getParameters();
			if (parameters.size() == 2) {
				String userID = parameters.get(0);
				String password = parameters.get(1);

				if (LoginPasswordListManagement.getInstance().isLoginAuthorized(userID, password)) {
					ClientPOJO clientPOJO = clientList.get(clientHashCode);
					clientPOJO.setUserID(userID);
					clientPOJO.setPassword(password);
					clientPOJO.setConnected(true);
					return true;
				}
//				else {
//					// userID/password combination unauthorised
//					return false;
//				}
			}
//			else {
//				return
//			}
		} catch (PasswordListException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	private synchronized boolean logout(int clientHashCode) {
		if (clientList.containsKey(clientHashCode)) {
			clientList.remove(clientHashCode);
			return true;
		}
		else {
			return false;
		}
	}
	
	private synchronized String listClients() {
		//clientList.entrySet()
		String message;
		for (Entry<Integer,ClientPOJO> clientEntry : clientList.entrySet()) {
			
		}
		return ""; //message;
	}
}
