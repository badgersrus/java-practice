package com.willhester.hashmaps;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;
public class SubStringMatch {

  @Test
  public void testPositive() {
    String s1 = new String("hello");
    String s2 = new String("world");

    assertTrue(hasCommonSubstring(s1, s2));
  }


  @Test
  public void testNegative() {
     String s1 = new String("hea");
     String s2 = new String("bzkloiu");

     assertFalse(hasCommonSubstring(s1, s2));
  }


  @Test
  public void testSpaces() {
    String s1 = new String("he went to -238the park");
    String s2 = new String("what you reckon lad");

    assertTrue(hasCommonSubstring(s1, s2));
  }


  static boolean hasCommonSubstring(String string1, String string2) {
    String filteredString1 = string1.chars().boxed()
        .map(i -> Character.toString(Character.toChars(i)[0]))
        .filter(p -> !p.equals(" "))
        .collect(joining(""));

    String filteredString2 = string2.chars().boxed()
        .map(i -> Character.toString(Character.toChars(i)[0]))
        .filter(p -> !p.equals(" "))
        .collect(joining(""));

    System.out.println(filteredString1);
    System.out.println(filteredString2);

    HashMap<Character, Integer> m = new HashMap<>();

    for(char c: filteredString1.toCharArray()) {
      m.put(c, m.getOrDefault(c, 0) + 1);
    }

    boolean isMatch = false;
    for(char c : filteredString2.toCharArray()) {
      if(m.getOrDefault(c, 0) != 0) {
        isMatch = true;
        m.put(c, m.get(c) - 1);
      }
    }
    return isMatch;
  }
}