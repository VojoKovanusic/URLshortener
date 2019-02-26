package com.shortener.url.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@Getter @Setter
@NoArgsConstructor
public class Url {
	
	private String realUrl;
	private String shortUrl;
	private long numbeOfVisits;
	@Override
	public String toString() {
		return "{" + realUrl +":" + numbeOfVisits + "}";
	}

}