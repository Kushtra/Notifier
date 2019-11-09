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
	public static final int DOTA_ID = 7;
	
	private final String lolName = "League of Legends";
	private final String owName = "Overwatch";
	private final String rlName = "Rocket League";
	private final String mcName = "Minecraft";
	private final String pubgName = "PUBG";
	private final String csName = "Counter-Strike: GO";
	private final String dotaName = "Dota 2";
	
	private String windowTitle = "Notifier v2.0";
	private JFrame frame;
	private JPanel panel;
	
	private JButton start = new JButton("Start");
	private JButton cancel = new JButton("Exit");
	
	private JButton lol = new JButton(lolName);
	private JButton ow = new JButton(owName);
	private JButton rl = new JButton(rlName);
	private JButton mc = new JButton(mcName);
	private JButton pubg = new JButton(pubgName);
	private JButton cs = new JButton(csName);
	private JButton dota = new JButton(dotaName);
	
	private int id, lastID;
	private String reminder;
	private Check check;
	
	public Window() {
		panel = new JPanel(new FlowLayout());
		panel.setBackground(new Color(128, 64, 255));
		
		frame = new JFrame(windowTitle);
		frame.setSize(340, 140);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		cancel.setEnabled(true);
		enableAllButtons();
		
		panel.add(start);
		panel.add(lol);
		panel.add(ow);
		panel.add(rl);
		panel.add(mc);
		panel.add(pubg);
		panel.add(cs);
		panel.add(dota);
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
		dota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select(DOTA_ID);
			}
		});
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				begin();
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == -1) {
					System.exit(0);
				} else {
					enableAllButtons();
				}
			}
		});
		
		frame.setVisible(true);
	}
	
	private void begin() {
		lastID = id;
		reminder = JOptionPane.showInputDialog("Reminder: ");
		if(reminder == null) {
			reminder = "";
			return;
		}
		frame.setVisible(false);
		
		check = new Check(id);
		check.start();
		check = null;
		
		frame.setVisible(true);
		if(reminder.equals("")) {
			JOptionPane.showMessageDialog(null, "Reminding you, becasue you ended " + getName());
		} else {
			JOptionPane.showMessageDialog(null, reminder);
		}
		enableAllButtons();
	}
	
	private String getName() {
		switch(lastID) {
			case LOL_ID:
				return lolName;
			case OW_ID:
				return owName;
			case RL_ID:
				return rlName;
			case MC_ID:
				return mcName;
			case PUBG_ID:
				return pubgName;
			case CS_ID:
				return csName;
			case DOTA_ID:
				return dotaName;
			default:
				return "Error getting name";
		}
	}
	
	private void select(int id) {
		start.setEnabled(true);
		
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
		
		cancel.setText("Cancel");
		
		this.id = id;
	}
	
	private void enableAllButtons() {
		lol.setEnabled(true);
		ow.setEnabled(true);
		rl.setEnabled(true);
		mc.setEnabled(true);
		pubg.setEnabled(true);
		cs.setEnabled(true);
		dota.setEnabled(true);
		start.setEnabled(false);
		
		reminder = "";
		id = -1;
		lastID = -1;
		cancel.setText("Exit");
	}
	
}
