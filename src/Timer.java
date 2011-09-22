
/**
 * A timer that helps measure execution time.
 * 
 * @author Magnus
 *
 */
public class Timer {

	private long time;
	private long stop;
	private long pausedTime;
	
	boolean stopped;
	
	public Timer(){
		time = 0;
		stop = 0;
		pausedTime = 0;
		stopped = false;
	}
	
	/**
	 * Starts the timer. Also works for resetting the timer.
	 * 
	 */
	public void start(){
		time = System.currentTimeMillis();
		stop = 0;
		pausedTime = 0;
	}
	

	/**
	 * Restarts the timer.
	 * 
	 */
	public void restart() {
		start();
	}
	
	/**
	 * Gets the time that has elapsed since start() was called.
	 * @return The elapsed time since start() was called in milliseconds
	 */
	public long getElapsedTime(){
		
		if(stopped){
			cont();
		}
		
		return System.currentTimeMillis() - (time + pausedTime);
	}

	/**
	 * Makes the timer run again, should only be called after
	 * stop() has been called.
	 */
	public void cont() {
		pausedTime += System.currentTimeMillis() - stop;
		stopped = false;
	}

	/**
	 * Stops the timer. To start the timer again call cont()
	 */
	public void stop() {
		
		if(!stopped){
			stop = System.currentTimeMillis();
			stopped = true;
		}
	}
}
