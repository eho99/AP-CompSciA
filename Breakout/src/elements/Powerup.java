package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Powerup extends Rectangle {
	private static final int SPEED = 5;
	private int id, dx = 0, dy = 5;
	
	
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
	 * 2. Gives the ball the ability to pass through all bricks
	 * 3. Increases ball size
	 * 4. Increases ball speed
	 * 5. Decreases ball speed
	 * 6. 
	 */

	public void getColorByID() {
		
	}
	
	// Ball movement
	public void update() {
		translate((int) dx, (int) dy);
	}

	// Draws ball
	public void draw(Graphics2D brush) {
		brush.setColor(Color.WHITE);
		brush.fill(this);
	}
	

}
