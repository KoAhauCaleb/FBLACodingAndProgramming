import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.*;

public class GUIWindow  extends JFrame{
	
	private boolean customers = true;
	
	Manager m = new Manager();
	
	private int currentIndex = 0;
	
	private JLabel nameLabel = new JLabel("Name");
	private JLabel payLabel = new JLabel("Pay");
	private JLabel scheduleLabel = new JLabel("Schedule");
	private JLabel indexLabel = new JLabel("Schedule to Edit Number");
	private TextArea schedule = new TextArea("Day	Start	End", 5, 30);
	private JFormattedTextField name = new JFormattedTextField();
	private JFormattedTextField pay = new JFormattedTextField();
	private JFormattedTextField index = new JFormattedTextField();
	private JFormattedTextField day = new JFormattedTextField();
	private JFormattedTextField sH = new JFormattedTextField();
	private JFormattedTextField sM = new JFormattedTextField();
	private JFormattedTextField eH = new JFormattedTextField();
	private JFormattedTextField eM = new JFormattedTextField();
	private JButton addButton = new JButton("Add");
	private JButton addScheduleButton = new JButton("Add");
	private JButton editScheduleButton = new JButton("Edit");
	private JButton firstButton = new JButton("<<");
	private JButton lastButton = new JButton(">>");
	private JButton previousButton = new JButton("<");
	private JButton nextButton = new JButton(">");
	private JButton customersButton = new JButton("Customers");
	private JButton employeesButton = new JButton("Employees");
	private JButton saveButton = new JButton("Save");
	private JButton loadButton = new JButton("Load");
	
	public GUIWindow(){
		schedule.setEditable(false);
		
		//setting up layout		
		JPanel cePanel = new JPanel(new GridLayout(2,2,3,3));
		cePanel.add(saveButton);
		cePanel.add(loadButton);
		cePanel.add(customersButton);
		cePanel.add(employeesButton);
		
		JPanel navPanel = new JPanel(new GridLayout(1,4,3,3));
		navPanel.add(firstButton);
		navPanel.add(previousButton);
		navPanel.add(nextButton);
		navPanel.add(lastButton);
		
		JPanel editSchedule = new JPanel(new GridLayout(1,2,3,3));
		editSchedule.add(index);
		editSchedule.add(editScheduleButton);
		
		JPanel editScheduleText = new JPanel(new GridLayout(2,1,3,3));
		editScheduleText.add(indexLabel);
		editScheduleText.add(editSchedule);
		
		JPanel editChangeSchedule = new JPanel(new GridLayout(2,5,3,3));
		editChangeSchedule.add(new JLabel("day 1-7")); 
		editChangeSchedule.add(new JLabel("Start hour 1-24")); 
		editChangeSchedule.add(new JLabel("Start minute 0-59"));
		editChangeSchedule.add(new JLabel("End hour 1-24")); 
		editChangeSchedule.add(new JLabel("End minute 0-59"));
		editChangeSchedule.add(day); 
		editChangeSchedule.add(sH); 
		editChangeSchedule.add(sM);
		editChangeSchedule.add(eH); 
		editChangeSchedule.add(eM);
		
		JPanel changeSchedule = new JPanel(new GridLayout(3,1,3,3));
		changeSchedule.add(addScheduleButton);
		changeSchedule.add(editScheduleText);
		changeSchedule.add(editChangeSchedule);
		
		JPanel scheduleGrid = new JPanel(new GridLayout(1,2,3,3));
		scheduleGrid.add(schedule);
		scheduleGrid.add(changeSchedule);
		
		JPanel infoGrid = new JPanel(new GridLayout(2,2,3,3));
		infoGrid.add(nameLabel);
		infoGrid.add(payLabel);
		infoGrid.add(name);
		infoGrid.add(pay);
		
		JPanel layout = new JPanel(new GridLayout(5,1,12,6));
		layout.setBorder(new EmptyBorder(10, 10, 10, 10));
		layout.add(cePanel);
		layout.add(addButton);
		layout.add(infoGrid);
		layout.add(scheduleGrid);
		layout.add(navPanel);
		
		
		Container container = getContentPane();
		container.add(layout, BorderLayout.CENTER);
		
		//set enabled to false so user can't edit and cause errors 
		day.setEnabled(false);
		sH.setEnabled(false);
		sM.setEnabled(false);
		eH.setEnabled(false);
		eM.setEnabled(false);
		
		//set up action listeners so buttons do things 
		saveButton.addActionListener(new saveListener());
		loadButton.addActionListener(new loadListener());
		customersButton.addActionListener(new customerListener());
		employeesButton.addActionListener(new employeeListener());
		editScheduleButton.addActionListener(new editScheduleListener());
		addButton.addActionListener(new addListener());
		nextButton.addActionListener(new nextListener());
		previousButton.addActionListener(new previousListener());
		lastButton.addActionListener(new lastListener());
		firstButton.addActionListener(new firstListener());
		
	}

