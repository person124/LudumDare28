package com.person124.yogo.entity.mob;

import java.awt.event.KeyEvent;

import com.person124.yogo.graphics.Render;
import com.person124.yogo.graphics.Sprite;
import com.person124.yogo.input.Keyboard;
import com.person124.yogo.level.Level;

public class MobPlayer extends Mob {
	
	public Keyboard key;
	private boolean isJumping = false, hasTouchedGround = true;
	private int jumpTime = 0, maxJumpTime = 35;
	public boolean carrying = false;
	
	public MobPlayer(Keyboard key) {
		super(Sprite.player_stand_right, 0, 0);
		this.key = key;
	}
	
	public void update() {
		if (dir == 0) sprite = Sprite.player_stand_left;
		if (dir == 1) sprite = Sprite.player_stand_right;
		
		if (!isJumping) gravCheck();
		
		int xa = 0, ya = 0;
		int temp = 2;
		if (carrying) temp = 1;
		if (key.left) xa -= temp;
		if (key.right) xa += temp;
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
		
		if (key.pickup && carrying) {
			int check = 33;
			if (dir == 0) check *= -1;
			if (!collision(check, 0)) {
				carrying = false;
				key.keys[KeyEvent.VK_E] = false;
				MobCrate crate = new MobCrate(x + check, y);
				crate.init(level);
			}
		}
	}
	
	public void render(Render render) {
		render.renderSprite(sprite, x, y);
		if (carrying) render.renderSprite(Sprite.crate, x, y - 32);
	}
	
	@Override
	public void init(Level level) {
		this.level = level;
		x = level.PLAYER_SPAWN.getXPos();
		y = level.PLAYER_SPAWN.getYPos();
		level.addEntity(this);
	}
	
}
