package com.willhester.arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Hashtable;

import org.junit.Test;

//TODO change the sock ints to enums
public class SockMerchant {

	@Test
	public void test() {
		int n = 9;
		int[] socks = {10, 20, 20, 10, 10, 30, 50, 10, 20};
		
		assertThat(sockMerchant(n, socks), equalTo(3));
	}
	
	
	/**
	 * Returns the total pairs of socks from an array of
	 * sock colours.
	 * 
	 * @param totalNumberOfSocks
	 * @param arrayOfSockColours
	 * @return the total number of same coloured pairs
	 */
    static int sockMerchant(int totalNumberOfSocks, int[] arrayOfSockColours) {
    	
    	Hashtable<Integer, Integer> nameAndCount = new Hashtable<>();
    	
    	for (int arrayElement: arrayOfSockColours) {
    		Integer count = nameAndCount.get(arrayElement);    
    		
    		if (count == null) {
    			nameAndCount.put(arrayElement, 1);
    		} else {
    			//Need to use the ++count notation as this allows the increment to be returned immediately
    			nameAndCount.put(arrayElement, ++count); 
    		}
    	}
        
    	int totalPairs =0;

    	for(int i: nameAndCount.values()) {
    		totalPairs = totalPairs + i/2;
    	}
    	return totalPairs;
    }
}
