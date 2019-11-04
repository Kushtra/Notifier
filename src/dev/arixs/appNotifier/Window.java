package dev.arixs.appNotifier;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window {

	public static final int LOL_ID = 1;
	public static final int OW_ID = 2;
	public static final int RL_ID = 3;
	public static final int MC_ID = 4;
	public static final int PUBG_ID = 5;
	public static final int CS_ID = 6;
	
	private String windowTitle = "Notifier!";
	private JFrame frame;
	private JPanel panel;
	
	private JButton start = new JButton("Start");
	private JButton cancel = new JButton("Cancel");
	
	private JButton lol = new JButton("League of Legends");
	private JButton ow = new JButton("Overwatch");
	private JButton rl = new JButton("Rocket League");
	private JButton mc = new JButton("Minecraft");
	private JButton pubg = new JButton("PUBG");
	private JButton cs = new JButton("Counter Strike GO");
	
	private int id;
	private String reminder;
	
	public Window() {
		frame = new JFrame(windowTitle);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel = new JPanel(new FlowLayout());
		panel.setBackground(new Color(128, 64, 255));
		
		enableAllButtons();
		panel.add(start);
		panel.add(lol);
		panel.add(ow);
		panel.add(rl);
		panel.add(mc);
		panel.add(pubg);
		panel.add(cs);
		panel.add(cancel);
		
		lol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select(LOL_ID);
			}
		});
		ow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select(OW_ID);
			}
		});
		rl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select(RL_ID);
			}
		});
		mc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select(MC_ID);
			}
		});
		pubg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select(PUBG_ID);
			}
		});
		cs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select(CS_ID);
			}
		});
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//start thread ask for reminder and go invi
				reminder = JOptionPane.showInputDialog("Reminder: ");
				frame.setVisible(false);
				Check check = new Check();
				check.start(id);
				frame.setVisible(true);
				if(reminder.equals("")) {
					JOptionPane.showMessageDialog(null, "Reminding you!");
				}else {
					JOptionPane.showMessageDialog(null, reminder);
				}
				enableAllButtons();
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deselect();
			}
		});
		
		frame.setVisible(true);
	}
	
	private void select(int id) {
		start.setEnabled(true);
		cancel.setEnabled(true);
		
		lol.setEnabled(true);
		ow.setEnabled(true);
		rl.setEnabled(true);
		mc.setEnabled(true);
		pubg.setEnabled(true);
		cs.setEnabled(true);
		
		switch(id) {
			case LOL_ID:
				lol.setEnabled(false);
				break;
			case OW_ID:
				ow.setEnabled(false);
				break;
			case RL_ID:
				rl.setEnabled(false);
				break;
			case MC_ID:
				mc.setEnabled(false);
				break;
			case PUBG_ID:
				pubg.setEnabled(false);
				break;
			case CS_ID:
				cs.setEnabled(false);
				break;
		}
		
		this.id = id;
	}
	
	private void enableAllButtons() {
		lol.setEnabled(true);
		ow.setEnabled(true);
		rl.setEnabled(true);
		mc.setEnabled(true);
		pubg.setEnabled(true);
		cs.setEnabled(true);
		
		start.setEnabled(false);
		cancel.setEnabled(false);
		
		reminder = "";
		id = -1;
	}
	
	private void deselect() {
		id = -1;
		enableAllButtons();
	}

}
