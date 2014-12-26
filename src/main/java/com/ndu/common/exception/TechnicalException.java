package com.ndu.common.exception;


public class TechnicalException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/*public TechnicalException(String exceptionName, String exceptionMessage) {
		super(exceptionName + CommonConstants.RETURN_LINE + exceptionMessage);
	}*/
	
	public TechnicalException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	private TechnicalException(String exceptionMessage, Throwable t) {
		super(exceptionMessage, t);
	}
	
	public static void throwExitException(String exceptionMessage) throws TechnicalException {
		throw new TechnicalException(ExceptionName.EXIT + exceptionMessage);
	}
	
	public static void throwParameterException(String exceptionMessage) throws TechnicalException {
		throw new TechnicalException(ExceptionName.PARAMETER + exceptionMessage);
	}
	
	public static void throwPasswordException(String exceptionMessage, Throwable exceptionCause) throws TechnicalException {
		throw new TechnicalException(ExceptionName.PASSWORD + exceptionMessage, exceptionCause);
	}
	
	public static void throwMessageException(String exceptionMessage) throws TechnicalException {
		throw new TechnicalException(ExceptionName.MESSAGE + exceptionMessage);
	}
	
	public static void throwCommunicationException(String exceptionMessage, Throwable exceptionCause) throws TechnicalException {
		throw new TechnicalException(ExceptionName.COMMUNICATION + exceptionMessage, exceptionCause);
	}
}
