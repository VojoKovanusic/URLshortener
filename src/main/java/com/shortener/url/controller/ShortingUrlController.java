package com.shortener.url.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.url.model.Url;
import com.shortener.url.service.ShortingUrlService;

@RestController
public class ShortingUrlController {

	private ShortingUrlService shortingUrlService;
	private Map<String, Url> urlList;

	@Autowired
	public ShortingUrlController(ShortingUrlService shortingUrlService, Map<String, Url> urlList) {
		this.shortingUrlService = shortingUrlService;
		this.urlList = urlList;
	}

	@PostMapping(value = "/shorturl")
	public ResponseEntity<Object> getShortUrl(@RequestBody Url url) throws MalformedURLException {
		String randomCharForUrl = shortingUrlService.generateRandomUrl();
		shortingUrlService.setShortUrl(randomCharForUrl, url);
		return new ResponseEntity<Object>(url, HttpStatus.OK);
	}

	@GetMapping(value = "/vojo.com/{random}")
	public void getFullUrl(HttpServletResponse response, @PathVariable("random") String random) throws IOException {
		shortingUrlService.setNumberOfVisitsForThisUrl(random); 
		response.sendRedirect(urlList.get(random).getRealUrl());

	}
 
	 

}
