package com.shortener.url.service;

import java.net.MalformedURLException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shortener.url.model.Url;

@Service
public class ShortingUrlServiceImpl implements ShortingUrlService {

	private Map<String, Url> urlList;

	@Autowired
	public ShortingUrlServiceImpl(Map<String, Url> shortenUrlList) {
		this.urlList = shortenUrlList;

	}

	@Override
	public void setShortUrl(String randomChar, Url url) throws MalformedURLException {
		url.setShortUrl("http://localhost:8090/vojo.com/" + randomChar);
		urlList.put(randomChar, url);

	}

	@Override
	public String generateRandomUrl() {
		String randomUrl = "";
		String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < 5; i++)
			randomUrl += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
		return randomUrl;
	}

	@Override
	public void setNumberOfVisitsForThisUrl(String random) {
		long visits = urlList.get(random).getNumbeOfVisits();
		urlList.get(random).setNumbeOfVisits(visits += 1);
		System.out.println(urlList.get(random).getRealUrl() +": "+urlList.get(random).getNumbeOfVisits());
	}
}
