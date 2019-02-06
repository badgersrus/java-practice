package com.willhester.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortFunctional {

	 public static List<Integer> merge(List<Integer> first, List<Integer> second, List<Integer> accumulator) {
     if (first.isEmpty()) {
         accumulator.addAll(second);
     }
     else if (second.isEmpty()) {
         accumulator.addAll(first);
     }
     else {
         if (first.get(0) <= second.get(0)) {
             accumulator.add(first.get(0));
             return merge(first.subList(1, first.size()), second, accumulator);
         }
         else {
             accumulator.add(second.get(0));
             return merge(first, second.subList(1, second.size()), accumulator);
         }
     }
     return accumulator;
 }

 public static List<Integer> mergeSort(List<Integer> list) {
     int mid = list.size()/2;
     if (mid == 0)
         return list;
     return merge(
             mergeSort(list.subList(0, mid)), 
             mergeSort(list.subList(mid, list.size())), 
             new ArrayList<Integer>());
 }

 public static void main(String[] args) {
     mergeSort(Arrays.asList(34, 3, 21, 6, 0, 32)).forEach(System.out::println);
 }
}
