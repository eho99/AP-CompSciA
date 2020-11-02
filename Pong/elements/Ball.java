package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pong.Pong;

public class Ball extends Rectangle {
	public int dx, dy; // Upper Bound is 14, lower bound for y is 2
	public boolean isServingToRight;

	final int STARTING_X, STARTING_Y, PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;;

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
	public void ballReset(Pong p) {
		this.setLocation(STARTING_X, STARTING_Y);
		this.dx = 0;
		this.dy = 0;
		p.is2PPlaying = false;
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
	public void setVel(Pong p) {
		dx = getSpeed() * getDir();
		dy = getSpeed() * getDir();

		if (isServingToRight) {
			if (dx < 0)
				dx *= -1;
		} else {
			if (dx > 0)
				dx *= -1;
		}

		p.is2PPlaying = true;
	}

	// Checks ball collision for window edges
	public void ballBounce(Pong p) {
		if (this.getMaxX() > MAX_WINDOW_X) {
			ballReset(p);
			p.p1Points += 1;
			isServingToRight = false;
			p.isRScoreScreen = true;
		} else if (this.getX() < 0) {
			ballReset(p);
			p.p2Points += 1;
			isServingToRight = true;
			p.isRScoreScreen = true;
		}

		boolean checkBounce = (this.getMinY() <= 0) || (this.getMaxY() >= MAX_WINDOW_Y);
		if (checkBounce) {
			dy *= -1;
		}
	}

	// Ball movement
	public void update(Pong p) {
		if (dx < -14)
			dx = -14;
		else if (dx > 14)
			dx = 14;

		if (dy < -14)
			dy = -14;
		else if (dx > 14)
			dy = 14;

		this.translate(dx, dy);
		ballBounce(p);
	}

	// Draws ball and center line
	public Color ballClr = Color.white;

	public void draw(Graphics2D brush) {
		Rectangle centerLine = new Rectangle((MAX_WINDOW_X / 2), 0, 1, 600);
		brush.setColor(Color.white);
		brush.draw(centerLine);
		brush.fill(centerLine);

		brush.setColor(ballClr);
		brush.fill(this);
	}

}