package com.ndu.common.exception;

public class ParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ParameterException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
	public ParameterException(Throwable exceptionCause) {
		super(exceptionCause);
	}

	public ParameterException(String exceptionMessage, Throwable exceptionCause) {
		super(exceptionMessage, exceptionCause);
	}
}
