package com.person124.yogo.entity;

import com.person124.yogo.Game;
import com.person124.yogo.entity.mob.MobPlayer;
import com.person124.yogo.graphics.Sprite;

public class EntityDoor extends Entity {

	public EntityDoor(int x, int y) {
		super(Sprite.door, x, y);
	}
	
	public void update() {
		for (Entity e : level.getEntities()) {
			if (e instanceof MobPlayer) {
				MobPlayer p = (MobPlayer) e;
				if (Entity.getDistance(this, p) < sprite.SIZE / 2) {
					Game.nextLevel();
				}
			}
		}
	}
	
}
