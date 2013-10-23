package com.ndu.common.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.ndu.common.constants.CommonConstants;
import com.ndu.common.exception.PasswordListException;

public class LoginPasswordListManagement {

	private static LoginPasswordListManagement instance = null;
	private HashMap<String,String> passwordList = new HashMap<String,String>();
	
	public static void initInstance(String passwordFileName) throws PasswordListException {
		if (instance == null) {
			if (passwordFileName != null) {
				instance = new LoginPasswordListManagement(passwordFileName);
			} else {
				instance = new LoginPasswordListManagement(CommonConstants.DEFAULT_PASSWORD_FILE);
			}
		}
	}
	
	public static LoginPasswordListManagement getInstance() throws PasswordListException {
		if (instance == null) {
			throw new PasswordListException("LoginPasswordListManagement " + CommonConstants.EXCEPTION_MUST_BE_INITIALIZED);
		}
		return instance;
	}
	
	private LoginPasswordListManagement(String passwordFileName) throws PasswordListException {

		try{
			BufferedReader br = new BufferedReader(new FileReader(passwordFileName));
			try {
				String line;
				while ((line = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(line, CommonConstants.PASSWORD_SEPARATOR);
					while(st.hasMoreElements()) {
						passwordList.put(st.nextToken(), st.nextToken());
					}
				}
			}
			finally {
				br.close();
			}
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
			throw new PasswordListException(CommonConstants.EXCEPTION_READING_PASSWORD_LIST, e);
		}
	}
	
	public boolean isLoginAuthorized(String login, String password) {
		if (passwordList.containsKey(login)
			&& passwordList.get(login).equals(password)) {
			return true;
		}
		return false;
	}
}
