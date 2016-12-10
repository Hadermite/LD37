package se.wiklund.ld37;

import java.awt.image.BufferedImage;

import se.wiklund.ld37.util.Loader;

public class Assets {
	
	//Tiles
	public static final BufferedImage TILE_GRASS = Loader.loadImage("/tiles/grass.png");
	public static final BufferedImage TILE_FARMLAND = Loader.loadImage("/tiles/farmland.png");
	public static final BufferedImage TILE_SEEDED = Loader.loadImage("/tiles/seeded.png");
	public static final BufferedImage TILE_GROWN = Loader.loadImage("/tiles/grown.png");
	
	//Entities
	public static final BufferedImage ENTITY_PLAYER = Loader.loadImage("/entities/player.png");
}
