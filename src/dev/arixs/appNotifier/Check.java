package dev.arixs.appNotifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Check {

	private String app;
	private int id;
	private boolean running;
	private boolean done;
	
	public Check(int id) {
		this.id = id;
		switch(this.id) {
			case 1:
				app = "league of legends.exe";
				break;
			case 2:
				app = "overwatch.exe";
				break;
			case 3:
				app = "rocketleague.exe";
				break;
			case 4:
				app = "javaw.exe";
				break;
			case 5:
				app = "tslgame.exe";
				break;
			case 6:
				app = "csgo.exe";
				break;
			case 7:
				app = "dota2.exe";
				break;
		}
	}
	
	public synchronized void start() {
		running = true;
		long now;
		long lastTime = System.nanoTime();
		double delta = 0;
		double fps = 2.0;
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
