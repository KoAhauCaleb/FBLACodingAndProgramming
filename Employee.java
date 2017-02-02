
public class Employee extends Person{
	private double pay;
	
	public Employee(){
		
	}
	
	public Employee(double p, String n){
		pay = p;
		name = n;
	}
	
	public void changePay(double p){
		pay = p;
	}
	
	public double getPay(){
		return pay;
	}
	
}
