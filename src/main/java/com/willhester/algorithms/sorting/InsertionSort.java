package com.willhester.algorithms.sorting;

import com.willhester.algorithms.service.SwapIntegerArrayElements;

/**<ul>
 * <li>Non-recursive</li>
 * <li>Useful for small data sets</li> 
 * <li>Starts from the left, check if there is item greater than current index to the left</li>
 * </ul>
 * 
 * @author WillHester
 */
public class InsertionSort {

	SwapIntegerArrayElements swapService = new SwapIntegerArrayElements();
	
	public Integer[] insertionSort(Integer[] array) {
		int i, j, currentValue = 0;

		for (i=1; i < array.length; i++) {
			currentValue = array[i];
			j=i-1;
		
			while(j >=0 && currentValue < array[j]) {
				swapService.swapNeighbouringElements(array, j);
				j--;
			}
		}
		return array;
		}
}
