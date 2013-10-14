package ndu.common.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import ndu.common.constants.CommonConstants;

public class RequestMessage implements Message, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String actionType;
	protected ArrayList<String> parameters;
	
	public String getActionType() {
		return actionType;
	}

	public ArrayList<String> getParameters() {
		return parameters;
	}

	public RequestMessage(String line, boolean isCommandLine) throws MessageException {
		if (isCommandLine) {
			constructMessageFromCommandLine(line);
		}
		else {
			constructMessageFromSocketLine(line);
		}
	}
	
	/**
	 * 
	 * @param commandLine
	 * @throws MessageException
	 */
	private void constructMessageFromCommandLine(String commandLine) throws MessageException {
		
		StringTokenizer st = new StringTokenizer(commandLine);
		String choosenOperation = st.nextToken();
		if (SupportedOrders.isSupportedType(choosenOperation)) {
			this.actionType = choosenOperation;
			if (SupportedOrders.isLoginType(choosenOperation)) {
				this.parameters = new ArrayList<String>();
				this.parameters.add(st.nextToken());
				this.parameters.add(st.nextToken());
			}
			/*else if (SupportedOrders.isLogoutType(choosenOperation)) {
				this.type = choosenOperation;
			}*/
		}
		else {
			throw new MessageException("Message::Unsuported operation : " + choosenOperation);
		}
	}
	
	/**
	 * 
	 * @param socketLine
	 * @throws MessageException
	 */
	private void constructMessageFromSocketLine(String socketLine) throws MessageException {
		
		StringTokenizer stSocketLine = new StringTokenizer(socketLine, CommonConstants.S_MESSAGE_SEPARATOR_1);
		this.actionType = stSocketLine.nextToken();
		
		String s_parameters = stSocketLine.nextToken();
		StringTokenizer stParameters = new StringTokenizer(s_parameters, CommonConstants.S_MESSAGE_SEPARATOR_2);
		
		if (stParameters.hasMoreTokens()) {
			this.parameters = new ArrayList<String>();
		}
		while (stParameters.hasMoreTokens()) {
			this.parameters.add(stParameters.nextToken());
		}
		
		/*if (stSocketLine.hasMoreTokens()) {
			this.messageContent = stSocketLine.nextToken();
		}*/
	}
	
	public String formatMessageContent() {
		String contentSentThruSocket = null;
		contentSentThruSocket = actionType;
		
		if (parameters != null
				&& parameters.size() != 0) {
			
			contentSentThruSocket += CommonConstants.S_MESSAGE_SEPARATOR_1;
			Iterator<String> itParameters = parameters.iterator();
			while( itParameters.hasNext() ) {
				contentSentThruSocket += itParameters.next();
				if ( itParameters.hasNext() ) {
					contentSentThruSocket += CommonConstants.S_MESSAGE_SEPARATOR_2;
				}
			}
		}
		return contentSentThruSocket;
	}
	
	public String toString() {
		return "ActionType=" + actionType + " / Parameters=" + parameters + "\n";
		//return "ActionType=" + actionType + " / Parameters=" + parameters + " / Content=" + messageContent + "\n";
		/*return "*****************" 							+ "\n"
		     + "* type       = " 	+ type 					+ "\n"
		     + "* parameters = " 	+ parameters 			+ "\n"
			 + "* content    = " 	+ getMessageContent()	+ "\n"
			 + "*****************";*/
	}

}
