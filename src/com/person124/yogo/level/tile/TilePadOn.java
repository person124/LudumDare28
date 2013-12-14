package com.person124.yogo.level.tile;

import com.person124.yogo.Game;
import com.person124.yogo.entity.Entity;
import com.person124.yogo.entity.mob.Mob;
import com.person124.yogo.graphics.Sprite;
import com.person124.yogo.sound.Audio;

public class TilePadOn extends Tile {
	
	public TilePadOn() {
		super(Sprite.pad_on, false, -1);
	}
	
	public void update(int loc) {
		int temp = 0;
		for (Entity e : Game.level.getEntities()) {
			if (e instanceof Mob) {
				Mob m = (Mob) e;
				int xPos = (m.x + 16) / 32;
				int yPos = m.y / 32;
				int mLoc = xPos + yPos * Game.level.WIDTH;
				if (mLoc == loc) temp++;
				else {
					xPos = (m.x - 16) / 32;
					mLoc = xPos + yPos * Game.level.WIDTH;
					if (mLoc == loc) temp++;
				}
			}
		}
		if (temp == 0) {
			Game.level.tiles[loc] = Tile.pad_off;
			Audio.playSound("pad.deactivate");
		}
	}
	
}
