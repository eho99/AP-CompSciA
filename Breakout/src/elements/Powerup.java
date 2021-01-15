package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import breakout.Breakout;
import utilities.GDV5;
import elements.Paddle;
import elements.Ball;

public class Powerup extends Rectangle {
	private static final int SPEED = 5;
	private static int id;
	private Color powerUpColor;
	
	
	// Powerup constructor to be defined in other classes
	public Powerup(int x, int y, int height, int width, int id) {
		super(x, y, height, width);
		this.x = x;
		this.y = y;
		this.height = height;	
		this.width = width;
		this.id = id;
	}
	
	/*
	 * ID KEY:
	 * 1. Extends the width of the paddle, color is white
	 * 2. Gives the ball the ability to pass through all bricks, color is orange
	 * 3. Increases ball size, color is cyan
	 * 4. Increases ball speed, color is green
	 * 5. Decreases ball speed, color is yellow
	 * 6. Decreases paddle width, color is red
	 */
	
	public static Color getColorByID() {
		if (id == 1) {
			return Color.WHITE;
		} else if (id == 2) {
			return Color.ORANGE;
		} else if (id == 3) {
			return Color.CYAN;
		} else if (id == 4) {
			return Color.GREEN;
		} else if (id == 5) {
			return Color.YELLOW;
		} else if (id == 6) {
			return Color.RED;
		}
		
		return Color.BLACK;
	}
	
	public static String getPowerByID() {
		if (id == 1) {
			return "extendPaddle";
		} else if (id == 2) {
			return "superBall";
		} else if (id == 3) {
			return "increaseBallSize";
		} else if (id == 4) {
			return "increaseBallSpeed";
		} else if (id == 5) {
			return "decreaseBallSpeed";
		} else if (id == 6) {
			return "shortenPaddle";
		}
		
		return null;
	}
	
	public static void performPowerUp(Paddle p, Ball b) {
		if (getPowerByID() == "extendPaddle") {
			
		}
	}
	
	// Checks for ball paddle collision
    public void collision(Paddle p) {
    	if (this.intersects(p)) {
            	
            }

    }
	
	// Ball movement
	public void update() {
		translate(0, SPEED);
	}

	// Draws ball
	public void draw(Graphics2D brush) {
		brush.setColor(powerUpColor);
		brush.fill(this);
	}
	

}
