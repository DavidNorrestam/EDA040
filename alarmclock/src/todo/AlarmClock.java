package todo;
import done.*;
import se.lth.cs.realtime.semaphore.Semaphore;
import se.lth.cs.realtime.semaphore.MutexSem;

public class AlarmClock extends Thread{
	private static final int UNINITIALIZED = -1;
	private static ClockInput	input;
	private static ClockOutput	output;
	private static Semaphore	sem; 
	protected int alarmTime;
	protected Time time;
	
	private int lastChoice; 

	public AlarmClock(ClockInput i, ClockOutput o) {
		input = i;
		output = o;
		sem = input.getSemaphoreInstance();
		lastChoice = UNINITIALIZED;
		alarmTime = 0;
	}
	
	// The AlarmClock thread is started by the simulator. No
	// need to start it by yourself, if you do you will get
	// an IllegalThreadStateException. The implementation
	// below is a simple alarmclock thread that beeps upon 
	// each keypress. To be modified in the lab.
	public void run() {
		// Start new thread that handles updating of time
		TimeUpdater timeUpdater = new TimeUpdater(this, input, output);
		timeUpdater.start();
		
		// Run while AlarmClock thread does not get interrupted
		while (!isInterrupted()) {
			// Wait for user interaction
			sem.take();
			int choice = input.getChoice();
			if (choice != lastChoice) {
				switch(lastChoice) {
				case ClockInput.SET_ALARM:
					setAlarm();
					break;
				case ClockInput.SET_TIME:
					setTime();
					break;
				case ClockInput.SHOW_TIME:
					showTime();
					break;
				default:
					// Last choice was uninitialized
				}	
				lastChoice = choice;
			}
			
		}
		
		// Interrupt timeUpdater thread and wait for it to terminate.
		timeUpdater.interrupt();
		while(timeUpdater.isAlive()){
			try {
				timeUpdater.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		// Return, program shuts down
	}
	
	private void showTime() {
		
	}

	private void setTime() {
		time.setTime(input.getValue());	
	}

	private void setAlarm() {
		alarmTime = input.getValue();
	}

	// Should wait for AlarmClock thread to terminate. Correct syntax?
	public void terminate() {
		this.interrupt();
		while(this.isAlive()) {
			try {
				this.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
