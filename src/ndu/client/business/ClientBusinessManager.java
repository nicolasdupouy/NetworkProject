package ndu.client.business;

import ndu.client.constants.ClientConstants;
import ndu.common.communication.CommunicationChannelAbstract;
import ndu.common.communication.CommunicationException;
import ndu.common.constants.CommonConstants;
import ndu.common.exception.ExitException;
import ndu.common.message.RequestMessage;
import ndu.common.message.ResponseMessage;
import ndu.common.message.SupportedOrders;

public class ClientBusinessManager {

	private boolean isClientConnected = false;
	private CommunicationChannelAbstract communicationChannel;

	public ClientBusinessManager(CommunicationChannelAbstract communicationChannel) {
		this.communicationChannel = communicationChannel;
	}
	
	public boolean isClientConnected() {
		return isClientConnected;
	}

	public void manageServerResponse(RequestMessage requestMessage, ResponseMessage responseMessage) throws CommunicationException, ExitException {

		if (SupportedOrders.isLoginType(requestMessage.getActionType())) {
			if (responseMessage.formatMessageContent().equals(CommonConstants.S_OK)) {
				isClientConnected = true;
				communicationChannel.writeToTerminal(ClientConstants.CONNECTION_GRANTED);
			}
			else {
				communicationChannel.writeToTerminal(ClientConstants.CONNECTION_REFUSED);
			}
		}
		
		else if (SupportedOrders.isLogoutType(requestMessage.getActionType())) {
			
			isClientConnected = false;
			if (responseMessage.formatMessageContent().equals(CommonConstants.S_OK)) {
				communicationChannel.writeToTerminal(ClientConstants.EXCEPTION_CONNECTION_ENDED_SUCCESS);
				throw new ExitException(ClientConstants.EXCEPTION_CONNECTION_ENDED_SUCCESS);
			}
			else {
				communicationChannel.writeToTerminal(ClientConstants.EXCEPTION_CONNECTION_ENDED_ERROR);
				throw new ExitException(ClientConstants.EXCEPTION_CONNECTION_ENDED_ERROR);
			}
			
		}
	}
}
