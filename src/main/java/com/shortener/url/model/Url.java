package com.shortener.url.model;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	 @Min(value = 301, message = "redirectType must be 301 or 302")
	 @Max(value = 302, message = "redirectType must be 301 or 302")
	private int redirectType = 302;
	private long numberOfVisits;
 

}
