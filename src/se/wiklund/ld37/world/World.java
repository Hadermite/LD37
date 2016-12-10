package se.wiklund.ld37.world;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
				tiles[x + y * SIZE] = new Tile(TileType.GRASS, x, y, this);
			}
		}

		Player player = new Player(this);
		entites.add(player);
	}

	public void tick() {
		for (Tile tile : tiles)
			tile.tick();

		for (Entity entity : entites)
			entity.tick();
	}

	public void render(Graphics2D g) {
		for (Tile tile : tiles)
			tile.render(g);

		for (Entity entity : entites)
			entity.render(g);
	}
	
	public void setTile(Tile tile, int xPos, int yPos) {
		tiles[xPos + yPos * SIZE] = tile;
	}

	public Tile getTileUnderFoot(Entity entity) {
		Rectangle entityFeet = new Rectangle((int) (entity.getX() + entity.getWidth() / 2), (int) (entity.getY() + entity.getHeight()), 1, 1);
		for (Tile tile : tiles) {
			Rectangle tileBounds = new Rectangle(tile.getxPos() * Tile.SIZE, tile.getyPos() * Tile.SIZE, Tile.SIZE, Tile.SIZE);

			if (entityFeet.intersects(tileBounds))
				return tile;
		}
		return null;
	}
}
