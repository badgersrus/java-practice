package com.willhester.algorithms.sorting;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

/**<ul>
 * <li>Recursive</li>
 * <li>Efficient for large data sets</li> 
 * </ul>
 * 
 * @author WillHester
 */
public class MergeSort {
	
	
	@Test
	public void positiveTest() {
		List<Integer> unsortedList = new ArrayList<Integer>(Arrays.asList(5, 1, 6, 2, 3, 4));
		 int[] array = convertListToArray(unsortedList);
		
	   int[] expected = { 1, 2, 3, 4, 5, 6 };
	   mergeSort(array, array.length);
	   assertArrayEquals(expected, array);
	}


	private int[] convertListToArray(List<Integer> unsortedList) {
		return unsortedList.stream().mapToInt(i->i).toArray();
	}

	
	public void mergeSort(int[] a, int n) {
	
		if (n == 1) return;
			
			int mid = n / 2;
			
			// Creat temp arryas for left and right
	    int[] L = new int[mid];
	    int[] R = new int[n - mid];
	 
	    // Populate temp arrays 
	    for (int i = 0; i < mid; i++) {
	        L[i] = a[i];
	    }
	    for (int i = mid; i < n; i++) {
	        R[i - mid] = a[i];
	    }
	    
			mergeSort(L, mid);
			mergeSort(a, n - mid);
			merge(a, L, R, mid, n - mid);
	}

	public static void merge(int[] a, int[] L, int[] R, int left, int right) {
		  
		int i = 0, j = 0, k = 0;
			
			while (i < left && j < right) {
				if (L[i] <= R[j]) {
					a[k++] = L[i++];
				} else {
					a[k++] = R[j++];
				}
			}

			// Copy remaining elements of L if any
			while (i < left) {
				a[k++] = L[i++];
			}
			// Copy remaining elements of R if any
			while (j < right) {
				a[k++] = R[j++];
			}
	}
}
