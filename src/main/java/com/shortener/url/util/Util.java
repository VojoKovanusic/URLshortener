package com.shortener.url.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {

	public String getPasswordHash(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	public String generateString(int numberOfChar) {

		String posibileChar =
				"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String password =
				RandomStringUtils.random(numberOfChar, posibileChar);

		return password;
	}

}
