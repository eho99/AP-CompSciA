package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import elements.*;
import design.*;
import utilities.*;

@SuppressWarnings("serial")
public class Breakout extends GDV5 {
	// JFRAME Constants
	final static int MAX_WINDOW_X = getMaxWindowX(), MAX_WINDOW_Y = getMaxWindowY();

	// Spacing between bricks
	private static final int HORIZONTAL_BRICK_DISTANCE = 15, VERTICAL_BRICK_DISTANCE = 15;

	// Brick position and dimensions
	private int sBrickX = HORIZONTAL_BRICK_DISTANCE, sBrickY = 90, brickHeight = 22, brickWidth = 0, paddleWidth = 175; // 140-200

	// Ball position and dimensions
	private int ballHeight = 25, ballWidth = 25, sBallX = (MAX_WINDOW_X / 2) - 10,
			sBallY = MAX_WINDOW_Y - brickHeight - ballHeight;

	// Paddle speed
	private static final int PADDLE_SPEED = 15;

	// Player score and variables
	private static int playerLives = 5, playerScore = 0, paddleBounces = 0, brickStreak = 0, highScore = 0;

	// Total brick and number of rows
	private int numBricks = 70, numRows = 10, bricksPerRow = numBricks / numRows;

	// Menu/State trackers
	public static boolean isGameScreen = false, isTitleScreen = true, isLossScreen = false,
			isWinScreen = false, unlockedLvl2 = false, unlockedLvl3 = false,
			lostMusicPlayed = false, isMuted = false, madeBricks = true;

	public static int level = 1;

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
	private static int FILE;

	// Populates the array and declares the SoundDriver object
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

	// Class Constructor - initializes sounds, calculates brick width, assigns colors, and makes first level
	public Breakout() {
		loadSounds();
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
		makeLvl1();
	}

	/**
	 * All sound playing functions are contained below. 
	 */
	// Plays title music
	public static void playTitleMusic() {
		if (isTitleScreen) {
			FILE = 9;
			for (int i = 0; i < soundFiles.length; i++) {
				if ((i != FILE) && sounds.isPlaying(i)) {
					sounds.stop(i);
				}
			}
			if (!sounds.isPlaying(FILE) && !isMuted) {
				sounds.loop(FILE);
			}
		}

	}

