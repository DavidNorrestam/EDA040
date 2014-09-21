package todo;

import se.lth.cs.realtime.semaphore.Semaphore;
import done.ClockInput;
import done.ClockOutput;

public class TimeUpdater extends Thread {
	private AlarmClock alarmClock;
	private ClockInput input;
	private ClockOutput output;
	private Semaphore sem; 
	
	public TimeUpdater(AlarmClock alarmClock, ClockInput input, ClockOutput output) {
		this.alarmClock = alarmClock;
		this.input = input;
		this.output = output;
		this.sem = input.getSemaphoreInstance(); 
	}
	
	public void run() {
		// Run while interrupt has not been requested.
		while(!isInterrupted()) {
			// Every second, call show time with the correct updated time
			long timeNow = System.currentTimeMillis();
			long timeDiff = System.currentTimeMillis() - timeNow;
			
			while(timeDiff < 1000){
				timeDiff = System.currentTimeMillis() - timeNow;
				try {
					sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			// If alarm is turned on, check if it's time to beep.
			
		}
	}
}