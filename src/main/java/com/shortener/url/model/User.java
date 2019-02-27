package com.shortener.url.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor 
public class User implements Serializable {

	private static final long serialVersionUID = -6945475342210470677L;

	private Long id;
	@NotNull
	@Size(min=3, message="Name should have atleast 3 characters")
	private String accountId ;
    
	private Map<String , Url > myUrlList=new HashMap<>();
	
    private String firstName;

	private String password;

	private boolean enabled;

	private String role;	
	


}
