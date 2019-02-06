package com.willhester.algorithms.sorting;


/**<ul>
 * <li>Non-recursive</li>
 * <li>Good for small data sets</li> 
 * <li>Find smallest item and move to first position</li>
 * <li>Then while i+1 < n, repeat</li>
 * </ul>
 * 
 * @author WillHester
 */
public class SelectionSort {
	
	public int[] selectionSort(int[] list) {
		int i, j, minValue, minIndex, temp = 0;

		//Outer loop
		for (i = 0; i < list.length; i++) {
			// Initialise to first unsorted item each time through the loop
			minValue = list[i];
			minIndex = i;

			// Inner loop at first unsorted item and goes to last item 
			for (j = i; j <list.length; j++) {
				if (list[j] < minValue) {
					minValue = list[j];
					minIndex = j;
				}
			}
		
			// Check if the minValue was first value. Swap if not
			if (minValue < list[i]) {
				temp = list[i];
				list[i] = list[minIndex];
				list[minIndex] = temp;
			}
		}
		return list;
		}
}
