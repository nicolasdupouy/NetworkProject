package com.ndu;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class InetAddressTest {

	@Test
	public void test() {
		InetAddress inet;
		try {
			//inet = InetAddress.getByName("http://www.google.com");
			inet = InetAddress.getByName("localhost");
			System.out.println ("IP  : " + inet.getHostAddress());
			assertNotNull("IP  : ", inet.getHostAddress());
			
		} catch (UnknownHostException e) {
			fail("UnknownHostException occured");
			e.printStackTrace();
		}
		
		
	}

}
