package se.wiklund.ld37.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.ld37.world.entity.Entity;
import se.wiklund.ld37.world.entity.Player;
import se.wiklund.ld37.world.tile.Tile;
import se.wiklund.ld37.world.tile.TileType;

public class World {
	
	public static final int SIZE = 10;
	
	private Tile[] tiles = new Tile[SIZE * SIZE];
	private List<Entity> entites = new ArrayList<>();
	
	public World() {
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				tiles[x + y * SIZE] = new Tile(TileType.GRASS, x, y);
			}
		}
		
		Player player = new Player();
		entites.add(player);
	}
	
	public void tick() {
		for (Tile tile : tiles) {
			tile.tick();
		}
		
		for (Entity entity : entites) {
			entity.tick();
		}
	}
	
	public void render(Graphics2D g) {
		for (Tile tile : tiles) {
			tile.render(g);
		}
		
		for (Entity entity : entites) {
			entity.render(g);
		}
	}
}
