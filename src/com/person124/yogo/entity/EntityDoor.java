package com.person124.yogo.entity;

import com.person124.yogo.Game;
import com.person124.yogo.entity.mob.MobPlayer;
import com.person124.yogo.graphics.Sprite;
import com.person124.yogo.sound.Audio;

public class EntityDoor extends Entity {
	
	public EntityDoor(int x, int y) {
		super(Sprite.door, x, y);
	}
	
	public void update() {
		for (int i = 0; i < Game.level.getEntities().size(); i++) {
			if (Game.level.getEntities().get(i) instanceof MobPlayer) {
				MobPlayer p = (MobPlayer) Game.level.getEntities().get(i);
				if (Entity.getDistance(this, p) < sprite.SIZE / 2) {
					Audio.playSound("door.win");
					Game.addWin();
				}
			}
		}
	}
	
}
