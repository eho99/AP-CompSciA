package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import utilities.*;
import breakout.Breakout;

public class Ball extends Rectangle {
	private double dx, dy; // Upper Bound is 14, lower bound for y is 2

	final int STARTING_X, STARTING_Y, PADDING = GDV5.getPadding(), MIN_WINDOW = 0, MAX_WINDOW_X = GDV5.getMaxWindowX(),
			MAX_WINDOW_Y = GDV5.getMaxWindowY();

	private boolean isInPlay = false;

	// Ball constructor to be defined in other classes
	public Ball(int x, int y, int height, int width, double dx, double dy) {
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

	// Reset ball to starting location
	public void reset() {
		setLocation(STARTING_X, STARTING_Y);
		dx = 0;
		dy = 0;
		isInPlay = false;
		Breakout.setLives(Breakout.getLives() - 1);
		Breakout.setScore(Breakout.getScore() - 250);

		if (Breakout.getScore() < 0) {
			Breakout.setScore(0);
		}
	}

	// Gets random speed
	public int getSpeed() {
		int range = 3, min = 6;
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
		dx = getSpeed() * getDir();
		dy = getSpeed() * -1; // Breakout always starts moving up
	}

	// Checks ball collision for window edges
	public void wallColl() {
		int previousXPos = x - (int) dx;
		int previousYPos = y - (int) dy;

		boolean rightWall = previousXPos + width <= MAX_WINDOW_X && getMaxX() >= MAX_WINDOW_X;
		boolean leftWall = previousXPos >= MIN_WINDOW && getMinX() <= MIN_WINDOW;
		boolean topWall = previousYPos >= MIN_WINDOW && getMinY() <= MIN_WINDOW;
		boolean bottomWall = previousYPos + height <= MAX_WINDOW_Y && getMaxY() >= MAX_WINDOW_Y;
		/*
		 * boolean collSides = (getMinX() <= 0) || (getMaxX() >= GDV5.getMaxWindowX());
		 * boolean collTop = (getMinY() <= 0); boolean collBottom = (getMaxY() >=
		 * GDV5.getMaxWindowY());
		 * 
		 * if (collSides) { dx *= -1; } if (collTop) { dy *= -1; } if (collBottom) {
		 * reset(); }
		 */

		if (rightWall || leftWall) {
			dx *= -1;
		}
		if (topWall) {
			dy *= -1;
		}
		if (bottomWall) {
			reset();
		}
	}

	// Ball movement
	public void update() {
		int MAX_SPEED = 9;
		if (dx < -MAX_SPEED)
			dy = -MAX_SPEED;
		else if (dx > MAX_SPEED)
			dx = MAX_SPEED;

		if (dy < -MAX_SPEED)
			dy = -MAX_SPEED;
		else if (dx > MAX_SPEED)
			dy = MAX_SPEED;

		translate((int) dx, (int) dy);
		wallColl();
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

	public boolean getisInPlay() {
		return isInPlay;
	}

	public void setisInPlay(boolean state) {
		isInPlay = state;
	}

}