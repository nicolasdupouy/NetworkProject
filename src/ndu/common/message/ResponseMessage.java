package ndu.common.message;

import java.io.Serializable;

public class ResponseMessage implements Message, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String messageContent = null;
	
	public ResponseMessage(String messageContent) {
		this.messageContent = messageContent; 
	}
	
	public String formatMessageContent() {
		return messageContent;
	}
}
