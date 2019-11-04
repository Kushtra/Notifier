package dev.arixs.appNotifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Check implements Runnable {

	private Thread thread;
	private int id;
	private boolean running;
	private long miniCounter, counter, delta, now, lastTime = System.nanoTime();
	
	public Check() {
		id = -1;
	}
	
	public void run() {
		int fps = 2;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				delta--;
			}
		}
		
		stop();
	}
	
	public synchronized void start(int id) {
		if(running)
			return;
		else {
			this.id = id;
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		try {
			thread.join();
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	private void tick() {
		now = System.nanoTime();
		delta = now - lastTime;
		lastTime = now;
		miniCounter += delta;
		if(miniCounter > 1000000000) {
			counter++;
			miniCounter = 0;
		}
		if(counter >= 2) {
			doCheck();
			counter = 0;
		}
		delta = 0;
	}
	
	private void doCheck() {
		try {
			String buffer;
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\"+"tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while((buffer = input.readLine()) != null) {
				String nameCheck = buffer.toString();
				
				switch(id) {
					case Window.LOL_ID:
						if(!nameCheck.equalsIgnoreCase("league of legends.exe")) stop();
						break;
					case Window.CS_ID:
						if(!nameCheck.equalsIgnoreCase("csgo.exe")) stop();
						break;
					case Window.OW_ID:
						if(!nameCheck.equalsIgnoreCase("overwatch.exe")) stop();
						break;
					case Window.RL_ID:
						if(!nameCheck.equalsIgnoreCase("rocketleague.exe")) stop();
						break;
					case Window.MC_ID:
						if(!nameCheck.equalsIgnoreCase("javaw.exe")) stop();
						break;
					case Window.PUBG_ID:
						if(!nameCheck.equalsIgnoreCase("tslgame.exe")) stop();
						break;
				}
			}
			input.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
