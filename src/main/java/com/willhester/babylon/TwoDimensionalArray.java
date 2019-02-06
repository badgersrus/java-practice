package com.willhester.babylon;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TwoDimensionalArray {

	
	@Test
	public void test() {
		List<List<Integer>> m = createMatrix();
		
		List<Integer> t = createFourElementArray(1,2,6,8);
		List<Integer> result = createFourElementArray(2,0,1,1);
		
		countGroups(m,t);
		
//		assertThat(countGroups(m,t), is(result));
	}
	

	/**
   * Complete the 'countGroups' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts following parameters:
   *  1. 2D_INTEGER_ARRAY m
   *  2. INTEGER_ARRAY t
   */

  public static List<Integer> countGroups(List<List<Integer>> m, List<Integer> t) {
		List<Integer> groups = new ArrayList<>();
  	int i, j, k, l, count = 0;
  	for (i = 1; i < m.size(); i++) {
  		for (j = 1; j < m.size(); j++) {
  			int x = m.get(i-1).get(j-1) - m.get(i).get(j-1);
  			int y = m.get(j-1).get(i-1) - m.get(j-1).get(i);
  			if(Math.abs(x) + Math.abs(y) == 1) {
  				count++;
  			} else {
  				if(count > 0 ) {
  					groups.add(count);
  				}
  				count = 0;
  			}
  		}
  	}
  	
  	System.out.println(groups);
  	
//  	List<Integer> result = new ArrayList<>();
//  	for(k = 0; k < t.size(); k++) {
//  		result = 
//  				groups.stream().filter(p -> p == k)
//  				
//  	}
  	
  	return t;
  // Write your code here

  }
  
  
  
  private List<Integer> createFourElementArray(int i, int j, int k, int l) {
  	List<Integer> t = new ArrayList<>();
  	t.add(i);
  	t.add(j);
  	t.add(k);
  	t.add(l);
  	return t;
  }
  
  
  private List<List<Integer>> createMatrix() {
  	List<List<Integer>> m = new ArrayList<>();
  	List<Integer> row1 = createRowEntry(1,1,1,1,1,1);
  	List<Integer> row2 = createRowEntry(1,1,0,0,0,0);
  	List<Integer> row3 = createRowEntry(0,0,0,1,1,1);
  	List<Integer> row4 = createRowEntry(0,0,0,1,1,1);
  	List<Integer> row5 = createRowEntry(0,0,1,0,0,0);
  	List<Integer> row6 = createRowEntry(1,0,0,0,0,0);
  	m.add(row1);
  	m.add(row2);
  	m.add(row3);
  	m.add(row4);
  	m.add(row5);
  	m.add(row6);
  	return m;
  }
  
  
  private List<Integer> createRowEntry(int i, int j, int k, int l, int m, int n) {
  	List<Integer> rowEntry = new ArrayList<>();
  	rowEntry.add(i);
  	rowEntry.add(j);
  	rowEntry.add(k);
  	rowEntry.add(l);
  	rowEntry.add(m);
  	rowEntry.add(n);
  	return rowEntry;
  }
}
