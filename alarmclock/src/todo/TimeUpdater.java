package todo;

import se.lth.cs.realtime.semaphore.Semaphore;
import done.ClockInput;
import done.ClockOutput;

public class TimeUpdater extends Thread {
	private AlarmClock alarmClock;
	private ClockInput input;
	private ClockOutput output;
	private Semaphore sem;
	long lastUpdate;

	public TimeUpdater(AlarmClock alarmClock, ClockInput input,
			ClockOutput output) {
		this.alarmClock = alarmClock;
		this.input = input;
		this.output = output;
		this.sem = input.getSemaphoreInstance();
		lastUpdate = System.currentTimeMillis();
	}

	public void run() {
		// Run while interrupt has not been requested.
		while (!isInterrupted()) {
			// Every second, call show time with the correct updated time
			long timeDiff = System.currentTimeMillis() - lastUpdate;
			if (timeDiff >= 1000) {
				lastUpdate += 1000;
			} else {
				try {
					sleep(timeDiff / 2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//If alarm is turned on, check if it's time to beep.
	}
}
