package ndu.client.constants;

public interface ClientConstants {

	/*
	 * ##########################
	 * ## Connection on server ##
	 * ##########################
	 */
	public static String CONNECTION_GRANTED				= "Connection granted.\n\n";
	public static String CONNECTION_REFUSED				= "Connection refused.\n\n";
	
	/*
	 * #################################
	 * ## CommunicationChannel prompt ##
	 * #################################
	 */
	public static String COMMUNICATION_CHANNEL_PROMPT	= "CLIENT SIDE";
	
	/*
	 * ################
	 * ## Exceptions ##
	 * ################
	 */
	public static String EXCEPTION_READ_TERMINAL		= "Exception while reading from terminal";
	public static String EXCEPTION_WRITE_TERMINAL		= "Exception while writing to terminal";
	public static String EXCEPTION_READ_CORRESPONDANT	= "Exception while reading from correspondant";
	public static String EXCEPTION_WRITE_CORRESPONDANT	= "Exception while writing to correspondant";
	
	public static String EXCEPTION_UNSUPPORTED_OPERATION	= "Unsupported operation !";
	
	public static String EXCEPTION_CONNECTION_ENDED_SUCCESS	= "Connection ended with SUCCESS";
	public static String EXCEPTION_CONNECTION_ENDED_ERROR	= "Connection ended with ERROR";
}
