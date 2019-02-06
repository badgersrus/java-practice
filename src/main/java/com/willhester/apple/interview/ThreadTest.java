package com.willhester.apple.interview;

import java.util.Scanner;

public class ThreadTest { 
  public static void main(String[] args) throws InterruptedException { 
      
    final ProduceConsumer pc = new ProduceConsumer(); 
    
    // Create a thread object that calls pc.produce() 
    Thread t1 = new Thread(() -> {
      try { 
        pc.produce(); 
      } catch(InterruptedException e) { 
        e.printStackTrace(); 
      } 
    }); 
    
    // Create another thread object that calls pc.consume() 
    Thread t2 = new Thread(() -> {
      try { 
        pc.consume(); 
      } catch(InterruptedException e) { 
        e.printStackTrace(); 
      }  
    }); 
    
    // Start both threads 
    t1.start(); 
    System.out.println("t1 started");
    t2.start(); 
    System.out.println("t2 started");
    
    // t1 finishes before t2 
    t1.join(); 
    t2.join(); 
  } 
}

class ProduceConsumer { 
  // Prints a string and waits for consume() 
  public void produce()throws InterruptedException  { 
    // synchronized block ensures only one thread running at a time. 
    synchronized(this) { 
      System.out.println("producer thread running"); 
      // releases the lock on shared resource 
      wait();   
      // and waits till some other method invokes notify(). 
      System.out.println("Resumed"); 
    } 
  } 

  // Sleeps for some time and waits for a key press. After key is pressed, it notifies produce(). 
  public void consume()throws InterruptedException { 
    // this makes the produce thread to run first. 
    Thread.sleep(10); 
    Scanner s = new Scanner(System.in);   
    // synchronized block ensures only one thread running at a time. 
    synchronized(this) { 
      System.out.println("Waiting for return key."); 
      s.nextLine(); 
      System.out.println("Return key pressed"); 
      // notifies the produce thread that it 
      // can wake up. 
      notify(); 
      // Sleep 
      Thread.sleep(20); 
      System.out.println("End of consume method");
    } 
  } 
} 