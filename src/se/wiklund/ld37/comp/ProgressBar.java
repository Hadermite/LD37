package se.wiklund.ld37.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ProgressBar {
	
	private String text;
	private Font font;
	private Color colorForeground, colorBackground, colorFrame;
	private int x, y, width, height;
	double min, max, delta, progress, pixelsPerProgress;
	
	public ProgressBar(String text, Color foreground, Color background, Color frame, double min, double max, int x, int y, int width, int height) {
		this.text = text;
		this.colorForeground = foreground;
		this.colorBackground = background;
		this.colorFrame = frame;
		this.min = min;
		this.max = max;
		this.delta = max - min;
		this.pixelsPerProgress = width / delta;
		this.progress = delta;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
	}
	
	public void render(Graphics2D g) {
		int foregroundWidth = (int) (progress * pixelsPerProgress);
		if (width < 0) width = 0;
		g.setColor(colorBackground);
		g.fillRect(x, y, width, height);
		g.setColor(colorForeground);
		g.fillRect(x, y, foregroundWidth, height);
		g.setColor(colorFrame);
		g.drawRect(x, y, width, height);
		g.setFont(font);
		g.drawString(text, x, y + height / 2 + font.getSize() / 3);
	}
	
	public void changeProgress(double change) {
		setProgress(this.progress + change);
	}
	
	public void setProgress(double progress) {
		this.progress = progress;
	}
	
	public double getProgress() {
		return progress;
	}
}
