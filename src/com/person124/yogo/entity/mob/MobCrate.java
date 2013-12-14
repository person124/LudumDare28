package com.person124.yogo.entity.mob;

import java.awt.event.KeyEvent;

import com.person124.yogo.Game;
import com.person124.yogo.entity.Entity;
import com.person124.yogo.graphics.Sprite;

public class MobCrate extends Mob {
	
	public MobCrate(int x, int y) {
		super(Sprite.crate, x, y);
	}
	
	public void update() {
		gravCheck();
		if (!Game.thePlayer.carrying && Entity.getDistance(this, Game.thePlayer) <= 32) {
			if (Game.thePlayer.key.pickup) {
				Game.thePlayer.key.keys[KeyEvent.VK_E] = false;
				Game.thePlayer.carrying = true;
				remove();
			}
		}
	}
}
