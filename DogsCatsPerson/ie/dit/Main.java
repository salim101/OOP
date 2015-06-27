package ie.dit;

import java.util.*;

public class Main
{	
	/* this is the same as below main method
	
	ArrayList<Animal> animals = new ArrayList<Animal>();
	
	Main()
	{
		System.out.println("Hello world!");
		Animal dog = new Dog("Misty",4);
		System.out.println(dog.getName() +dog.getNumLegs());		
		dog.speak();
	}
	
	public static void main(String[] args)
	{
		Main main = new Main();
	}
	
	*/
	
	public static void main(String[] args)
	{
	
		ArrayList<Animal> animals = new ArrayList<Animal>();
	
		System.out.println("Hello world!");
		Animal dog = new Dog("Misty",4);
		
		
		System.out.println(dog.getName() +dog.getNumLegs());		
		dog.speak();
		
		Animal person = new Person("salim",2);
		System.out.println(person.getName() +person.getNumLegs());		
		person.speak();
		
	}
}