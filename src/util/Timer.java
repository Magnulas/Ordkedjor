package util;

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
	
	public void start(){
		time = System.currentTimeMillis();
		stop = 0;
		pausedTime = 0;
	}
	
	public long getElapsedTime(){
		return System.currentTimeMillis() - (time + pausedTime);
	}

	public void cont() {
		pausedTime += System.currentTimeMillis() - stop;
		stopped = false;
	}

	public void stop() {
		
		if(!stopped){
			stop = System.currentTimeMillis();
			stopped = true;
		}
	}
}
