package com.ndu.server.constants;

public final class ServerConstants {

	private ServerConstants() {

	}

	/*
	 * #################################
	 * ## CommunicationChannel prompt ##
	 * #################################
	 */
	public static final String COMMUNICATION_CHANNEL_PROMPT = "SERVER SIDE";

	public static final String COMMUNICATION_RECEIVED_COMMAND = "Received command from client n°";
	public static final String COMMUNICATION_COMMAND_SENT = "Command send to client n°";
	public static final String COMMUNICATION_CLIENT_DISCONNECTED = "Following client disconnected: ";
	public static final String COMMUNICATION_CLIENT_SUCCESFULLY_REMOVED = "Client successfully removed after CommunicationException: ";
}
