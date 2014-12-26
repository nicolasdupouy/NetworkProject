package com.ndu.common.utils;

import java.util.HashMap;

import com.ndu.common.constants.CommonConstants;
import com.ndu.common.exception.TechnicalException;

public class ParametersManagement {
	
	private static ParametersManagement instance = null;
	private HashMap<String, String> parameters = new HashMap<String, String>();
	private int port;
	private boolean debugMode;
	private String passwordFile;
	private String address;
	private String exchangeDataMethod;
	
	public static void initInstance(boolean serverParameters, String[] argv) throws TechnicalException {
		if (instance == null) {
			instance = new ParametersManagement(serverParameters, argv);
		}
	}
	
	public static ParametersManagement getInstance() throws TechnicalException {
		if (instance == null) {
			TechnicalException.throwParameterException("ParametersManagement" + CommonConstants.EXCEPTION_MUST_BE_INITIALIZED);
		}
		return instance;
	}
	
	private ParametersManagement(boolean serverParameters, String[] argv) throws TechnicalException {
		setParameters(argv);
		String s_port = (String) readParameters(CommonConstants.PARAMETER_PORT, true);
		port = Integer.valueOf(s_port).intValue();
		String s_debugMode = (String) readParameters(CommonConstants.PARAMETER_DEBUG_MODE, false);
		if (s_debugMode != null && s_debugMode.equals(CommonConstants.S_YES)) {
			debugMode = true;
		} else {
			debugMode = false;
		}
		
		exchangeDataMethod = (String) readParameters(CommonConstants.PARAMETER_EXCHANGE_DATA_METHOD, true);
		if (!exchangeDataMethod.equals(CommonConstants.EXCHANGE_METHOD_OBJECT) && !exchangeDataMethod.equals(CommonConstants.EXCHANGE_METHOD_STRING)) {
			TechnicalException.throwParameterException(CommonConstants.EXCEPTION_UNKNOW_EXCHANGE_METHOD + " : " + exchangeDataMethod);
		}
		
		// Parameters available only for server
		if (serverParameters) {
			passwordFile = (String) readParameters(CommonConstants.PARAMETER_PASSWORD_FILE, false);
		}
		// Parameters available only for clients
		else {
			address = (String) readParameters(CommonConstants.PARAMETER_ADDRESS, true);
		}
	}
	
	/**
	 * Read and store all parameters from command line
	 * 
	 * @param argv
	 *            command line
	 * @throws Exception
	 */
	private void setParameters(String[] argv) throws TechnicalException {
		if (argv != null && (argv.length % 2 == 0)) {
			for (int i = 0; i < argv.length; i++) {
				parameters.put(argv[i], argv[++i]);
			}
		} else {
			TechnicalException.throwParameterException(CommonConstants.EXCEPTION_WRONG_PARAMETERS_NUMBER_BEGIN + argv.length + CommonConstants.EXCEPTION_END);
		}
	}
	
	/**
	 * Get the value given at the command line for one parameter
	 * 
	 * @param key
	 *            parameter name
	 * @param mandatory
	 *            is the parameter mandatory or not ?
	 * @return parameter value
	 * @throws Exception
	 */
	private Object readParameters(String key, boolean mandatory) throws TechnicalException {
		
		if (parameters.isEmpty()) {
			TechnicalException.throwParameterException(CommonConstants.EXCEPTION_EMPTY_PARAMETERS_LIST);
		} else if (parameters.containsKey(key)) {
			return parameters.get(key);
		}
		// mandatory parameter type not defined
		else if (mandatory == true) {
			TechnicalException.throwParameterException(CommonConstants.EXCEPTION_MANDATORY_PARAM_NOT_SET_BEGIN + key + CommonConstants.EXCEPTION_END);
		}
		// parameter type not defined but not mandatory.
		else {
			return null;
		}
		return null;
	}
	
	/*
	 * ##############################################
	 * ## Parameters commons to server and clients ##
	 * ##############################################
	 */
	/**
	 * 
	 * @return used port
	 */
	public int getPort() {
		return port;
	}
	
	public boolean isDebugMode() {
		return debugMode;
	}
	
	public boolean isExchangeDataMethodString() {
		if (exchangeDataMethod != null) {
			if (exchangeDataMethod.equals(CommonConstants.EXCHANGE_METHOD_STRING)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isExchangeDataMethodObject() {
		if (exchangeDataMethod != null) {
			if (exchangeDataMethod.equals(CommonConstants.EXCHANGE_METHOD_OBJECT)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * ##########################################
	 * ## Parameters available only for server ##
	 * ##########################################
	 */
	public String getPasswordFile() {
		if (passwordFile != null) {
			return passwordFile;
		} else {
			return null;
		}
	}
	
	/*
	 * ###########################################
	 * ## Parameters available only for clients ##
	 * ###########################################
	 */
	public String getAddress() {
		return address;
	}
}
