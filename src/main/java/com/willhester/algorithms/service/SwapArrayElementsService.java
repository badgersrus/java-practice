package com.willhester.algorithms.service;

public interface SwapArrayElementsService<T> {
	
	/**
	 * For a generic array, the elements of two 
	 * neighbouring indexes will be swapped.
	 * 
	 * @param array. The array to be operated on
	 * @param index. The given index to swap with a 
	 * 	neighbouring element
	 */
	public void swapNeighbouringElements(T[] array, int index);
	
	/**
	 * For a generic array, swap two elements at 
	 * their specified indices. 
	 * 
	 * @param array. The array to be operated on
	 * @param index1. The index of one element to
	 * 	be swapped
	 * @param index2. The index of second element
	 * 	to be swapped
	 */
	public void swapElements(T[] array, int index1, int index2);
}
