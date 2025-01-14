package com.mavin.SapConnectService.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncryptUtils {
	// Encrypt sensitive data using a strong encryption algorithm
	private static final String DEFAULT_SECRET = "123456";
	private static SecretKeySpec secretKey;
	private static final String ALGORITHM = "AES";
	
	public EncryptUtils() {}
	
	public static void setSecreteKey(String secret) {
		MessageDigest sha = null;
		try {
	      byte[] key = secret.getBytes(StandardCharsets.UTF_8);
	      sha = MessageDigest.getInstance("SHA-1");
	      key = sha.digest(key);
	      key = Arrays.copyOf(key, 16);
	      secretKey = new SecretKeySpec(key, ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
	      log.error("Error while preparing secret key: " + e.toString());
	    } 
	}
	
	public static String encrypt(String strToEncrypt) {
		try {
			if(secretKey == null) {
				setSecreteKey(DEFAULT_SECRET);
			}
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(1, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
	    } catch (Exception e) {
	    	log.error("Error while encrypting: " + e.toString());
	    	return null;
	    } 
	}
	
	public static String encrypt(String strToEncrypt, String secret) {
	    try {
	    	setSecreteKey(secret);
	    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	    	cipher.init(1, secretKey);
	    	return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
	    } catch (Exception e) {
	    	log.error("Error while encrypting: " + e.toString());
	    	return null;
	    } 
	}
	
	public static String decrypt(String strToDecrypt) {
	    try {
	    	if(secretKey == null) {
				setSecreteKey(DEFAULT_SECRET);
			}
	    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	    	cipher.init(2, secretKey);
	    	return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	    } catch (Exception e) {
	    	log.error("Error while decrypting: " + e.toString());
	    	return null;
	    } 
	}
	
	public static String decrypt(String strToDecrypt, String secret) {
	    try {
	    	setSecreteKey(secret);
	    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	    	cipher.init(2, secretKey);
	    	return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	    } catch (Exception e) {
	    	log.error("Error while decrypting: " + e.toString());
	    	return null;
	    } 
	}
}
