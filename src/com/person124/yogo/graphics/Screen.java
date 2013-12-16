package com.person124.yogo.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.person124.yogo.Game;

public class Screen {
	
	public int[] pixels;
	
	public Screen(String path) {
		pixels = new int[Game.WIDTH * Game.HEIGHT];
		try {
			BufferedImage image = ImageIO.read(Screen.class.getResource(path));
			image.getRGB(0, 0, Game.WIDTH, Game.HEIGHT, pixels, 0, Game.WIDTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
