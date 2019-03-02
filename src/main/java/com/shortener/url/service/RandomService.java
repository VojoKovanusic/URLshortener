package com.shortener.url.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;

public class RandomService {

	public static String getPasswordHash(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	public static String generateRandomString(int numberOfChar) {
		String posibileChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String password = RandomStringUtils.random( numberOfChar, posibileChar );
		 
		return password;
	}

	 
}