	// Plays startup music
	public static void playStartMusic() {
		FILE = 0;
		for (int i = 0; i < soundFiles.length; i++) {
			if ((i != FILE) && sounds.isPlaying(i)) {
				sounds.stop(i);
			}
		}
		if (!sounds.isPlaying(FILE) && !isMuted) {
			sounds.play(FILE);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Plays background music
	public static void playInGameMusic() {
		FILE = 11;
		if (!lostMusicPlayed) {
			if (!sounds.isPlaying(FILE) && !isMuted) {
				sounds.play(FILE);
			}
		}

	}

	// Plays victory music
	public static void playWinMusic() {
		FILE = 12;
		if (!lostMusicPlayed) {
			sounds.stop(11);
			if (!sounds.isPlaying(FILE) && !isMuted) {
				sounds.play(FILE);
				lostMusicPlayed = true;
			}
		}
	}

	// Plays losing music
	public static void playLoseMusic() {
		FILE = 10;
		if (!lostMusicPlayed) {
			sounds.stop(11);
			if (!sounds.isPlaying(FILE) && !isMuted) {
				sounds.play(FILE);
				lostMusicPlayed = true;
			}
		}

	}

	// Picks a random collision sound and plays it
	public static void playCollision() {
		int range = 7, min = 1;
		int random = (int) (Math.random() * range) + min;
		sounds.play(random);
	}

	// setup colors
	public void colorAssignment() {
		int x = 80;
		Color color;

		for (int row = 0; row < bricks.length; ++row) {
			color = new Color(150, x, 250 - x / 2);
			for (int iterBrick = 0; iterBrick < bricks[0].length; ++iterBrick) {
				bricks[row][iterBrick].setColor(color);
			}
			x += 15;
		}
	}

	// Global escape key
	public void escapeToMenu(Graphics2D brush) {
		if (KeysPressed[KeyEvent.VK_ESCAPE]) {
			DesignDriver.canvasClean(brush);
			isTitleScreen = true;

			isWinScreen = false;
			isLossScreen = false;
			lostMusicPlayed = false;
			isGameScreen = false;
			unlockedLvl2 = false;
			unlockedLvl3 = false;
			level = 1;

			ball.reset();
			paddle.reset();

			for (Brick[] row : bricks) {
				for (Brick bk : row) {
					bk.setShownState(true);
					bk.setPowerup(null);
				}
			}
			setLives(5);
			setScore(0);
			setPaddleBounces(0);
			setBrickStreak(0);
			makeLvl1();
		}
	}
	
	
	// Level Skip - VIDEO TESTING PURPOSES ONLY
	public void levelSkip() {
		if (KeysPressed[KeyEvent.VK_L]) {
			for (int row = 0; row < numRows; ++row) {
				for (int bk = 0; bk < bricksPerRow; ++bk) {
					bricks[row][bk].setShownState(false);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Title Screen Selection
	public void titleScreenSelection() {
		if (KeysPressed[KeyEvent.VK_ENTER]) {
			isTitleScreen = false;
			isGameScreen = true;
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

	// checks if all the bricks are hit
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

	// Makes all bricks shown
	public void setAllShown() {
		for (int row = 0; row < numRows; ++row) {
			for (int bk = 0; bk < bricksPerRow; ++bk) {
				bricks[row][bk].setShownState(true);
			}
		}
	}

	// Generates the pattern for level 1
	public void makeLvl1() {
		setAllShown();
		for (int row = 0; row < numRows; ++row) {
			for (int bk = 0; bk < bricksPerRow; ++bk) {
				if (row > 7) {
					bricks[row][bk].setShownState(false);
				}
				if (bk == 1 || bk == (bricksPerRow - 2)) {
					bricks[row][bk].setShownState(false);
				}
			}
		}
	}

	// Generates the pattern for level 2
	public void makeLvl2() {
		setAllShown();
		for (int row = 0; row < numRows; ++row) {
			for (int bk = 0; bk < bricksPerRow; ++bk) {
				if (row > 7) {
					bricks[row][bk].setShownState(false);
				}
				if (row == 1 || row == 6) {
					if (bk != 0 && bk != 6) {
						bricks[row][bk].setShownState(false);
					}
				}
				if (row == 2 || row == 5) {
					if (bk == 1 || bk == 5) {
						bricks[row][bk].setShownState(false);
					}
				}
				if (row == 3 || row == 4) {
					if (bk == 1 || bk == 3 || bk == 5) {
						bricks[row][bk].setShownState(false);
					}

				}
			}
		}
	}

	// Generates the pattern for level 3
	public void makeLvl3() {
		setAllShown();
	}

	// Draws bricks, score, lives, level, and paddle bounces
	public void drawPlayScreen(Graphics2D brush) {
		for (Brick[] row : bricks) {
			for (Brick bk : row) {
				bk.draw(brush);
			}
		}

		ball.draw(brush);
		paddle.draw(brush);
		scoreboard.drawTopBar(brush);
	}

	// Checks for levels and cleans up to start the new level when necessary
	public void handleLevels() {
		ball.reset();
		if (unlockedLvl3) {
			isWinScreen = true;
		} else if (unlockedLvl2) {
			unlockedLvl3 = true;
			if (!madeBricks) {
				makeLvl3();
				setLives(5);
				level = 3;
			}

			for (Brick[] row : bricks) {
				for (Brick bk : row) {
					bk.setPowerup(null);
				}
			}
		} else {
			unlockedLvl2 = true;
			if (!madeBricks) {
				makeLvl2();
				setLives(5);
				level = 2;
			}

			for (Brick[] row : bricks) {
				for (Brick bk : row) {
					bk.setPowerup(null);
				}
			}
		}
	}

	
	// draw method for all game objects
	public void draw(Graphics2D brush) {
		if (isTitleScreen) {
			title.drawTitle(brush);
			title.drawHelpScreen(brush);
		} else if (isGameScreen) {
			
			if (getLives() > 0 && !isBoardClear()) {
				drawPlayScreen(brush);
			} else if (isBoardClear()) {
				madeBricks = false;
				handleLevels();
				ball.setisInPlay(false);
			} else {
				isLossScreen = true;
				isGameScreen = false;
				ball.setisInPlay(false);
			}
			if (!ball.getisInPlay()) {
				scoreboard.drawSpace(brush);
			}
		} else if (isLossScreen) {
			scoreboard.drawEndScreen(brush);
		} else if (isWinScreen) {
			scoreboard.drawWinScreen(brush);
		}

		escapeToMenu(brush);
	}
	
	// update method for all game objects - includes sound
	public void update() {
		if (isTitleScreen) {
			playTitleMusic();
			titleScreenSelection();
		} else if (isGameScreen) {
			playInGameMusic();
			if (ball.getisInPlay()) {
				paddle.ballPadCol(ball);

				for (Brick[] row : bricks) {
					for (Brick bk : row) {
						bk.ballBrickCol(ball);
						bk.update(paddle, ball);
					}
				}

				paddleMoveCtrl();
				paddle.update();
				ball.update();
			} else {
				paddle.reset();
				startPlay();
			}
		} else if (isLossScreen) {
			playLoseMusic();
		} else if (isWinScreen) {
			playWinMusic();
		}
		
		levelSkip();
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

	public boolean isUnlockedLvl3() {
		return unlockedLvl3;
	}

	public static int getLevel() {
		return level;
	}

	public static int getHighScore() {
		return highScore;
	}

	public static void setHighScore(int highScore) {
		Breakout.highScore = highScore;
	}

	// MAIN
	public static void main(String[] args) {
		Breakout breakout = new Breakout();
		breakout.start();
	}
}
