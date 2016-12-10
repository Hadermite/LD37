package se.wiklund.ld37;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import se.wiklund.ld37.world.World;

public class Screen extends Canvas {

	private World world;

	public Screen() {
		createWorld();
	}
	
	public void tick() {
		if (world != null)
			world.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocusInWindow();
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.scale(Main.SCALE, Main.SCALE);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Main.RENDER_SIZE.width, Main.RENDER_SIZE.height);

		if (world != null)
			world.render(g);

		g.dispose();
		bs.show();
	}
	
	public World getWorld() {
		return world;
	}
	
	public void createWorld() {
		world = new World();
	}
	
	public void deleteWorld() {
		world = null;
	}
}
