package com.shortener.url.service;

import java.net.MalformedURLException;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;

public interface UrlService {

	void createShortUrl(Url url) throws MalformedURLException;
 
	long setNumberOfVisitsForThisUrl(String shortUrlPath);

	boolean is301RedirectType(String shortUrlPath, User user);
}
