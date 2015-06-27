package ie.dit;


public class Person extends Animal
{
	public Person(String name, int numLegs)
	{
		super(name, 2);
	}
	
	public void speak()
	{
		System.out.println(getName() + " says Hello!");
	}	
	
	public void fetch()
	{
		System.out.println(getName() + " is fetching");
	}
}