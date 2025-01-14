package com.mavin.SapConnectService.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class TransCodeHelper {
	public String generateRandomNumber(int length) { 
		String characters = "0123456789"; 
		SecureRandom random = new SecureRandom(); 
		StringBuilder result = new StringBuilder(length); 
		for (int i = 0; i < length; i++) { 
			result.append(characters.charAt(random.nextInt(characters.length()))); 
		} 
		return result.toString(); }
	
	
}
