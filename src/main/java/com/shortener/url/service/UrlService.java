package com.shortener.url.service;

import java.net.MalformedURLException;

import com.shortener.url.model.Url;

public interface UrlService {

	void createShortUrl(Url url) throws MalformedURLException;
 
	void setNumberOfVisitsForThisUrl(String shortUrlPath);
}
