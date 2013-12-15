package com.person124.yogo.level.tile;

import com.person124.yogo.Game;
import com.person124.yogo.entity.Entity;
import com.person124.yogo.entity.mob.MobPlayer;
import com.person124.yogo.graphics.Sprite;
import com.person124.yogo.sound.Audio;

public class TileElevator extends Tile {
	
	public TileElevator(int id) {
		super(Sprite.elevator, false, id);
	}
	
	public void update(int loc) {
		for (Entity e : Game.level.getEntities()) {
			if (e instanceof MobPlayer) {
				MobPlayer m = (MobPlayer) e;
				int xPos = m.x / 32;
				int yPos = m.y / 32;
				int mLoc = xPos + yPos * Game.level.WIDTH;
				if (mLoc == loc) onPlayerTouch(loc, xPos, yPos);
			}
		}
	}
	
	private void onPlayerTouch(int loc, int x, int y) {
		Audio.playSound("ele.arrive");
		int w = Game.level.WIDTH;
		for (int i = 0; i < y; i++) {
			if (Game.level.tiles[x + (y - i) * w] == Tile.elevator_deactive) {
				Game.thePlayer.y = (y - i) * 32;
				return;
			}
		}
	}
	
}
