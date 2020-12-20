package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import utilities.*;

public class Ball extends Rectangle {
	private double dx, dy; // Upper Bound is 14, lower bound for y is 2

	final int STARTING_X, STARTING_Y, PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = GDV5.getMaxWindowX(), MAX_WINDOW_Y = GDV5.getMaxWindowY();

	// Ball constructor to be defined in other classes
	public Ball(int x, int y, int height, int width, int dx, int dy) {
		super(x, x, height, width);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.dx = dx;
		this.dy = dy;

		STARTING_X = x;
		STARTING_Y = y;
	}

	// Reset ball to center
	public void ballReset() {
		this.setLocation(STARTING_X, STARTING_Y);
		this.dx = 0;
		this.dy = 0;
	}

	// Gets random speed
	public int getSpeed() {
		int range = 3, min = 3;
		return (int) (Math.random() * range) + min;
	}

	// Gets random direction
	public int getDir() {
		int rand = (int) (Math.random() * 2);
		if (rand == 1)
			return 1;
		return -1;
	}

	// Sets velocity by multiplying speed and direction
	public void setVel() {
		this.dx = getSpeed() * getDir();
		this.dy = getSpeed() * -1; // Breakout always starts moving up
	}

	// Checks ball collision for window edges
	public void ballBounce() {
		if (this.getMaxX() > MAX_WINDOW_X) {
			ballReset();
		} else if (this.getX() < 0) {
			ballReset();
		}

		boolean checkBounce = (this.getMinY() <= 0) || (this.getMaxY() >= MAX_WINDOW_Y);
		
		if (checkBounce) {
			this.dy = this.dy * -1;
		}
	}

	// Ball movement
	public void update() {
		if (this.dx < -14)
			this.dy = -14;
		else if (this.dx > 14)
			this.dx = 14;

		if (this.dy < -14)
			this.dy = -14;
		else if (this.dx > 14)
			this.dy = 14;

		this.translate((int) this.dx, (int) this.dy);
		ballBounce();
	}

	// Draws ball

	public void draw(Graphics2D brush) {
		Color ballClr = Color.white;
		brush.setColor(ballClr);
		brush.fill(this);
	}

	// Accessor Methods
	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

}