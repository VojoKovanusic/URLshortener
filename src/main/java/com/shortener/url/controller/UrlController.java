package com.shortener.url.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
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
import com.shortener.url.service.JSONMessageService;
import com.shortener.url.service.UrlService;
import com.shortener.url.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UrlController {

	private HttpHeaders headers;
	private UrlService urlService;
	private UserService userService;

	@Autowired
	public UrlController(JSONObject json, UrlService shortingUrlService, Map<String, Url> urlList,
			UserService userService, HttpHeaders httpHeaders) {
		this.urlService = shortingUrlService;
		this.userService = userService;
		this.headers = httpHeaders;
	}

	@PostMapping(value = "/register", produces = { "application/json" })
	public ResponseEntity<String> createShortUrl(@RequestBody Url url) throws MalformedURLException {
		
		urlService.createShortUrl(url);
		
		return new ResponseEntity<String>(JSONMessageService.getForShortUrl(url),HttpStatus.CREATED);
	}

	@GetMapping(value = "/vojo.com/{shortUrlPath}")
	public ResponseEntity<HttpHeaders> redirectToFullUrl(@PathVariable("shortUrlPath") String shortUrlPath) {

		User u = this.userService.findUserByAccountId(userService.getCurrentlyLoggingID());

		String pathForRealUrl = u.getMyUrlList().get(shortUrlPath).getRealUrl();
		this.urlService.setNumberOfVisitsForThisUrl(shortUrlPath);

		System.out.println("pathForRealUrl: " + pathForRealUrl);
		this.headers.setLocation(URI.create(pathForRealUrl));
		
		return new ResponseEntity<>(this.headers, HttpStatus.MOVED_PERMANENTLY);

	}

	@GetMapping(value = "/statistic/{accountId}", produces = { "application/json" })
	public ResponseEntity<String> getStatistic(HttpServletResponse response, @PathVariable("accountId") String accountId)
			throws IOException {
		if (userService.isLoginUser(accountId)) {
			User user = userService.findUserByAccountId(accountId);

			return new ResponseEntity<String>(JSONMessageService.getForStatistic(user),HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(HttpStatus.METHOD_NOT_ALLOWED);
	}

}
