
public class DateTime {
	
	int SHour = 0;
	int SMinute = 0;
	int EHour = 0;
	int EMinute = 0;
	int dayInt = 0;
	String day = "";
  	
	public DateTime(int SH, int SM, int EH, int EM, int D){
		SHour = SH;
		SMinute = SM;
		EHour = EH;
		EMinute = EM;
		dayInt = D;
		
		switch(D){
			case 1: day = "Sunday";
			case 2: day = "Monday";
			case 3: day = "Tuesday";
			case 4: day = "Wednesday";
			case 5: day = "Thursday";
			case 6: day = "Friday";
			case 7: day = "Saturday";
		}
	}
}
