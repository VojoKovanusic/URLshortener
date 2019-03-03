package com.shortener.url.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.url.model.User;
import com.shortener.url.service.JSONMessageService;
import com.shortener.url.util.Util;
import com.shortener.url.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@Slf4j
public class RegistrationController {

	private UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/account", produces = { "application/json" })
	public ResponseEntity<String> registration(@Valid @RequestBody User user) {
		if (!this.userService.isUserExist(user)) {

			String password = Util.generateString(8);
			user.setPassword(password);
			userService.saveUser(user);

			log.info("{} is successfully registered", user);

			return new ResponseEntity<>(JSONMessageService.getApprovedAccount(password), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(JSONMessageService.getRejectedAccount(), HttpStatus.CONFLICT);
	}

	@GetMapping("/users")
	public List<User> test() {
		return userService.getUsers();
	}
}
