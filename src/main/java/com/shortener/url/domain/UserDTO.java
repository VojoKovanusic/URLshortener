package com.shortener.url.domain;

import com.shortener.url.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

	private User user;
	private String token;

}
