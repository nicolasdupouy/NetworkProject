package com.ndu.common.constants;

public final class CommonConstants {

	private CommonConstants() {}
	
	public static final String LOCAL_DIRECTORY 				= System.getProperty("user.dir");
	
	/*
	 * ################
	 * ## Parapeters ##
	 * ################
	 */
	public static final String PARAMETER_PORT					= "-port";
	public static final String PARAMETER_DEBUG_MODE			= "-debugMode";
	public static final String PARAMETER_EXCHANGE_DATA_METHOD	= "-exchangeDataMethod";
	public static final String PARAMETER_PASSWORD_FILE		= "-passwordFile";
	public static final String PARAMETER_ADDRESS				= "-address";
	
	public static final String EXCHANGE_METHOD_STRING			= "string";
	public static final String EXCHANGE_METHOD_OBJECT			= "object";
	
	/*
	 * ###############
	 * ## Passwords ##
	 * ###############
	 */
	public static final String DEFAULT_PASSWORD_DIRECTORY 	= LOCAL_DIRECTORY.concat("/data");
	public static final String DEFAULT_PASSWORD_FILE 			= DEFAULT_PASSWORD_DIRECTORY.concat("/UserInfo.txt");
	public static final String PASSWORD_SEPARATOR 			= ",";
	
	/*
	 * ##################
	 * ## Return codes ##
	 * ##################
	 */
	public static final int RETURN_CODE_OK 				= 0;
	public static final int WARNING_RETURN_CODE 				= 4;
	public static final int ERROR_RETURN_CODE 				= 8;
	
	/*
	 * #########################
	 * ## Messages separators ##
	 * #########################
	 */
	public static final String S_MESSAGE_SEPARATOR_1			= "|";
	public static final String S_MESSAGE_SEPARATOR_2			= ",";
	
	/*
	 * ##################
	 * ## String codes ##
	 * ##################
	 */
	public static final String S_YES 							= "Y";
	public static final String S_OK 							= "1";
	public static final String S_KO 							= "0";
	public static final String S_WRONG_OPERATION				= "-1";
	
	/*
	 * ################
	 * ## Exceptions ##
	 * ################
	 */
	public static final String EXCEPTION_MUST_BE_INITIALIZED				= "must be initialized.";
	
	// ParameterException
	public static final String EXCEPTION_EMPTY_PARAMETERS_LIST			= "Empty parameters list !";
	public static final String EXCEPTION_UNKNOW_EXCHANGE_METHOD			= "Unknow exchange data method";
	public static final String EXCEPTION_WRONG_PARAMETERS_NUMBER_BEGIN	= "Wrong parameters number (should be even): \"";
	public static final String EXCEPTION_MANDATORY_PARAM_NOT_SET_BEGIN 	= "Mandatoty parameter not set : \"";
	public static final String EXCEPTION_END 								= "\" !";
	
	// LoginPasswordListManagement
	public static final String EXCEPTION_READING_PASSWORD_LIST		 	= "Error while reading the file contening the passwords list.";
}
