package com.shortener.url.service;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;
import com.shortener.url.util.Util;
@Service
public class UrlServiceImpl implements UrlService {

	private UserService userService;
	@Autowired
	
	public UrlServiceImpl(UserService userService ) {
		this.userService = userService;
	}

	@Override
	public void createShortUrl(Url url) throws MalformedURLException {
		String randomCharForUrl = Util.generateString(5);
		
		User user=this.userService.getCurrentUser();
		
		url.setShortUrl("http://localhost:8090/vojo.com/"+ randomCharForUrl);
		
		user.getMyUrlList().put(randomCharForUrl, url);
		
	}
 
	@Override
	public void setNumberOfVisitsForThisUrl(String shortUrlPath) {
		
		User user=this.userService.getCurrentUser();
		
		long visits = user.getMyUrlList().get(shortUrlPath).getNumberOfVisits();
		
		user.getMyUrlList().get(shortUrlPath).setNumberOfVisits(visits += 1);
	
	}

	@Override
	public boolean is301RedirectType(String shortUrlPath, User user) {
		 return user.getMyUrlList().get(shortUrlPath).getRedirectType()==301; 
	}
}
