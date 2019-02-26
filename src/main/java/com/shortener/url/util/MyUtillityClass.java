package com.shortener.url.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shortener.url.model.User;

public class MyUtillityClass {
	static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static String getPasswordHash(String password) {
		return passwordEncoder.encode(password);
	}
	
	public static String generateRandomString(int numberOfChar) {
		String posibileChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String password = RandomStringUtils.random( numberOfChar, posibileChar );
		 
		return password;
	}
}
