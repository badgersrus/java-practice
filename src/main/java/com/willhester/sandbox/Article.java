package com.willhester.sandbox;

import java.util.List;
import java.util.Objects;

class Article {

private final long id;
	private String title;
	private String author;
	private List<String> tags;

	Article(long id) {
		this.id = id;
	}
	
	Article(long id, String title, String author, List<String> tags) {
		this.id = id;
	  this.title = title;
	  this.author = author;
	  this.tags = tags;
	}
	

	public String getTitle() {
	    return title;
	}

	
	public String getAuthor() {
	    return author;
	}

	
	public List<String> getTags() {
	    return tags;
	}

	
	public long getId() {
		return id;
	}
}
