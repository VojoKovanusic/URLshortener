package com.shortener.url.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;

public class JSONMessageService {

	private static final String SUCCESS_KEY = "success";

	private static final String DESCRIPTION_KEY = "description";

	private static final String PASSWORD_KEY = "password";
 
	public static String generateRandomString(int numberOfChar) {
		String posibileChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String password = RandomStringUtils.random(numberOfChar, posibileChar);

		return password;
	}

	public static String getForShortUrl(Url url) {
		return new JSONObject().put("shortUrl", url.getShortUrl()).toString();
	}

	public static String getForStatistic(User user) {
		JSONObject json = new JSONObject();
		user.getMyUrlList().forEach((shortUrl, url) -> json.put(url.getRealUrl(), url.getNumberOfVisits()));
		return json.toString();
	}

	public static String getApprovedAccount(String password) {
		return new JSONObject().put(SUCCESS_KEY, true).put(DESCRIPTION_KEY, "Your account is opened")
				.put(PASSWORD_KEY, password).toString();
	}

	public static String getRejectedAccount() {
		return new JSONObject().put(SUCCESS_KEY, false)
				.put(DESCRIPTION_KEY, "Account with that accountId already exist").toString();
	}
}