package com.ndu.common.message;

public enum SupportedOrders {

	LOGIN("Login", 					false,	"Login <UserName> <Password>"),
	LOGOUT("Logout", 				true,	"Logout <UserName>"),
	LIST_CLIENTS("List_Clients", 	true,	"List_Clients");
	//BROWSE_REMOTE("Browse_Remote", 	true,	"Browse_Remote"),
	//BROWSE_LOCAL("Browse_Local", 	true,	"Browse_Local"),
	//GET("Get", 						true,	"Get <fileName>"),
	//PUT("Put", 						true,	"Put <fileName>"),
	
	private String type;
	private boolean needBeingConnected;
	private String descrition;
	
	private SupportedOrders(String type, boolean needBeingConnected, String descrition) {
		this.type = type;
		this.needBeingConnected = needBeingConnected;
		this.descrition = descrition;
	}
	
	private static boolean testOrderType(String type, String choosenOperation) {
		if (choosenOperation.equals(type)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isLoginType(String choosenOperation) {
		return testOrderType("Login", choosenOperation);
	}
	
	public static boolean isLogoutType(String choosenOperation) {
		return testOrderType("Logout", choosenOperation);
	}
	
	public static boolean isListClients(String choosenOperation) {
		return testOrderType("List_Clients", choosenOperation);
	}
	
	public static void displaySupportedOperations() {
		System.out.println("The system supports the following options:");
		for (SupportedOrders supportedOrder: SupportedOrders.values()) {
			System.out.println(supportedOrder.descrition);
		}
	}
	
	public static void displayAllowedOperations(boolean isClientConnected) {
		System.out.println("Please select an operation:");
        
		int i = 0;
		if (isClientConnected) {
			System.out.print("\t");
			for (SupportedOrders supportedOrder: SupportedOrders.values()) {
				if (supportedOrder.needBeingConnected) {
					if (i > 0) {
						System.out.print("  |  ");
					}
					System.out.print(++i + "." + supportedOrder.type);
				}
			}
			System.out.println();
		}
		else {
			System.out.print("\t");
			for (SupportedOrders supportedOrder: SupportedOrders.values()) {
				if (!supportedOrder.needBeingConnected) {
					if (i > 0) {
						System.out.print("  |  ");
					}
					System.out.print(++i + "." + supportedOrder.type);
				}
			}
		}
		System.out.println();
	}
	
	public static boolean isSupportedType(String type) {
		if (type != null) {
			for (SupportedOrders supportedOrder: SupportedOrders.values()) {
				if (supportedOrder.type.equals(type)) {
					return true;
				}
			}
		}
		return false;
	}
}
