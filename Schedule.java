import java.util.ArrayList;

public class Schedule {
	
	private ArrayList<DateTime> schedule; 
	
	public Schedule(){
		schedule = new ArrayList<DateTime>();
	}
	
	public void addDateTime(int index, DateTime d){
		schedule.add(index, d);
	}
	
	public void addDateTime(DateTime d){
		schedule.add(d);
	}
	
	public DateTime getSchedule(int index){
		return schedule.get(index);
	}
	
	public int length(){
		return schedule.size();
	}
}