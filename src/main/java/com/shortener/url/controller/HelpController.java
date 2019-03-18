package com.shortener.url.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.url.service.MessageService;

@RestController
public class HelpController {
	MessageService messageService;

	public HelpController(MessageService messageService) {
		this.messageService = messageService;
	}

	@Autowired

	@GetMapping(value = "/help")
	public ResponseEntity<String> getStatistic() {


		String message = messageService.getHelpPageInfo();
		
		return new ResponseEntity<>(message, HttpStatus.OK);
	}



}
