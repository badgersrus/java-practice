package com.willhester.apple.interview;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.willhester.apple.interview.Size.Sex;

public class AppleJava {
	
	@Test
	public void test() {
		
	List<List<String>> list = Arrays.asList(
		  Arrays.asList("a"),
		  Arrays.asList("b"));
		System.out.println(list); // [[a],[b]]
		System.out.println(list
		  .stream()
		  .flatMap(Collection::stream)
		  .collect(Collectors.toList())); //[a, b]
	}

  /**
   * 1. What are the 2 broad categories of operations that can be performed on a Java stream
   */
  @Test
  public void testStream() {
    List<Integer> intsToFifty = IntStream.iterate(1, i ->  i+ 1)
      .limit(50)
      .boxed() // Intermediate
      .collect(Collectors.toList()); // Terminal
  }

  /**
   * 2. What are the valid garbage collection algorithms from the options below?
   */
  @Test
  public void testGarbageCollection() {
     //Serial
    // Incremental
    // Parallel Scavenge
    // G1
  }
  
  @Test
  public void testBoxing() {
  	int i = 1;
  	List<Integer> list = Arrays.asList(i);
  	HashMap<Integer, String> map = new HashMap<>(Map.of(1, "a", 2, "b", 3, "c", 4, "d", 5, "e"));
  	LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>(Map.of(1, "a", 2, "b", 3, "c", 4, "d", 5, "e"));
  	map.keySet().stream().forEach(System.out::println);
  	linkedMap.keySet().stream().forEach(System.out::println);
  }
  
  @Test
  public void testLinkedHashMap() {
  	LinkedHashMap<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);
  	map.putAll(Map.of(1, "a", 2, "b", 3, "c", 4, "d", 5, "e"));
  	
