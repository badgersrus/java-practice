package com.willhester.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class MaxSumTwoDimensionalArray {

	@Test
	public void testMaxSum2DArray() {
	  int[][] arr =
	   {{1, 1, 1, 0, 0, 0},
	     {0, 1, 0, 0, 0, 0},
	     {1, 1, 1, 0, 0, 0},
	     {0, 0, 2, 4, 4, 0},
	     {0, 0, 0, 2, 0, 0},
	     {0, 0, 1, 2, 4, 0,}};
	
	  assertThat(hourglassSum(arr), is(19));
	}
	
	static int hourglassSum(int[][] arr) {
	  List<Integer> sum = new ArrayList<>();
	  for (int y = 0; y < 4; y++) {
	    for (int x=0; x < 4; x++) {
	      sum.add(arr[y][x]       + arr[y][x+1]      + arr[y][x+2]
	                                        + arr[y+1][x+1]
	                   +arr[y+2][x]  + arr[y+2][x+1]  + arr[y+2][x+2]);
	    }
	  }
	  return Collections.max(sum);
	}
}
