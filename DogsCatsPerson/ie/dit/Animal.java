package ie.dit;

public class Animal
{
	private String name;
	private int numLegs;
	
	
	public Animal(String name, int numLegs)
	{
		this.name = name;
		this.numLegs=numLegs;
	}
	
	public Animal()
	{
		this("", 0);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	public int getNumLegs()
	{
		return numLegs;
	}
	
	public void setNumLegs(int numLegs)
	{
		this.numLegs = numLegs;
	}
	
	
	public void speak()
	{
		System.out.println("I can't speak!");
	}
}