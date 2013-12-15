package com.person124.yogo.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	
	public final int SIZE;
	public int[] pixels;
	
	public static Sprite nullSprite = new Sprite(32, 0x000000);
	
	//Player stuff
	public static Sprite player_stand_right = new Sprite(32, "/textures/player_right.png");
	public static Sprite player_stand_left = player_stand_right.reverseSprite();
	public static Sprite player_hold_right = new Sprite(32, "/textures/player_hold_right.png");
	public static Sprite player_hold_left = player_hold_right.reverseSprite();
	
	//Entities
	public static Sprite window = new Sprite(64, "/textures/window.png");
	public static Sprite door = new Sprite(32, "/textures/door.png");
	
	//Mobs
	public static Sprite crate = new Sprite(32, "/textures/crate.png");
	
	//Normal Tiles
	public static Sprite stone_wall = new Sprite(32, "/textures/stone_wall.png");
	public static Sprite stone_wall_stand = new Sprite(32, "/textures/stone_wall_stand.png");
	public static Sprite elevator = new Sprite(32, "/textures/elevator.png");
	
	//Backgrounds
	public static Sprite bg_stone_wall = new Sprite(32, "/textures/bg_stone_wall.png");
	
	//pad
	public static Sprite pad_off = new Sprite(32, "/textures/pad_off.png");
	public static Sprite pad_on = new Sprite(32, "/textures/pad_on.png");
	
	//Doors
	public static Sprite door_closed = new Sprite(32, "/textures/door_closed.png");
	public static Sprite door_open = new Sprite(32, "/textures/door_open.png");
	
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
	
	public Sprite(int s, int[] p) {
		SIZE = s;
		pixels = new int[SIZE * SIZE];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = p[i];
		}
	}
	
	public Sprite reverseSprite() {
		int[] temp = new int[pixels.length];
		int size = (int) Math.sqrt(pixels.length);
		int sizeMask = size - 1;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				temp[x + y * size] = pixels[(sizeMask - x) + y * size];
			}
		}
		return new Sprite(size, temp);
	}
	
}
