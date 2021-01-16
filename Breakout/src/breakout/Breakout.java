package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import elements.*;
import design.*;
import utilities.DesignDriver;
import utilities.GDV5;
import utilities.SoundDriver;

public class Breakout extends GDV5 {
	// JFRAME Constants
	final static int MAX_WINDOW_X = getMaxWindowX(), MAX_WINDOW_Y = getMaxWindowY();

	// Spacing between bricks
	private static final int HORIZONTAL_BRICK_DISTANCE = 10, VERTICAL_BRICK_DISTANCE = 10;

	// Brick position and dimensions
	private int sBrickX = HORIZONTAL_BRICK_DISTANCE, sBrickY = 90, brickHeight = 22, brickWidth = 0, paddleWidth = 200; // 140-200

	// Ball position and dimensions
	private int ballHeight = 25, ballWidth = 25, sBallX = (MAX_WINDOW_X / 2) - 10,
			sBallY = MAX_WINDOW_Y - brickHeight - ballHeight;

	// Paddle speed
	private static final int PADDLE_SPEED = 15;

	// Player score and variables
	private static int playerLives = 5, playerScore = 0, paddleBounces = 0, brickStreak = 0;

	// Total brick and number of rows
	private int numBricks = 49, numRows = 7;

	// Menu/State trackers
	public boolean isPlaying, isHelpScreen = false, isTitleScreen = true, isEndScreen = false, isPauseScreen = false,
			unlockedLvl2 = false, unlockedLvl3 = false;

	// Declaration of brick array
	Brick[][] bricks;

	// Declaration of ball object
	Ball ball = new Ball(sBallX, sBallY, ballHeight, ballWidth);

	// Declaration of paddle object
	Paddle paddle = new Paddle((MAX_WINDOW_X / 2) - (paddleWidth / 2), (MAX_WINDOW_Y - brickHeight), brickHeight,
			paddleWidth);

	// Declaration of design elements
	TitleScreen title = new TitleScreen();
	Scoreboard scoreboard = new Scoreboard();
	
	// SoundDriver Declaration
	String[] soundFiles = new String[9].;
	soundFiles[1] = "./../sounds/background.wav";
	soundFiles[2] = "./../sounds/collision1.wav";
	soundFiles[3] = "./../sounds/collision2.wav";
	soundFiles[4] = "./../sounds/collision3.wav";
	soundFiles[5] = "./../sounds/collision4.wav";
	soundFiles[6] = "./../sounds/collision5.wav";
	soundFiles[7] = "./../sounds/collision6.wav";
	soundFiles[8] = "./../sounds/collision7.wav";
	soundFiles[9] = "./../sounds/collision8.wav";
	SoundDriver sd = new SoundDrive(soundFiles, this);

	public void soundHandler() {
		
		SoundDriver sd = new SoundDriver(soundFiles, this);
	}

	// Class Constructor
	public Breakout() {
		int bricksPerRow = numBricks / numRows;
		bricks = new Brick[numRows][bricksPerRow];

		int whiteSpace = HORIZONTAL_BRICK_DISTANCE * bricksPerRow;
		brickWidth = (MAX_WINDOW_X - whiteSpace) / bricksPerRow;

		for (int row = 0; row < numRows; ++row) {
			for (int iterBrick = 0; iterBrick < bricksPerRow; ++iterBrick) {
				bricks[row][iterBrick] = new Brick(sBrickX, sBrickY, brickHeight, brickWidth);
				sBrickX += brickWidth + HORIZONTAL_BRICK_DISTANCE;
			}
			sBrickY += brickHeight + VERTICAL_BRICK_DISTANCE;
			sBrickX = HORIZONTAL_BRICK_DISTANCE;
		}

		int edgeBrickX = whiteSpace + (bricksPerRow * brickWidth);
		setMaxWindowX(edgeBrickX + HORIZONTAL_BRICK_DISTANCE);
		colorAssignment();
	}

	public void colorAssignment() {
		Color[] clrArray = new Color[4];
		clrArray[0] = new Color(42, 191, 101);
		clrArray[1] = new Color(7, 99, 120);
		clrArray[2] = new Color(242, 79, 191);
		clrArray[3] = new Color(222, 111, 11);
		// clrArray[4] = new Color(145, 234, 83);

		for (int row = 0; row < bricks.length; ++row) {
			int clrIndex = row % clrArray.length;
			for (int iterBrick = 0; iterBrick < bricks[0].length; ++iterBrick) {
				bricks[row][iterBrick].setColor(clrArray[clrIndex]);
			}
		}

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
			setBrickStreak(0);
		}
	}

	// Title Screen Selection
	public void titleScreenSelection() {
		if (KeysPressed[KeyEvent.VK_1]) {
			isTitleScreen = false;
			ball.setisInPlay(false);
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
		if (KeysPressed[KeyEvent.VK_D] || KeysPressed[KeyEvent.VK_RIGHT]) {
			paddle.setDx(PADDLE_SPEED);
		} else if (KeysPressed[KeyEvent.VK_A] || KeysPressed[KeyEvent.VK_LEFT]) {
			paddle.setDx(-PADDLE_SPEED);
		} else if (KeysTyped[KeyEvent.VK_D] || KeysTyped[KeyEvent.VK_RIGHT]) {
			paddle.setDx(0);
		} else if (KeysTyped[KeyEvent.VK_A] || KeysTyped[KeyEvent.VK_LEFT]) {
			paddle.setDx(0);
		}
	}

	public boolean isBoardClear() {
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

	public void drawLvl1(Graphics2D brush) {
		numBricks = 49;
		numRows = 7;
		for (Brick[] row : bricks) {
			for (Brick bk : row) {
				bk.draw(brush);
			}
		}
	}

	public void drawLvl2(Graphics2D brush) {

	}

	public void drawLvl3(Graphics2D brush) {

	}

	public void drawPlayScreen(Graphics2D brush) {
		if (unlockedLvl3) {
			drawLvl3(brush);
		} else if (unlockedLvl2) {
			drawLvl2(brush);
		} else {
			drawLvl1(brush);
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
			title.drawHelpScreen(brush);
		} else {
			if (getLives() > 0 && !isBoardClear()) {
				drawPlayScreen(brush);
			} else if (isBoardClear()) {
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

	public boolean isUnlockedLvl2() {
		return unlockedLvl2;
	}

	public void setUnlockedLvl2(boolean unlockedLvl2) {
		this.unlockedLvl2 = unlockedLvl2;
	}

	public boolean isUnlockedLvl3() {
		return unlockedLvl3;
	}

	public void setUnlockedLvl3(boolean unlockedLvl3) {
		this.unlockedLvl3 = unlockedLvl3;
	}

	// MAIN
	public static void main(String[] args) {
		// Breakout breakout = new Breakout(12, 3);
		Breakout breakout = new Breakout();
		breakout.start();

	}
}
