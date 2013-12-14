package com.person124.yogo.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	
	public final int SIZE;
	public int[] pixels;
	
	public static Sprite nullSprite = new Sprite(32, 0x000000);
	
	//Player stuff
	public static Sprite player_stand_right = new Sprite(32, "/textures/player_right_stand.png");
	public static Sprite player_stand_left = new Sprite(32, "/textures/player_left_stand.png");
	
	//Mobs
	public static Sprite crate = new Sprite(32, "/textures/crate.png");
	
	//Normal Blocks
	public static Sprite stone_wall = new Sprite(32, "/textures/stone_wall.png");
	
	//Backgrounds
	public static Sprite bg_stone_wall = new Sprite(32, "/textures/bg_stone_wall.png");
	
	//pad
	public static Sprite pad_off = new Sprite(32, "/textures/pad_off.png");
	public static Sprite pad_on = new Sprite(32, "/textures/pad_on.png");
	
	public Sprite(int s, int color) {
		SIZE = s;
		pixels = new int[SIZE * SIZE];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	public Sprite(int s, String path) {
		SIZE = s;
		pixels = new int[SIZE * SIZE];
		try {
			BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
			image.getRGB(0, 0, SIZE, SIZE, pixels, 0, SIZE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
