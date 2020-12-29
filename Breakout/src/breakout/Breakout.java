package breakout;

import utilities.GDV5;

import java.awt.Graphics2D;

import elements.Ball;
import elements.Brick;

public class Breakout extends GDV5 {
	int PADDING = 2, MAX_WINDOW_X = getMaxWindowX(), MAX_WINDOW_Y = getMaxWindowY();

	int sBrickX = PADDING, sBrickY = 100, brickHeight = 25, brickWidth = 0;
	int ballHeight = 20, ballWidth = 20;
	int sBallX = (MAX_WINDOW_X / 2) - 10, sBallY = (MAX_WINDOW_X / 2) - 10;

	Brick[] bricks;
	Ball ball = new Ball(sBallX, sBallY, ballHeight, ballWidth, 9, 10);

	public Breakout(int numBricks, int numRows) {
		bricks = new Brick[numBricks];
		int bricksPerRow = numBricks / numRows;
		int whiteSpace = PADDING * bricksPerRow;
		brickWidth = (MAX_WINDOW_X - whiteSpace) / bricksPerRow;

		for (int i = 0; i < numBricks; i++) {
			bricks[i] = new Brick(sBrickX, sBrickY, brickHeight, brickWidth);
			sBrickX += brickWidth + PADDING;
			if (i != 0 && i % bricksPerRow == (bricksPerRow - 1)) {
				sBrickY += brickHeight + PADDING;
				sBrickX = PADDING;
			}
		}
		int edgeBrickX = whiteSpace + (bricksPerRow * brickWidth);
		setMaxWindowX(edgeBrickX + PADDING);
	}

	public void draw(Graphics2D brush) {
		for (Brick bk : bricks) {
			bk.draw(brush);
		}
		ball.draw(brush);

	}

	public void update() {
		for (Brick bk : bricks) {
			bk.ballBrickCol(ball);
			bk.update();
		}
		ball.update();

	}

	public static void main(String[] args) {
		Breakout breakout = new Breakout(28, 4);
		breakout.start();
	}
}
