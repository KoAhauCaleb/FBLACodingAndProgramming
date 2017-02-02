import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Manager {
	
	private Employees employees = new Employees();
	private Customers customers = new Customers();
	
	public Manager(){
		
	}
	
	public Manager(File file) throws FileNotFoundException,BadDataException{
		//Open .dat file, read and set data
		
		Scanner in = new Scanner(file);
		
		String check = in.nextLine();
		
		if(!check.equals("nKt,:7\\#c`ULm%#")){
			throw new BadDataException("Incorect check");
		}
		
		String employed = in.nextLine();
		
		//System.out.println(employed);
		separatePeopleE(employed);
		if(in.hasNextLine()){
			separatePeopleE(in.nextLine());
		}
		
	}
	
	public void separatePeopleE(String s){
		ArrayList<Integer> places = new ArrayList<Integer>();
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '*'){
				places.add(i);
			}
		}
		
		for(int i = 0; i < places.size() - 1; i++){
			int start = places.get(i) + 1;
			int end = places.get(i + 1);
			//System.out.println(s.substring(start, end));
			addEmployee(s.substring(start, end));
		}
		//System.out.println(s.substring(places.get(places.size() - 1) + 1));
		addEmployee(s.substring(places.get(places.size() - 1) + 1));
	}
	
	public void separatePeopleC(String s){
		ArrayList<Integer> places = new ArrayList<Integer>();
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '*'){
				places.add(i);
			}
		}
		
		for(int i = 0; i < places.size() - 1; i++){
			int start = places.get(i) + 1;
			int end = places.get(i + 1);
			//System.out.println(s.substring(start, end));
			addCustomer(s.substring(start, end));
		}
		//System.out.println(s.substring(places.get(places.size() - 1) + 1));
		addCustomer(s.substring(places.get(places.size() - 1) + 1));
	}
	
	public void addEmployee(String s){
		int payStart = -1;
		int schedule = -1;
		ArrayList<Integer> places = new ArrayList<Integer>();
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '/' && payStart == -1){
				payStart = i;
			}
			if(s.charAt(i) == '=')
				schedule = i;
			if(s.charAt(i) == '<')
				places.add(i);
		}
		Employee e;
		System.out.println(s.substring(0, payStart));
		if(schedule != -1){
			e = new Employee(Double.parseDouble(s.substring(payStart + 1, schedule)),s.substring(0, payStart));
		}
		else{
			e = new Employee(Double.parseDouble(s.substring(payStart + 1)),s.substring(0, payStart));
		}
		for(int i = 1; i < places.size() - 1; i++){
			//System.out.println(s.substring(places.get(i) + 1, places.get(i + 1) - 1));
			addSchedule(e, s.substring(places.get(i) + 1, places.get(i + 1) - 1));
			employees.add(e);
		}
		//System.out.println(s.substring(places.get(places.size() - 1) + 1));
		addSchedule(e, s.substring(places.get(places.size() - 1) + 1));
		employees.add(e);
	}
	
	public void addCustomer(String s){
		ArrayList<Integer> places = new ArrayList<Integer>();
		int schedule = -1;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '=')
				schedule = i;
			if(s.charAt(i) == '<')
				places.add(i);
		}
		Person e;
		if(schedule != -1){
			e = new Person(s.substring(0, schedule));
		}
		else{
			e = new Person(s.substring(0, schedule));
		}
		for(int i = 1; i < places.size() - 1; i++){
			addSchedule(e, s.substring(places.get(i) + 1, places.get(i + 1) - 1));
			customers.add(e);
		}
		addSchedule(e, s.substring(places.size() - 1));
		customers.add(e);
	}
	
	public void addSchedule(Person p, String s){
		ArrayList<Integer> places = new ArrayList<Integer>();
		
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '/'){
				places.add(i); 
			}
		}
		System.out.println(places.get(1));
		System.out.println(Integer.parseInt(s.substring(places.get(4) + 1)));
		DateTime d = new DateTime(Integer.parseInt(s.substring(1, places.get(1))),
				Integer.parseInt(s.substring(places.get(1) + 1, places.get(2))),
				Integer.parseInt(s.substring(places.get(2) + 1, places.get(3))),
				Integer.parseInt(s.substring(places.get(3) + 1, places.get(4))),
				Integer.parseInt(s.substring(places.get(4) + 1)));
		p.addDateTime(d);
	}
	
	public void save(File file) throws FileNotFoundException{
		//Save data to .dat file
		
		PrintWriter out = new PrintWriter(file);
		System.out.println("ran1");
		out.println("nKt,:7\\#c`ULm%#");
		for(int i = 0; i < employees.length(); i++){
			System.out.println("ran");
			Employee e = employees.get(i);
			out.print("*");
			out.print(e.getName());
			out.print("/");
			out.print(e.getPay());
			out.print("=");
			for(int j = 0; j < e.length(); j++){
				System.out.println("ran");
				out.print("<");
				DateTime d = e.getSchedule(j);
				out.print("/");
				out.print(d.SHour);
				out.print("/");
				out.print(d.SMinute);
				out.print("/");
				out.print(d.EHour);
				out.print("/");
				out.print(d.EMinute);
				out.print("/");
				out.print(d.dayInt);
			}
		}
		System.out.println("ran2");
		out.println();
		for(int i = 0; i < customers.length(); i++){
			System.out.println("ran");
			Person e = customers.get(i);
			out.print("*");
			out.print(e.getName());
			out.print("/");
			for(int j = 0; j < e.length(); j++){
				out.print("<");
				DateTime d = e.getSchedule(j);
				out.print("/");
				out.print(d.SHour);
				out.print("/");
				out.print(d.SMinute);
				out.print("/");
				out.print(d.EHour);
				out.print("/");
				out.print(d.EMinute);
				out.print("/");
				out.print(d.dayInt);
			}
		}
		out.close();
	}
	
	public void changeEmployeeName(int index, String n){
		employees.get(index).changeName(n);
	}
	
	public void changeCustomerName(int index, String n){
		customers.get(index).changeName(n);
	}
	
	public void addEmployee(int index, Employee p){
		employees.add(index, p);
	}
	
	public void addCustomer(int index, Person p){
		customers.add(index, p);
	}
	
	public void addEmployee(Employee p){
		employees.add(p);
	}
	
	public void addCustomer(Person p){
		customers.add(p);
	}
	
	public Employee getEmployee(int index){
		return employees.get(index);
	}
	
	public Person getCustomer(int index){
		return customers.get(index);
	}
	
	public int getCustomersSize(){
		return customers.length();
	}
	
	public int getEmployeesSize(){
		return employees.length();
	}
	
	
}
