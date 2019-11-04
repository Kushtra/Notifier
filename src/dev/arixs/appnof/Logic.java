package dev.arixs.appnof;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class Logic implements Runnable{
	
	public Logic(){
		display = new Display();
	}
	
	private Thread thread;
	private Display display;
	private boolean running;
	public static boolean checking;
	public static boolean lol, ow, rl, mc, cs, pubg;
	public static String rem;

	public void run() {
		int fps = 2;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				delta--;
			}
		}
		
		stop();
	}
	
	public synchronized void start(){
		if(running)
			return;
		if(!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		try{
			thread.join();
			}catch(Exception e){}
	}
	
	private long miniCounter, counter, delta, now, lastTime = System.nanoTime();
	
	private void tick(){
		now = System.nanoTime();
		delta = now - lastTime;
		lastTime = now;
		miniCounter += delta;
		if(miniCounter > 1000000000){ //1 Sec
			counter++;
			miniCounter = 0;
		}
		if(counter >= 2){
			check();
			counter = 0;
		}
		delta = 0;
		
	}
	
	private void check(){
		if(checking){
			
				if(lol){
					if(!pList(0)){
						display.getFrame().setVisible(true);
						if(!rem.equals(""))
							JOptionPane.showMessageDialog(null, rem);
						else 
							JOptionPane.showMessageDialog(null, "LoL has been shutdown!");
						rem = "";
						lol = false;
						display.getFrame().setVisible(false);
					}
				}
				
				if(ow){
					if(!pList(1)){
						display.getFrame().setVisible(true);
						if(!rem.equals(""))
							JOptionPane.showMessageDialog(null, rem);
						else
							JOptionPane.showMessageDialog(null, "Overwatch has been shutdown!");
						rem = "";
						ow = false;
						display.getFrame().setVisible(false);
					}
				}
				if(rl){
					if(!pList(2)){
						display.getFrame().setVisible(true);
						if(!rem.equals(""))
							JOptionPane.showMessageDialog(null, rem);
						else
							JOptionPane.showMessageDialog(null, "Rocket league has been shutdown!");
						rem = "";
						rl = false;
						display.getFrame().setVisible(false);
					}
				}
				if(mc){
					if(!pList(3)){
						display.getFrame().setVisible(true);
						if(!rem.equals(""))
							JOptionPane.showMessageDialog(null, rem);
						else
							JOptionPane.showMessageDialog(null, "Minecraft has been shutdown!");
						rem = "";
						mc = false;
						display.getFrame().setVisible(false);
					}
				}
				if(cs){
					if(!pList(4)){
						display.getFrame().setVisible(true);
						if(!rem.equals(""))
							JOptionPane.showMessageDialog(null, rem);
						else
							JOptionPane.showMessageDialog(null, "Counter strike has been shutdown!");
						rem = "";
						cs = false;
						display.getFrame().setVisible(false);
					}
				}
				if(pubg){
					if(!pList(5)){
						display.getFrame().setVisible(true);
						if(!rem.equals(""))
							JOptionPane.showMessageDialog(null, rem);
						else
							JOptionPane.showMessageDialog(null, "PUBG has been shutdown!");
						rem = "";
						pubg = false;
						display.getFrame().setVisible(false);
					}
				}
				
				
			if(!ow && !lol && !rl && !mc && !cs && !pubg){
				checking = false;
				display.owBtn.setEnabled(true);
				display.lolBtn.setEnabled(true);
				display.rlBtn.setEnabled(true);
				display.mcBtn.setEnabled(true);
				display.csBtn.setEnabled(true);
				display.startBtn.setEnabled(true);
				display.owBtn.setVisible(true);
				display.lolBtn.setVisible(true);
				display.rlBtn.setVisible(true);
				display.mcBtn.setVisible(true);
				display.csBtn.setVisible(true);
				display.pubgBtn.setVisible(true);
				display.startBtn.setVisible(true);
				display.getFrame().setVisible(true);
			}
		}
	}
	
	private boolean pList(int x){
		try{
			String line;
			Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			  while ((line = input.readLine()) != null) { 
				  String checkName = line.toString();
				  switch(x) {
				  	case 0:
				  		if(checkName.equalsIgnoreCase("league of legends.exe")) return true;
				  		break;
				  	case 1:
				  		if(checkName.equalsIgnoreCase("overwatch.exe")) return true;
				  		break;
				  	case 2:
				  		if(checkName.equalsIgnoreCase("rocketleague.exe")) return true;
				  		break;
				  	case 3:
				  		if(checkName.equalsIgnoreCase("javaw.exe")) return true;
				  		break;
				  	case 4:
				  		if(checkName.equalsIgnoreCase("csgo.exe")) return true;
				  		break;
				  	case 5:
				  		if(checkName.equalsIgnoreCase("tslgame.exe")) return true;
				  		break;
				  }
				  /*
			//change to checking with strings equals to
		        	char[] charArray = line.toCharArray();
		        	int i = 0;
		        	if(x == 1){
			        	if(charArray[i] == 'O' && charArray[i+1] == 'v' && charArray[i+2] == 'e' && charArray[i+3] == 'r' && charArray[i+4] == 'w' && charArray[i+5] == 'a' && charArray[i+6] == 't' && charArray[i+7] == 'c' && charArray[i+8] == 'h' && charArray[i+9] == '.' && charArray[i+10] == 'e' && charArray[i+11] == 'x' && charArray[i+12] == 'e'){
			        		return true;
			        	}
		        	}
		        	else if(x == 0){
		        		if(charArray[i] == 'L' && charArray[i+1] == 'e' && charArray[i+2] == 'a' && charArray[i+3] == 'g' && charArray[i+4] == 'u' && charArray[i+5] == 'e' && charArray[i+6] == ' ' && charArray[i+7] == 'o' && charArray[i+8] == 'f' && charArray[i+9] == ' ' && charArray[i+10] == 'L' && charArray[i+11] == 'e' && charArray[i+12] == 'g' && charArray[i+13] == 'e' && charArray[i+14] == 'n' && charArray[i+15] == 'd' && charArray[i+16] == 's' && charArray[i+17] == '.' && charArray[i+18] == 'e' && charArray[i+19] == 'x' && charArray[i+20] == 'e'){
		        			return true;
		        		}
		        	}
		        	else if(x == 2){
		        		if(charArray[i] == 'R' && charArray[i+1] == 'o' && charArray[i+2] == 'c' && charArray[i+3] == 'k' && charArray[i+4] == 'e' && charArray[i+5] == 't' && charArray[i+6] == 'L' && charArray[i+7] == 'e' && charArray[i+8] == 'a' && charArray[i+9] == 'g' && charArray[i+10] == 'u' && charArray[i+11] == 'e' && charArray[i+12] == '.' && charArray[i+13] == 'e' && charArray[i+14] == 'x' && charArray[i+15] == 'e'){
		        			return true;
		        		}
		        	}else if(x == 3){
			        	if(charArray[i] == 'j' && charArray[i+1] == 'a' && charArray[i+2] == 'v' && charArray[i+3] == 'a' && charArray[i+4] == 'w' && charArray[i+5] == '.' && charArray[i+6] == 'e' && charArray[i+7] == 'x' && charArray[i+8] == 'e'){
			        		return true;
			        	}
		        	}else if(x == 4){
		        		if(charArray[i] == 'c' && charArray[i+1] == 's' && charArray[i+2] == 'g' && charArray[i+3] == 'o' && charArray[i+4] == '.' && charArray[i+5] == 'e' && charArray[i+6] == 'x' && charArray[i+7] == 'e'){
		        			return true;
		        		}
		        	}else if(x == 5){
		        		if(charArray[i] == 'T' && charArray[i+1] == 's' && charArray[i+2] == 'l' && charArray[i+3] == 'G' && charArray[i+4] == 'a' && charArray[i+5] == 'm' && charArray[i+6] == 'e' && charArray[i+7] == '.' && charArray[i+8] == 'e' && charArray[i+9] == 'x' && charArray[i+10] == 'e'){
		        			return true;
		        		}
		        	} */
		        }
			
			input.close();
		}catch(Exception e){e.printStackTrace();}
		return false;
	}
	
}
