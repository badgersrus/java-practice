package com.willhester.arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class ArrayRotations {
	
	@Test
	public void testArrayRotations() {
	int n = 4; //rotations
	int[] array = {1, 2, 3, 4, 5};
	int[] result = {5, 1, 2, 3, 4};

	rotateLeftBy(array, n);
	
	assertThat(array, equalTo(result));
	}


	private void rotateLeftBy(int[] array, int n) {
		for (int i =0; i < n; i++) {
			rotateLeftByOne(array);
		}
	}


	private void rotateLeftByOne(int[] array) {
		int n = array.length;
		int i, temp;
		temp = array[0];
		for (i = 0; i < n - 1; i++) {
			array[i] = array[i + 1];
		}
		array[n-1] = temp;
	}
}
