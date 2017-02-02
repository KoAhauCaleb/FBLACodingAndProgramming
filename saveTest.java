import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;


public class saveTest {

	public static void main(String[] args) {

		
		String name;
		double pay;
		int SH;
		int SM;
		int EH;
		int EM;
		int day;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter employees name: ");
		
		name = in.next();
		
		System.out.println("Enter employees pay: ");
		
		pay = in.nextDouble();
		
		System.out.println("Enter employees start hour(0-24): ");
		do{
			SH = in.nextInt();
		}
		while(!(SH >= 0 && SH <= 60));
		System.out.println("Enter employees start minute(0-60): ");
		do{
			SM = in.nextInt();
		}
		while(!(SM >= 0 && SM <= 60));
		
		System.out.println("Enter employees end hour(0-24): ");
		do{
			EH = in.nextInt();
		}
		while(!(EH >= 0 && EH <= 24));
		
		System.out.println("Enter employees end minute(0-60): ");
		do{
			EM = in.nextInt();
		}
		while(!(EM >= 0 && EM <= 60));
		
		System.out.println("Enter employees day(1-7): ");
		
		do{
			day = in.nextInt();
		}
		while(!(day >= 1 && day <= 7));
		
		Employee e = new Employee(pay, name);
		DateTime d = new DateTime(SH, SM, EH, EM, day);
		e.addDateTime(d);
		
		Manager m = new Manager();
		
		m.addEmployee(e);
		
		JFileChooser chooser = new JFileChooser();
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			try {
				m.save(chooser.getSelectedFile());
			} 
			catch (FileNotFoundException f) {
				f.printStackTrace();
			}
		}
		else{
			System.out.println("You chose not to save.");
		}
	}

}
