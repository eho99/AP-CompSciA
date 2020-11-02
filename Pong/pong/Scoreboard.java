package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import java.awt.Rectangle;

public class Scoreboard {
	final int PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;
	final String fontStyle = "Trebuchet MS";
	TitleScreen title = new TitleScreen();

	// Global Draw String Function for multi-line strings
	public void drawString(Graphics2D g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, (y += g.getFontMetrics().getHeight()));
	}

	// Get width of the text 
	public int getTextWidth(Graphics2D obj, String text) {
		Font f = obj.getFont();
		FontMetrics fm = obj.getFontMetrics(f);
		int width = fm.stringWidth(text);
		return width;
	}

	// Draw 2P points
	public void drawScore(Graphics2D brush, Pong p) {
		brush.setColor(Color.WHITE);
		brush.setFont(new Font(fontStyle, Font.BOLD, 100));
		String p1Points = Integer.toString(p.p1Points);
		String p2Points = Integer.toString(p.p2Points);
		int textWidth, alignment, yLevel;

		yLevel = 100;
		textWidth = getTextWidth(brush, p1Points);
		alignment = (MAX_WINDOW_X / 4) - (textWidth / 2);
		brush.drawString(p1Points, alignment, yLevel);

		textWidth = getTextWidth(brush, p2Points);
		alignment = ((MAX_WINDOW_X / 4) * 3) - (textWidth / 2);
		brush.drawString(p2Points, alignment, yLevel);
	}

	// Draw 1P points
	public void drawRallyChallengePt(Graphics2D brush, Pong p) {
		String rallyChallengePt = Integer.toString(p.rallyChallengePt);
		int textWidth, centerAlignment, yLevel;
		textWidth = getTextWidth(brush, rallyChallengePt);
		centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);

		yLevel = 120;
		brush.setColor(Color.BLACK);
		Rectangle blackBox = new Rectangle(centerAlignment, 0, textWidth, yLevel);
		brush.draw(blackBox);
		brush.fill(blackBox);

		yLevel = 100;
		brush.setColor(Color.WHITE);
		brush.setFont(new Font(fontStyle, Font.BOLD, 100));
		textWidth = getTextWidth(brush, rallyChallengePt);
		centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
		brush.drawString(rallyChallengePt, centerAlignment, yLevel);
	}

	// Display final score
	public void drawFinalScore(Graphics2D brush, Pong p, TitleScreen title) {
		title.canvasClean(brush);
		int textSize, centerAlignment, textWidth, yLevel;
		String text;
		
		String finalPt = Integer.toString(p.rallyChallengePt);
		text = "You scored " + finalPt + " points!";
		
		// Checks for ongoing rally
		if (!p.is1PPlaying && p.is1P) {
			textSize = 70;
			yLevel = 180;
			brush.setColor(Color.WHITE);
			brush.setFont(new Font(fontStyle, Font.BOLD, textSize));
			textWidth = getTextWidth(brush, text);
			centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
			brush.drawString(text, centerAlignment, yLevel);
			
			yLevel = 240;
			textSize = 25;
			brush.setFont(new Font(fontStyle, Font.BOLD, textSize));

			if (p.rallyChallengePt > p.rallyHighScore) {
				text = "You got a high score!";
				textWidth = getTextWidth(brush, text);
				centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
				brush.drawString(text, centerAlignment, yLevel);
			} else {
				text = "Current high score: " + p.rallyHighScore;
				textWidth = getTextWidth(brush, text);
				centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
				brush.drawString(text, centerAlignment, yLevel);
			}

			textSize = 30;
			yLevel = 440;
			text = "Press Escape to exit to Main Menu";
			brush.setFont(new Font(fontStyle, Font.PLAIN, textSize));
			textWidth = getTextWidth(brush, text);
			centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
			brush.drawString(text, centerAlignment, yLevel);

			if (p.rallyChallengePt >= 50) {
				p.ball.ballClr = new Color(255, 223, 0); // Sets color of the ball to gold for 50 rallies

				yLevel = 320;
				text = "You have unlocked the golden ball!";
				textWidth = getTextWidth(brush, text);
				centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
				brush.drawString(text, centerAlignment, yLevel);
			}
		}
	}

	// Draw win screen
	public void drawWin(Graphics2D brush, Pong p) {
		int textWidth, centerAlignment, yLevel, textSize;
		String text;
	
		// Checks for win condition and draws respective text
		if (p.p1Points == 11) {
			title.canvasClean(brush);
			yLevel = 200;
			textSize = 90;
			text = "Player 1 has won!";
			brush.setFont(new Font(fontStyle, Font.BOLD, textSize));
			textWidth = getTextWidth(brush, text);
			centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
			brush.drawString(text, centerAlignment, yLevel);

			yLevel = 400;
			textSize = 30;
			brush.setFont(new Font(fontStyle, Font.PLAIN, textSize));
			text = "Press Escape to exit to Main Menu";
			textWidth = getTextWidth(brush, text);
			centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
			brush.drawString(text, centerAlignment, yLevel);
			p.isWinScreen = true;
		} else if (p.p2Points == 11) {
			title.canvasClean(brush);
			yLevel = 200;
			textSize = 90;
			text = "Player 2 has won!";
			brush.setFont(new Font(fontStyle, Font.BOLD, textSize));
			textWidth = getTextWidth(brush, text);
			centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
			brush.drawString(text, centerAlignment, yLevel);

			yLevel = 400;
			textSize = 30;
			brush.setFont(new Font(fontStyle, Font.PLAIN, textSize));
			text = "Press Escape to exit to Main Menu";
			textWidth = getTextWidth(brush, text);
			centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
			brush.drawString(text, centerAlignment, yLevel);
			p.isWinScreen = true;
		}
	}
}