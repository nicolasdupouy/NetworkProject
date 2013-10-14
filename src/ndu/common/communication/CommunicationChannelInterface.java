package ndu.common.communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface CommunicationChannelInterface {

	public void setTerminalIN(BufferedReader terminalIN);
	public void setTerminalOUT(BufferedWriter terminalOUT) throws IOException;
	public void setCorrespondantIN(Object correspondantIN);
	public void setCorrespondantOUT(Object correspondantOUT) throws IOException;
	
	public void writeToTerminal(String string) throws CommunicationException;
	public String readFromTerminal() throws CommunicationException;
	public Object readFromCorrespondant() throws CommunicationException;
	public void writeToCorrespondant(Object object) throws CommunicationException;
	
}
