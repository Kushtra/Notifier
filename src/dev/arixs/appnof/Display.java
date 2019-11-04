package dev.arixs.appnof;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Display {
	

		private String title = "App Ender Notf";
		private JFrame frame;
		private JPanel panel;
		
		JButton startBtn = new JButton("Start");
		JButton owBtn = new JButton("Overwatch");
		JButton lolBtn = new JButton("League Of Legends");
		JButton rlBtn = new JButton("Rocket League");
		JButton mcBtn = new JButton("Minecraft");
		JButton csBtn = new JButton("Counter Strike");
		JButton pubgBtn = new JButton("PUBG");
		JButton canBtn = new JButton("Cancel");
		
		public Display(){
			createDisplay();
		}
		
		private void createDisplay(){
			frame = new JFrame(title);
			panel = new JPanel(new FlowLayout());
			frame.setSize(1420, 1100);
			frame.setResizable(true);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(panel);
			
			panel.setBackground(new Color(11, 11, 182));
			panel.add(startBtn);
			panel.add(owBtn);
			panel.add(lolBtn);
			panel.add(rlBtn);
			panel.add(mcBtn);
			panel.add(csBtn);
			panel.add(pubgBtn);
			panel.add(canBtn);
			startBtn.setEnabled(false);
			startBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Logic.rem = JOptionPane.showInputDialog("Reminder:");
					Logic.checking = true;
					startBtn.setEnabled(false);
					frame.setVisible(false);
				}
			});
			owBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Logic.ow = true;
					owBtn.setEnabled(false);
					startBtn.setEnabled(true);
					setFalse(owBtn);
				}
			});
			lolBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Logic.lol = true;
					lolBtn.setEnabled(false);
					startBtn.setEnabled(true);
					setFalse(lolBtn);
				}
			});
			rlBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Logic.rl = true;
					rlBtn.setEnabled(false);
					startBtn.setEnabled(true);
					setFalse(rlBtn);
				}
			});
			mcBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Logic.mc = true;
					mcBtn.setEnabled(false);
					startBtn.setEnabled(true);
					setFalse(mcBtn);
				}
			});
			csBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Logic.cs = true;
					csBtn.setEnabled(false);
					startBtn.setEnabled(true);
					setFalse(csBtn);
				}
			});
			pubgBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Logic.pubg = true;
					pubgBtn.setEnabled(false);
					startBtn.setEnabled(true);
					setFalse(pubgBtn);
				}
			});
			canBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					Logic.checking = false;
					startBtn.setEnabled(true);
					Logic.lol =  false;
					lolBtn.setEnabled(true);
					Logic.ow = false;
					owBtn.setEnabled(true);
					Logic.rl = false;
					rlBtn.setEnabled(true);
					Logic.mc = false;
					mcBtn.setEnabled(true);
					Logic.cs = false;
					csBtn.setEnabled(true);
					Logic.pubg = false;
					pubgBtn.setEnabled(true);
				}
			});
			
			frame.setVisible(true);
//			frame.pack();
		}
		
		private void setFalse(JButton btn){
			if(btn != owBtn){
				owBtn.setVisible(false);
				Logic.ow = false;
			}
			if(btn != lolBtn){
				lolBtn.setVisible(false);
				Logic.lol = false;
			}
			if(btn != rlBtn){
				rlBtn.setVisible(false);
				Logic.rl = false;
			}
			if(btn != mcBtn){
				mcBtn.setVisible(false);
				Logic.mc = false;
			}
			if(btn != csBtn){
				csBtn.setVisible(false);
				Logic.cs = false;
			}
			if(btn != pubgBtn){
				pubgBtn.setVisible(false);
				Logic.pubg = false;
			}
				
		}
		
		public JFrame getFrame(){
			return frame;
		}
}
