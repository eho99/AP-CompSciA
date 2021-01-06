package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import breakout.Breakout;
import utilities.GDV5;

public class Brick extends Rectangle {
	final int PADDING = GDV5.getPadding(), MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;
	private boolean isShown = true, hasShattered = false;

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
		if (!isShown && hasShattered) {
			numPieces = (this.width / 20) * 3;

		}
		Rectangle[] shatterRect = new Rectangle[numPieces];
		for (Rectangle r : shatterRect) {

		}
	}

	// Checks for ball paddle collision
	public void ballBrickCol(Ball b) {
		int bDx = (int) b.getDx();
		int bDy = (int) b.getDy();
		int collDir = GDV5.collisionDirection(this, b, bDx, bDy);

		if (isShown && this.intersects(b)) {
			if (collDir == 0 || collDir == 2) { /* intersects from right/left */
				double newDx = -1 * b.getDx();
				b.setDx(newDx);
			}
			if (collDir == 1 || collDir == 3) { /* intersects from top/bottom */
				double newDy = -1 * b.getDy();
				b.setDy(newDy);
			}
			isShown = false;

			int streak = Breakout.getBrickStreak();
			int scoreIter = 100;
			if (streak > 0) {
				scoreIter *= (streak + 1);
			}

			Breakout.setScore(Breakout.getScore() + scoreIter);
			Breakout.setBrickStreak(streak + 1);
		}

	}

	// Draws paddle object
	public void draw(Graphics2D win) {
		Color padClr = Color.white;

		if (isShown) {
			win.setColor(padClr);
			win.fill(this);
		}
	}

	public boolean getShownState() {
		return isShown;
	}

	public void setShownState(boolean state) {
		isShown = state;
	}

}