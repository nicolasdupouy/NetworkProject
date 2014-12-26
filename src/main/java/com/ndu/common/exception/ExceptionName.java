package com.ndu.common.exception;

import com.ndu.common.constants.CommonConstants;

public enum ExceptionName {
	EXIT(CommonConstants.EXCEPTION_NAME_EXIT_EXCEPTION),
	PARAMETER(CommonConstants.EXCEPTION_NAME_PARAMETER_EXCEPTION),
	PASSWORD(CommonConstants.EXCEPTION_NAME_PARAMETER_EXCEPTION),
	MESSAGE(CommonConstants.EXCEPTION_NAME_MESSAGE_EXCEPTION),
	COMMUNICATION(CommonConstants.EXCEPTION_NAME_COMMUNICATION_EXCEPTION);
	
	private String name;
	
	private ExceptionName(String name) {
		this.name = name;
	}
}
