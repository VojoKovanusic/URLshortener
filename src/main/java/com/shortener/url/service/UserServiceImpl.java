package com.shortener.url.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.url.model.User;
import com.shortener.url.util.PasswordUtil;
@Service
public class UserServiceImpl implements UserService  {
   
	@Autowired
	List <User> users;
	
	@Override
	public User save(User user) {
		String password = PasswordUtil.getPasswordHash(user.getPassword());
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
		User user=
				this.users.stream()
				.filter(u->u.getAccountId().equals(accountId))
				.findFirst().orElse(null);
		
		return user;
	}

}
