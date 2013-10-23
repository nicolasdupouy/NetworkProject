package com.ndu.common.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import com.ndu.client.constants.ClientConstants;

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
	public void setTerminalOUT(BufferedWriter terminalOUT) throws IOException {
		this.terminalOUT = terminalOUT;
	}
	
	/**
	 * 
	 */
	public void writeToTerminal(String string) throws CommunicationException {
		try {
			terminalOUT.write(" - " + this.typeName + "> ");
			terminalOUT.write(string);
			terminalOUT.write("\n");
			terminalOUT.flush();
		} catch (IOException e) {
			throw new CommunicationException(ClientConstants.EXCEPTION_WRITE_TERMINAL, e);
		}
	}
	
	/**
	 * 
	 */
	public String readFromTerminal() throws CommunicationException {
		try {
			return terminalIN.readLine();
		} catch (IOException e) {
			throw new CommunicationException(ClientConstants.EXCEPTION_READ_TERMINAL, e);
		}
	}
}
