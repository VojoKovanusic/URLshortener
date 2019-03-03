package com.shortener.url.model;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@Getter @Setter
@NoArgsConstructor
public class Url {
	@NotNull
	private String realUrl;
	private String shortUrl;
	private String redirectType = "302";
	private long numberOfVisits;
 

}
