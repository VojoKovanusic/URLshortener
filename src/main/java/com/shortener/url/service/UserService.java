package com.shortener.url.service;

import java.util.List;


import com.shortener.url.model.User;
 
public interface UserService {
   
	User saveUser(User user);

	List<User> getUsers();

	User findUserByAccountId(String str);

	boolean isUserExist(User user);

	boolean isLoginUser(String accountId);

	User getCurrentUser();
	
	String getCurrentlyLoggingID();

}
