package com.shortener.url.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.url.model.User;
import com.shortener.url.service.JSONMessageService;
import com.shortener.url.service.RandomService;
import com.shortener.url.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class RegistrationController {
	@Autowired
	private JSONObject json;
	private UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/account", produces = { "application/json" })
	public String registration(@Valid @RequestBody User user) {
		if (!this.userService.isUserExist(user)) {
			String password = RandomService.generateRandomString(8);
			user.setPassword(password);
			userService.saveUser(user);
			
			return JSONMessageService.getApprovedAccount(password);
		}

		return JSONMessageService.getRejectedAccount();
	}
 

	@GetMapping("/users")
	public List<User> test() {
		return userService.getUsers();
	}
}
