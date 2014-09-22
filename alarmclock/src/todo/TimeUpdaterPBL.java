package todo;

public class TimeUpdater {
	private long latestUpdate;
	private CurrentTime currentTime;
	
	public TimeUpdater(){
		latestUpdate = System.currentTimeMillis();
		currentTime = new CurrentTime();
	}
	
	public void update(){
		long diff = latestUpdate - System.currentTimeMillis();
		if(diff >= 1000){
			currentTime.incTime();
			latestUpdate += 1000; 
		}
	}
}
