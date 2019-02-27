package com.shortener.url.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shortener.url.model.Url;
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

	public static String getJSONformatForShortUrl(JSONObject json,Url url) {
		
			json.put("shortUrl", url.getShortUrl());
			return json.toString();
	
	}

	public static String getStatisticJSONformat(JSONObject json,User user) {
		 
		user.getMyUrlList().forEach((shortUrl, url) -> json.put(url.getRealUrl(), url.getNumberOfVisits()));
		return json.toString();
	}

	public static String getApprovedAccountMessage(JSONObject json,String password) {
		
		json.put("success", true);
		json.put("description", "Your account is opened");
		json.put("password", password);
		
		return  json.toString();
	}

	public static String getRejectedAccountMessage(JSONObject json) {
		 
		json.put("success", false);
		json.put("description", "Your account is not opened, account with that accountId already exist");
		
		return  json.toString();
	}
}
