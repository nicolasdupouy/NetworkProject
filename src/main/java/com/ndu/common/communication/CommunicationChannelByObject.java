package com.ndu.common.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.ndu.client.constants.ClientConstants;

public class CommunicationChannelByObject extends CommunicationChannelAbstract {

	// data canals FROM/TO server
	// FROM
	private ObjectInputStream correspondantIN;
	// TO
	private ObjectOutputStream correspondantOUT;

		
	public CommunicationChannelByObject(String type) {
		super(type);
	}

	public CommunicationChannelByObject(String type, Socket socket) throws IOException {
		super(type);
		
		this.setTerminalIN( new BufferedReader(new InputStreamReader(System.in)) );
		this.setTerminalOUT( new BufferedWriter(new OutputStreamWriter(System.out)) );
		
		this.setCorrespondantOUT( new ObjectOutputStream(socket.getOutputStream()) );
		this.setCorrespondantIN( new ObjectInputStream(socket.getInputStream()) );
	}
	

	@Override
	public void setCorrespondantIN(Object correspondantIN) {
		this.correspondantIN = (ObjectInputStream)correspondantIN;
	}

	@Override
	public void setCorrespondantOUT(Object correspondantOUT) throws IOException {
		this.correspondantOUT = (ObjectOutputStream)correspondantOUT;
		this.correspondantOUT.flush();
	}

	
	@Override
	public Object readFromCorrespondant() throws CommunicationException {
		
		Object object = null; 
		try {
			object = (Object)(correspondantIN.readObject());
		} catch(Exception e) {
			throw new CommunicationException("Exception while reading from correspondant", e);
		} 
		return object;
	}
	
	@Override
	public void writeToCorrespondant(/*Message message*/ Object object) throws CommunicationException {
		try {
			correspondantOUT.writeObject(object);
			//correspondantOUT.write(message.formatMessageContent());
			//correspondantOUT.write("\n");
			correspondantOUT.flush();
		} catch (IOException e) {
			throw new CommunicationException(ClientConstants.EXCEPTION_WRITE_CORRESPONDANT, e);
		}
	}
	
}
