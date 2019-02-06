package com.willhester.algorithms.service;

/**
 * Integer implementation of the {@link SwapArrayElementsService} 
 * interface. 
 * 
 * @author WillHester
 */
public class SwapIntegerArrayElements implements SwapArrayElementsService<Integer> {

	@Override
	public void swapNeighbouringElements(Integer[] array, int i) {
		int temp = array[i];
		array[i] = array[i +1];
		array[i +1] = temp;
	}

	
	@Override
	public void swapElements(Integer[] array, int index1, int index2) {
		int temp = array[index1];
  	array[index1] = array[index2];
  	array[index2] = temp;
	}
}
