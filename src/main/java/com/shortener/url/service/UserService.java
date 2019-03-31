package com.shortener.url.service;

import java.util.List;


import com.shortener.url.model.User;
 
public interface UserService {
   
	User saveUser(User user);

	 User getUser(String accountId);

	User findUserByAccountId(String str);

	boolean isUserExist(User user);

	boolean isLoginUser(String accountId);

	User getCurrentUser();
	
	String getCurrentlyLoggingID();

}
