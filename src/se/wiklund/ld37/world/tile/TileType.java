package se.wiklund.ld37.world.tile;

import java.awt.image.BufferedImage;

import se.wiklund.ld37.Assets;

public enum TileType {
	
	GRASS("Grass", Assets.TILE_GRASS),
	FARMLAND("Farmland", Assets.TILE_FARMLAND),
	GROWN("Grown", Assets.TILE_GROWN);
	
	private String name;
	private BufferedImage texture;
	
	private TileType(String name, BufferedImage texture) {
		this.name = name;
		this.texture = texture;
	}
	
	public String getName() {
		return name;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
}
