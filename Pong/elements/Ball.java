package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pong.Pong;

public class Ball extends Rectangle {
	public int dx, dy; // Upper Bound is 14
	public boolean isServingToRight; 

	final int x_start, y_start, PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;;

	public Ball(int x, int y, int height, int width, int dx, int dy) {
		super(x, x, height, width);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.dx = dx;
		this.dy = dy;

		x_start = x;
		y_start = y;
	}

	public void ballReset(Pong p) {
		this.setLocation(x_start, y_start);
		this.dx = 0;
		this.dy = 0;
		p.is2PPlaying = false;
	}

	// Shatter Animation for Ball hitting side wall?
	public void ballShatter(Graphics2D brush) {
		for (int x = (int) this.getMinX(); x <= this.getMaxX(); x += 5) {
			for (int y = (int) this.getMinY(); y <= this.getMaxY(); y += 5) {
				brush.drawRect(x, y, 5, 5);
			}
		}
	}

	public int getSpeed() {
		int range = 3, min = 3;
		return (int) (Math.random() * range) + min;
	}

	public int getDir() {
		int rand = (int) (Math.random() * 2);
		if (rand == 1)
			return 1;
		return -1;
	}

	public void setVel(Pong p) {
		dx = getSpeed() * getDir();
		dy = getSpeed() * getDir();

		if (isServingToRight == false) {
			if (dx > 0)
				dx *= -1;
		} else if (isServingToRight == true) {
			if (dx < 0)
				dx *= -1;
		}

		p.is2PPlaying = true;
	}

	/*
	 * BALL-PADDLE COLLISION INTERACTIONS 1. Check if ball hits middle or side 1a)
	 * if middle, perfect reflection following law of reflection, regular speed 1b)
	 * If side, off shoot angle at more extreme 1c) if direction of movement of the
	 * paddle is same as ball, increase speed, if its opposite, decrease speed 2.
	 * Start at a slower speed and increase over time 2a) Implement a counter
	 * tracking the number of times a ball has hit the paddle, at certain intervals,
	 * randomly increase MIN SPEED
	 * 
	 */

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
		
		if (this.getMinY() <= 0) {
			dy *= -1;
		} else if (this.getMaxY() >= MAX_WINDOW_Y) {
			dy *= -1;
		}
	}

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

	public void draw(Graphics2D brush) {
		Color ballClr = Color.white;

		brush.setColor(ballClr);
		brush.fill(this);

		Rectangle centerLine = new Rectangle((MAX_WINDOW_X / 2), 0, 1, 600);
		brush.draw(centerLine);
		brush.fill(centerLine);
	}
	
	

}
