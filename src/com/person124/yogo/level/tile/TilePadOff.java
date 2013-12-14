package com.person124.yogo.level.tile;

import com.person124.yogo.Game;
import com.person124.yogo.entity.Entity;
import com.person124.yogo.entity.mob.Mob;
import com.person124.yogo.graphics.Sprite;
import com.person124.yogo.sound.Audio;

public class TilePadOff extends Tile {
	
	public int ID;
	
	public TilePadOff(int id) {
		super(Sprite.pad_off, false, id);
	}
	
	public void update(int loc) {
		for (Entity e : Game.level.getEntities()) {
			if (e instanceof Mob) {
				Mob m = (Mob) e;
				int xPos = (m.x + 16) / 32;
				int yPos = m.y / 32;
				int mLoc = xPos + yPos * Game.level.WIDTH;
				if (mLoc == loc) {
					Game.level.tiles[loc] = Tile.pad_on;
					Audio.playSound("pad.activate");
				} else {
					xPos = (m.x - 16) / 32;
					mLoc = xPos + yPos * Game.level.WIDTH;
					if (mLoc == loc) {
						Game.level.tiles[loc] = Tile.pad_on;
						Audio.playSound("pad.activate");
					}
				}
			}
		}
	}
	
}
