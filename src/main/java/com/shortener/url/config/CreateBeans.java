package com.shortener.url.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.shortener.url.model.Url;
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