	public void showCustomer(){
		name.setValue(m.getCustomer(currentIndex).getName());
		for(int i = 0; i < m.getCustomer(currentIndex).length(); i++){
			schedule.append(m.getCustomer(currentIndex).getSchedule(i).day +
					 "	" + m.getCustomer(currentIndex).getSchedule(i).SHour + ":" +
					 m.getCustomer(currentIndex).getSchedule(i).SMinute +
					 "	" + m.getCustomer(currentIndex).getSchedule(i).EHour + ":" +
					 m.getCustomer(currentIndex).getSchedule(i).EMinute);
		}
		
	}
	
	public void showEmployee(){
		name.setValue(m.getCustomer(currentIndex).getName());
		pay.setValue(m.getEmployee(currentIndex).getPay());
		for(int i = 0; i < m.getCustomer(currentIndex).length(); i++){
			schedule.append((i + 1) + ": ");
			schedule.append(m.getCustomer(currentIndex).getSchedule(i).day +
					 "	" + m.getCustomer(currentIndex).getSchedule(i).SHour + ":" +
					 m.getCustomer(currentIndex).getSchedule(i).SMinute +
					 "	" + m.getCustomer(currentIndex).getSchedule(i).EHour + ":" +
					 m.getCustomer(currentIndex).getSchedule(i).EMinute);
		}
		
	}
	
