package com.willhester.arrays;

import static java.lang.Math.max;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;


public class ArrayPractice {
	
	@Test 
	public void testMergeSort() {
		int[] inputEasy = {4,3,2,1};
		int[] inputMed = {2,3,4,1,5};
		int[] inputHard = {1,3,5,2,4,6,7};
		
		assertThat(minimumSwaps(inputEasy), equalTo(3));
		assertThat(minimumSwaps(inputMed), equalTo(3));
		assertThat(minimumSwaps(inputHard), equalTo(3));
	}
	
	private Integer minimumSwaps(int[] input) {
		// TODO Auto-generated method stub
		return 1;
	}

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
	
	
	private Integer minimumBribes(int[] result) {
		int n = result.length;
		int bribes = 0;
		for (int i = n-1; i >=0; i--) {
			if (result[i] - (i+1) > 2) {
				throw new IllegalArgumentException("Too chaotic");
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
