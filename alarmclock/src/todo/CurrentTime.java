package todo;

public class CurrentTime {
	private int hours;
	private int minutes;
	private int seconds;
	
	public CurrentTime(){
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}
	
	//increases the time with one second
	public void incTime(){
		if(seconds + 1 < 60){
			seconds++;
		} else if(minutes + 1 < 60){
			minutes++;
			seconds = 0;
		} else if(hours + 1 < 24){
			hours++;
			minutes = 0;
			seconds = 0; 
		} else{
			hours = 0;
			minutes = 0;
			seconds = 0;
		}
	}
	
	public void setTime(int hhmmss){
		hours = hhmmss / 10000;
		minutes = (hhmmss / 100) % 100;
		seconds = hhmmss % 100;
				
	}
	
	//returns the value as hhmmss
	//funkar verkligen detta?????????
	public int getTime(){
		String h = ensureTwoDigits(hours);
		String m= ensureTwoDigits(minutes);
		String s= ensureTwoDigits(seconds);
		String hms = h + m + s;
		return Integer.parseInt(hms);
	}
	
	private String ensureTwoDigits(int value){
		if(value / 10 == 0){
			String strVal = Integer.toString(value);
			strVal = "0" + strVal;
			return strVal;
		}else{
			return Integer.toString(value);
		}
	}
}
