package com.ndu.client;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import com.ndu.client.business.ClientBusinessManager;
import com.ndu.client.constants.ClientConstants;
import com.ndu.common.communication.CommunicationChannelAbstract;
import com.ndu.common.communication.CommunicationChannelByObject;
import com.ndu.common.communication.CommunicationChannelByString;
import com.ndu.common.communication.CommunicationException;
import com.ndu.common.exception.ExitException;
import com.ndu.common.exception.ParameterException;
import com.ndu.common.message.MessageException;
import com.ndu.common.message.RequestMessage;
import com.ndu.common.message.ResponseMessage;
import com.ndu.common.message.SupportedOrders;
import com.ndu.common.utils.ParametersManagement;

public class Client {

	private Socket socket;
	private ClientBusinessManager clientBusinessManager;

	private CommunicationChannelAbstract communicationChannel;

	public Client(String [] argv) throws ParameterException, ConnectException, IOException {

		ParametersManagement.initInstance(false, argv);
		
		socket = new Socket(ParametersManagement.getInstance().getAddress(), ParametersManagement.getInstance().getPort());
//		communicationChannel = new CommunicationChannelByObject(ClientConstants.COMMUNICATION_CHANNEL_PROMPT);
//		communicationChannel.setTerminalIN( new BufferedReader(new InputStreamReader(System.in)) );
//		communicationChannel.setTerminalOUT( new BufferedWriter(new OutputStreamWriter(System.out)) );
//		
//		communicationChannel.setCorrespondantOUT( new ObjectOutputStream(socket.getOutputStream()) );
//		communicationChannel.setCorrespondantIN( new ObjectInputStream(socket.getInputStream()) );
//		//communicationChannel.setCorrespondantIN( new BufferedReader(new InputStreamReader(socket.getInputStream())) );
//		//communicationChannel.setCorrespondantOUT( new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())) );
		
		if (ParametersManagement.getInstance().isExchangeDataMethodString()) {
			communicationChannel = new CommunicationChannelByString(ClientConstants.COMMUNICATION_CHANNEL_PROMPT, socket);
		}
		else {
			communicationChannel = new CommunicationChannelByObject(ClientConstants.COMMUNICATION_CHANNEL_PROMPT, socket);
		}
		
		clientBusinessManager = new ClientBusinessManager(communicationChannel);
	}


	public void launchClient() throws CommunicationException, ParameterException, ExitException {
		SupportedOrders.displaySupportedOperations();
		chooseOperation();
	}

	private void chooseOperation() throws CommunicationException, ParameterException, ExitException {
		String commandLine;

		SupportedOrders.displayAllowedOperations(clientBusinessManager.isClientConnected());

		ResponseMessage responseMessage = null;
		while( (commandLine=communicationChannel.readFromTerminal()) != null ) {
			try {
				RequestMessage requestMessage = new RequestMessage(commandLine, true);

				if (ParametersManagement.getInstance().isDebugMode()) {
					communicationChannel.writeToTerminal(requestMessage.toString());
				}
				communicationChannel.writeToCorrespondant(requestMessage);

				if (ParametersManagement.getInstance().isExchangeDataMethodString()) {
					responseMessage = new ResponseMessage((String)communicationChannel.readFromCorrespondant());
				}
				else {
					responseMessage = (ResponseMessage)(communicationChannel.readFromCorrespondant());
				}
				
				clientBusinessManager.manageServerResponse(requestMessage, responseMessage);

				SupportedOrders.displayAllowedOperations(clientBusinessManager.isClientConnected());

			}
			catch (MessageException e) {
				System.err.println(ClientConstants.EXCEPTION_UNSUPPORTED_OPERATION);
				SupportedOrders.displayAllowedOperations(clientBusinessManager.isClientConnected());
			}
		}
	}
}
