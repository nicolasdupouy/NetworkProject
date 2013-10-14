package ndu.common.exception;

public class ExitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExitException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	public ExitException(Throwable exceptionCause) {
		super(exceptionCause);
	}

	public ExitException(String exceptionMessage, Throwable exceptionCause) {
		super(exceptionMessage, exceptionCause);
	}
}
