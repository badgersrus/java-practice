package com.willhester.hashmaps;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HashMapFluentCreation {

	@Test
	public void testJava9() {
		// This will only work for 10 elements
		// Immutable
		Map<Integer, String> map = Map.of(
				1, "string1", 
				2, "string2", 
				3, "string3", 
				4, "string4", 
				5, "string5");
		
		// Immutable 
		Map<Integer, String> unlimitedMap = Map.ofEntries(
				Map.entry(1, "string1"),
				Map.entry(2, "string2"), 
				Map.entry(3, "string3"), 
				Map.entry(4, "string4"), 
				Map.entry(5, "string5"));
		
		// Mutable map 
		Map<Integer, String> mutableMap = new HashMap<>(Map.ofEntries(Map.entry(1, "string1")));
	}
}
