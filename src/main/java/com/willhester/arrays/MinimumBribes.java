package com.willhester.arrays;

import static java.lang.Math.max;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

/**
 * <p>Consider a line where each person is assigned a number in ascending
 * order with increments of 1.</p>
 * 
 * <p>Calculate the minimum number of swaps that could've taken place 
 * when given the ed order of the line.</p>
 * 
 * <p>Each person can only swap with the two people in front of them. 
 * If they have jumped the second person then this is not allowed.</p>
 * 
 * @author WillHester
 */
public class MinimumBribes {
	
	@Test 
	public void testMinimumBribes() {
		int[] result = {2,1,5,3,4};
		
		assertThat(minimumBribes(result), equalTo(3));
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testMinimumBribesIllegal() {
		int[] result 	 = {2,5,1,3,4};
		
		minimumBribes(result);
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testMinimumBribesIllegalLarge() {
		int[] result = {5,1,2,3,7,8,6,4};

		minimumBribes(result);
	}

	
	@Test 
	public void testMinimumBribesLarges() {
		int[] result = {1,2,5,3,7,8,6,4};
		
		assertThat(minimumBribes(result), equalTo(7));
	}
	
	// Not optimised
	private Integer minimumBribes(int[] result) {
		int n = result.length;
		int bribes = 0;
		for (int i = n-1; i >=0; i--) {
			if (result[i] - (i+1) > 2) {
				throw new IllegalArgumentException("Slow down buckaroo.");
			} 
			for(int j = max(0, result[i]-2); j<i; j++) {
				if (result[j] > result[i]) {
					bribes++;
				}
			}
		}
		return bribes;
	}
}
