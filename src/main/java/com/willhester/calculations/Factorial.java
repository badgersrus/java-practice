package com.willhester.calculations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.IntStream;

import org.junit.Test;

public class Factorial {

	@Test
	public void testFactorial() {
	  int i = 3;
	  int j = 4;
	  int k = 8;
	  
	  assertThat(factorial(i), equalTo(6));
	  assertThat(factorial(j), equalTo(24));
	  assertThat(factorialStream(k), equalTo(40320));
	}
	
	
	static int factorial(int n) {
	  return n == 1 ? 1 : n*factorial(n-1);
	}
	
	static int factorialStream(int n) {
	return IntStream.rangeClosed(1, n)
					.reduce((n1, n2) -> n1 * n2)
					.getAsInt();
	}
}
