package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LongSummaryStatistics;

import elements.*;
import design.*;
import utilities.DesignDriver;
import utilities.GDV5;
import utilities.SoundDriver;

public class Breakout extends GDV5 {
	// JFRAME Constants
	final static int MAX_WINDOW_X = getMaxWindowX(), MAX_WINDOW_Y = getMaxWindowY();

	// Spacing between bricks
	private static final int HORIZONTAL_BRICK_DISTANCE = 15, VERTICAL_BRICK_DISTANCE = 15;

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
	public static boolean isPlaying, isHelpScreen = false, isTitleScreen = true, isLossScreen = false,
			isWinScreen = false, isPauseScreen = false, unlockedLvl2 = false, unlockedLvl3 = false,
			lostMusicPlayed = false, isMuted = false;

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
	private static String[] soundFiles;
	private static SoundDriver sounds;

	public void loadSounds() {
		soundFiles = new String[13];
		soundFiles[0] = "./../sounds/start.wav";
		soundFiles[1] = "./../sounds/collision1.wav";
		soundFiles[2] = "./../sounds/collision2.wav";
		soundFiles[3] = "./../sounds/collision3.wav";
		soundFiles[4] = "./../sounds/collision4.wav";
		soundFiles[5] = "./../sounds/collision5.wav";
		soundFiles[6] = "./../sounds/collision6.wav";
		soundFiles[7] = "./../sounds/collision7.wav";
		soundFiles[8] = "./../sounds/collision8.wav";
		soundFiles[9] = "./../sounds/backgroundWii.wav";
		soundFiles[10] = "./../sounds/lose.wav";
		soundFiles[11] = "./../sounds/inGameSoundFade.wav";
		soundFiles[12] = "./../sounds/win.wav";
		sounds = new SoundDriver(soundFiles, this);
	}

	// Class Constructor
	public Breakout() {
		loadSounds();
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

	// All sound playing functions
	public static void playTitleMusic() {
		int file = 9;
		for (int i = 0; i < soundFiles.length; i++) {
			if ((i != file) && sounds.isPlaying(i)) {
				sounds.stop(i);
			}
		}
		if (!sounds.isPlaying(file) && !isMuted) {
			sounds.loop(file);
		}
	}

	public static void playStartMusic() {
		int file = 0;
		for (int i = 0; i < soundFiles.length; i++) {
			if ((i != file) && sounds.isPlaying(i)) {
				sounds.stop(i);
			}
		}
		if (!sounds.isPlaying(file) && !isMuted) {
			sounds.play(file);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void playInGameMusic() {
		int file = 11;
		if (!lostMusicPlayed) {
			for (int i = 0; i < soundFiles.length; i++) {
				if ((i != file) && sounds.isPlaying(i)) {
					sounds.stop(i);
				}
			}
			if (!sounds.isPlaying(file) && !isMuted) {
				sounds.loop(file);
			}
		}

	}

	public static void playWinMusic() {
		int file = 12;
		if (!lostMusicPlayed) {
			for (int i = 0; i < soundFiles.length; i++) {
				if ((i != file) && sounds.isPlaying(i)) {
					sounds.stop(i);
				}
			}
			if (!sounds.isPlaying(file) && !isMuted) {
				sounds.play(file);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lostMusicPlayed = true;
			}
		}
	}

	public static void playLoseMusic() {
		int file = 10;
		if (!lostMusicPlayed) {
			for (int i = 0; i < soundFiles.length; i++) {
				if ((i != file) && sounds.isPlaying(i)) {
					sounds.stop(i);
				}
			}
			if (!sounds.isPlaying(file) && !isMuted) {
				sounds.play(file);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lostMusicPlayed = true;
			}
		}

	}

	public static void playCollision() {
		int range = 7, min = 1;
		int random = (int) (Math.random() * range) + min;

		sounds.play(random);
	}

	public void globalMute() {
		if (KeysPressed[KeyEvent.VK_M]) {
			if (!isMuted) {
				sounds.setVolumeAll(0);
				isMuted = !isMuted;
				System.out.println("here1");
			} else {
				sounds.setVolumeAll(5);
				isMuted = !isMuted;
				System.out.println("here2");
			}
		}

	}

	// setup colors
	public void colorAssignment() {
		int x = 100;
		Color color;

		for (int row = 0; row < bricks.length; ++row) {
			color = new Color(0, x, 250 - x / 2);
			for (int iterBrick = 0; iterBrick < bricks[0].length; ++iterBrick) {
				bricks[row][iterBrick].setColor(color);
			}
			x += 25;
		}
	}

	// Global escape key
	public void escapeToMenu(Graphics2D brush) {
		if (KeysPressed[KeyEvent.VK_ESCAPE]) {
			DesignDriver.canvasClean(brush);
			isTitleScreen = true;
			isHelpScreen = false;
			isWinScreen = false;
			isLossScreen = false;
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
		if (KeysPressed[KeyEvent.VK_ENTER]) {
			isTitleScreen = false;
			ball.setisInPlay(false);
			playStartMusic();
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
				isWinScreen = true;
				ball.setisInPlay(false);
				scoreboard.drawWinScreen(brush);
			} else {
				isLossScreen = true;
				ball.setisInPlay(false);
				scoreboard.drawEndScreen(brush);
			}
			if (!ball.getisInPlay() && !isWinScreen && !isLossScreen) {
				scoreboard.drawSpace(brush);
			}
		}
		escapeToMenu(brush);
	}

	public void update() {
		if (isTitleScreen && !isHelpScreen) {
			playTitleMusic();
			titleScreenSelection();
		} else {
			playInGameMusic();
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
		if (isLossScreen) {
			playLoseMusic();
		} else if (isWinScreen) {
			playWinMusic();
		}
		globalMute();
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
