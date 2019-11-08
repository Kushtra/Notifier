package dev.arixs.appNotifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Check {

	private int id;
	private boolean running;
	
	public Check(int id) {
		this.id = id;
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
			//maybe put if insted of while
			while(delta >= 1) {
				tick();
				delta--;
			}
		}
	}
	
	private void tick() {
		doCheck();
	}
	
	private void doCheck() {
		try {
			String buffer;
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\"+"tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			List<String> allProcess = new ArrayList<String>();
			while((buffer = input.readLine()) != null) {
				String nameCheck = buffer.toString();
				allProcess.add(nameCheck);
			}
			input.close();
			
			int counter = 0;
			boolean[] isActive = new boolean[allProcess.size()];
			for(String s: allProcess) {
				switch(id) {
				case Window.LOL_ID:
					if(s.equalsIgnoreCase("league of legends.exe")) isActive[counter] = true;
					break;
				case Window.OW_ID:
					if(s.equalsIgnoreCase("overwatch.exe")) isActive[counter] = true;
					break;
				case Window.RL_ID:
					if(s.equalsIgnoreCase("rocketleague.exe")) isActive[counter] = true;
					break;
				case Window.MC_ID:
					if(s.equalsIgnoreCase("javaw.exe")) isActive[counter] = true;
					break;
				case Window.PUBG_ID:
					if(s.equalsIgnoreCase("tslgame.exe")) isActive[counter] = true;
					break;
				case Window.CS_ID:
					if(s.contains("csgo.exe")) isActive[counter] = true;
					break;
				}
				counter++;
			}

			int booleanCounter = 0;
			for(boolean b: isActive) {
				if(b) booleanCounter++;
			}
			
			if(booleanCounter == 0) running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
