package ndu.common.exception;

public class PasswordListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PasswordListException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	public PasswordListException(Throwable exceptionCause) {
		super(exceptionCause);
	}

	public PasswordListException(String exceptionMessage, Throwable exceptionCause) {
		super(exceptionMessage, exceptionCause);
	}
}
