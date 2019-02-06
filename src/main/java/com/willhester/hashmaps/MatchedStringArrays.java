package com.willhester.hashmaps;

import static org.junit.Assert.assertFalse;

import java.util.Hashtable;

import org.junit.Test;

public class MatchedStringArrays {
	
	@Test
	public void testMatch() {
		String[] magazine = {"two", "times", "three", "is", "not", "four", "two"};
		String[] note = {"two", "times", "two", "is", "four"};

		assertFalse(isNoteAvailableInMagazine(magazine, note));
	}

	
	private boolean isNoteAvailableInMagazine(String[] magazine, String[] note) {
		Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
		
		for(String string: magazine) {
			hashtable.put(string, hashtable.getOrDefault(string, 0)+1);
		}
		
		for(String string: note) {
			if (hashtable.getOrDefault(string, 0) == 0) {
				return false;
			} else {
				hashtable.put(string, hashtable.get(string)-1);
			}
		}
		return true;
	}	
}
