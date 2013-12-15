package com.person124.yogo.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Audio {
	
	private static AudioClip audio;
	public static boolean playEffects = true, playMusic = true;
	
	//List songs here:
	public static Audio trapped = new Audio("/music/trapped.wav");
	
	public Audio(String path) {
		Audio.audio = Applet.newAudioClip(Audio.class.getResource(path));
	}
	
	public void play(boolean loop) {
		if (playMusic) {
			if (loop) audio.loop();
			else audio.play();
		}
	}
	
	public void stop() {
		audio.stop();
	}
	
	public static void playSound(String path) {
		if (playEffects) {
			String temp = "/sfx/" + path.replace(".", "/") + ".wav";
			audio = Applet.newAudioClip(Audio.class.getResource(temp));
			audio.play();
		}
	}
	
}
