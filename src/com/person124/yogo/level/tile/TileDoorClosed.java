package com.person124.yogo.level.tile;

import com.person124.yogo.Game;
import com.person124.yogo.graphics.Sprite;

public class TileDoorClosed extends Tile {
	
	private int doorInt = 556;

	public TileDoorClosed(int id) {
		super(Sprite.door_closed, true, id);
	}
	
	public void setButtonLookNumber(int num) {
		doorInt = num;
	}
	
	public void update(int loc) {
		if (Game.level.tiles[doorInt] instanceof TilePadOn) {
			TileDoorOpen tile = (TileDoorOpen) Tile.door_open;
			tile.setButtonLookNumber(doorInt);
			Game.level.tiles[loc] = tile;
		}
	}
	
}
