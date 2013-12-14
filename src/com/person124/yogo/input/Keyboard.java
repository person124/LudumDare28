package com.person124.yogo.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener, FocusListener {
	
	public boolean[] keys = new boolean[25565];
	public boolean left, right, jump, pickup, debug = false;
	public static int leftInt = KeyEvent.VK_A, rightInt = KeyEvent.VK_D, jumpInt = KeyEvent.VK_SPACE, pickupInt = KeyEvent.VK_E, debugInt = KeyEvent.VK_F5;
	
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
		if (keys[debugInt]) {
			keys[debugInt] = false;
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
		left = keys[leftInt];
		right = keys[rightInt];
		jump = keys[jumpInt];
		pickup = keys[pickupInt];
	}
	
}
