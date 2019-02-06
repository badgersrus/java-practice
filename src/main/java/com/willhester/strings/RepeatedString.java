package com.willhester.strings;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RepeatedString {

	@Test
	public void testOne() {
	  String s = new String("aba");
	  long n = 10L;

	  assertThat(repeatedString(n,s), is(7L));
	}


	@Test
	public void testTwo() {
	  String s = new String("a");
	  long n = 1000000000000L;

	  assertThat(repeatedString(n,s), is(n));
	}

	
	private static long repeatedString(long n, String s) {
	  int length = s.length();
	  long count = 0;
	  long count1 = 0;

	  for (int i = 0; i < length; i++) {
	    if (s.charAt(i) == 'a') {
	    count++;
	    }
	  }

	   for (int j = 0; j < n/length; j++) {
	     if (s.charAt(j) == 'a') {
	       count1++;
	     }
	   }
	   count = count * (n/length) + count1;
	   return count;
	}
}
