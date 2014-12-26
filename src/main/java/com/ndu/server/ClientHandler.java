package com.ndu.server;

import java.net.Socket;

import com.ndu.common.communication.CommunicationChannelAbstract;
import com.ndu.common.communication.CommunicationChannelByObject;
import com.ndu.common.communication.CommunicationChannelByString;
import com.ndu.common.constants.CommonConstants;
import com.ndu.common.exception.TechnicalException;
import com.ndu.common.message.RequestMessage;
import com.ndu.common.message.ResponseMessage;
import com.ndu.common.message.SupportedOrders;
import com.ndu.common.utils.ParametersManagement;
import com.ndu.server.business.ServerBusinessManager;
import com.ndu.server.constants.ServerConstants;

public class ClientHandler implements Runnable {
	
	private ServerBusinessManager serverBusinessManager;
	private CommunicationChannelAbstract communicationChannel;
	private Socket clientSocket;
	
	public ClientHandler(/* Server server, */Socket clientSocket) {
		try {
			this.clientSocket = clientSocket;
			
			if (ParametersManagement.getInstance().isExchangeDataMethodString()) {
				communicationChannel = new CommunicationChannelByString(ServerConstants.COMMUNICATION_CHANNEL_PROMPT, clientSocket);
			} else {
				communicationChannel = new CommunicationChannelByObject(ServerConstants.COMMUNICATION_CHANNEL_PROMPT, clientSocket);
			}
			
			this.serverBusinessManager = new ServerBusinessManager(/* server, */communicationChannel);
			this.serverBusinessManager.addToClientList(clientSocket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			RequestMessage requestMessage = null;
			ResponseMessage responseMessage = null;
			
			while ((requestMessage = getRequestMessage(communicationChannel)) != null) {
				// while( (requestMessage=new
				// RequestMessage((String)communicationChannel.readFromCorrespondant(),
				// false)) != null ) {
				// while(
				// (requestMessage=(RequestMessage)(communicationChannel.readFromCorrespondant()))
				// != null ) {
				communicationChannel.writeToTerminal(ServerConstants.COMMUNICATION_RECEIVED_COMMAND + clientSocket.hashCode() + ":: " + requestMessage.formatMessageContent());
				
				responseMessage = serverBusinessManager.manageCorrespondantMessage(clientSocket.hashCode(), requestMessage);
				
				communicationChannel.writeToTerminal(ServerConstants.COMMUNICATION_COMMAND_SENT + clientSocket.hashCode() + ":: " + responseMessage.formatMessageContent());
				communicationChannel.writeToCorrespondant(responseMessage);
				
				if (SupportedOrders.isLogoutType(requestMessage.getActionType()) && responseMessage.messageContent.equals(CommonConstants.S_OK)) {
					break;
				}
			}
		} catch (TechnicalException e) { // CommunicationException
			try {
				boolean clientSuccessfullyRemoved = serverBusinessManager.removeFromClientList(clientSocket);
				communicationChannel.writeToTerminal(ServerConstants.COMMUNICATION_CLIENT_SUCCESFULLY_REMOVED + clientSuccessfullyRemoved);
			} catch (TechnicalException e2) { //CommunicationException
				e2.printStackTrace();
			}
		}
	}
	
	private RequestMessage getRequestMessage(CommunicationChannelAbstract communicationChannel) throws TechnicalException {
		if (ParametersManagement.getInstance().isExchangeDataMethodString()) {
			return new RequestMessage((String) communicationChannel.readFromCorrespondant(), false);
		} else {
			return (RequestMessage) (communicationChannel.readFromCorrespondant());
		}
	}
}
