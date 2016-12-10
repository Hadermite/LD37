package se.wiklund.ld37;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import se.wiklund.ld37.input.Keyboard;
import se.wiklund.ld37.world.World;
import se.wiklund.ld37.world.tile.Tile;

public class Main implements Runnable {
	
	public static final int SCALE = 4;
	public static final Dimension RENDER_SIZE = new Dimension(Tile.SIZE * World.SIZE, Tile.SIZE * World.SIZE);
	public static final Dimension SCREEN_SIZE = new Dimension(RENDER_SIZE.width * SCALE, RENDER_SIZE.height * SCALE);
	public static final String NAME = "LD37 by Hadermite";
	
	private boolean running;
	private Screen screen;
	private JFrame frame;
	private Thread thread;
	
	public void start() {
		if (running) return;
		running = true;
		
		screen = new Screen();
		screen.setPreferredSize(SCREEN_SIZE);
		
		frame = new JFrame(NAME);
		frame.add(screen);
		frame.pack();
		frame.setSize(SCREEN_SIZE.width, SCREEN_SIZE.height + 20);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		screen.addKeyListener(new Keyboard());
		
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	@Override
	public void run() {
		
		double TICK_INTERVAL = 1000000000 / 60;
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		int ticks = 0;
		int frames = 0;
		while (running) {
			
			long now = System.nanoTime();
			delta += now - lastTime;
			lastTime = now;
			
			while (delta >= TICK_INTERVAL) {
				delta -= TICK_INTERVAL;
				
				screen.tick();
				ticks++;
			}
			
			screen.render();
			frames++;
			
			if (timer + 1000 <= System.currentTimeMillis()) {
				timer += 1000;
				frame.setTitle(NAME + " | TPS: " + ticks + ", FPS: " + frames);
				ticks = 0;
				frames = 0;
			}
		}
	}
	
	public void stop() {
		if (!running) return;
		running = false;
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
}
