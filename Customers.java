import java.util.ArrayList;

public class Customers {
	
	ArrayList<Person> customers = new ArrayList<Person>(); 
	
	public  Customers(){
		
	}
	
	public void add(int index, Person p){
		customers.add(index, p);
	}
	
	public void add(Person p){
		customers.add(p);
	}
	
	public Person get(int index){
		return customers.get(index);
	}
	
	public int length(){
		return customers.size();
	}
	
}
