package com.shortener.url.controller;

import java.net.MalformedURLException;
import java.net.URI;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;
import com.shortener.url.service.MessageService;
import com.shortener.url.service.UrlService;
import com.shortener.url.service.UserService;
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UrlController {

	private HttpHeaders headers;
	private UrlService urlService;
	private UserService userService;
    private  MessageService messageService;
	@Autowired
	public UrlController(UrlService shortingUrlService,
			UserService userService, HttpHeaders httpHeaders,
						 MessageService messageService) {
		this.urlService = shortingUrlService;
		this.userService = userService;
		this.headers = httpHeaders;
		this.messageService=messageService;
	}

	@PostMapping(value = "/register", produces = { "application/json"})
	public ResponseEntity  createShortUrl( @RequestBody Url url)  {

			urlService.createShortUrl(url);

		return new ResponseEntity<>(messageService.getForShortUrl(url),HttpStatus.CREATED);
	}

	@GetMapping(value = "/vojo.com/{shortUrlPath}")
	public ResponseEntity<HttpHeaders> redirectToFullUrl(@PathVariable("shortUrlPath") String shortUrlPath) {

		User user = this.userService.findUserByAccountId(userService.getCurrentlyLoggingID());

		String pathForRealUrl = user.getMyUrlList().get(shortUrlPath).getRealUrl();

		this.urlService.setNumberOfVisitsForThisUrl(shortUrlPath);

		this.headers.setLocation(URI.create(pathForRealUrl));

		if(this.urlService.is301RedirectType(shortUrlPath, user))	{
		return new ResponseEntity<>(this.headers, HttpStatus.MOVED_PERMANENTLY);}
		else {
	    return new ResponseEntity<>(this.headers, HttpStatus.FOUND);}
	}



	@GetMapping(value = "/statistic/{accountId}", produces = { "application/json" })
	public ResponseEntity<String> getStatistic(@PathVariable("accountId") String accountId)  {
		if (userService.isLoginUser(accountId)) {
			User user = userService.findUserByAccountId(accountId);

			return new ResponseEntity<>(messageService.getForStatistic(user),HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}

}
