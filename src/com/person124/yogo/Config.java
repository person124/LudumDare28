package com.person124.yogo;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.person124.yogo.input.Keyboard;
import com.person124.yogo.sound.Audio;

public class Config {
	
	public static File file = new File(Game.NAME.toLowerCase().replace(" ", "_") + ".properties");
	private static Properties prop = new Properties();
	private static FileOutputStream out;
	private static FileInputStream in;
	
	public static void createConfig() {
		if (file.exists()) return;
		else {
			try {
				file.createNewFile();
				out = new FileOutputStream(file);
				
				prop.setProperty("left", String.valueOf(Keyboard.leftInt));
				prop.setProperty("right", String.valueOf(Keyboard.rightInt));
				prop.setProperty("jump", String.valueOf(Keyboard.jumpInt));
				prop.setProperty("pickup", String.valueOf(Keyboard.pickupInt));
				prop.setProperty("debug", String.valueOf(Keyboard.debugInt));
				
				prop.setProperty("music", String.valueOf(Audio.playMusic));
				prop.setProperty("effect", String.valueOf(Audio.playEffects));
				
				prop.setProperty("screenSize", String.valueOf(Launcher.defaultScreenSize));
				
				prop.store(out, "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadData() {
		if (file.exists()) {
			Keyboard.leftInt = getInt("left");
			Keyboard.rightInt = getInt("right");
			Keyboard.jumpInt = getInt("jump");
			Keyboard.pickupInt = getInt("pickup");
			Keyboard.debugInt = getInt("debug");
			
			Audio.playMusic = getBoolean("music");
			Audio.playEffects = getBoolean("effect");
			
			Launcher.defaultScreenSize = getInt("screenSize");
		} else {
			createConfig();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void reset() {
		if (file.exists()) {
			try {
				out = new FileOutputStream(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
				out = new FileOutputStream(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		prop.setProperty("left", String.valueOf(KeyEvent.VK_A));
		prop.setProperty("right", String.valueOf(KeyEvent.VK_D));
		prop.setProperty("jump", String.valueOf(KeyEvent.VK_SPACE));
		prop.setProperty("pickup", String.valueOf(KeyEvent.VK_E));
		prop.setProperty("debug", String.valueOf(KeyEvent.VK_F5));
		
		prop.setProperty("music", String.valueOf(true));
		prop.setProperty("effect", String.valueOf(true));
		
		prop.setProperty("screenSize", String.valueOf(3));
		
		prop.save(out, "");
	}
	
	@SuppressWarnings("deprecation")
	public static void set(String key, String value) {
		try {
			out = new FileOutputStream(file);
			prop.setProperty(key, String.valueOf(value));
			prop.save(out, file.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getString(String key) {
		if (!file.exists()) return null;
		try {
			in = new FileInputStream(file);
			prop.load(in);
			return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getInt(String key) {
		return Integer.parseInt(getString(key));
	}
	
	public static boolean getBoolean(String key) {
		String temp = getString(key);
		if (temp.equalsIgnoreCase("true")) return true;
		if (temp.equalsIgnoreCase("false")) return false;
		return false;
	}
	
}
