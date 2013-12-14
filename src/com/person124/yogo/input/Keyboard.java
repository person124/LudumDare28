package com.person124.yogo.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener, FocusListener {
	
	public boolean[] keys = new boolean[25565];
	public boolean up, down, left, right, jump, pickup, debug = false;
	
	@Override
	public void focusGained(FocusEvent e) {
		
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		if (keys[KeyEvent.VK_F5]) {
			keys[KeyEvent.VK_5] = false;
			debug = !debug;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		jump = keys[KeyEvent.VK_SPACE];
		pickup = keys[KeyEvent.VK_E];
	}
	
}
