package com.ndu.common.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import com.ndu.common.constants.CommonConstants;
import com.ndu.common.exception.TechnicalException;

public abstract class CommunicationChannelAbstract implements CommunicationChannelInterface {
	
	private String typeName;
	
	// data canals FROM/TO terminal
	// FROM
	private BufferedReader terminalIN;
	// TO
	private BufferedWriter terminalOUT;
	
	public CommunicationChannelAbstract(String type) {
		this.typeName = type;
	}
	
	public void setTerminalIN(BufferedReader terminalIN) {
		this.terminalIN = terminalIN;
	}
	
	public void setTerminalOUT(BufferedWriter terminalOUT) throws TechnicalException {
		this.terminalOUT = terminalOUT;
	}
	
	/**
	 * 
	 */
	public void writeToTerminal(String string) throws TechnicalException {
		try {
			terminalOUT.write(" - " + this.typeName + "> ");
			terminalOUT.write(string);
			terminalOUT.write("\n");
			terminalOUT.flush();
		} catch (IOException e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_WRITE_TERMINAL, e);
		}
	}
	
	/**
	 * 
	 */
	public String readFromTerminal() throws TechnicalException {
		try {
			return terminalIN.readLine();
		} catch (IOException e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_READ_TERMINAL, e);
		}
		return null;
	}
}
