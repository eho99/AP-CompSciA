package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utilities.GDV5;

public class Brick extends Rectangle {
	private int xPos, yPos, dx, dy;
	final int PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;
	private boolean isShown = true, hasShattered = false;

	// Brick constructor to be defined in other classes
	public Brick(int x, int y, int height, int width) {
		super(x, y, height, width);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;

		xPos = x;
		yPos = y;
	}

	// TBD implemented
	public void shatter(Graphics2D brush) {
		int numPieces = 0;
		if (!isShown && hasShattered) {
			numPieces = (this.width / 20) * 3;

		}
		Rectangle[] shatterRect = new Rectangle[numPieces];
		for (Rectangle r : shatterRect) {

		}
	}

	// Checks for ball paddle collision
	public void ballBrickCol(Ball b) {
		if (isShown) {
			if (this.intersects(b)) {
				if (GDV5.collisionDirection(this, b, b.getDx(), b.getDy()) == 0
						|| GDV5.collisionDirection(this, b, b.getDx(), b.getDy()) == 2) { // intersects from right/left
					int newDx = -1 * b.getDx();
					b.setDx(newDx);
				} else if (GDV5.collisionDirection(this, b, b.getDx(), b.getDy()) == 1
						|| GDV5.collisionDirection(this, b, b.getDx(), b.getDy()) == 3) { // intersects from top/bottom
					int newDy = -1 * b.getDy();
					b.setDy(newDy);
				}
				isShown = false;
			}
		}

	}

	// calls movement
	public void update() {
		move();
	}

	// Draws paddle object
	public void draw(Graphics2D win) {
		Color padClr = Color.white;

		if (isShown) {
			win.setColor(padClr);
			win.fill(this);
		}
	}

}