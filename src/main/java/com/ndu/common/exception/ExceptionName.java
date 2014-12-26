package com.ndu.common.exception;

import com.ndu.common.constants.CommonConstants;

public enum ExceptionName {
	EXIT(CommonConstants.EXCEPTION_NAME_EXIT_EXCEPTION),
	PARAMETER(CommonConstants.EXCEPTION_NAME_PARAMETER_EXCEPTION),
	PASSWORD(CommonConstants.EXCEPTION_NAME_PARAMETER_EXCEPTION),
	MESSAGE(CommonConstants.EXCEPTION_NAME_MESSAGE_EXCEPTION),
	COMMUNICATION(CommonConstants.EXCEPTION_NAME_COMMUNICATION_EXCEPTION);
	
	private String fullName;
	
	private ExceptionName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getFullNameAndMessage(String exceptionMessage) {
		return fullName + CommonConstants.RETURN_LINE + exceptionMessage;
	}
}
