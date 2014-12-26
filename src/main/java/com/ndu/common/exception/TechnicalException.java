package com.ndu.common.exception;

public class TechnicalException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private TechnicalException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	private TechnicalException(String exceptionMessage, Throwable t) {
		super(exceptionMessage, t);
	}
	
	public static void throwExitException(String exceptionMessage) throws TechnicalException {
		throw new TechnicalException(ExceptionName.EXIT.getFullNameAndMessage(exceptionMessage));
	}
	
	public static void throwParameterException(String exceptionMessage) throws TechnicalException {
		throw new TechnicalException(ExceptionName.PARAMETER.getFullNameAndMessage(exceptionMessage));
	}
	
	public static void throwPasswordException(String exceptionMessage, Throwable exceptionCause) throws TechnicalException {
		throw new TechnicalException(ExceptionName.PASSWORD.getFullNameAndMessage(exceptionMessage), exceptionCause);
	}
	
	public static void throwMessageException(String exceptionMessage) throws TechnicalException {
		throw new TechnicalException(ExceptionName.MESSAGE.getFullNameAndMessage(exceptionMessage));
	}
	
	public static void throwCommunicationException(String exceptionMessage, Throwable exceptionCause) throws TechnicalException {
		throw new TechnicalException(ExceptionName.COMMUNICATION.getFullNameAndMessage(exceptionMessage), exceptionCause);
	}
}
