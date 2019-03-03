package com.shortener.url.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.url.service.MessageService;

@RestController
public class HelpController {

	@GetMapping(value = "/help")
	public ResponseEntity<String> getStatistic() {
	
		String message = MessageService.getHelpPageInfo();
		
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}



}
