package com.ndu.common.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.ndu.common.constants.CommonConstants;
import com.ndu.common.exception.TechnicalException;
import com.ndu.common.message.Message;

public class CommunicationChannelByString extends CommunicationChannelAbstract {
	
	// data canals FROM/TO server
	// FROM
	private BufferedReader correspondantIN;
	// TO
	private BufferedWriter correspondantOUT;
	
	public CommunicationChannelByString(String type) {
		super(type);
	}
	
	public CommunicationChannelByString(String type, Socket socket) throws TechnicalException {
		super(type);
		
		this.setTerminalIN(new BufferedReader(new InputStreamReader(System.in)));
		this.setTerminalOUT(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		try {
			this.setCorrespondantIN(new BufferedReader(new InputStreamReader(socket.getInputStream())));
			this.setCorrespondantOUT(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
		} catch (IOException e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_CONNECTION_SOCKET, e);
		}
	}
	
	@Override
	public void setCorrespondantIN(Object correspondantIN) {
		this.correspondantIN = (BufferedReader) correspondantIN;
		
	}
	
	@Override
	public void setCorrespondantOUT(Object correspondantOUT) throws TechnicalException {
		this.correspondantOUT = (BufferedWriter) correspondantOUT;
		try {
			this.correspondantOUT.flush();
		} catch (IOException e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_CONNECTION_SOCKET, e);
		}
	}
	
	/**
	 * @return String if exchange by String
	 * @return Object type Message if exchange by Object
	 */
	@Override
	public Object readFromCorrespondant() throws TechnicalException {
		
		String messageString = null;
		try {
			messageString = correspondantIN.readLine();
		} catch (Exception e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_READ_CORRESPONDANT, e);
		}
		return messageString;
	}
	
	@Override
	public void writeToCorrespondant(/*Message message*/Object object) throws TechnicalException {
		try {
			Message message = (Message) object;
			//correspondantOUT.writeObject(object);
			correspondantOUT.write(message.formatMessageContent());
			correspondantOUT.write("\n");
			correspondantOUT.flush();
		} catch (IOException e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_WRITE_CORRESPONDANT, e);
		}
	}
}
