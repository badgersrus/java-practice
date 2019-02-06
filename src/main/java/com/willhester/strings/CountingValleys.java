package com.willhester.strings;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

/**
 * Warm up challenge two.
 *  
 * @author WillHester
 */
public class CountingValleys {

	@Test
	public void test() {
		int steps = 8;
		String directions = "UDDDUDUU";
		
		assertThat(countingValleys(steps, directions), is(1));
	}
	
	
	/**
	 * Calculates the total number of valleys crossed by the hiker
	 * if we consider anything 2D less than sea level.
	 * 
	 * @param numberOfSteps
	 * @param direction
	 * @return the number of valleys traversed
	 */
	static int countingValleys(int numberOfSteps, String direction) {
		int level = 0;
		int valleyCount = 0;
		for (char c: direction.toCharArray()) {
			if (c == 'U') level++;
			if (c == 'D') level--;
			
			// Only care about the times we're coming up to sea level
			if (level == 0 && c == 'U') {
				valleyCount++;
			}
		}
		return valleyCount;
    }
}
