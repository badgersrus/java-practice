package com.willhester.babylon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * <p>Verify whether a string has matched3 brackets or not</p>
 * <p>Consecutive square brackets are not allowed.</p>
 * 
 * @author WillHester
 */
public class BracketMatching {

	@Test(expected=IllegalArgumentException.class)
	public void testNoBracket() {
		String string = new String("!@#$%");
		isBracketMatched(string);
	}
	
	
  @Test
  public void testCorrectMatch() {
    String string = new String("{aa[bb(cc)]}");
    assertTrue(isBracketMatched(string));
  }

  
  @Test
  public void testFalseMatch() {
    String string = new String("{[(])}");
    assertFalse(isBracketMatched(string));
  }

  
  @Test
  public void testSquareBracketFalse() {
    String string = new String("[aaa[bbb]]");
    assertFalse(isBracketMatched(string));
  }


  public boolean isBracketMatched(String s){

    String bracketsOnlyString = s.chars().boxed()
      .map(i -> Character.toString(Character.toChars(i)[0]))
      .filter(i -> isBracketCharacter(i))
      .collect(Collectors.joining(""));

    if (bracketsOnlyString.isBlank()) 
    	throw new IllegalArgumentException("The string does not contain any brackets.");
    
    //Better performance than stack in single threaded environment
    Deque<Character> deque = new ArrayDeque<>();
   
    char characters[] = bracketsOnlyString.toCharArray();

    for (int i = 1; i < characters.length-1; i++) {
      // Early return for consecutive square brackets
      if (characters[i] == '[' && characters[i-1] == '[')
          return false;
        else if (characters[i]=='(')
          deque.push(')');
        else if(characters[i]=='{')
          deque.push('}');
        else if(characters[i]=='[')
          deque.push(']');
        else if (deque.isEmpty() ||  deque.pop() != characters[i])
           return false;
    }
    return deque.isEmpty();
  }

  
  private boolean isBracketCharacter(String s) {
    boolean isBracket = false;
    for(char c: s.toCharArray()){
      if (c == '(' || c == ')' ||
          c == '{' || c == '}' ||
          c == '[' || c == ']' ) {
        isBracket = true;
      } else {
        isBracket = false;
      }
    }
    return isBracket;
  }
}