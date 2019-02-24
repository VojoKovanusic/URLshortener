package com.shortener.url.model;

import java.io.Serializable;
import java.util.ArrayList;
 

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
 
@NoArgsConstructor 
public class User implements Serializable {

	private static final long serialVersionUID = -6945475342210470677L;
	
	 
	private Long id;
	
	private String accountId ;
 
	private String firstName;
	
 
	private String lastName;
	
	 
	private String email;
	
 
	private String password;
	
	 
	private boolean enabled;
	
 
	private String role;	
	
 
	private String phoneNumber;


}
