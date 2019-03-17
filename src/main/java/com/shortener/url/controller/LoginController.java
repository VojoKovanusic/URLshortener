package com.shortener.url.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.url.config.UnauthorizedException;
import com.shortener.url.domain.UserDTO;
import com.shortener.url.model.User;
import com.shortener.url.security.JwtTokenUtil;
import com.shortener.url.security.JwtUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
class LoginController {

	@Value("${jwt.header}")
	private String tokenHeader;
	private AuthenticationManager authenticationManager;
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	public LoginController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {

		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@PostMapping(value = "/login", produces = {"application/json" })
	public ResponseEntity<UserDTO> userLogin(@RequestBody User user ) {
		
		try {

			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getAccountId(), user.getPassword()));

			final JwtUser userDetails = (JwtUser) authentication.getPrincipal();

			SecurityContextHolder.getContext().setAuthentication(authentication);

			final String token = jwtTokenUtil.generateToken(userDetails);
   
	            UserDTO userDTO = UserDTO.builder()
	                .user(userDetails.getUser())
	                .token(token)
	                .build();
	            log.info("Token: {}",userDTO.getToken());
	            return new ResponseEntity<>(userDTO, HttpStatus.OK);

		} catch (Exception e) {
			throw new UnauthorizedException(" Message" + e.getMessage());
		}

	}

}