	private boolean adding = false;
	private class addListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(!adding){
				addButton.setText("Save");
				if(customers){
					loadButton.setEnabled(false);
					saveButton.setEnabled(false);
					employeesButton.setEnabled(false);
					customersButton.setEnabled(false);
					nextButton.setEnabled(false);
					previousButton.setEnabled(false);
					lastButton.setEnabled(false);
					firstButton.setEnabled(false);
					addScheduleButton.setEnabled(false);
					addButton.setEnabled(true);
					name.setEnabled(true);
					pay.setEnabled(false);
					editScheduleButton.setEnabled(false);
					indexLabel.setEnabled(false);
					day.setEnabled(false);
					sH.setEnabled(false);
					sM.setEnabled(false);
					eH.setEnabled(false);
					eM.setEnabled(false);
				}
				else{
					loadButton.setEnabled(false);
					saveButton.setEnabled(false);
					employeesButton.setEnabled(false);
					customersButton.setEnabled(false);
					nextButton.setEnabled(false);
					previousButton.setEnabled(false);
					lastButton.setEnabled(false);
					firstButton.setEnabled(false);
					addScheduleButton.setEnabled(false);
					addButton.setEnabled(true);
					name.setEnabled(true);
					pay.setEnabled(true);
					editScheduleButton.setEnabled(false);
					indexLabel.setEnabled(false);
					day.setEnabled(false);
					sH.setEnabled(false);
					sM.setEnabled(false);
					eH.setEnabled(false);
					eM.setEnabled(false);
				}
				adding = true;
			}
			else{
				addButton.setText("Add");
				if(customers){
					m.addCustomer(new Person(name.toString()));
					loadButton.setEnabled(true);
					saveButton.setEnabled(true);
					employeesButton.setEnabled(true);
					customersButton.setEnabled(true);
					nextButton.setEnabled(true);
					previousButton.setEnabled(true);
					lastButton.setEnabled(true);
					firstButton.setEnabled(true);
					addScheduleButton.setEnabled(true);
					addButton.setEnabled(true);
					name.setEnabled(true);
					pay.setEnabled(true);
					editScheduleButton.setEnabled(true);
					indexLabel.setEnabled(true);
					day.setEnabled(false);
					sH.setEnabled(false);
					sM.setEnabled(false);
					eH.setEnabled(false);
					eM.setEnabled(false);
				}
				else{
					try{
						m.addEmployee(new Employee(Double.parseDouble(pay.toString()),name.toString()));
					} catch(Exception ex){
						
					}
					
					loadButton.setEnabled(true);
					saveButton.setEnabled(true);
					employeesButton.setEnabled(true);
					customersButton.setEnabled(true);
					nextButton.setEnabled(true);
					previousButton.setEnabled(true);
					lastButton.setEnabled(true);
					firstButton.setEnabled(true);
					addScheduleButton.setEnabled(true);
					addButton.setEnabled(true);
					name.setEnabled(true);
					pay.setEnabled(true);
					editScheduleButton.setEnabled(true);
					indexLabel.setEnabled(true);
					day.setEnabled(false);
					sH.setEnabled(false);
					sM.setEnabled(false);
					eH.setEnabled(false);
					eM.setEnabled(false);
				}
				adding = false;
			}
		}
	}
	
	private class customerListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			currentIndex = 0;
			customers = true;
			pay.removeAll();
			pay.setEnabled(false);
			showEmployee();
		}
	}
	
	private class employeeListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			currentIndex = 0;
			customers = false;
			pay.setEnabled(true);
			showEmployee();
		}
	}
	
	private class saveListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JFileChooser chooser = new JFileChooser();
			if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
				try {
					Manager m = new Manager(chooser.getSelectedFile());					
				} 
				catch (FileNotFoundException f) {
					f.printStackTrace();
				}
				catch (BadDataException f) {
					f.printStackTrace();
				}
			}
			else{
				//System.out.println("You chose not to open.");
			}
		}
	}
	
	private class loadListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JFileChooser chooser = new JFileChooser();
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				currentIndex = 0;
				try {
					Manager m = new Manager(chooser.getSelectedFile());	
					currentIndex = 0;
				} 
				catch (FileNotFoundException f) {
					f.printStackTrace();
				}
				catch (BadDataException f) {
					f.printStackTrace();
				}
			}
			else{
				//System.out.println("You chose not to open.");
			}
			if(customers){
				try{
					showCustomer();
				}
				catch(Exception ex){
					//no customers added
				}
			}
			else{
				try{
					showEmployee();
				}
				catch(Exception ex){
					//no employees added
				}
			}
		}
	}
	
	private boolean editingSchedule = false;
	private class editScheduleListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			if(!editingSchedule){
				editScheduleButton.setText("Save");
				//int i = Integer.parseInt(index.toString());
				loadButton.setEnabled(false);
				saveButton.setEnabled(false);
				employeesButton.setEnabled(false);
				customersButton.setEnabled(false);
				nextButton.setEnabled(false);
				previousButton.setEnabled(false);
				lastButton.setEnabled(false);
				firstButton.setEnabled(false);
				addScheduleButton.setEnabled(false);
				addButton.setEnabled(false);
				name.setEnabled(false);
				pay.setEnabled(false);
				scheduleLabel.setEnabled(false);
				indexLabel.setEnabled(false);
				day.setEnabled(true);
				sH.setEnabled(true);
				sM.setEnabled(true);
				eH.setEnabled(true);
				eM.setEnabled(true);
				editingSchedule = true;
			}
			else{
				editScheduleButton.setText("Edit");
				try{
					m.getCustomer(currentIndex).getSchedule(Integer.parseInt(index.toString())).dayInt = Integer.parseInt(day.toString());
				} catch(Exception ex){
					
				}
				loadButton.setEnabled(true);
				saveButton.setEnabled(true);
				employeesButton.setEnabled(true);
				customersButton.setEnabled(true);
				nextButton.setEnabled(true);
				previousButton.setEnabled(true);
				lastButton.setEnabled(true);
				firstButton.setEnabled(true);
				addScheduleButton.setEnabled(true);
				addButton.setEnabled(true);
				name.setEnabled(true);
				pay.setEnabled(true);
				scheduleLabel.setEnabled(true);
				indexLabel.setEnabled(true);
				day.setEnabled(false);
				sH.setEnabled(false);
				sM.setEnabled(false);
				eH.setEnabled(false);
				eM.setEnabled(false);
				editingSchedule = false;
			}
			if(customers){
				showCustomer();
			}
			else{
				showEmployee();
			}
		}
	}
	
	private class nextListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			
			if(customers){
				if(currentIndex - 1 < m.getCustomersSize()){
					currentIndex++;
				}
			}
			else{
				if(currentIndex - 1 < m.getEmployeesSize()){
					currentIndex++;
				}
			}
			if(customers){
				showCustomer();
			}
			else{
				showEmployee();
			}
		}
	}
	private class previousListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(currentIndex > 0){
				currentIndex--;
			}
			if(customers){
				showCustomer();
			}
			else{
				showEmployee();
			}
		}
	}
	private class lastListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(customers){
				currentIndex = m.getCustomersSize() - 1;
			}
			else{
				currentIndex = m.getEmployeesSize() - 1;
			}
			if(customers){
				showCustomer();
			}
			else{
				showEmployee();
			}
		}
	}
	private class firstListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			currentIndex = 0;
			if(customers){
				showCustomer();
			}
			else{
				showEmployee();
			}
		}
		
	}
}
