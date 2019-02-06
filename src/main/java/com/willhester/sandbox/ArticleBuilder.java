package com.willhester.sandbox;

import java.util.ArrayList;
import java.util.List;

class ArticleBuilder {
		
	private final long id;
	private String title;
	private String author;
	private List<String> tags = new ArrayList<>();

	ArticleBuilder(long id) {
		this.id = id;
	}

	
	ArticleBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	

	ArticleBuilder withAuthor(String author) {
		this.author = author;
		return this;
	}
	
	
	ArticleBuilder withTags(String ... tags) {
		for (String tag: tags) {
			this.tags.add(tag);
		}
		return this;
	}
	 
	
	Article build() {
	 return new Article(id, title, author, tags);
	}
}
