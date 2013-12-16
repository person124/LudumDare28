package com.person124.yogo.graphics;

import com.person124.yogo.Game;

public class Render {
	
	public int[] pixels;
	private int xOff = 0, yOff = 0;
	
	public Render(int w, int h) {
		pixels = new int[w * h];
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void setOffset(int x, int y) {
		xOff = x;
		yOff = y;
	}
	
	public void renderSprite(Sprite sprite, int xp, int yp) {
		xp -= xOff;
		yp -= yOff;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				
				if (xa < -sprite.SIZE || xa >= Game.WIDTH || ya < 0 || ya >= Game.HEIGHT) break;
				if (xa < 0) xa = 0;
				
				int col = sprite.pixels[x + y * sprite.SIZE];
				if (col != 0xffff00ff) pixels[xa + ya * Game.WIDTH] = col;
			}
		}
	}
	
	public void renderScreen(Screen screen) {
		for (int y = 0; y < Game.HEIGHT; y++) {
			for (int x = 0; x < Game.WIDTH; x++) {
				pixels[x + y * Game.WIDTH] = screen.pixels[x + y * Game.WIDTH];
			}
		}
	}
	
}
