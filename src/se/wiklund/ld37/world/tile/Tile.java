package se.wiklund.ld37.world.tile;

import java.awt.Graphics2D;

import se.wiklund.ld37.world.World;

public class Tile {
	
	public static final int SIZE = 16;
	
	protected TileType type;
	protected int xPos, yPos;
	protected World world;
	
	public Tile(TileType type, int xPos, int yPos, World world) {
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.world = world;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		g.drawImage(type.getTexture(), xPos * SIZE, yPos * SIZE, SIZE, SIZE, null);
	}
	
	public void setType(TileType type) {
		this.type = type;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	public TileType getType() {
		return type;
	}
}
