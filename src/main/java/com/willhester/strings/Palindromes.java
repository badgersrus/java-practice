package com.willhester.strings;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


import java.util.LinkedList;

import org.junit.Test;

public class Palindromes {
	
	@Test(expected=IllegalArgumentException.class)
	public void testShortPalindrome() {
		String str = "ab";
		
		LinkedList<Palindrome> palindromes = findThreeLongestPalindromes(str);
		assertTrue(palindromes.isEmpty());
	}
	
	
	@Test
	public void testLongPalindrom() {
		String str = "sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop";
		
		LinkedList<Palindrome> result = findThreeLongestPalindromes(str);
		assertThat(result.size(), equalTo(3));
		assertThat(result.get(0).getPalindrome(), equalTo("hijkllkjih"));
		assertThat(result.get(0).getStartingIndex(), equalTo(23));
		assertThat(result.get(0).getLength(), equalTo(10));
		
		assertThat(result.get(1).getPalindrome(), equalTo("defggfed"));
		assertThat(result.get(1).getStartingIndex(), equalTo(13));
		assertThat(result.get(1).getLength(), equalTo(8));
		
		assertThat(result.get(2).getPalindrome(), equalTo("abccba"));
		assertThat(result.get(2).getStartingIndex(), equalTo(5));
		assertThat(result.get(2).getLength(), equalTo(6));
	}

	
	
	private LinkedList<Palindrome> findThreeLongestPalindromes(String str) {
		char[] chars = str.toCharArray();
		if (chars.length < 3) {
			throw new IllegalArgumentException("This string is too short to find 3 unique palindromes.");
		}
		
		//LinkedList to ensure order insertion order
		LinkedList<Palindrome> pals = new LinkedList<>();
		
		Palindrome pal1 = findLongestPalindrome(str);
		String trimmed = str.replace(pal1.getPalindrome(), "");
		
		Palindrome pal2 = findLongestPalindrome(trimmed);
		String trimmed2 =  trimmed.replace(pal2.getPalindrome(), "");
		
		Palindrome pal3 = findLongestPalindrome(trimmed2);
		
		pals.add(pal1);
		pals.add(pal2);
		pals.add(pal3);
		return pals;
	}


	private Palindrome findLongestPalindrome(String str) {
		int maxLength = 1;
		int beginningIndex = 0;
		int length = str.toCharArray().length;
		int low, high; 
		
		for(int i = 1; i < length; i++ ) {
			
			// Longest even palindrome
			low = i - 1; 
      high = i; 
      
      while (low >= 0 && high < length && str.charAt(low) == str.charAt(high)) { 
      	if (high - low + 1 > maxLength) { 
      		beginningIndex = low; 
      		maxLength = high - low + 1; 
      	} 
      --low; 
      ++high; 
      }
      
      //Longest odd palindrome
      low = i - 1; 
      high = i + 1; 
      
      while (low >= 0 && 
      		   high < length && 
      		   str.charAt(low) == str.charAt(high)) { 
       if (high - low + 1 > maxLength) { 
         beginningIndex = low; 
         maxLength = high - low + 1; 
       } 
      --low; 
      ++high; 
      } 
		}
		
		PalindromeBuilder builder = new PalindromeBuilder();
		String palString = str.substring(beginningIndex, beginningIndex + maxLength);
		Palindrome pal = builder.withString(palString).withIndex(beginningIndex).withLength(maxLength).build();
		return pal;
	}
}



class Palindrome {
	private String palindrome;
	private int startingIndex;
	private int length;
	
	Palindrome(String palindrome, int startingIndex, int length) {
		this.palindrome=palindrome;
		this.startingIndex=startingIndex;
		this.length=length;
	}
	
	public String getPalindrome() {
		return palindrome;
	}

	public void setPalindrome(String palindrome) {
		this.palindrome = palindrome;
	}

	public int getStartingIndex() {
		return startingIndex;
	}

	public void setStartingIndex(int startingIndex) {
		this.startingIndex = startingIndex;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "Text: " + getPalindrome() + ", Index: " + getStartingIndex() + ", Length: " + getLength();
	}
}

class PalindromeBuilder {
	private String string;
	private int index;
	private int length;
	

	public PalindromeBuilder withString(String string) {
		this.string = string;
		return this;
	}
	
	public PalindromeBuilder withIndex(int index) {
		this.index = index;
		return this;
	}
	
	public PalindromeBuilder withLength(int length) {
		this.length = length;
		return this;
	}
	
	public Palindrome build() {
		return new Palindrome(string, index, length);
	}
}
