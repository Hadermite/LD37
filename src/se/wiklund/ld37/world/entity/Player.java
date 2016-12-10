package se.wiklund.ld37.world.entity;

import com.sun.glass.events.KeyEvent;

import se.wiklund.ld37.Assets;
import se.wiklund.ld37.input.Keyboard;
import se.wiklund.ld37.world.World;
import se.wiklund.ld37.world.tile.SeededTile;
import se.wiklund.ld37.world.tile.Tile;
import se.wiklund.ld37.world.tile.TileType;

public class Player extends Entity {
	
	public Player(World world) {
		super(Assets.ENTITY_PLAYER, Tile.SIZE * 3, Tile.SIZE * 3, 16, 32, world);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
			moveX(true);
		}
		
		if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
			moveX(false);
		}
		
		if (Keyboard.isKeyDown(KeyEvent.VK_W)) {
			moveY(true);
		}
		
		if (Keyboard.isKeyDown(KeyEvent.VK_S)) {
			moveY(false);
		}
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_F)) {
			world.getTileUnderFoot(this).setType(TileType.FARMLAND);
		}
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
			Tile tile = world.getTileUnderFoot(this);
			if (tile.getType() == TileType.FARMLAND) {
				
				int xPos = tile.getxPos();
				int yPos = tile.getyPos();
				world.setTile(new SeededTile(xPos, yPos, world), xPos, yPos);
			}
		}
	}
}
