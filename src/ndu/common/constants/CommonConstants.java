package ndu.common.constants;

public interface CommonConstants {

	public static String LOCAL_DIRECTORY 						= System.getProperty("user.dir");
	
	/*
	 * ################
	 * ## Parapeters ##
	 * ################
	 */
	public static String PARAMETER_PORT					= "-port";
	public static String PARAMETER_DEBUG_MODE			= "-debugMode";
	public static String PARAMETER_EXCHANGE_DATA_METHOD	= "-exchangeDataMethod";
	public static String PARAMETER_PASSWORD_FILE		= "-passwordFile";
	public static String PARAMETER_ADDRESS				= "-address";
	
	public static String EXCHANGE_METHOD_STRING			= "string";
	public static String EXCHANGE_METHOD_OBJECT			= "object";
	
	/*
	 * ###############
	 * ## Passwords ##
	 * ###############
	 */
	public static String DEFAULT_PASSWORD_DIRECTORY 	= LOCAL_DIRECTORY.concat("/data");
	public static String DEFAULT_PASSWORD_FILE 			= DEFAULT_PASSWORD_DIRECTORY.concat("/UserInfo.txt");
	public static String PASSWORD_SEPARATOR 			= ",";
	
	/*
	 * ##################
	 * ## Return codes ##
	 * ##################
	 */
	public static int RETURN_CODE_OK 				= 0;
	public static int WARNING_RETURN_CODE 				= 4;
	public static int ERROR_RETURN_CODE 				= 8;
	
	/*
	 * #########################
	 * ## Messages separators ##
	 * #########################
	 */
	public static String S_MESSAGE_SEPARATOR_1			= "|";
	public static String S_MESSAGE_SEPARATOR_2			= ",";
	
	/*
	 * ##################
	 * ## String codes ##
	 * ##################
	 */
	public static String S_YES 							= "Y";
	public static String S_OK 							= "1";
	public static String S_KO 							= "0";
	public static String S_WRONG_OPERATION				= "-1";
	
	/*
	 * ################
	 * ## Exceptions ##
	 * ################
	 */
	public static String EXCEPTION_MUST_BE_INITIALIZED				= "must be initialized.";
	
	// ParameterException
	public static String EXCEPTION_EMPTY_PARAMETERS_LIST			= "Empty parameters list !";
	public static String EXCEPTION_UNKNOW_EXCHANGE_METHOD			= "Unknow exchange data method";
	public static String EXCEPTION_WRONG_PARAMETERS_NUMBER_BEGIN	= "Wrong parameters number (should be even): \"";
	public static String EXCEPTION_MANDATORY_PARAM_NOT_SET_BEGIN 	= "Mandatoty parameter not set : \"";
	public static String EXCEPTION_END 								= "\" !";
	
	// LoginPasswordListManagement
	public static String EXCEPTION_READING_PASSWORD_LIST		 	= "Error while reading the file contening the passwords list.";
}
