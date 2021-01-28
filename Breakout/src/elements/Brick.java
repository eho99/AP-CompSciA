package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import breakout.Breakout;
import utilities.*;

@SuppressWarnings("serial")
public class Brick extends Rectangle {
	private static int scoreIter = 100;
	private boolean isShown = true;
	private Color brickClr;
	private int powerUpID;
	private Powerup powerup;

	// Brick constructor to be defined in other classes
	public Brick(int x, int y, int height, int width) {
		super(x, y, height, width);
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	// TBD implemented
//	public void shatter(Graphics2D brush) {
//		int numPieces = 0;
//		if (!isShown && !hasShattered) {
//			numPieces = this.width * 3;
//
//		}
//		Rectangle[] shatterRect = new Rectangle[numPieces];
//		for (Rectangle r : shatterRect) {
//
//		}
//	}

	// Calculates changes to get a powerup
	public boolean calcChances() {
		int range = 10, min = 1;
		int num = (int) (Math.random() * range) + min;
		if (num == 1) {
			powerUpID = 1;
			return true;
		} else if (num == 2) {
			powerUpID = 2;
			return true;
		} else if (num == 3) {
			powerUpID = 3;
			return true;
		} else if (num == 4) {
			powerUpID = 4;
			return true;
		}
		return false;
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

			Breakout.playCollision();

			if (calcChances()) {
				powerup = new Powerup((int) this.getCenterX() - 7, (int) this.getCenterY(), powerUpID);
			}
		}

	}

	// Draws paddle object
	public void draw(Graphics2D win) {
		if (isShown) {
			win.setColor(brickClr);
			win.fill(this);
		}
		if (powerup != null) {
			powerup.draw(win);
		}

	}

	public void update(Paddle p, Ball b) {
		if (powerup != null) {
			powerup.collision(p, b);
			powerup.update();
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
	
	public void setPowerup(Powerup p) {
		powerup = p;
	}

}