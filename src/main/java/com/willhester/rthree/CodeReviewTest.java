package com.willhester.rthree;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

// Imagine you are a peer of the developer who committed this (syntactically correct) Java code and asked you to review
// their pull request. You work on the same product but are not familiar with this piece of work or its associated
// requirements.
//
// Please use Java comments for your review feedback, putting them on separate lines around the code. Do not modify the
// code itself.
/**
 * <p>This code appears to be attempting to try and find three
 * different pieces of information from a database that contains records
 * of people data:</p>
 * <ol>
 * <li>Calculate the total age of all the people in the database</li>
 * <li>Find the total number of records within the database</li>
 * <li>Find the total number of records that are male</li>
 * </ol>
 * 
 * <p>The code will currently not make it to any of those calculations. I will highlight areas
 * within the code that are fundamentally broken but at a higher level, the entire
 * code should be restructured as follows:</p>
 * <ul> 
 * <li>Segregate the business logic of the calculations into a dedicated service.</li>
 * <li>The service might have three public methods that carry out the calculation.</li>
 * <li>Implement a creational design pattern such as a factory to create your Person objects.
 * This will allow for extensibility of the properties that you can store on each record.</li>
 * <li>Use appropriate package structure to separate the different components.</li>
 * <li>Write unit tests for each piece of functionality to ensure it works.</li>
 * <li>Rename the classes to describe what they actually are.</li>
 * <li>Javadoc on each class to offer more explanation where appropriate.</li>
 * 
 * @author WillHester
 */
public class CodeReviewTest {

		// Volatile should be used for reading data
		// Use synchronized locks for writing data
		// If you are expecting nulls here as evident by using Integer.class then use Optional to handle
		// these correctly
		// Have you considered Integer overflow at 42,949,673 records of age 50? 
    volatile Integer totalAge = 0;
    
    // Constructor should not contain any business logic
    // Rename the personPersonDatabase and add as a class level field and assign it within the constructor
    // so it can be used from other methods in the class.
    CodeReviewTest(PersonDatabase<Person> personPersonDatabase) {
      // If the method returns a list we can avoid all this copying from array to 
    	Person[] persons = null;
    		// This is a leakage of concern from the DAO to service layer - this should be handled in the 
    		// implementation of the DAO.
        try {
            persons = personPersonDatabase.getAllPersons();
        } catch (IOException e) {
        	// Use this block to print useful information about the exception/ class 
        	// and actually handle the exception
        }
        
        // Is the list size big enough to warrant a LinkedList? 
        List<Person> personsList = new LinkedList();

        // No need for this loop if list is returned originally
        // Will return IndexOutOfBounds exception as arrays are zero-indexed
        // Need to use i < persons.length
        for (int i = 0; i <= persons.length; i++) {
            personsList.add(persons[i]);
        }

        // volatile variable will have issues here with multiple threads trying to write to the same member
        // I am questioning the requirement for the need to calculate the total age of all the people 
        // in the database. It feels like an intermediate calculation for calculating averages - if so, this can
        // be carried out fluently within this stream.
        // Parallel stream will need totalAge to be synchronised
        personsList.parallelStream().forEach(person -> {
            totalAge += person.getAge();
        });

        // For the following calculations I would recommend separate methods on the DAO to select female and male 
        // records from the database. This would perform much better than iterating over all the records twice 
        //
        List<Person> males = new LinkedList<>();
        
        for (Person person : personsList) {
        		// NPE here as the gender is never set 
        		// What about other genders? It is 2019 after all.
            switch (person.gender) {
                case "Female": personsList.remove(person);
                case "Male"  : males.add(person);
            }
        }

        // These methods should return these values as integers and unit tests written as assertions against them 
        System.out.println("Total age =" + totalAge);
        System.out.println("Total number of females =" + personsList.size());
        System.out.println("Total number of males =" + males.size());
    }

}


class Person {

		// public getters and setters for each of these fields for POJO
    private int age;
    // Should be final
    private String firstName;
    // Should be final
    private String lastName;
    // Use an enum for the gender to ensure it cannot be input incorrectly
    // Make it private and final
    String gender;

    // We should set the gender of the person during construction also 
    public Person(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    // Inconsistent spacing between the methods
    public int getAge() {
        return age;
    }
    
    // If you override the equals() method then similar logic should be used to override the hashCode() method. 
    // This method should check that it is not null, that the classes are the same type and that the element object 
    // is not null. If you consider two object with the same last name equal that will result in a extremely high frequency
    // of hash collisions if you were to implement the same logic on the hashCode. You should compare the age, first name
    // and gender.
    @Override
    public boolean equals(Object obj) {
    	// Lots of missing checks in this method
    	// return true if this == obj
    	// return false if obj == null
    	// return false if getClass() != obj.getClass()
    	
    	// When comparing elements of the object you should use the equals method. 
    	// !lastName.equals((Person)obj).lastName) return false
    	// Repeat for other members of the object
    	// No need to specify this as it is unambigious which instance we're referring too
      return this.lastName == ((Person)obj).lastName;
    }

}

// Is there an implementation of this interface? I assume it would be commmitted alongside 
// the interface.
// Does this need to be generic? The only method you have returns an array of Person
// and it wouldn't make sense for any other type to be used with the current name.
// The generic type would be T if this was the case as it indicates we're expecting different
// types. 
interface PersonDatabase<E> {

	// Use 'listAll' or 'findAll' method signatures as get is typically reserved for getters 
	// Return a List instead of an array as resizing the array is very expensive and List offers 
	// more functionaltity
	// Why are you expecting IOException? We do not expect this in a DAO as it's reserved for resources
	// with an input/ output. A Persistance exception would be better here if you expect one.
  Person[] getAllPersons() throws IOException;

}
