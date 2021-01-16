package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import utilities.*;
import breakout.Breakout;

public class Ball extends Rectangle {
	private double dx, dy; 

	private static final int MIN_WINDOW = 0, MAX_WINDOW_X = GDV5.getMaxWindowX(),
			MAX_WINDOW_Y = GDV5.getMaxWindowY(), MAX_SPEED = 10;
	private static int STARTING_X, STARTING_Y;
	private static boolean isInPlay = false;

	// Ball constructor to be defined in other classes
	public Ball(int x, int y, int height, int width) {
		super(x, x, height, width);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;

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
		Breakout.setBrickStreak(0);

		if (Breakout.getScore() < 0) {
			Breakout.setScore(0);
		}
	}

	// Gets random speed
	public int getSpeed() {
		int range = 3, min = 5;
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
		boolean rightWall =  getMaxX() >= MAX_WINDOW_X; // previousXPos + width <= MAX_WINDOW_X && getMaxX() >= MAX_WINDOW_X;
		boolean leftWall = getMinX() <= MIN_WINDOW; // previousXPos >= MIN_WINDOW && getMinX() <= MIN_WINDOW;
		boolean topWall = getMinY() <= MIN_WINDOW; // previousYPos >= MIN_WINDOW && getMinY() <= MIN_WINDOW;
		boolean bottomWall = getMaxY() >= MAX_WINDOW_Y; // previousYPos + height <= MAX_WINDOW_Y && getMaxY() >= MAX_WINDOW_Y;
		
		if (rightWall && (Math.signum(dx) == 1.0) || leftWall && (Math.signum(dx) == -1.0)) {
			dx *= -1;
			Breakout.playCollision();
		}
		if (topWall && Math.signum(dy) == -1.0) {
			dy *= -1;
			Breakout.playCollision();
		}
		if (bottomWall && Math.signum(dy) == 1.0) {
			reset();
		}
	}

	// Ball movement
	public void update() {
		if (dx < -MAX_SPEED)
			dx = -MAX_SPEED;
		else if (dx > MAX_SPEED)
			dx = MAX_SPEED;

		if (dy < -MAX_SPEED)
			dy = -MAX_SPEED;
		else if (dy > MAX_SPEED)
			dy = MAX_SPEED;

		translate((int) dx, (int) dy);
		wallColl();
	}

	// Draws ball
	public void draw(Graphics2D brush) {
		brush.setColor(Color.WHITE);
		brush.fill(this);
		// rush.drawOval(x, y, width, height);
		// brush.fillOval(x, y, width, height);
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