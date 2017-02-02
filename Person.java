
public class Person extends Schedule{
	protected String name;
	
	public Person(){
		
	}
	
	public Person(String n){
		name = n;
	}
	
	public void changeName(String n){
		name = n;
	}
	
	public String getName(){
		return name;
	}
}
