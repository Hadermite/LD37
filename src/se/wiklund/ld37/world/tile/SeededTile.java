package se.wiklund.ld37.world.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import se.wiklund.ld37.Assets;
import se.wiklund.ld37.Main;
import se.wiklund.ld37.world.World;

public class SeededTile extends Tile {
	
	private BufferedImage texture;
	private int timer;
	
	public SeededTile(int xPos, int yPos, World world) {
		super(null, xPos, yPos, world);
		this.texture = Assets.TILE_SEEDED;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		timer++;
		
		if (timer >= 10 * Main.TICKRATE) {
			world.setTile(new Tile(TileType.GROWN, xPos, yPos, world), xPos, yPos);
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(texture, xPos * SIZE, yPos * SIZE, SIZE, SIZE, null);
	}
}
