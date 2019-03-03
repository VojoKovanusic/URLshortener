package com.shortener.url.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.shortener.url.model.User;
@Configuration
public class CreateBeans {
	@Bean
	List<User> users() {
		return new ArrayList<>();
	}
 
	@Bean
	HttpHeaders header() {
		return new HttpHeaders();
	}
 
	 
}
