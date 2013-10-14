package ndu.server;

import java.net.InetAddress;

public class ClientPOJO {
	private String userID;
	private String password;
	
	private InetAddress inetAddress;
	private int port;
	
	private boolean isConnected;

	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public InetAddress getInetAddress() {
		return inetAddress;
	}*/
	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}
	public String getHostAddress() {
		return inetAddress.getHostAddress();
	}
	public String getCanonicalHostName() {
		return inetAddress.getCanonicalHostName();
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isConnected() {
		return isConnected;
	}
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	
	public String toString() {
		return "userID=" 			+ userID				+ " / "
			 + "password="			+ password				+ " / "
			 + "hostAddress="		+ getHostAddress()		+ " / "
			 + "canonicalHostName="	+ getCanonicalHostName() + "\n";
	}
}
