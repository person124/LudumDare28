package com.person124.yogo.level.tile;

import com.person124.yogo.graphics.Render;
import com.person124.yogo.graphics.Sprite;

public class Tile {
	
	public Sprite sprite;
	public final boolean SOLID;
	public final int ID;
	
	public static Tile nullTile = new Tile(Sprite.nullSprite, false, -1);
	
	public static Tile stoneWall = new Tile(Sprite.stone_wall, true, 0xff626262);
	public static Tile stoneWallStand = new Tile(Sprite.stone_wall_stand, false, 0xff808080);
	
	public static Tile bg_stoneWall = new Tile(Sprite.bg_stone_wall, false, 0xff9d9d9d);
	
	public static Tile pad_off = new TilePadOff(0xffff6700);
	public static Tile pad_on = new TilePadOn();
	
	public static Tile lava = new TileLava(0xffff0000);
	
	public static Tile door_closed = new TileDoorClosed(0xffababab);
	public static Tile door_open = new TileDoorOpen();
	
	public static Tile elevator_active = new TileElevator(0xff545454);
	public static Tile elevator_deactive = new Tile(Sprite.elevator, false, 0xff454545);
	
	public Tile(Sprite sprite, boolean solid, int id) {
		this.sprite = sprite;
		SOLID = solid;
		ID = id;
	}
	
	public static Tile getTile(int col) {
		if (col == stoneWall.ID) return stoneWall;
		if (col == stoneWallStand.ID) return stoneWallStand;
		
		if (col == bg_stoneWall.ID) return bg_stoneWall;
		
		if (col == pad_off.ID) return pad_off;
		
		if (col == lava.ID) return new TileLava(0xffff00);
		
		if (col == door_closed.ID) return door_closed;
		
		if (col == elevator_active.ID) return new TileElevator(0xff545454);
		if (col == elevator_deactive.ID) return elevator_deactive;
		
		return nullTile;
	}
	
	public void update(int loc) {
		
	}
	
	public void render(Render render, int x, int y) {
		render.renderSprite(sprite, x, y);
	}
	
}
