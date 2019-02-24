package com.shortener.url.service;

import java.util.List;


import com.shortener.url.model.User;
 
public interface UserService {
   
	User save(User user);

	List<User> getUsers();

	User findByAccountId(String str);

}
