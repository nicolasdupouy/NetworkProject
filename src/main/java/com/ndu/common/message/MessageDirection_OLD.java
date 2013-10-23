package com.ndu.common.message;

public enum MessageDirection_OLD {
	ClientToServer(1), ServerToClient(2);
	
	private int directionCode;
	
	private MessageDirection_OLD(int directionCode) {
		this.directionCode = directionCode;
	}
	
	public int getDirectionCode() {
		return directionCode;
	}
}