  	System.out.println(map.keySet());
  	map.get(1);
  	System.out.println("get(1): " + map.keySet());
  	map.get(2);
  	System.out.println("get(2): " + map.keySet());
  	map.get(3);
  	System.out.println("get(3): " + map.keySet());
  	map.get(4);
  	System.out.println("get(4): " + map.keySet());
  	map.get(5);
  	System.out.println("get(5): " + map.keySet());
  }

  /**
   * 3. The differences between Heap and Stack memory include?
   */
  public void testHeapStack() {
    // Stack is used for static memory allocation, Heap is for dynamic
    // Stack memory only contains local primitive variables and reference variables to objects in heap space.
    // Heap memory is used by all the parts of the application whereas stack memory is used only by one thread of execution.
    // Objects stored in the heap are globally accessible whereas stack memory can’t be accessed by other threads.
    // Stack memory management is done in LIFO manner whereas it’s more complex in Heap memory because it’s used globally.
    // Heap memory is divided into Young-Generation, Old-Generation PermGen.
    // The garbage collector does scan the stack -- to see what things in the heap are currently being used (pointed to) by things on the stack.
    // Everything on the stack is considered to be "in use." And memory used by the stack is automatically reclaimed when you return from method calls.
    // Memory management of stack space is so simple, cheap and easy that you wouldn't want garbage collection to be involved.
    // Java Heap space is used by java runtime to allocate memory to Objects and JRE classes. Whenever we create any object, it’s always created in the Heap space.
    // Garbage Collection runs on the heap memory to free the memory used by objects that doesn’t have any reference.
    // Any object created in the heap space has global access and can be referenced from anywhere of the application.
  }

  /**
   * 4. When writing a subclass of RecursiveTask, which of the following are true?
   */
  public void testRecursiveTask() {
    // If you must do multiple joins, then it really doesn't matter what order you join(). Each fork() makes the task available for any thread.
  }

  static class FactorialTask extends RecursiveTask<BigInteger> {

    private static int SEQUENTIAL_THRESHOLD = 5;
    private List<BigInteger> integerList;

    private FactorialTask (List<BigInteger> integerList) {
        this.integerList = integerList;
    }

    @Override
    protected BigInteger compute() {
      if (integerList.size() <= SEQUENTIAL_THRESHOLD) {
        return sumFactorials();
      } else {
        int middle = integerList.size() / 2;
        List<BigInteger> newList = integerList.subList(middle, integerList.size());
        integerList = integerList.subList(0, middle);
        FactorialTask task = new FactorialTask(newList);
        task.fork();
        BigInteger thisSum = this.compute();
        BigInteger thatSum = task.join();
        return thisSum.add(thatSum);
      }
    }
      private BigInteger sumFactorials () {
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger i : integerList) {
            //sum = sum.add(calculateFactorial(i));
        }
        return sum;
      }
  }


  /**
   * 5. SOLID is an acronym that stands for the following 5 design principles:
   */
  @Test
  public void testSolid() {
    // SRP – Single Responsibility Principle
    // OCP – Open/Closed Principle
    // LSP – Liskov Substitution Principle
    // ISP – Interface Segregation Principle
    // DIP – Dependency Inversion Principle
  }

  /**
   * 6. What is the output of the code below?
   */
  @Test
  public void testMethodOverride() {
    Child child = new Child();
    child.perform();
    // Child - perform
    // Method override takes precedence here
    
    // Child child = new Paraent(); wont compile
    Parent parent = new Child();
    parent.perform();
    // Child - perform
    // At runtime, the Child is created and assigned to the parent variable > dynamic polymorphism
    // If you make the perform methods final, this breaks dynamic polymorphism

  }

  class Parent {
    public void perform() {
      System.out.println("Parent - perform");
    }
  }

  class Child extends Parent {
    public void perform() {
      System.out.println("Child - perform");
    }
  }


  /**
   * 7. What is the output of the code below?
   */
  @Test
  public void testInheritance() {
    Boat boat = new Boat();
    boat.move();

    // Vehicle - move
    // Boat - move
    // Vehicle - move
  }

  public class Vehicle {
    protected void move() {
      System.out.println("Vehicle - move");
    }
  }

  public class Boat extends Vehicle {
    Vehicle v = new Vehicle();
    @Override
    public void move() {
      super.move();
      System.out.println("Boat - move");
      v.move();
    }
  }


  /**
   * 8. What code will let us know the names of the students in descending order of total marks for section B?
   */
  @Test
  public void testScores() {
    List<Student> students = createStudentList();

    List<String> names = students.stream()
      .filter(s -> s.getSection().equals("B"))
      //Creates an Object with name and total score
      .map(s -> new Object[] { s.getFullName(), s.getMarksMaths() + s.getMarksBiology() + s.getMarksEnglish() + s.getMarksHistory() })
      // Comparator that looks at total score if [1] in our Object
      .sorted((objArr1, objArr2) -> (int) objArr2[1] - (int) objArr1[1])
      // Take the names from the Object[]
      .map(objArr -> (String) objArr[0])
      .collect(Collectors.toList());

    System.out.println(names);
  }

  private List<Student> createStudentList() {
    Student student0 = new Student("Mr A", "B", 90, 80, 70, 60);
    Student student1 = new Student("Mrs B", "B", 90, 80, 60, 60);
    Student student2 = new Student("Miss C", "C", 90, 80, 70, 60);
    Student student3 = new Student("Mr D", "B", 90, 80, 70, 60);
    Student student4 = new Student("Mr E", "I", 90, 50, 70, 60);
    Student student5 = new Student("Mrs F", "B", 90, 80, 70, 60);
    Student student6= new Student("Mr G", "B", 100, 100, 100, 60);
    List<Student> students = new ArrayList<>();
    students.add(student0);
    students.add(student1);
    students.add(student2);
    students.add(student3);
    students.add(student4);
    students.add(student5);
    students.add(student6);
    return students;
  }
 
  class Student {

    private final String fullName;
    private final String section;
    private final int marksMaths;
    private final int marksBiology;
    private final int marksEnglish;
    private final int marksHistory;

    Student(String fullName, String section, int marksMaths, int marksBiology, int marksEnglish, int marksHistory) {
      super();
      this.fullName = fullName;
      this.section = section;
      this.marksMaths = marksMaths;
      this.marksBiology = marksBiology;
      this.marksEnglish = marksEnglish;
      this.marksHistory = marksHistory;
    }
    String getFullName() {
      return fullName;
    }
    String getSection() {
      return section;
    }
    int getMarksMaths() {
      return marksMaths;
    }
    int getMarksBiology() {
      return marksBiology;
    }
    int getMarksEnglish() {
      return marksEnglish;
    }
    int getMarksHistory() {
      return marksHistory;
    }
  }


  /**
   * 9. What will be the output of the following statement?
   */
  @Test
  public void testInfiniteStream() {
    Stream.iterate(0, i -> (i + 2) % 5)
      .sorted() //Program hangs because this attempts to sort an infinite stream before we impose the limit.
      .limit(10) // Moving this before will allow it to compute
      .forEach(i -> System.out.print(i + ", "));
  }


  /**
   * 10. What's the output of the following code
   */
  @Test
  public void testChange() {
    BigDecimal payment = new BigDecimal(2.00);
    BigDecimal cost = new BigDecimal(1.10); // Shouldnt have 0.1 because it cannot be represented exactly as a double or as a binary fraction of infinite length
    System.out.println(payment.subtract(cost));
    //0.899999999999999911182158029987476766109466552734375
  }


  /**
   * 11. Which statements describe the template method and strategy design patterns?
   */
  @Test
  public void testDesignPatterns() {
    // In template method design pattern, a base class defines the standard part of a algorithm or workflow,
    // along with a few methods that represent the variable parts. Subclasses can then extend the base class
    // and implement the variable parts and thus shape the concrete workflow.

    // In strategy design pattern, a base class defines the standard part of a algorithm or workflow, along with
    //instances of one or more interfaces, that represent the variable parts. Implementations of these interfaces
    //can then be injected into the base class and thus shape the concrete workflow.
  }


  /**
   * 12. For object types, does Java use pass-by-value or pass-by-reference while passing arguments and return values between methods?
   */
  @Test
  public void testJavaReferencing() {
    // Pass by Value: The method parameter values are copied to another variable and then the copied object is passed, that’s why it’s called pass by value.
    // Pass by Reference: An alias or reference to the actual parameter is passed to the method, that’s why it’s called pass by reference.
    // Java is always Pass by Value and not pass by reference, we can prove it with a simple example.

    Balloon red = new Balloon("Red"); //memory reference 50
    Balloon blue = new Balloon("Blue"); //memory reference 100
    //The following swap method can be used to test any language is pass by value or ref
    swap(red, blue);
    System.out.println("red color="+red.getColor()); //Will still return Red because we pass by value
    System.out.println("blue color="+blue.getColor());
  }
  public class Balloon {
    private String color;
    public Balloon(String c){
      this.color=c;
    }
    public String getColor() {
      return color;
    }
    public void setColor(String color) {
      this.color = color;
    }
  }

  public static void swap(Object o1, Object o2){
    Object temp = o1;
    o1=o2;
    o2=temp;
  }


  /**
   * 12. With respect to try, catch and finally blocks, which of the below statements are correct?
   * @throws IOException
   */
  @Test
  public void testTryCatch() throws IOException {
    // A try block must be accompanied by either at least one catch or a finally block, but not both.
    // The catch and the finally blocks are optional if a try-with-resources block is used.

    try (One one = new One(); Two two = new Two()) { // Second resource exception is printed first
      System.out.println("Try");
      throw new RuntimeException();
    } catch (Exception e) {
      System.out.println("Catch");
    } finally {
      System.out.println("Finally");
    }

    try (Three three = new Three();) {
      System.out.println("Yo");
    } catch (Exception e) {
      System.out.println("What");
    } finally {
      System.out.println("Up");
    } 
    // Trytc
    // Close - Two
    // Close - One
    // Catch
    // Finally
}

  static class One implements AutoCloseable {
    @Override
    public void close() throws Exception {
      System.out.println("Close - One");
    }
  }
  static class Two implements AutoCloseable {
    @Override
    public void close() throws Exception {
      System.out.println("Close - Two");
    }
  }
  class Three implements Closeable {
    @Override
    public void close() throws IOException {
    	System.out.println("Close - Three");
      throw new IOException();
    }
  }
  


  /**
   * 14. What is the simplest way to write factorial expression?
   */
  @Test
  public void testFactorialExpression() {
    IntStream.rangeClosed(1, 8)
    .reduce((n1, n2) -> n1 * n2)
    .getAsInt();

    IntStream.range(1, 8)
      .map(n -> n + 1) // Hacky, could just do range(1,9) instead
      .reduce(1, (n1, n2) -> n1 * n2);

    IntStream.rangeClosed(1, 8).forEach(System.out::print);
    System.out.println(" ");
    IntStream.range(1, 8).forEach(System.out::print);
    System.out.println(" ");
    IntStream.range(1, 9).forEach(System.out::print);
  }


  /**
   * 15. What is the output of the following string statements?
   */
  @Test
  public void testStringLiteral() {
     String s1 = "abc";
     String s2 = "abc";

     if (s1 == s2) {
       System.out.println("s1 == s2"); // Prints this line
     }
     System.out.println("s1 == s2 is:" + s1 == s2); // false because it tries to add "s1 == s2 is:" to s1 first, then calculates if its equal
     System.out.println("s1 == s2 is:" + (s1 == s2)); // s1 == s2 is:true
   }


  /**
   * 16. Can you have multiple classes in a Java source file?
   */
  @Test
  public void testJavaSource() {
    // Yes but only one public class as it must have the same name as the source file
  
  }


  /**
   * 17. What is the difference between a static and a transient variable?
   */
  @Test
  public void testStaticTransientVariable() {
    // The reasons behind not being serialized
    // Serialization is a mechanism of converting the state of an object into a byte stream.

    // Static
    // Static variables belong to a class and not to any individual instance.
    // The concept of serialization is concerned with the object’s current state.
    // Only data associated with a specific instance of a class is serialized, therefore static member fields are ignored during serialization.

    // Transient
    // If you dont want to store the state of a variable during serialization then you have to mark it as transient
    // So neither will be saved, but the reasons behind why is different
  }


  /**
   * 17.1. What are the benefits of Serialization?
   */
  @Test
  public void testSerialization() {
    // Allows us to persist object state
    // Object > serialization > byte stream > memory > de serialization > object
    // Move an object around a network
    // Object must implement Serializable
    // If a parent class has implemented Serializable interface then child class doesn’t need to implement it but vice-versa is not true
    // Constructor of object is never called when an object is deserialized.
  }


  /**
   * 18. If Car s asubclass of abstract class Vehicle which can be true?
   */
  @Test
  public void testGetPut() {
    List<VehicleAbstract> vehicles = new ArrayList<>();
//  ArrayList<VehicleAbstract> vehicles1 = new List<VehicleAbstract>();
//  List<VehicleAbstract> vehicles2 = new ArrayList<Car>();
  List<? extends VehicleAbstract> vehiclesGen = new ArrayList<Car>(); // only allowed because a car is a child of vehicle
//  List<? super Car> vehiclesSuper = new ArrayList<Plane>(); // not allowed because the List is of something higher than a Car which may not be a plane

  }
  abstract class VehicleAbstract {}
  class Car extends VehicleAbstract {}
  class Plane extends VehicleAbstract {}


  /**
   * 19. Evaluate the inheritance
   */
  @Test
  public void testObjects() {
    VehicleObject v1 = new CarObject(); // v1 is still a VehicleObject
    VehicleObject v2 = new VehicleObject();

    // CarObject c1 = v1;   //Need to case (CarObject) v1
    CarObject c2 = (CarObject) v1;
    CarObject c3 = (CarObject) v2;
    CarObject c4 = new Volswagen();
    CarObject c5 = (Volswagen) v1;
    CarObject c6 = (CarObject) v1;

    Object o = v1;
    // BusObject b1 = new Volswagen();
  }

  class VehicleObject {} //extends Object
  class CarObject extends VehicleObject {}
  class Volswagen extends CarObject {}
  class BusObject extends VehicleObject {}


  /**
   * 20.
   * Write an SQL query to retrieve all Student Names enrolled in the Course called ‘Advanced English’.
   * Write a SQL query to retrieve the total number of Courses in which at least one Student is enrolled.
   *
   * Student
   *  - studentId (PK)
   *  - name
   *
   * Course
   *  - courseId (PK)
   *  - name
   *
   * StudentCourseEnrollment
   *  - studentId (PK)
   *  - courseId (PK)
   */
  @Test
  public void testSqlQuery() {
    //Select name FROM Student s
    //  INNER JOIN  StudentCourseEnrollment sce ON sce.studentId = s.studentId
    //  INNER JOIN  Course c ON sce.courseId = c.courseId
    //  WHERE c.name = 'Advanced English';
  }


  /**
   * 21. Not counting the main thread, how many threads are created by this code?
   *
   * @throws Exception
   */
  @Test
  public void testThreadCount() throws Exception {
    Runnable r = () -> System.out.println("This is " + Thread.currentThread().getName()); //Main thread
    Callable<String> c = () -> { System.out.println("This is " + Thread.currentThread().getName()); //Main thread
      return Thread.currentThread().getName(); // Main thread
    };

    Thread thread1 = new MyThread();
    Thread thread2 = new Thread(r);
    thread1.run();
    thread1.start(); // Thread 1
    thread2.run();
    thread2.start(); // Thread 2
    c.call();
  }

  class MyThread extends Thread {
    @Override
    public void run() {
      System.out.println("This is " + Thread.currentThread().getName());
    }
  }

  /**
   * 22. What is needed to get the following code to compile properly?
   */
  @Test
  public void testGenericsInheritance() {
    //interface Herbivore<E extends Animal> extends Hungry<E> {}
  }

  interface Hungry<E> {
    void munch(E x);
  }
  interface Carnivore<E extends Animal> extends Hungry<E> {}

  //interface Herbivore<E extends Plant> extends Hungry<E> {} // Herbivore obviously shouldn't extend plant
  interface Herbivore<E extends Animal> extends Hungry<E> {}
  abstract class Plant {}

  class Grass extends Plant {}

  abstract class Animal {}

  class Sheep extends Animal implements Herbivore<Sheep> { //Comp error on Sheep here if extends Plant
    @Override
    public void munch(Sheep x) {
    }
  }

  class Wolf extends Animal implements Carnivore<Sheep> {
    @Override
    public void munch(Sheep x) {
    }
  }


  /**
   * 23.  A company has offices in several locations across the country.
   * There is a requirement to know which employees have an annual salary above and below a certain threshold for a given location.
   * Write some code that will let us filter the employees above £50,000 in London
   */
  @Test
  public void testGetSalary() {
    List<Employee> employees = createEmployeeList();
    List<Employee> highEarnersLondon = employees.stream()
        .filter(e -> e.getLocation().equals("London"))
        .filter(p -> p.getAnnualSalary().compareTo(new BigDecimal(50000)) == 1)
        .collect(Collectors.toList());

    System.out.println(highEarnersLondon);
  }
  
  class Employee {
    private final String fullName;
    private final String location;
    private final BigDecimal annualSalary;

    Employee(String fullName, String location, BigDecimal annualSalary) {
      super();
      this.fullName = fullName;
      this.location = location;
      this.annualSalary = annualSalary;
    }

    String getFullName() {
      return fullName;
    }
    String getLocation() {
      return location;
    }
    BigDecimal getAnnualSalary() {
      return annualSalary;
    }
  }

  private List<Employee> createEmployeeList() {
    List<Employee> employees = new ArrayList<>();
    Employee employee1 = new Employee("Luna Lovegood", "London", new BigDecimal(50001));
    Employee employee2 = new Employee("Ron Weasley", "London", new BigDecimal(40000));
    Employee employee3 = new Employee("Prof Snape", "Bristol", new BigDecimal(50001));
    Employee employee4 = new Employee("Dumbelsnore", "LeedsLeedsLees", new BigDecimal(25000));
    employees.add(employee1);
    employees.add(employee2);
    employees.add(employee3);
    employees.add(employee4);
    String abd = "abc";
    return employees;
  }


  /**
   * 24. When is the finalize() method called on an object?
   */
  @Test
  public void testFinalize() {
    // Called by the garbage collector on an object when garbage collection determines that there are no more references to the object.
    // AKA right before being removed from memory
  }


  /**
   * 25. What is the output for the following?
   */
  @Test
  public void testRemovalStream() {
    List<Integer> list =
        IntStream.range(0, 10)
                 .boxed()
                 .collect(Collectors.toList());

    list.stream()
      .peek(list::remove)
      .forEach(System.out::println);

    //ConcurrentModException without this wtf? If we add sorted() before then
  }


  /**
   * 26. What is the output of the following code?
   */
  @Test
  public void testEnum() {
  	Size size = new Size();
    size.printSize(new HashMap<Sex, Sex>() );
    size.printSize(new EnumMap<Sex, Sex>(Sex.class) );

    // 2
    // 2
    // The keys of the Maps are the same, only the value changes
  }
  
  @Test
  public void testStrings() {
  	String o1 = new String("abc");
  	String o2 = new String("abc");
  	String l1 = "abc";
  	String l2 = "abc";
  	
  	System.out.println(o1.equals(o2));
  	System.out.println(o1==o2);
  	System.out.println(l1.equals(l2));
  	System.out.println(l1==l2);
  	
  }
}