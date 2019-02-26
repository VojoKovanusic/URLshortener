package com.shortener.url.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shortener.url.model.User;
import com.shortener.url.util.MyUtillityClass;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	List<User> users;

	@Override
	public User saveUser(User user) {
		String password = MyUtillityClass.getPasswordHash(user.getPassword());
		user.setPassword(password);
		user.setEnabled(true);
		users.add(user);
		return user;
	}

	@Override
	public List<User> getUsers() {
		return this.users;
	}

	@Override
	public User findByAccountId(String accountId) {

		User user = this.users.stream()
				.filter(u -> u.getAccountId().equals(accountId))
				.findFirst().orElse(null);
		
		return user;

	}

	@Override
	public boolean isUserExist(User user) {

		return this.users.stream().anyMatch(u -> u.getAccountId().equals(user.getAccountId()));
	}

	@Override
	public boolean isLoginUser(String accountId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String currentlyLoggingID = ((UserDetails) principal).getUsername();
			if (accountId.equals(currentlyLoggingID))
				return true;
		}
		return false;
	}
}