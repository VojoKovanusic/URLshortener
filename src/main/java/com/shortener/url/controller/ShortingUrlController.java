package com.shortener.url.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;
import com.shortener.url.service.ShortingUrlService;
import com.shortener.url.service.UserService;
import com.shortener.url.util.MyUtillityClass;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ShortingUrlController {

	private ShortingUrlService shortingUrlService;
	private Map<String, Url> urlList;
	private UserService userService;

	@Autowired
	public ShortingUrlController(ShortingUrlService shortingUrlService, Map<String, Url> urlList,
			UserService userService) {
		this.shortingUrlService = shortingUrlService;
		this.urlList = urlList;
		this.userService = userService;
	}

	@PostMapping(value = "/register")
	public Url createShortUrl(@RequestBody Url url) throws MalformedURLException {
		String randomCharForUrl = MyUtillityClass.generateRandomString(5);
		shortingUrlService.setShortUrl(randomCharForUrl, url);
		System.out.println("randomCharForUrl "+ randomCharForUrl);
		return url;
	}

	@GetMapping(value = "/vojo.com/{shortUrlPath}",produces = { "application/json" })
	public void getFullUrl(HttpServletResponse response, @PathVariable("shortUrlPath") String shortUrlPath)
			throws IOException {
		shortingUrlService.setNumberOfVisitsForThisUrl(shortUrlPath);
		response.sendRedirect(urlList.get(shortUrlPath).getRealUrl());

	}
 
	@GetMapping(value = "/statistic/{accountId}",produces = { "application/json" })
	public ResponseEntity<Object> getStatistic(HttpServletResponse response,
			@PathVariable("accountId") String accountId) throws IOException {
		System.out.println("1");
		if (userService.isLoginUser(accountId)) {
			System.out.println("2");
			User user= userService.findByAccountId(accountId);
			
			return new ResponseEntity<Object>(userService.findByAccountId(accountId), HttpStatus.OK);
		}
		return null;
	}

}
