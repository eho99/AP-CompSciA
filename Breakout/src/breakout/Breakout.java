package breakout;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import elements.*;
import design.*;
import utilities.GDV5;

public class Breakout extends GDV5 {
	// JFRAME Constants
	int PADDING = getPadding(), MAX_WINDOW_X = getMaxWindowX(), MAX_WINDOW_Y = getMaxWindowY();

	// Parameters for ball and brick objects
	private int sBrickX = PADDING, sBrickY = 100, brickHeight = 23, brickWidth = 0, paddleWidth = 200; // 140-200
	private int ballHeight = 20, ballWidth = 20;
	private int sBallX = (MAX_WINDOW_X / 2) - 10, sBallY = MAX_WINDOW_Y - brickHeight - ballHeight;
	private static int playerLives = 5, playerScore = 0, paddleBounces = 0, brickStreak = 0;

	// Menu/State trackers
	public boolean isPlaying, isHelpScreen = false, isTitleScreen = true, isEndScreen = false, isPauseScreen = false;

	// Declaration of all objects
	Brick[][] bricks;
	Ball ball = new Ball(sBallX, sBallY, ballHeight, ballWidth, 9, 10);
	Paddle paddle;
	TitleScreen title = new TitleScreen();
	Scoreboard scoreboard = new Scoreboard();

	public Breakout(int numBricks, int numRows) {
		int bricksPerRow = numBricks / numRows;
		bricks = new Brick[numRows][bricksPerRow];

		int whiteSpace = PADDING * bricksPerRow;
		brickWidth = (MAX_WINDOW_X - whiteSpace) / bricksPerRow;

		for (int row = 0; row < numRows; row++) {
			for (int iterBrick = 0; iterBrick < bricksPerRow; iterBrick++) {
				bricks[row][iterBrick] = new Brick(sBrickX, sBrickY, brickHeight, brickWidth);
				sBrickX += brickWidth + PADDING;
			}
			sBrickY += brickHeight + PADDING;
			sBrickX = PADDING;
		}

		paddle = new Paddle((MAX_WINDOW_X / 2) - (paddleWidth / 2), (MAX_WINDOW_Y - brickHeight), brickHeight,
				paddleWidth);

		int edgeBrickX = whiteSpace + (bricksPerRow * brickWidth);
		setMaxWindowX(edgeBrickX + PADDING);
	}

	// Global escape key
	public void escapeToMenu(Graphics2D brush) {
		if (KeysPressed[KeyEvent.VK_ESCAPE]) {
			DesignDriver.canvasClean(brush);
			isTitleScreen = true;
			isHelpScreen = false;
			isEndScreen = false;
			ball.reset();
			paddle.reset();

			for (Brick[] row : bricks) {
				for (Brick bk : row) {
					bk.setShownState(true);
				}
			}
			setLives(5);
			setScore(0);
			setPaddleBounces(0);
		}
	}

	// Title Screen Selection
	public void titleScreenSelection() {
		if (KeysPressed[KeyEvent.VK_1]) {
			isTitleScreen = false;
			ball.setisInPlay(false);
		} else if (KeysPressed[KeyEvent.VK_H]) {
			isTitleScreen = false; 
			isHelpScreen = true;
		}
	}	

	// Key Check for Starting Play
	public void startPlay() {
		if (KeysPressed[KeyEvent.VK_SPACE]) {
			ball.setisInPlay(true);
			ball.setVel();
		}
	}

	// Controls all Paddle movement and Key Detection
	public void paddleMoveCtrl() {
		int paddSpeed = 15;
		if (ball.getisInPlay()) {
			if (KeysPressed[KeyEvent.VK_D] || KeysPressed[KeyEvent.VK_RIGHT]) {
				paddle.setDx(paddSpeed);
			} else if (KeysPressed[KeyEvent.VK_A] || KeysPressed[KeyEvent.VK_LEFT]) {
				paddle.setDx(-paddSpeed);
			} else if (KeysTyped[KeyEvent.VK_D] || KeysTyped[KeyEvent.VK_RIGHT]) {
				paddle.setDx(0);
			} else if (KeysTyped[KeyEvent.VK_A] || KeysTyped[KeyEvent.VK_LEFT]) {
				paddle.setDx(0);
			}
		}

	}

	public boolean checkBoardClear() {
		boolean notClear = false;

		for (Brick[] row : bricks) {
			for (Brick bk : row) {
				if (bk.getShownState()) {
					return notClear;
				}
			}
		}
		return true; // true means all bricks are cleared
	}

	public void drawPlayScreen(Graphics2D brush) {
		for (Brick[] row : bricks) {
			for (Brick bk : row) {
				bk.draw(brush);
			}
		}
		ball.draw(brush);
		paddle.draw(brush);
		scoreboard.drawLives(brush);
		scoreboard.drawScore(brush);
		scoreboard.drawBounces(brush);
	}

	public void draw(Graphics2D brush) {
		if (isTitleScreen && !isHelpScreen) {
			title.drawTitle(brush);
		} else if (isHelpScreen) {
			title.drawHelpScreen(brush);
		} else {
			if (getLives() > 0 && !checkBoardClear()) {
				drawPlayScreen(brush);
			} else if (checkBoardClear()) {
				isEndScreen = true;
				ball.setisInPlay(false);
				scoreboard.drawWinScreen(brush);
			} else {
				isEndScreen = true;
				ball.setisInPlay(false);
				scoreboard.drawEndScreen(brush);
			}
			if (!ball.getisInPlay() && !isEndScreen) {
				scoreboard.drawSpace(brush);
			}
		}
		escapeToMenu(brush);

	}

	public void update() {
		if (isTitleScreen && !isHelpScreen) {
			titleScreenSelection();
		} else {
			if (ball.getisInPlay()) {
				paddle.ballPadCol(ball);
				
				for (Brick[] row : bricks) {
					for (Brick bk : row) {
						bk.ballBrickCol(ball);
					}
				}
				
				paddleMoveCtrl();
				paddle.update();
				ball.update();
			} else {
				paddle.reset();
				Breakout.setBrickStreak(0);
				startPlay();
			}

		}
	}

	// ACCESSOR METHODS
	public static int getLives() {
		return playerLives;
	}

	public static int getScore() {
		return playerScore;
	}

	public static int getPaddleBounces() {
		return paddleBounces;
	}

	public static int getBrickStreak() {
		return brickStreak;
	}

	public static void setLives(int lives) {
		playerLives = lives;
	}

	public static void setScore(int score) {
		playerScore = score;
	}

	public static void setPaddleBounces(int bounces) {
		paddleBounces = bounces;
	}

	public static void setBrickStreak(int streak) {
		brickStreak = streak;
	}

	// MAIN
	public static void main(String[] args) {
		// Breakout breakout = new Breakout(12, 3);
 		Breakout breakout = new Breakout(35, 5);
		breakout.start();

	}
}
