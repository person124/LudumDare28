package com.person124.yogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Info extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JTextArea text;
	private JButton exit;
	
	private Info() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setTitle(Game.NAME + " ~ Infomation");
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		setContentPane(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		
		text = new JTextArea();
		text.setBounds(5, 5, 590, 500);
		text.setEditable(false);
		text.setBorder(BorderFactory.createEtchedBorder());
		panel.add(text);
		
		exit = new JButton("Close");
		exit.setBounds(200, 515, 200, 50);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Launcher.enableButtons(true);
				setVisible(false);
				dispose();
			}
		});
		panel.add(exit);
		
		begin();
	}
	
	private void begin() {
		text.append(Game.NAME + " Infomation:\n");
		text.append("You are Bob, a robot.\n");
		text.append("Your job is to get through the chambers without dying.\n");
		text.append("Becareful though lava hurts, and YOU ONLY HAVE ONE try per chamber.\n");
		text.append("Also as a side note, there are 8 chambers, and when you are done,\nyou will recive a message from me.\n\n");
		
		text.append("Use: " + KeyEvent.getKeyText(Config.getInt("left")) + " & " + KeyEvent.getKeyText(Config.getInt("right")) + " to move.\n");
		text.append(KeyEvent.getKeyText(Config.getInt("jump")) + " to jump.\n");
		text.append(KeyEvent.getKeyText(Config.getInt("pickup")) + " to pickup and putdown boxes.\n\n");
		
		text.append("If you are getting a lot of lag, or are on a bad machine,\n in options you can turn off the lava animations.\n\n");
		
		text.append("Thank you for playing, and remember: Have fun!\n\nGame made by:\n");
		text.append("Person124, 2013, For Ludum Dare 28");
	}
	
	public static void openInfoScreen() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Info info = new Info();
				info.setVisible(true);
			}
		});
	}
	
}
