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

	private int getUsage(String task) {
		if(task == "League of Legends.exe") return -1;

		char[] arr = task.toCharArray();
		String storage = "";
		int finale = -1;

		for(int i=0;i<arr.length;i++) {
			if(i >= arr.length - 9) {
				if(arr[i] != '.' && arr[i] != ' ' && arr[i] != 'K') storage += arr[i];
			}
		}		
		finale = Integer.parseInt(storage);

		return finale;
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
					System.out.print("RAM used: " + getUsage(buffer) + " ");
					if(getUsage(buffer) == -1 || getUsage(buffer) < 150000) {
						
					}
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
