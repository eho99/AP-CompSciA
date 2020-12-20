package breakout;

import utilities.GDV5;

import java.awt.Graphics2D;

import elements.Ball;
import elements.Paddle;

public class Breakout extends GDV5 {
	final int PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = getMaxWindowX(), MAX_WINDOW_Y = getMaxWindowY();

	int sRPadX = 10, sRPadY = 20 - PADDING, RPadHeight = 30, RPadWidth = 120;
	int ballHeight = 25, ballWidth = 25;
	int sBallX = (MAX_WINDOW_X / 2) - 12, sBallY = (MAX_WINDOW_Y - (2*PADDING - ballHeight));
	
	Paddle[] bricks;
	Ball ball = new Ball(sBallX, sBallY, ballHeight, ballWidth, 0, 0);

	public Breakout(int numBricks) {
		bricks = new Paddle[numBricks];

		for (int i = 0; i < numBricks; i++) {
			bricks[i] = new Paddle(sRPadX, sRPadY, RPadHeight, RPadWidth, 0, 0);
			sRPadX += RPadWidth + PADDING;
			if (i != 0 && i % 5 == 4) {
				sRPadY += RPadHeight + 10;
				sRPadX = 10;
			}
		}
	}

	public void draw(Graphics2D brush) {
		for (Paddle bk : bricks) {
			bk.draw(brush);
		}
		ball.draw(brush);
		
	}

	public void update() {
		for (Paddle bk : bricks) {
			bk.update();
		}
		ball.update();
	}

	public static void main(String[] args) {
		Breakout breakout = new Breakout(20);
		breakout.start();
	}
}
