package com.willhester.sandbox;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * I can't go no sleep. 
 * Do 
 * do  
 * do
 * do do 
 * do 
 * do do 
 * do do do do
 * 
 * @author WillHester
 */
public class LoopsToStreams {
	
	@Test 
	public void testGetFirstJavaArticle() {
		List<Article> articles = setUpTestData();
		assertThat(getFirstJavaArticleLoop(articles), is(articles.get(0)));
		assertThat(getFirstJavaArticleStream(articles).get(), is(articles.get(0)));
	}
	
	
	@Test 
	public void testGetAllJavaArticles() {
		List<Article> articles = setUpTestData();
		List<Article> result = new ArrayList<>();
		result.add(articles.get(5));
		assertThat(getAllJavaArticlesLoop(articles), is(result));
		assertThat(getAllJavaArticlesStream(articles), is(result));
	}
	
	
	@Test
	public void testGroupByAuthor() {
		List<Article> articles = setUpTestData();
		Map<String, List<Article>> authorGroups = groupByAuthorLoop(articles);
	}
	
	
	@Test
	public void testGetDistinctTags(List<Article> articles) {
		
	}
	
	
	/**
	 * 
	 */
	public Set<String> getDistinctTagsLoop(List<Article> articles) {
    Set<String> result = new HashSet<>();
    for (Article article : articles) {
        result.addAll(article.getTags());
    }
    return result;
	}
	
	/**
	 * 
	 */
	public Set<String> getDistinctTagsStream(List<Article> articles) {  
    return articles.stream()
        .flatMap(article -> article.getTags().stream())
        .collect(Collectors.toSet());
	}
	
	
	/**
	 * 
	 */
	public Map<String, List<Article>> groupByAuthorLoop(List<Article> articles) {
    Map<String, List<Article>> result = new HashMap<>();

    for (Article article : articles) {
        if (result.containsKey(article.getAuthor())) {
            result.get(article.getAuthor()).add(article);
        } else {
            ArrayList<Article> authoredArticles = new ArrayList<>();
            authoredArticles.add(article);
            result.put(article.getAuthor(), articles);
        }
    }

    return result;
	}

	
	/**
	 * 
	 */
	public Map<String, List<Article>> groupByAuthorStream(List<Article> articles) {  
    return articles.stream()
        .collect(Collectors.groupingBy(Article::getAuthor));
	}    
	
	
	/**
	 * Returns first article with "Java" tag using traditional for loop.
	 */
	private Article getFirstJavaArticleLoop(List<Article> articles) {
    for (Article article : articles) {
        if (article.getTags().contains("Java")) {
            return article;
        }
    }
    return null;
	}
	
	/**
	 * Returns first article with "Java" tag using stream if it exists.
	 * Otherwise {@link Optional#isEmpty()}.
	 */
	private Optional<Article> getFirstJavaArticleStream(List<Article> articles) {
		return articles.stream()
				.filter(a -> a.getTags().contains("Java"))
				.findFirst();
	}
	
	
	/**
	 * Returns list of all articles with "Java" tag using traditional for loop.
	 */
	private List<Article> getAllJavaArticlesLoop(List<Article> articles) {
    List<Article> result = new ArrayList<>();
    for (Article article : articles) {
        if (article.getTags().contains("Java")) {
            result.add(article);
        }
    }
    return result;
	}
	
	/**
	 * Returns list of all articles with "Java" tag using a stream.
	 */
	private List<Article> getAllJavaArticlesStream(List<Article> articles) {
	    return articles.stream()
	        .filter(article -> article.getTags().contains("Java"))
	        .collect(Collectors.toList());
	}


	
	/**
	 * Add a bunch of jokes articles to the list for testing purposes. 
	 */
	public List<Article> setUpTestData() {
		List<Article> articles = new ArrayList<>();
		Article article1 = new ArticleBuilder(1).withAuthor("JK I am Howling")
				.withTitle("Harry potter 1").withTags("Jama", "Not Java", "For test").build();
		Article article2 = new ArticleBuilder(2).withAuthor("Mrs guy")
				.withTitle("Harry potter 2").withTags("Wizrds").build();
		Article article3 = new ArticleBuilder(3).withAuthor("Gender neutral alien guy")
				.withTitle("Harry potter 3").withTags("Muggles").build();
		Article article4 = new ArticleBuilder(4).withAuthor("Dumblesnore")
				.withTitle("Harry potter 4").withTags("Waldermort").build();
		Article article5 = new ArticleBuilder(5).withAuthor("Waldermort")
				.withTitle("Harry potter 5").withTags("Dumblesnore").build();
		Article article6 = new ArticleBuilder(6).withAuthor("SkrrtMasterMC")
				.withTitle("Harry potter 6").withTags("Java").build();
		Article article7 = new ArticleBuilder(7).withAuthor("PigFeatures")
				.withTitle("Harry potter 7").withTags("Javg").build();
		Article article8 = new ArticleBuilder(8).withAuthor("OtterMouth")
				.withTitle("Harry potter 8").withTags("Python").build();
		Article article9 = new ArticleBuilder(9).withAuthor("Habberdasher")
				.withTitle("Harry potter 9").withTags("Japanese", "Chines", "Kotlin").build();
		articles.add(article1);
		articles.add(article2);
		articles.add(article3);
		articles.add(article4);
		articles.add(article5);
		articles.add(article6);
		articles.add(article7);
		articles.add(article8);
		articles.add(article9);
		return articles;
	}
}
