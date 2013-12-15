package com.person124.yogo.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import com.person124.yogo.entity.Entity;

public class LevelData {
	
	private static HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
	static {
		hash.put(0xff663300, 0);
		hash.put(0xff0073bb, 1);
		hash.put(0xff363636, 2);
	}
	
	public List<Entity> entities = new ArrayList<Entity>();
	public final TileCoordinate SPAWN;
	public int[] doorPixels;
	
	public static LevelData level1 = new LevelData("/levels/data/level1");
	public static LevelData level2 = new LevelData("/levels/data/level2");
	public static LevelData level3 = new LevelData("/levels/data/level3");
	public static LevelData level4 = new LevelData("/levels/data/level4");
	
	public LevelData(String path) {
		int size = 0;
		int[] pixels = null;
		try {
			BufferedImage image = ImageIO.read(LevelData.class.getResource(path + "_entity.png"));
			size = image.getHeight();
			pixels = new int[size * 3];
			image.getRGB(0, 0, 3, size, pixels, 0, 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SPAWN = new TileCoordinate(pixels[1] - 0xff000050, pixels[2] - 0xff000050);
		int temp = (pixels.length - 3) / 3;
		for (int i = 0; i < temp; i++) {
			Entity e = Entity.parseEntity(hash.get(pixels[3 + (i * 3)]));
			e.x = (pixels[3 + (i * 3) + 1] - 0xff000050) * 32;
			e.y = (pixels[3 + (i * 3) + 2] - 0xff000050) * 32;
			entities.add(e);
		}
		
		size = 0;
		doorPixels = null;
		try {
			BufferedImage image = ImageIO.read(LevelData.class.getResource(path + "_door.png"));
			size = image.getHeight();
			doorPixels = new int[size * 2];
			image.getRGB(0, 0, 2, size, doorPixels, 0, 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
