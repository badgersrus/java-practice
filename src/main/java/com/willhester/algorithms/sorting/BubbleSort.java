package com.willhester.algorithms.sorting;

import com.willhester.algorithms.service.SwapIntegerArrayElements;

/**
 * <ul>
 * <li>Non-recursive</li>
 * <li>Good for small data sets</li>
 * <li>Larger values are bumped to the right of the array</li>
 * 
 * @author WillHester
 */
public class BubbleSort {
	
	SwapIntegerArrayElements swapService = new SwapIntegerArrayElements();
	
	public Integer[] bubbleSort(Integer[] array) {
	
		int i,j = 0;

		for(i=0; i < array.length - 1; i++) {
			// shorten by i each time element is found
			for (j=0; j < array.length - 1 - i; j++) { 
				// If the element on the left is greater than the rig ht then swap
				if (array[j] > array[j+1]) {
					swapService.swapNeighbouringElements(array, j);
				}
			}
	}
	return array;
	}
}
