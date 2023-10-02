package com.ar.moviezone.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtility {

	public static String hashValuesSHA256(String  data) throws NoSuchAlgorithmException {
		MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
		msgDigest.update(data.getBytes());
		
		byte[] byteData = msgDigest.digest();
		StringBuilder hexString = new StringBuilder();
		
		for (int i=0;i<byteData.length;i++) {
    		
    		/*
    		 * converting each byte data to its corresponding 
    		 * positive number and storing the hexadecimal format of the same
    		 */
    		String hex=Integer.toHexString(0xff & byteData[i]);
    		
    		/*
    		 * if the size of hexadecimal value is one, 
    		 * then appending a '0' in front of it
    		 */
   	     	if(hex.length()==1) 
   	     		hexString.append('0');
   	     	
   	     	/*
   	     	 * appending the hexadecimal value to a string buffer
   	     	 */
   	     	hexString.append(hex);
    	}
		return hexString.toString();
	}
}
