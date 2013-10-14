package ndu.common.communication;

public class CommunicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommunicationException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	public CommunicationException(Throwable exceptionCause) {
		super(exceptionCause);
	}

	public CommunicationException(String exceptionMessage, Throwable exceptionCause) {
		super(exceptionMessage, exceptionCause);
	}
}
