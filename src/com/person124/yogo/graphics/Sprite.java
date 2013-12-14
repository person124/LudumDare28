package com.person124.yogo.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	
	public final int SIZE;
	public int[] pixels;
	
	public static Sprite nullSprite = new Sprite(32, 0x000000);
	
	public static Sprite test = new Sprite(32, "/textures/test.png");
	public static Sprite stone_wall = new Sprite(32, "/textures/stone_wall.png");
	
	public static Sprite bg_stone_wall = new Sprite(32, "/textures/bg_stone_wall.png");
	
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
