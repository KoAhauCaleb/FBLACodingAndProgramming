import java.util.ArrayList;

public class Employees {
	
	ArrayList<Employee> employees; 
	
	public Employees(){
		employees = new ArrayList<Employee>();
	}
	
	public void add(int index, Employee p){
		employees.add(index, p);
	}
	
	public void add(Employee p){
		employees.add(p);
	}
	
	public Employee get(int index){
		return employees.get(index);
	}
	
	public int length(){
		return employees.size();
	}
	
}
