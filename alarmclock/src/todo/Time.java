package todo;

public class Time {
	int time;
	int setTime;
	long latestUpdate;
	
	public Time() {
		time = 0;
		setTime = 0;
		latestUpdate= System.currentTimeMillis();
	}
	public void update() {
		long diff = System.currentTimeMillis() - latestUpdate;
		latestUpdate = System.currentTimeMillis();
		update(diff);
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public void update(long addTime){
		time += (int) addTime;
	}
}