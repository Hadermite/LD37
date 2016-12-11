package se.wiklund.ld37.world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import se.wiklund.ld37.Main;
import se.wiklund.ld37.world.entity.Entity;
import se.wiklund.ld37.world.entity.Player;
import se.wiklund.ld37.world.tile.Tile;
import se.wiklund.ld37.world.tile.TileType;

public class World {

	public static final int SIZE = 10;
	private static final Font FONT_HUD = new Font(Font.SANS_SERIF, Font.PLAIN, 6);
	
	private Tile[] tiles = new Tile[SIZE * SIZE];
	private List<Entity> entites = new ArrayList<>();
	private Player player;
	private int survivalTime;

	public World() {
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				tiles[x + y * SIZE] = new Tile(TileType.GRASS, x, y, this);
			}
		}

		player = new Player(this);
		entites.add(player);
	}

	public void tick() {
		for (Tile tile : tiles)
			tile.tick();

		for (Entity entity : entites)
			entity.tick();
		
		survivalTime++;
	}

	public void render(Graphics2D g) {
		for (Tile tile : tiles)
			tile.render(g);

		for (Entity entity : entites)
			entity.render(g);
		
		g.setColor(Color.BLACK);
		g.setFont(FONT_HUD);
		g.drawString("Amount of Food: " + player.getInventory().getFoodCount(), 2, 25);
		g.drawString("Seconds Survived: " + survivalTime / Main.TICKRATE, 2, 35);
	}

	public void setTile(Tile tile, int xPos, int yPos) {
		if (xPos < 0 || xPos >= SIZE || yPos < 0 || yPos >= SIZE)
			return;
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
	
	public Player getPlayer() {
		return player;
	}
	
	public int getSurvivalTime() {
		return survivalTime;
	}
}
