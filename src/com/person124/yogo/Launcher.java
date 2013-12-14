package com.person124.yogo;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Launcher extends JFrame {
	private static final long serialVersionUID = 1L;

	private final String NAME = Game.NAME;
	
	private JPanel panel;
	private JTextArea text;
	private JTextPane sizeOut;
	private JTextField sizeIn;
	private static JButton start, info, controls, exit;
	
	public static int defaultScreenSize = 3;
	
	private void begin() {
		text.append("Hello!\n");
		text.append("Welcome to: " + NAME + "!\n");
		text.append("Type a number inside the \"Scale:\" and hit enter to change\n");
		text.append("the size when you start (default: " + defaultScreenSize + ")\n\n");
		text.append("If the game doesn't start,\ndelete data.properties and try again.");
	}
	
	private Launcher() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setTitle(NAME);
		setSize(400, 530);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		setContentPane(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		
		text = new JTextArea();
		text.setBounds(5, 5, 390, 200);
		text.setEditable(false);
		text.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Info"));
		panel.add(text);
		
		sizeIn = new JTextField(String.valueOf(defaultScreenSize));
		sizeIn.setBounds(5 + 53, 210, 75, 45);
		sizeIn.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Scale:"));
		sizeIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!e.getActionCommand().isEmpty()) {
					sizeOut.setText(getSize(sizeIn.getText()));
				}
			}
		});
		panel.add(sizeIn);
		
		sizeOut = new JTextPane();
		sizeOut.setBounds(90 + 53, 210, 200, 45);
		sizeOut.setEditable(false);
		sizeOut.setText(getSize(sizeIn.getText()));
		sizeOut.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Window Size:"));
		panel.add(sizeOut);
		
		start = new JButton("Start Game");
		start.setBounds(100, 265, 200, 50);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] args = new String[] { sizeIn.getText() };
				Game.start(args);
				closeWindow();
			}
		});
		panel.add(start);
		
		info = new JButton("Infomation");
		info.setEnabled(false);
		info.setBounds(100, 325, 200, 50);
		info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(info);
		
		controls = new JButton("Options");
		controls.setEnabled(false);
		controls.setBounds(100, 385, 200, 50);
		controls.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel.add(controls);
		
		exit = new JButton("Quit");
		exit.setBounds(100, 445, 200, 50);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		panel.add(exit);
		
		begin();
	}
	
	private void closeWindow() {
		setVisible(false);
		dispose();
	}
	
	private String getSize(String s) {
		int scale = Integer.parseInt(s);
		return (Game.WIDTH * scale) + "x" + (scale * Game.HEIGHT);
	}
	
	public static void main(String[] main) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Launcher launcher = new Launcher();
				launcher.setVisible(true);
			}
		});
	}
	
}