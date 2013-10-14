package ndu.client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;

import ndu.client.business.ClientBusinessManager;
import ndu.client.constants.ClientConstants;
import ndu.common.communication.CommunicationChannelAbstract;
import ndu.common.communication.CommunicationChannelByObject;
import ndu.common.communication.CommunicationChannelByString;
import ndu.common.communication.CommunicationException;
import ndu.common.exception.ExitException;
import ndu.common.exception.ParameterException;
import ndu.common.message.MessageException;
import ndu.common.message.RequestMessage;
import ndu.common.message.ResponseMessage;
import ndu.common.message.SupportedOrders;
import ndu.common.utils.ParametersManagement;
import ndu.server.constants.ServerConstants;

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
