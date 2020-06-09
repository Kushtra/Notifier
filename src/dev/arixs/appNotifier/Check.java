package dev.arixs.appNotifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Check {

	private String app;
	private boolean running;
	private boolean done;
	
	public Check(String task) {
		this.app = task;
	}
	
	public synchronized void start() {
		running = true;
		long now;
		long lastTime = System.nanoTime();
		double delta = 0;
		double fps = 1.0;
		double timePerTick = 1000000000 / fps;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
		}
	}
	
	private void tick() {
		try {
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\"+"tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String buffer = "";
			done = true;
			
			while((buffer = input.readLine()) != null) {
				if(buffer.contains(app)) {
					done = false;
					break;
				}
			}
			
			if(done) {
				running = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
