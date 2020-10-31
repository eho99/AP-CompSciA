package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle extends Rectangle {
	final int PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;
	public int dx, dy;
	public int isMovingUp = 0;

	// Rectangle p1 = new Rectangle(x, y, height, width);
	final int STARTING_X, STARTING_Y;

	public Paddle(int x, int y, int height, int width, int dx, int dy) {
		super(x, y, height, width);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.dx = dx;
		this.dy = dy;

		STARTING_X = x;
		STARTING_Y = y;
	}

	// Resets paddle location at end of round
	public void padReset() {
		this.setLocation(STARTING_X, STARTING_Y);
		this.dy = 0;
	}

	// Moves the paddle and checks for edge
	public void move() {
		this.translate(dx, dy);
		if (this.getMinY() < MIN_WINDOW) {
			this.setLocation(x, 0);
		} else if (this.getMaxY() > MAX_WINDOW_Y) {
			this.setLocation(x, MAX_WINDOW_Y - this.height);
		}
	}

	// Checks for ball paddle collision
	public void ballPadCol(Ball b) {
		boolean inYRange = (b.getMaxY() >= this.getMinY()) && (b.getMinY() <= this.getMaxY());
		boolean isAtFront = (((b.getMinX() >= this.getCenterX()) && (b.getMinX() <= PADDING + this.width))
				&& (b.dx < 0))
				|| (((b.getMaxX() <= this.getCenterX()) && b.getMaxX() >= MAX_WINDOW_X - this.width) && (b.dx > 0));

		// Check in front of the ball, multiply by constant to check for fps

		if (inYRange) {
			if (isAtFront) {
				if (this.dy == 0) {
					b.dx *= -1.15;
				} else if (((this.dy > 0) && (b.dy > 0)) || ((this.dy < 0) && (b.dy < 0))) {
					b.dx *= -1.2;
					b.dy *= 1.5;
				} else if (((this.dy < 0) && (b.dy > 0)) || ((this.dy > 0) && (b.dy < 0))) {
					b.dx *= -1.35;
					b.dy *= -0.86;
					if (Math.abs(b.dy) < 2) {
						b.dy = 2;
					}
					//got stuck on bottom floor
				}
			}
		}
	}

	public void update() {
		move();
	}

	public void draw(Graphics2D win) {
		Color padClr = Color.white;

		win.setColor(padClr);
		win.fill(this);
	}

}