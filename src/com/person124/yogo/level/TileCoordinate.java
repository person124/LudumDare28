package com.person124.yogo.level;

public class TileCoordinate {
	
	private final int X, Y;
	
	public TileCoordinate(int x, int y) {
		X = x;
		Y = y;
	}
	
	public int getXPos() {
		return X * 32;
	}
	
	public int getYPos() {
		return Y * 32;
	}
	
}
