package com.person124.yogo.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.person124.yogo.entity.Entity;
import com.person124.yogo.graphics.Render;
import com.person124.yogo.level.tile.Tile;

public class Level {
	
	public Tile[] tiles;
	private List<Entity> entities = new ArrayList<Entity>();
	public final int WIDTH, HEIGHT;
	public final TileCoordinate PLAYER_SPAWN;
	
	public Level(int size, TileCoordinate coord) {
		WIDTH = size;
		HEIGHT = size;
		PLAYER_SPAWN = coord;
		tiles = new Tile[WIDTH * HEIGHT];
		for (int i = 0; i < tiles.length; i++) {
			if (i % 2 == 0) tiles[i] = Tile.stoneWall;
			else tiles[i] = Tile.nullTile;
		}
		tiles[tiles.length / 2] = Tile.nullTile;
	}
	
	public Level(String path, TileCoordinate coord) {
		int w = 0, h = 0;
		int[] pixels = null;
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			w = image.getWidth();
			h = image.getHeight();
			pixels = new int[w * h];
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		WIDTH = w;
		HEIGHT = h;
		PLAYER_SPAWN = coord;
		tiles = new Tile[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			tiles[i] = Tile.getTile(pixels[i]);
		}
	}
	
	public void update() {
		for (Tile t : tiles) {
			t.update();
		}
		for (Entity e : entities) {
			e.update();
			if (e.isRemoved()) entities.remove(e);
		}
	}
	
	public void render(Render render, int xOff, int yOff) {
		render.setOffset(xOff, yOff);
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				getTile(x, y).render(render, x * 32, y * 32);
			}
		}
		for (Entity e : entities) {
			e.render(render);
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) return Tile.nullTile;
		return tiles[x + y * WIDTH];
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
}
