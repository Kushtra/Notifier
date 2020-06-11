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

    private final double VERSION = 2.3;
    private final String windowTitle = "Notifier v" + VERSION;
    private final int numberOfApps = 7;
    private final int NONE = -1;

    private JFrame frame;
    private JPanel panel;
    private JButton start, cancel;
    private JButton[] buttons;

    private String reminder;
    private int selected;

    public Window() {
        buttons = new JButton[numberOfApps];

        panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(128, 64, 255));

        frame = new JFrame(windowTitle);
        frame.setSize(340, 140);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        start = new JButton("Start");
        cancel = new JButton("Exit");
        buttons[0] = new JButton("League of Legends");
        buttons[1] = new JButton("Overwatch");
        buttons[2] = new JButton("Rocket League");
        buttons[3] = new JButton("Minecraft");
        buttons[4] = new JButton("PUBG");
        buttons[5] = new JButton("Counter-Strike: GO");
        buttons[6] = new JButton("Dota 2");

        buttons[0].setName("League of Legends.exe");
        buttons[1].setName("overwatch.exe");
        buttons[2].setName("rocketleague.exe");
        buttons[3].setName("javaw.exe");
        buttons[4].setName("tslgame.exe");
        buttons[5].setName("csgo.exe");
        buttons[6].setName("dota2.exe");

        start.setEnabled(false);

        panel.add(start);
        for(int i=0;i<buttons.length;i++) {
            panel.add(buttons[i]);
        }
        panel.add(cancel);

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(selected == NONE) {
                    System.exit(0);
                } else {
                    reset();
                }
            }
        });
        for(JButton b: buttons) {
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    select(b.getText());
                }
            });
        }

        frame.setVisible(true);
    }

    private void start() {
        reminder = JOptionPane.showInputDialog("Reminder: ");
        if(reminder == null) reminder = "";

        frame.setVisible(false);

        Check check = new Check(buttons[selected].getName());
        check.start();

        frame.setVisible(true);

        if(reminder.equals("")) {
            JOptionPane.showMessageDialog(null, "Reminding you, because " + buttons[selected].getText() + " has been shutdown!");
        } else {
            JOptionPane.showMessageDialog(null, reminder);
        }
        reset();
    }

    private void select(String name) {
        start.setEnabled(true);

        for(int i=0;i<buttons.length;i++){
            if(buttons[i].getText() == name) {
                buttons[i].setEnabled(false);
                selected = i;
            } else {
                buttons[i].setEnabled(true);
            }
        }

        cancel.setText("Cancel");
    }

    private void reset() {
        for(int i=0;i<buttons.length;i++) {
            buttons[i].setEnabled(true);
        }
        start.setEnabled(false);

        reminder = "";
        selected = NONE;
        cancel.setText("Exit");
    }

}
