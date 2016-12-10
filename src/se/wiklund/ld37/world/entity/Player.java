package se.wiklund.ld37.world.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import se.wiklund.ld37.Assets;
import se.wiklund.ld37.Main;
import se.wiklund.ld37.comp.ProgressBar;
import se.wiklund.ld37.input.Keyboard;
import se.wiklund.ld37.world.Inventory;
import se.wiklund.ld37.world.World;
import se.wiklund.ld37.world.tile.SeededTile;
import se.wiklund.ld37.world.tile.Tile;
import se.wiklund.ld37.world.tile.TileType;

public class Player extends Entity {

	private Inventory inventory;
	private ProgressBar hunger;

	public Player(World world) {
		super(Assets.ENTITY_PLAYER, Tile.SIZE * 3, Tile.SIZE * 3, 16, 32, world);

		inventory = new Inventory();
		hunger = new ProgressBar("Hunger", Color.GREEN, Color.WHITE, Color.BLACK, 0, 10, 0, 0, Tile.SIZE * 4,
				Tile.SIZE);
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
			Tile tile = world.getTileUnderFoot(this);
			if (tile != null)
				world.getTileUnderFoot(this).setType(TileType.FARMLAND);
		}

		if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
			Tile tile = world.getTileUnderFoot(this);
			if (tile.getType() == TileType.FARMLAND) {
				int xPos = tile.getxPos();
				int yPos = tile.getyPos();
				world.setTile(new SeededTile(xPos, yPos, world), xPos, yPos);
			}
			if (tile.getType() == TileType.GROWN) {
				tile.setType(TileType.FARMLAND);

				inventory.addFood(1);
			}
		}

		if (Keyboard.isKeyPressed(KeyEvent.VK_E)) {
			if (inventory.getFoodCount() >= 1) {
				hunger.changeProgress(1);
				inventory.removeFood(1);
			}
		}

		hunger.changeProgress(-0.006);

		if (hunger.getProgress() <= 0) {
			Main.onPlayerDie();
		}
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);

		hunger.render(g);
	}
	
	public ProgressBar getHunger() {
		return hunger;
	}
}
