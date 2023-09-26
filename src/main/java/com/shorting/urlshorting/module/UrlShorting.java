package com.shorting.urlshorting.module;


import org.springframework.data.mongodb.core.mapping.Document;


/*the module class to save the url in the database*/
@Document
public class UrlShorting {
	
	private String id;
	private String url;

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
