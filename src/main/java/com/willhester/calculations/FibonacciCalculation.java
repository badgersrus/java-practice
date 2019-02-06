package com.willhester.calculations;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class FibonacciCalculation {

	  @Test
	  public void testFibonacci() {
	    List<Integer> fibonacciSequence = createFibonacciList();
	    assertEquals(fibonacciSequence, fibonacciList(12));
	  }


	  public static List<Integer> fibonacciList(int number) {
	    return IntStream.rangeClosed(1, number)
	      .boxed()
	      .map(i -> fibonacciRecursion(i))
	      .collect(Collectors.toList());
	  }


	  /**
	   * Recursive formula for fibonacci sequence. Sum of the previous two numbers.
	   */
	  private static int fibonacciRecursion(int n) {
	    if (n ==1 || n ==2) return 1;
	      return fibonacciRecursion(n-1) + fibonacciRecursion(n-2);
	  }

	  private List<Integer> createFibonacciList() {
	    List<Integer> fibList = new ArrayList<>();
	    fibList.add(1);
	    fibList.add(1);
	    fibList.add(2);
	    fibList.add(3);
	    fibList.add(5);
	    fibList.add(8);
	    fibList.add(13);
	    fibList.add(21);
	    fibList.add(34);
	    fibList.add(55);
	    fibList.add(89);
	    fibList.add(144);
	    return fibList;
	  }
}
