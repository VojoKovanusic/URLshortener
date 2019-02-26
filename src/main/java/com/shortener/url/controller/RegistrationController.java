package com.shortener.url.controller;

import com.shortener.url.domain.Response;
import com.shortener.url.model.User;
import com.shortener.url.service.UserService;
import com.shortener.url.util.MyUtillityClass;

import java.util.List;

import javax.validation.Valid;

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
	public ResponseEntity<Response> registration(@Valid @RequestBody User user) {
		if (!this.userService.isUserExist(user)) {
			String password = MyUtillityClass.generateRandomString(8);
			user.setPassword(password);
			userService.saveUser(user);
			
			return new ResponseEntity<>(new Response(
					"{success: 'true', description: 'Your account is opened', password: " + "'" + password + "'" + "}"),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(new Response("{success: 'false', description: 'Your account is not opened,account with that accountId already exist'" + "}"),
				HttpStatus.OK);

	}

	@GetMapping("/users")
	public List<User> test() {
		return userService.getUsers();
	}
}
