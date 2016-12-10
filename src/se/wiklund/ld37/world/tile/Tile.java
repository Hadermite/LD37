package se.wiklund.ld37.world.tile;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tile {
	
	public static final int SIZE = 16;
	
	private TileType type;
	private int xPos, yPos;
	
	public Tile(TileType type, int xPos, int yPos) {
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics2D g) {
		g.drawImage(type.getTexture(), xPos * SIZE, yPos * SIZE, SIZE, SIZE, null);
		g.setColor(Color.YELLOW);
		g.drawRect(xPos * SIZE, yPos * SIZE, SIZE, SIZE);
	}
}
