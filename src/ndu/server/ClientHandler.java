package ndu.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import ndu.common.communication.CommunicationChannelAbstract;
import ndu.common.communication.CommunicationChannelByObject;
import ndu.common.communication.CommunicationChannelByString;
import ndu.common.communication.CommunicationException;
import ndu.common.constants.CommonConstants;
import ndu.common.exception.ParameterException;
import ndu.common.message.MessageException;
import ndu.common.message.RequestMessage;
import ndu.common.message.ResponseMessage;
import ndu.common.message.SupportedOrders;
import ndu.common.utils.ParametersManagement;
import ndu.server.business.ServerBusinessManager;
import ndu.server.constants.ServerConstants;

public class ClientHandler implements Runnable {
	
	private ServerBusinessManager serverBusinessManager;
	private CommunicationChannelAbstract communicationChannel;
	private Socket clientSocket;
		
	public ClientHandler(/*Server server,*/ Socket clientSocket) {
		try {
			this.clientSocket = clientSocket;
			
			if (ParametersManagement.getInstance().isExchangeDataMethodString()) {
				communicationChannel = new CommunicationChannelByString(ServerConstants.COMMUNICATION_CHANNEL_PROMPT, clientSocket);
			}
			else {
				communicationChannel = new CommunicationChannelByObject(ServerConstants.COMMUNICATION_CHANNEL_PROMPT, clientSocket);
			}
						
			this.serverBusinessManager = new ServerBusinessManager(/*server,*/ communicationChannel);
			this.serverBusinessManager.addToClientList(clientSocket);
		}
		catch(CommunicationException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void run() {
		try {
			RequestMessage requestMessage = null;
			ResponseMessage responseMessage = null;
			
			while( (requestMessage=getRequestMessage(communicationChannel)) != null ) {
			//while( (requestMessage=new RequestMessage((String)communicationChannel.readFromCorrespondant(), false)) != null ) {
			//while( (requestMessage=(RequestMessage)(communicationChannel.readFromCorrespondant())) != null ) {
				communicationChannel.writeToTerminal(ServerConstants.COMMUNICATION_RECEIVED_COMMAND + clientSocket.hashCode() + ":: " + requestMessage.formatMessageContent());

				responseMessage = serverBusinessManager.manageCorrespondantMessage(clientSocket.hashCode(), requestMessage);
				
				communicationChannel.writeToTerminal(ServerConstants.COMMUNICATION_COMMAND_SENT + clientSocket.hashCode() + ":: " + responseMessage.formatMessageContent());
				communicationChannel.writeToCorrespondant(responseMessage);
				
				if (SupportedOrders.isLogoutType(requestMessage.getActionType())
						&& responseMessage.messageContent.equals(CommonConstants.S_OK)) {
					break;
				}
			}
		} catch(CommunicationException e) {
			try {
				boolean clientSuccessfullyRemoved = serverBusinessManager.removeFromClientList(clientSocket);
				communicationChannel.writeToTerminal(ServerConstants.COMMUNICATION_CLIENT_SUCCESFULLY_REMOVED + clientSuccessfullyRemoved);
			} catch(CommunicationException e2) {
				e2.printStackTrace();
			}
		} catch(MessageException e) {
			e.printStackTrace();
		} catch(ParameterException e) {
			e.printStackTrace();
		}
	}
	
	private RequestMessage getRequestMessage(CommunicationChannelAbstract communicationChannel) throws CommunicationException, MessageException, ParameterException {
		if (ParametersManagement.getInstance().isExchangeDataMethodString()) {
			return new RequestMessage((String)communicationChannel.readFromCorrespondant(), false);
		}
		else {
			return (RequestMessage)(communicationChannel.readFromCorrespondant());
		}
	}
}
