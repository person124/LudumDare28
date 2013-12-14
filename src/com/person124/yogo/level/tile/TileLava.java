package com.person124.yogo.level.tile;

import java.util.Random;

import com.person124.yogo.Game;
import com.person124.yogo.entity.Entity;
import com.person124.yogo.entity.mob.Mob;
import com.person124.yogo.graphics.Sprite;

public class TileLava extends Tile {
	
	private int color = 0xff5800;
	private Random rand = new Random();

	public TileLava(int id) {
		super(new Sprite(32, 0xff0000), false, id);
	}
	
	public void update(int loc) {
		sprite = new Sprite(32, color);
		if (rand.nextBoolean()) color += 0x000100;
		if (color >= 0xffba00) color = 0xff5800;
		for (Entity e : Game.level.getEntities()) {
			if (e instanceof Mob) {
				Mob m = (Mob) e;
				int xPos = (m.x + 16) / 32;
				int yPos = m.y / 32;
				int mLoc = xPos + yPos * Game.level.WIDTH;
				if (mLoc == loc) m.remove();
			}
		}
	}
	
}
