package com.person124.yogo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Controls extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JList<String> list;
	private DefaultListModel<String> model;
	private JButton exit, reset;
	
	public Controls() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setTitle(Game.NAME + " ~ Options");
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panel = new JPanel();
		setContentPane(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setBounds(5, 5, 500, 500);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int sel = list.getSelectedIndex();
				if (sel == 0) Config.set("left", String.valueOf(e.getKeyCode()));
				if (sel == 1) Config.set("right", String.valueOf(e.getKeyCode()));
				if (sel == 2) Config.set("jump", String.valueOf(e.getKeyCode()));
				if (sel == 3) Config.set("pickup", String.valueOf(e.getKeyCode()));
				if (sel == 4) Config.set("debug", String.valueOf(e.getKeyCode()));
				if (sel == 5) {
					if (e.getKeyCode() == KeyEvent.VK_Y) Config.set("music", String.valueOf(true));
					if (e.getKeyCode() == KeyEvent.VK_N) Config.set("music", String.valueOf(false));
				}
				if (sel == 6) {
					if (e.getKeyCode() == KeyEvent.VK_Y) Config.set("effect", String.valueOf(true));
					if (e.getKeyCode() == KeyEvent.VK_N) Config.set("effect", String.valueOf(false));
				}
				if (sel == 7) {
					if (e.getKeyCode() == KeyEvent.VK_Y) Config.set("renderLava", String.valueOf(true));
					if (e.getKeyCode() == KeyEvent.VK_N) Config.set("renderLava", String.valueOf(false));
				}
				refresh();
			}
		});
		panel.add(list);
		
		reset = new JButton("Reset to Defaults");
		reset.setBounds(100, 515, 200, 50);
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Config.reset();
				refresh();
			}
		});
		panel.add(reset);
		
		exit = new JButton("Close");
		exit.setBounds(310, 515, 200, 50);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Launcher.enableButtons(true);
				setVisible(false);
				dispose();
			}
		});
		panel.add(exit);
		
		refresh();
	}
	
	public void refresh() {
		model.clear();
		Config.createConfig();
		model.add(0, "Move Left: " + KeyEvent.getKeyText(Config.getInt("left")));
		model.add(1, "Move Right: " + KeyEvent.getKeyText(Config.getInt("right")));
		model.add(2, "Jump: " + KeyEvent.getKeyText(Config.getInt("jump")));
		model.add(3, "Pickup/Putdown Boxes: " + KeyEvent.getKeyText(Config.getInt("pickup")));
		model.add(4, "Show FPS: " + KeyEvent.getKeyText(Config.getInt("debug")));
		String music = "Y", effect = "Y", lava = "Y";
		if (!Config.getBoolean("music")) music = "N";
		if (!Config.getBoolean("effect")) effect = "N";
		if (!Config.getBoolean("renderLava")) lava = "N";
		model.add(5, "Play Music (Y/N): " + music);
		model.add(6, "Player Sound Effects (Y/N): " + effect);
		model.add(7, "Do Lava Animations (Y/N): " + lava); 
	}
	
	public static void openWindow() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Controls con = new Controls();
				con.setVisible(true);
			}
		});
	}
	
}
