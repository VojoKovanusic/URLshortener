package com.shortener.url.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shortener.url.model.User;
import com.shortener.url.service.UserService;

 

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	private UserService  userService;
      
	 
	@Autowired
	public JwtUserDetailsServiceImpl(UserService userService) {
	 
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String str) throws UsernameNotFoundException {
		User user = userService.findByAccountId(str);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No User found with username '%s'.", str));
		} else {
			return JwtuserFactory.create(user);
		}
		
	}

}
