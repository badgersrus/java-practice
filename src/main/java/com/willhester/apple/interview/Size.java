package com.willhester.apple.interview;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Size {
		
   public enum Sex {MALE, FEMALE}

  public static void printSize(Map<Sex, Sex> map) {

    map.put(Sex.MALE, Sex.FEMALE);
    map.put(Sex.FEMALE,Sex.MALE);
    map.put(Sex.MALE, Sex.MALE);
    map.put(Sex.FEMALE, Sex.FEMALE);

    Set<Map.Entry<Sex, Sex>> set =
               new HashSet<Map.Entry<Sex, Sex>> (map.entrySet());
    System.out.println(set.size());
  }
}