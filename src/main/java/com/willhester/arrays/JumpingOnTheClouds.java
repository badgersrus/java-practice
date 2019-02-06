package com.willhester.arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
public class JumpingOnTheClouds {

	@Test
	public void test() {
		int[] clouds = {0,0,0,0,1,0};
		
		assertThat(numberOfClouds(clouds), is(3));
	}

	private int numberOfClouds(int[] clouds) {
		int count = 0;
		int i =0;
		
		while (i < clouds.length-1) {
			if (i+2 < clouds.length &&clouds[i+2] != 1) {
				count++;
				i=i+2;
			} else {
				count++;
				i++;
			}
		}
		return count;
	}
}
