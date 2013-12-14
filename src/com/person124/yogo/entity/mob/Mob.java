package com.person124.yogo.entity.mob;

import com.person124.yogo.entity.Entity;
import com.person124.yogo.graphics.Sprite;

public class Mob extends Entity {
	
	private boolean isEffectedByGravity = true;
	private int gravTime = 0, maxGravTime = 1;
	private boolean dead = false;
	
	public Mob(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}
	
	public void gravCheck() {
		if (isEffectedByGravity) {
			gravTime++;
			if (gravTime >= maxGravTime) {
				gravTime = 0;
				move(0, 1);
			}
		}
	}
	
	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa / 2, 0);
			move(0, ya / 2);
		}
		
		if (collision(xa, ya)) return;
		
		x += xa;
		y += ya;
	}
	
	protected boolean collision(int xa, int ya) {
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 30 + 1) / 32;
			int yt = ((y + ya) + c / 2 * 30 + 1) / 32;
			if (level.getTile(xt, yt).SOLID) return true;
		}
		
		return false;
	}
	
	public void setDead() {
		dead = true;
	}
	
	public boolean isDead() {
		return dead;
	}
	
}
