package com.shortener.url.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.url.model.User;
import com.shortener.url.service.MessageService;
import com.shortener.url.service.UserService;
import com.shortener.url.util.Util;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@Slf4j
 class RegistrationUserController {

	private UserService userService;
	private  MessageService messageService;
	private Util util;

	@Autowired
	public RegistrationUserController(UserService userService,MessageService messageService,Util util) {
		this.userService = userService;
		this.messageService=messageService;
		this.util=util;

	}

	@PostMapping(value = "/account", produces = { "application/json" })
	public ResponseEntity<String> registration(@Valid @RequestBody User user) {

		if (!this.userService.isUserExist(user)) {

			String password = this.util.generateString(8);
			user.setPassword(password);
			userService.saveUser(user);

			log.info("{} is successfully registered", user.getAccountId());

			return new ResponseEntity<>(messageService.getApprovedAccount(password), HttpStatus.CREATED);
		}

		return new ResponseEntity<>(messageService.getRejectedAccount(), HttpStatus.CONFLICT);
	}
 
}
