package com.person124.yogo.level.tile;

import com.person124.yogo.Game;
import com.person124.yogo.graphics.Sprite;

public class TileDoorOpen extends Tile {
	
	private int doorInt;

	public TileDoorOpen() {
		super(Sprite.door_open, false, -1);
	}
	
	public void setButtonLookNumber(int num) {
		doorInt = num;
	}
	
	public void update(int loc) {
		if (Game.level.tiles[doorInt] instanceof TilePadOff) {
			TileDoorClosed tile = (TileDoorClosed) Tile.door_closed;
			tile.setButtonLookNumber(doorInt);
			Game.level.tiles[loc] = tile;
		}
	}
	
}
