package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import breakout.Breakout;
import utilities.GDV5;

public class Brick extends Rectangle {
	private static int scoreIter = 100;
	private boolean isShown = true, hasShattered = false;
	private Color brickClr;

	// Brick constructor to be defined in other classes
	public Brick(int x, int y, int height, int width) {
		super(x, y, height, width);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	// TBD implemented
	public void shatter(Graphics2D brush) {
		int numPieces = 0;
		if (!isShown && !hasShattered) {
			numPieces = (this.width / 20) * 3;

		}
		Rectangle[] shatterRect = new Rectangle[numPieces];
		for (Rectangle r : shatterRect) {

		}
	}

	// Checks for ball paddle collision
	public void ballBrickCol(Ball b) {
		if (isShown && intersects(b)) {
			int bDx = (int) b.getDx();
			int bDy = (int) b.getDy();
			int collDir = GDV5.collisionDirection(this, b, bDx, bDy);
			
			if (collDir == 0 || collDir == 2) { /* intersects from right/left */
				b.setDx(-1 * b.getDx());
			}
			if (collDir == 1 || collDir == 3) { /* intersects from top/bottom */
				b.setDy(-1 * b.getDy());
			}
			isShown = false;

			int streak = Breakout.getBrickStreak();
			if (streak > 0) {
				scoreIter = 100;
				scoreIter *= (streak + 1);
			}

			Breakout.setScore(Breakout.getScore() + scoreIter);
			Breakout.setBrickStreak(streak + 1);
		}

	}

	// Draws paddle object
	public void draw(Graphics2D win) {
		if (isShown) {
			win.setColor(brickClr);
			win.fill(this);
		}
	}

	public boolean getShownState() {
		return isShown;
	}

	public void setShownState(boolean state) {
		isShown = state;
	}
	
	public Color getColor() {
		return brickClr;
	}
	
	public void setColor(Color clr) {
		brickClr = clr;
	}

}