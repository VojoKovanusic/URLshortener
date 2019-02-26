package com.shortener.url.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@Getter @Setter
@NoArgsConstructor
public class Url {
	
	private String realUrl;
	private String shortUrl;
	private long numberOfVisits;
	@Override
	public String toString() {
		return "{" + realUrl +":" + numberOfVisits + "}";
	}

}
