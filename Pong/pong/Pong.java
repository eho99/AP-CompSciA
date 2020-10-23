package pong;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import elements.Ball;
import elements.Paddle;
import elements.AIPaddle;
import utilities.GDV5;

public class Pong extends GDV5 {
	final int PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;
	public int p1Points = 0, p2Points = 0, rallyChallengePt = 0;

	int sBallX = (MAX_WINDOW_X / 2) - 12, sBallY = 275, ballHeight = 25, ballWidth = 25;
	int sLPadX = 10, sLPadY = 250 - PADDING, LPadHeight = 100, LPadWidth = 25;
	int sRPadX = 775, sRPadY = 250 - PADDING, RPadHeight = 100, RPadWidth = 25;

	public boolean is2PPlaying = false, is1PPlaying = false, isHelpScreen = false, isTitleScreen = true, is1P = false, is2P = false, isWinScreen = false,
			isRScoreScreen = false, isPauseScreen = false;

	Ball ball = new Ball(sBallX, sBallY, ballHeight, ballWidth, 0, 0);
	Paddle p1 = new Paddle(sLPadX, sLPadY, LPadHeight, LPadWidth, 0, 0);
	Paddle p2 = new Paddle(sRPadX, sRPadY, RPadHeight, RPadWidth, 0, 0);
	AIPaddle AIPaddle = new AIPaddle(sRPadX, sRPadY, RPadHeight, RPadWidth, 0, 0); 
	
	TitleScreen title = new TitleScreen();
	Scoreboard scoreboard = new Scoreboard();

	public void paddleMove() {
		if (is2PPlaying) {
			if (KeysPressed[KeyEvent.VK_S]) {
				p1.dy = 10;
			} else if (KeysPressed[KeyEvent.VK_W]) {
				p1.dy = -10;
			} else if (KeysTyped[KeyEvent.VK_S]) {
				p1.dy = 0;
			} else if (KeysTyped[KeyEvent.VK_W]) {
				p1.dy = 0;
			}

			if (KeysPressed[KeyEvent.VK_DOWN]) {
				p2.dy = 10;
			} else if (KeysPressed[KeyEvent.VK_UP]) {
				p2.dy = -10;
			} else if (KeysTyped[KeyEvent.VK_DOWN]) {
				p2.dy = 0;
			} else if (KeysTyped[KeyEvent.VK_UP]) {
				p2.dy = 0;
			}
		} else if (!isTitleScreen && !isWinScreen && !is1P) {
			p1.padReset();
			p2.padReset();
			if (ball.isServingToRight) {
				if (KeysPressed[KeyEvent.VK_UP] && KeysPressed[KeyEvent.VK_DOWN]) {
					ball.setVel(this);
				}
			} else {
				if (KeysPressed[KeyEvent.VK_W] && KeysPressed[KeyEvent.VK_S]) {
					ball.setVel(this);
				}
				
				// TEST SET SPEED
				// ball.dx = -14;
				// ball.dy = 0;

			}
		} else if (is1PPlaying) {
			if (KeysPressed[KeyEvent.VK_S] || KeysPressed[KeyEvent.VK_DOWN]) {
				p1.dy = 10;
			} else if (KeysPressed[KeyEvent.VK_W] || KeysPressed[KeyEvent.VK_UP]) {
				p1.dy = -10;
			} else if (KeysTyped[KeyEvent.VK_S] || KeysTyped[KeyEvent.VK_DOWN]) {
				p1.dy = 0;
			} else if (KeysTyped[KeyEvent.VK_W] || KeysTyped[KeyEvent.VK_UP]) {
				p1.dy = 0;
			}
		} else if (!isTitleScreen && !isRScoreScreen) {
			p1.padReset();
			p2.padReset();
			if ((KeysPressed[KeyEvent.VK_UP] && KeysPressed[KeyEvent.VK_DOWN]) || (KeysPressed[KeyEvent.VK_W] && KeysPressed[KeyEvent.VK_S])) {
					ball.setVel(this);
				}
			} 				
				
			// TEST SET SPEED
			// ball.dx = -14;
			// ball.dy = 0;

		if (KeysPressed[KeyEvent.VK_R]) {
			p1.padReset();
			p2.padReset();
			ball.ballReset(this);
		}
	}

	public boolean showHelp() {
		if (!is2PPlaying && isTitleScreen) {
			if (KeysPressed[KeyEvent.VK_H]) {
				return true;
			}
		}
		return false;
	}

	public void escapeToMenu(Graphics2D brush) {
		if (KeysPressed[KeyEvent.VK_ESCAPE]) {
			title.canvasClean(brush);
			isTitleScreen = true;
			isHelpScreen = false;
			is2PPlaying = false;
			isWinScreen = false;
			is2P = false;
			is1P = false;
			isRScoreScreen = false;
			ball.ballReset(this);
			p1Points = 0;
			p2Points = 0;
			rallyChallengePt = 0;
		}
	}

	/* TBD Implemented - PAUSE FEATURES
	public boolean pauseGame(Graphics2D brush) {
		if (is2PPlaying && !isTitleScreen) {
			if (KeysPressed[KeyEvent.VK_P]) {
				return true;
			}
		}
		return false;
	}

	public void unPause(Graphics2D brush) {
		if (KeysPressed[KeyEvent.VK_U]) {
			title.canvasClean(brush);
			is2PPlaying = true;
			isPauseScreen = false;
		}
	}
	*/

	public void update() {
		p1.ballPadCol(ball);
		p2.ballPadCol(ball);

		if (isTitleScreen && !isHelpScreen) {
			if (KeysPressed[KeyEvent.VK_1]) {
				isTitleScreen = false;
				is1P = true;
			} else if (KeysPressed[KeyEvent.VK_2]) {
				isTitleScreen = false;
				is2P = true;
			}
		}

		paddleMove();

		ball.update(this);
		p1.update();
		p2.update();
	}

	public void draw(Graphics2D brush) {
		if (showHelp())
			isHelpScreen = true;
		this.escapeToMenu(brush);
		
		if (isTitleScreen) {
			if (!isHelpScreen && !is2PPlaying) {
				title.drawTitle(brush);
			} else if (isHelpScreen & !is2PPlaying) {
				title.displayControls(brush);
			}
		} else {
			if (!isHelpScreen && !isPauseScreen) {
				if (is2P) {
					title.canvasClean(brush);
					p1.draw(brush);
					p2.draw(brush);
					ball.draw(brush);
					scoreboard.drawScore(brush, this);
					scoreboard.drawWin(brush, this);
				} else if (is1P) {
					if (!isRScoreScreen) {
						title.canvasClean(brush);
						p1.draw(brush);
						AIPaddle.teleportAndDraw(brush, ball, this);
						ball.draw(brush);
						scoreboard.drawRallyChallengePt(brush, this);
					} else {
						scoreboard.drawFinalScore(brush, this, title);
					}
					
				} 
			}
		}

		/*
		if (pauseGame(brush)) {
			isPauseScreen = true;
			is2PPlaying = false;
		}
		unPause(brush);
		if (isPauseScreen) {
			title.canvasClean(brush);
			scoreboard.drawPause(brush);
		}
		*/
	}

	public static void main(String[] args) {
		Pong pongGame = new Pong();

		pongGame.start();
	}

}
