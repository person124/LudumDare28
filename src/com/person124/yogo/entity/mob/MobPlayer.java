package com.person124.yogo.entity.mob;

import com.person124.yogo.graphics.Sprite;
import com.person124.yogo.input.Keyboard;
import com.person124.yogo.level.Level;

public class MobPlayer extends Mob {
	
	private Keyboard key;
	private boolean isJumping = false, hasTouchedGround = true;
	private int jumpTime = 0, maxJumpTime = 35;
	
	public MobPlayer(Keyboard key) {
		super(new Sprite(32, 0x00ff00), 0, 0);
		this.key = key;
	}
	
	public void update() {
		if (!isJumping) gravCheck();
		
		int xa = 0, ya = 0;
		if (key.left) xa -= 2;
		if (key.right) xa += 2;
		move(xa, ya);
		
		if (key.jump && !isJumping && hasTouchedGround) {
			isJumping = true;
			hasTouchedGround = false;
			jumpTime = 0;
		}
		if (isJumping && jumpTime >= maxJumpTime) {
			isJumping = false;
		} else if (isJumping) {
			jumpTime++;
			move(0, -1);
		}
		if (collision(0, 1)) hasTouchedGround = true;
		else hasTouchedGround = false;
	}
	
	@Override
	public void init(Level level) {
		this.level = level;
		x = level.PLAYER_SPAWN.getXPos();
		y = level.PLAYER_SPAWN.getYPos();
		level.addEntity(this);
	}
	
}
