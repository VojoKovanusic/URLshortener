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
		url.setShortUrl( "http://localhost:8090/vojo.com/"+ randomChar);
		urlList.put(randomChar, url);

	}
 
	@Override
	public void setNumberOfVisitsForThisUrl(String urlPath) {
		long visits = urlList.get(urlPath).getNumbeOfVisits();
		urlList.get(urlPath).setNumbeOfVisits(visits += 1);
		System.out.println(urlList.get(urlPath).getRealUrl() +": "+urlList.get(urlPath).getNumbeOfVisits());
	}
}
