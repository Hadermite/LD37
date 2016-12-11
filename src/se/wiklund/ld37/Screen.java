package se.wiklund.ld37;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import com.sun.glass.events.KeyEvent;

import se.wiklund.ld37.input.Keyboard;
import se.wiklund.ld37.world.World;

public class Screen extends Canvas {

	private static final Font FONT_TITLE = new Font(Font.SANS_SERIF, Font.BOLD, 14);
	private static final Font FONT_MESSAGE = new Font(Font.SANS_SERIF, Font.BOLD, 8);
	private static final String STR_TITLE = "You died!";
	private static final String STR_DISMISS = "Press [ENTER] to play again!";
	
	private World world;
	private int survivalTimeSeconds;

	public Screen() {
		createWorld();
	}

	public void tick() {
		if (world != null) {
			world.tick();
		} else {
			// Update death screen
			if (Keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
				createWorld();
			}
		}
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

		if (world != null) {
			world.render(g);
		} else {
			// Render death screen
			g.setColor(Color.BLACK);
			g.setFont(FONT_TITLE);
			int width = g.getFontMetrics().stringWidth(STR_TITLE);
			g.drawString(STR_TITLE, (Main.RENDER_SIZE.width - width) / 2, 15);
			
			g.setFont(FONT_MESSAGE);
			String message = "You survived " + survivalTimeSeconds + " seconds!";
			width = g.getFontMetrics().stringWidth(message);
			g.drawString(message, (Main.RENDER_SIZE.width - width) / 2, 50);
			
			width = g.getFontMetrics().stringWidth(STR_DISMISS);
			g.drawString(STR_DISMISS, (Main.RENDER_SIZE.width - width) / 2, 70);
		}

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
		survivalTimeSeconds = world.getSurvivalTime() / Main.TICKRATE;
		world = null;
	}
}
