package com.willhester.algorithms.sorting;

import java.util.Random;

import com.willhester.algorithms.service.SwapIntegerArrayElements;

/**<ul>
 * <li>Recursive</li>
 * <li>Very efficient for large data sets</li> 
 * <li>Performance depends on the pivot selection</li>
 * <li>Divide into partitions around a pivot, if indexes meet then swap left with right</li>
 * <li>Randomly chosen pivots ensure O(Nlog(N)</li>
 * </ul>
 * 
 * @author WillHester
 */
public class QuickSort {
	
	SwapIntegerArrayElements swapService = new SwapIntegerArrayElements();

  public void quickSort(Integer[] A) {
    quickSort(A, 0, A.length - 1);
  } 

  
  private void quickSort(Integer[] A, int low, int high) {
  	if (low < high + 1) {
  		int p = partition(A, low, high);
  		// Left partition
  		quickSort(A, low, p-1);
  		// Right partition
  		quickSort(A, p+1, high);
  	}
  }
  
  
  /**
  * Returns random pivot index between low and high inclusive
  */ 
  private int getPivot(int low, int high) {
  	Random rand = new Random();
  	return rand.nextInt((high-low)+1) + low;
  }
  
  
  /**
   * Moves all n < pivot to the left of the pivot and all n > pivot 
   * to the right, then returns pivot index.
   */
  private int partition(Integer[] array, int low, int high) {
  
  	swapService.swapElements(array, low, getPivot(low, high));
  	int border = low + 1;
 
  	for (int i = border; i <= high; i++) {
  		if (array[i] < array[low]) {
  			swapService.swapElements(array, i, border++);
  		}
  	}		
  	swapService.swapElements(array, low, border-1);
  return border - 1;
  }
}
