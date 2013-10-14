package ndu.common.message;

public class MessageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	public MessageException(Throwable exceptionCause) {
		super(exceptionCause);
	}

	public MessageException(String exceptionMessage, Throwable exceptionCause) {
		super(exceptionMessage, exceptionCause);
	}
}
