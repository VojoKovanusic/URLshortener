package com.shortener.url.controller;

import com.shortener.url.domain.Response;
import com.shortener.url.model.User;
import com.shortener.url.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

	private UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/account", produces = { "application/json" })
	public ResponseEntity<Response> registration(@RequestBody User user) {
		User dbUser = userService.save(user);
		if (dbUser != null) {
			return new ResponseEntity<>(
					new Response("{success: 'true', description: 'Your account is opened', password: " + "'xC345Fc0'}"),
					HttpStatus.OK);
		}

		return null;

	}

	@GetMapping("/users")
	public List<User> test() {
		return userService.getUsers();
	}
}
