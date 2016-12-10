package se.wiklund.ld37.world.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import se.wiklund.ld37.Main;

public class Entity {
	
	private BufferedImage texture;
	private double x, y;
	private int width, height;
	private double acceleration;
	private double friction;
	private double maxSpeed;
	private double velX, velY;
	private boolean movedX, movedY;
	
	public Entity(BufferedImage texture, double x, double y, int width, int height) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		acceleration = 1.5;
		friction = 1.5;
		maxSpeed = 3;
	}
	
	public void tick() {
		if (!movedX) {
			if (velX > 0) {
				velX -= friction;
				if (velX < 0) velX = 0;
			}
			if (velX < 0) {
				velX += friction;
				if (velX > 0) velX = 0;
			}
		}
		if (!movedY) {
			if (velY > 0) {
				velY -= friction;
				if (velY < 0) velY = 0;
			}
			if (velY < 0) {
				velY += friction;
				if (velY > 0) velY = 0;
			}
		}
		
		x += velX;
		y += velY;
		
		if (x < 0) x = 0;
		if (x > Main.RENDER_SIZE.width - width) x = Main.RENDER_SIZE.width - width;
		if (y < 0) y = 0;
		if (y > Main.RENDER_SIZE.height - height) y = Main.RENDER_SIZE.height - height;
		
		movedX = false;
		movedY = false;
	}
	
	public void moveX(boolean left) {
		movedX = true;
		double amount = 0;
		if (left) {
			amount = -acceleration;
			if (velX > 0) amount -= acceleration;
		} else {
			amount = acceleration;
			if (velX < 0) amount += acceleration;
		}
		
		velX += amount;
		if (velX < -maxSpeed) velX = -maxSpeed;
		if (velX > maxSpeed) velX = maxSpeed;
	}
	
	public void moveY(boolean up) {
		movedY = true;
		double amount;
		if (up) {
			amount = -acceleration;
		} else {
			amount = acceleration;
		}
		
		velY += amount;
		
		if (velY < -maxSpeed) velY = -maxSpeed;
		if (velY > maxSpeed) velY = maxSpeed;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(texture, (int) x, (int) y, width, height, null);
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public void setFriction(double friction) {
		this.friction = friction;
	}
	
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
}
