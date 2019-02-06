package com.willhester.babylon;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * <p>Given an array of _bad numbers_ and a given range, determine the longest
 * segment of consecutive integers within that inclusive range that doesn't
 * contain a _bad number_.</p>
 * 
 * @author WillHester
 */
public class GoodSegment {
	
	@Test
	public void testLastElementInclusive() {
		int l = 1;
		int r = 10;
		
		List<Integer> badNumbers = 
				new ArrayList<Integer>(Arrays.asList(2,4,5));
		
		assertThat(goodSegment(badNumbers, l, r), equalTo(5));
	}

	@Test
	public void testFirstElementInclusive() {
		int l = 1;
		int r = 100;
		
		List<Integer> badNumbers = 
				new ArrayList<Integer>(Arrays.asList(50,55,60));
		
		assertThat(goodSegment(badNumbers, l, r), equalTo(49));
	}
	
	@Test
	public void testNonInclusive() {
		int l = 3;
		int r = 48;
		
		List<Integer> badNumbers = 
				new ArrayList<Integer>(Arrays.asList(37,7,22,15,49,60,1,100));
		
		assertThat(goodSegment(badNumbers, l, r), equalTo(14));
	}

	
	/*
   * Complete the 'goodSegment' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY badNumbers
   *  2. INTEGER l
   *  3. INTEGER r
   */
	 public static int goodSegment(List<Integer> badNumbers, int l, int r) {

     // Include the limits in the list of numbers to evaluate
     badNumbers.add(Integer.valueOf(l));
     badNumbers.add(Integer.valueOf(r));

     ArrayList<Integer> badNumbersReduced = filterElementsOutOfRange(badNumbers, l, r);

     int maxDifference = 0;
     int n = badNumbersReduced.size();
     boolean isRangeInclusive = false;

     for (int i = 1; i < n; i++) {
         int difference = badNumbersReduced.get(i) - badNumbersReduced.get(i - 1);
         if (difference > maxDifference) {
             maxDifference = difference;
             isRangeInclusive = false;
             // Calculate the inclusive range if the first
             // or last elements results in a max difference
             if (i == 1 || i == n-1) {
                 isRangeInclusive = true;
             }
         }
     }

     // The max range was found with the first or last
     // elements which are not 'bad' numbers. 
     if (isRangeInclusive)
         return maxDifference;
     else
         return maxDifference - 1;
 }

 private static ArrayList<Integer> filterElementsOutOfRange(List<Integer> badNumbers, int l, int r) {
     return badNumbers.stream().sorted().filter(p -> (p.intValue() >= l && p.intValue() <= r))
             .collect(Collectors.toCollection(ArrayList::new));
 }
}
