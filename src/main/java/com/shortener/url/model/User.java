package com.shortener.url.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

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
	private String accountId ;
    
	private Map<String , Url > myUrlList=new HashMap<>();

	private String password;

	private String role;


}
