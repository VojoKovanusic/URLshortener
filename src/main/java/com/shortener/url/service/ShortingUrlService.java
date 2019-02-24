package com.shortener.url.service;

import java.net.MalformedURLException;

import com.shortener.url.model.Url;

public interface ShortingUrlService {

	void setShortUrl(String randomChar, Url url) throws MalformedURLException;

	String generateRandomUrl();

	void setNumberOfVisitsForThisUrl(String random);
}
