package elements;

import java.awt.Graphics2D;
import pong.Pong;

public class AIPaddle extends Paddle {
	// Constructor for use in other classes
	public AIPaddle(int x, int y, int height, int width, int dx, int dy) {
		super(x, y, height, width, dx, dy);
	}
	
	// Tracks movement of the ball and draws to match it 
	public void trackAndDraw(Graphics2D brush, Ball b, Pong p) {
		this.y = (int) ((b.getCenterY() - (this.height / 2)));
		this.AIPadCol(b, p);
		this.draw(brush);
	}
	
	// Checks collision with the ball - modified from regular paddle to always hit, has slightly different multipliers
	public void AIPadCol(Ball b, Pong p) {
		boolean inYRange = (b.getMaxY() >= this.getMinY()) && (b.getMinY() <= this.getMaxY());
		boolean isAtFront = (((b.getMaxX() <= this.getMaxX()) && b.getMaxX() >= MAX_WINDOW_X - this.width) && (b.dx > 0));

		if (inYRange) {
			if (isAtFront) {
				if (this.dy == 0) {
					b.dx *= -1;
				} else if (((this.dy > 0) && (b.dy > 0)) || ((this.dy < 0) && (b.dy < 0))) {
					b.dx *= -1.2;
					b.dy *= 1.62;
				} 
				// Every hit with the AI paddle counts as a rally
				p.rallyChallengePt++;
			}
		}
	}

}