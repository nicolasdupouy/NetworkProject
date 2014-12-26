package com.ndu.common.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import com.ndu.common.exception.TechnicalException;

public interface CommunicationChannelInterface {
	
	void setTerminalIN(BufferedReader terminalIN);
	
	void setTerminalOUT(BufferedWriter terminalOUT) throws TechnicalException;
	
	void setCorrespondantIN(Object correspondantIN);
	
	void setCorrespondantOUT(Object correspondantOUT) throws TechnicalException;
	
	void writeToTerminal(String string) throws TechnicalException;
	
	String readFromTerminal() throws TechnicalException;
	
	Object readFromCorrespondant() throws TechnicalException;
	
	void writeToCorrespondant(Object object) throws TechnicalException;
	
}
