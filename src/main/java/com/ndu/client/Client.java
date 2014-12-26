package com.ndu.client;

import java.io.IOException;
import java.net.Socket;

import com.ndu.client.business.ClientBusinessManager;
import com.ndu.client.constants.ClientConstants;
import com.ndu.common.communication.CommunicationChannelAbstract;
import com.ndu.common.communication.CommunicationChannelByObject;
import com.ndu.common.communication.CommunicationChannelByString;
import com.ndu.common.constants.CommonConstants;
import com.ndu.common.exception.TechnicalException;
import com.ndu.common.message.RequestMessage;
import com.ndu.common.message.ResponseMessage;
import com.ndu.common.message.SupportedOrders;
import com.ndu.common.utils.ParametersManagement;

public class Client {
	
	private Socket socket;
	private ClientBusinessManager clientBusinessManager;
	
	private CommunicationChannelAbstract communicationChannel;
	
	public Client(String[] argv) throws TechnicalException {
		
		ParametersManagement.initInstance(false, argv);
		try {
			socket = new Socket(ParametersManagement.getInstance().getAddress(), ParametersManagement.getInstance().getPort());
		} catch (IOException e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_CONNECTION_SOCKET, e);
		}
		
		if (ParametersManagement.getInstance().isExchangeDataMethodString()) {
			communicationChannel = new CommunicationChannelByString(ClientConstants.COMMUNICATION_CHANNEL_PROMPT, socket);
		} else {
			communicationChannel = new CommunicationChannelByObject(ClientConstants.COMMUNICATION_CHANNEL_PROMPT, socket);
		}
		
		clientBusinessManager = new ClientBusinessManager(communicationChannel);
	}
	
	public void launchClient() throws TechnicalException {
		SupportedOrders.displaySupportedOperations();
		chooseOperation();
	}
	
	private void chooseOperation() throws TechnicalException {
		String commandLine;
		
		SupportedOrders.displayAllowedOperations(clientBusinessManager.isClientConnected());
		
		ResponseMessage responseMessage = null;
		while ((commandLine = communicationChannel.readFromTerminal()) != null) {
			try {
				RequestMessage requestMessage = new RequestMessage(commandLine, true);
				
				if (ParametersManagement.getInstance().isDebugMode()) {
					communicationChannel.writeToTerminal(requestMessage.toString());
				}
				communicationChannel.writeToCorrespondant(requestMessage);
				
				if (ParametersManagement.getInstance().isExchangeDataMethodString()) {
					responseMessage = new ResponseMessage((String) communicationChannel.readFromCorrespondant());
				} else {
					responseMessage = (ResponseMessage) (communicationChannel.readFromCorrespondant());
				}
				
				clientBusinessManager.manageServerResponse(requestMessage, responseMessage);
				
				SupportedOrders.displayAllowedOperations(clientBusinessManager.isClientConnected());
				
			} catch (TechnicalException e) {
				System.err.println(ClientConstants.EXCEPTION_UNSUPPORTED_OPERATION);
				SupportedOrders.displayAllowedOperations(clientBusinessManager.isClientConnected());
			}
		}
	}
}
