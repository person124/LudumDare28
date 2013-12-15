package com.person124.yogo.level.tile;

import com.person124.yogo.Game;
import com.person124.yogo.entity.Entity;
import com.person124.yogo.entity.mob.MobPlayer;
import com.person124.yogo.graphics.Sprite;

public class TileDoorClosed extends Tile {
	
	private int doorInt = 0;

	public TileDoorClosed(int id) {
		super(Sprite.door_closed, true, id);
	}
	
	public void setButtonLookNumber(int num) {
		doorInt = num;
	}
	
	public void update(int loc) {
		for (Entity e : Game.level.getEntities()) {
			if (e instanceof MobPlayer) {
				MobPlayer m = (MobPlayer) e;
				int xPos = (m.x + 16) / 32;
				int yPos = m.y / 32;
				int mLoc = xPos + yPos * Game.level.WIDTH;
				if (mLoc == loc) {
					m.x -= 16;
				} else {
					xPos = (m.x - 16) / 32;
					mLoc = xPos + yPos * Game.level.WIDTH;
					if (mLoc == loc) {
						m.x -= 16;
					}
				}
			}
		}
		
		if (Game.level.tiles[doorInt] instanceof TilePadOn) {
			TileDoorOpen tile = (TileDoorOpen) Tile.door_open;
			tile.setButtonLookNumber(doorInt);
			Game.level.tiles[loc] = tile;
		}
	}
	
}
