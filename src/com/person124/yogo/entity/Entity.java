package com.person124.yogo.entity;

import com.person124.yogo.graphics.Render;
import com.person124.yogo.graphics.Sprite;
import com.person124.yogo.level.Level;

public class Entity {
	
	public int x, y;
	public Sprite sprite;
	protected Level level;
	private boolean removed = false;
	
	public Entity(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		
	}
	
	public void render(Render render) {
		render.renderSprite(sprite, x, y);
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void init(Level level) {
		this.level = level;
		level.addEntity(this);
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public static int getDistance(Entity e1, Entity e2) {
		int temp = 0;
		
		int xDist = Math.abs(e1.x - e2.x);
		int yDist = Math.abs(e1.y - e2.y);
		temp = (int) Math.sqrt((xDist * xDist) + (yDist * yDist));
		
		return temp;
	}
	
}
