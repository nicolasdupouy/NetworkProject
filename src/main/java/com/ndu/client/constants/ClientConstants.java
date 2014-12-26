package com.ndu.client.constants;

public final class ClientConstants {
	
	private ClientConstants() {
		
	}
	
	/*
	 * ##########################
	 * ## Connection on server ##
	 * ##########################
	 */
	public static final String CONNECTION_GRANTED = "Connection granted.\n\n";
	public static final String CONNECTION_REFUSED = "Connection refused.\n\n";
	
	/*
	 * #################################
	 * ## CommunicationChannel prompt ##
	 * #################################
	 */
	public static final String COMMUNICATION_CHANNEL_PROMPT = "CLIENT SIDE";
	
	/*
	 * ################
	 * ## Exceptions ##
	 * ################
	 */
	public static final String EXCEPTION_UNSUPPORTED_OPERATION = "Unsupported operation !";
	
	public static final String EXCEPTION_CONNECTION_ENDED_SUCCESS = "Connection ended with SUCCESS";
	public static final String EXCEPTION_CONNECTION_ENDED_ERROR = "Connection ended with ERROR";
}
