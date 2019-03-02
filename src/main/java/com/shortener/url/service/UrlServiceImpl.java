package com.shortener.url.service;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;

@Service
public class UrlServiceImpl implements UrlService {


	private UserService userService;
	@Autowired
	public UrlServiceImpl(UserService userService ) {
		this.userService = userService;
	}

	@Override
	public void createShortUrl(Url url) throws MalformedURLException {
		String randomCharForUrl = RandomService.generateRandomString(5);
		User user=this.userService.getCurrentUser();
		url.setShortUrl("http://localhost:8090/vojo.com/"+ randomCharForUrl);
		user.getMyUrlList().put(randomCharForUrl, url);
System.out.println("ahort- "+randomCharForUrl);
	}
 
	@Override
	public void setNumberOfVisitsForThisUrl(String shortUrlPath) {
		User user=this.userService.getCurrentUser();
		long visits = user.getMyUrlList().get(shortUrlPath).getNumberOfVisits();
		user.getMyUrlList().get(shortUrlPath).setNumberOfVisits(visits += 1);
		
		System.out.println(user.getMyUrlList().get(shortUrlPath).getRealUrl() +": "+user.getMyUrlList().get(shortUrlPath).getNumberOfVisits());
	}
}
