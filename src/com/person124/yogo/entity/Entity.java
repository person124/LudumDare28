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
		removed = false;
	}
	
	public boolean isRemoved() {
		return removed;
	}

}
