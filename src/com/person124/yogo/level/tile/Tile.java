package com.person124.yogo.level.tile;

import com.person124.yogo.graphics.Render;
import com.person124.yogo.graphics.Sprite;

public class Tile {
	
	public Sprite sprite;
	public final boolean SOLID;
	public final int ID;
	
	public static Tile nullTile = new Tile(Sprite.nullSprite, false, -1);
	
	public static Tile stoneWall = new Tile(Sprite.stone_wall, true, 0xff626262);
	
	public static Tile bg_stoneWall = new Tile(Sprite.bg_stone_wall, false, 0xff9d9d9d);
	
	public Tile(Sprite sprite, boolean solid, int id) {
		this.sprite = sprite;
		SOLID = solid;
		ID = id;
	}
	
	public static Tile getTile(int col) {
		if (col == stoneWall.ID) return stoneWall;
		
		if (col == bg_stoneWall.ID) return bg_stoneWall;
		
		return nullTile;
	}
	
	public void update() {
		
	}
	
	public void render(Render render, int x, int y) {
		render.renderSprite(sprite, x, y);
	}

}
