package com.ndu.common.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.ndu.common.constants.CommonConstants;
import com.ndu.common.exception.TechnicalException;

public class CommunicationChannelByObject extends CommunicationChannelAbstract {
	
	// data canals FROM/TO server
	// FROM
	private ObjectInputStream correspondantIN;
	// TO
	private ObjectOutputStream correspondantOUT;
	
	public CommunicationChannelByObject(String type) {
		super(type);
	}
	
	public CommunicationChannelByObject(String type, Socket socket) throws TechnicalException {
		super(type);
		
		this.setTerminalIN(new BufferedReader(new InputStreamReader(System.in)));
		this.setTerminalOUT(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		try {
			this.setCorrespondantOUT(new ObjectOutputStream(socket.getOutputStream()));
			this.setCorrespondantIN(new ObjectInputStream(socket.getInputStream()));
		} catch (IOException e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_CONNECTION_SOCKET, e);
		}
	}
	
	@Override
	public void setCorrespondantIN(Object correspondantIN) {
		this.correspondantIN = (ObjectInputStream) correspondantIN;
	}
	
	@Override
	public void setCorrespondantOUT(Object correspondantOUT) throws TechnicalException {
		this.correspondantOUT = (ObjectOutputStream) correspondantOUT;
		
		try {
			this.correspondantOUT.flush();
		} catch (IOException e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_CONNECTION_SOCKET, e);
		}
	}
	
	@Override
	public Object readFromCorrespondant() throws TechnicalException {
		
		Object object = null;
		try {
			object = (Object) (correspondantIN.readObject());
		} catch (Exception e) {
			TechnicalException.throwCommunicationException("Exception while reading from correspondant", e);
		}
		return object;
	}
	
	@Override
	public void writeToCorrespondant(/*Message message*/Object object) throws TechnicalException {
		try {
			correspondantOUT.writeObject(object);
			//correspondantOUT.write(message.formatMessageContent());
			//correspondantOUT.write("\n");
			correspondantOUT.flush();
		} catch (IOException e) {
			TechnicalException.throwCommunicationException(CommonConstants.EXCEPTION_WRITE_CORRESPONDANT, e);
		}
	}
	
}
