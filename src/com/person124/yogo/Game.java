package com.person124.yogo;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.person124.yogo.entity.mob.MobCrate;
import com.person124.yogo.entity.mob.MobPlayer;
import com.person124.yogo.graphics.Render;
import com.person124.yogo.input.Keyboard;
import com.person124.yogo.level.Level;
import com.person124.yogo.level.TileCoordinate;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 256, HEIGHT = WIDTH / 12 * 9;
	private int scale;
	private final Dimension SIZE;
	public static final String NAME = "Chambers";
	private boolean running = false;
	private JFrame frame;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Render render;
	private Keyboard key;
	public static Level level;
	public static MobPlayer thePlayer;
	
	public Game(int s) {
		scale = s;
		SIZE = new Dimension(WIDTH * scale, HEIGHT * scale);
		frame = new JFrame();
		
		render = new Render(WIDTH, HEIGHT);
		key = new Keyboard();
		level = new Level("/levels/test.png", new TileCoordinate(2, 23));
		
		thePlayer = new MobPlayer(key);
		thePlayer.init(level);
		
		MobCrate crate = new MobCrate(32, 23 * 32);
		crate.init(level);
		
		addKeyListener(key);
		addFocusListener(key);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		final double NS = 1000000000.0 / 60.0;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int fps = 0, ups = 0;
		requestFocus();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / NS;
			lastTime = now;
			while(delta >= 0) {
				update();
				ups++;
				delta--;
			}
			render();
			fps++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				if(key.debug) frame.setTitle(NAME + " | fps: " + fps + ", ups: " + ups);
				else frame.setTitle(NAME);
				timer += 1000;
				fps = 0;
				ups = 0;
			}
		}
	}
	
	public void update() {
		key.update();
		level.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		int xOff = thePlayer.x - (WIDTH / 2);
		int yOff = thePlayer.y - (HEIGHT / 2);
		
		render.clear();
		Graphics g = bs.getDrawGraphics();
		level.render(render, xOff, yOff);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = render.pixels[i];
		}
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this, NAME.toLowerCase().replace(" ", "_") + "_thread");
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void start(String[] args) {
		Game g = new Game(Integer.parseInt(args[0]));
		
		g.frame.setPreferredSize(g.SIZE);
		g.frame.setTitle(NAME);
		g.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.frame.add(g);
		g.frame.pack();
		g.frame.setLocationRelativeTo(null);
		g.frame.setResizable(false);
		g.frame.setVisible(true);
		
		g.start();
	}

}
