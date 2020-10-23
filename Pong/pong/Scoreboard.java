package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import java.awt.Rectangle;

public class Scoreboard {
	final int PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;
	TitleScreen title = new TitleScreen();

	public void drawString(Graphics2D g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	public int getTextWidth(Graphics2D obj, String text) {
		Font f = obj.getFont();
		FontMetrics fm = obj.getFontMetrics(f);
		int width = fm.stringWidth(text);
		return width;
	}

	public void drawScore(Graphics2D brush, Pong p) {
		String p1Points = Integer.toString(p.p1Points);
		String p2Points = Integer.toString(p.p2Points);

		brush.setColor(Color.WHITE);
		brush.setFont(new Font("Trebuchet MS", Font.BOLD, 100));
		int width = getTextWidth(brush, p1Points);
		brush.drawString(p1Points, (MAX_WINDOW_X / 4) - (width / 2), 100);
		width = getTextWidth(brush, p1Points);
		brush.drawString(p2Points, ((MAX_WINDOW_X / 4) * 3) - (width / 2), 100);
	}

	public void drawRallyChallengePt(Graphics2D brush, Pong p) {
		String rallyChallengePt = Integer.toString(p.rallyChallengePt);
		int width = getTextWidth(brush, rallyChallengePt);

		brush.setColor(Color.BLACK);
		Rectangle blackBox = new Rectangle((MAX_WINDOW_X / 2) - (width / 2), 0, width, 120);
		brush.draw(blackBox);
		brush.fill(blackBox);

		brush.setColor(Color.WHITE);
		brush.setFont(new Font("Trebuchet MS", Font.BOLD, 100));
		width = getTextWidth(brush, rallyChallengePt);
		brush.drawString(rallyChallengePt, ((MAX_WINDOW_X / 2) - (width / 2)), 100);
	}

	public void drawFinalScore(Graphics2D brush, Pong p, TitleScreen title) {
		title.canvasClean(brush);
		String finalPt = Integer.toString(p.rallyChallengePt);
		String text = "You scored " + finalPt + " points!";

		if (!p.is1PPlaying && p.is1P) {
			int textSize = 70;
			brush.setColor(Color.WHITE);
			brush.setFont(new Font("Trebuchet MS", Font.BOLD, 70));
			int width = getTextWidth(brush, text);
			brush.drawString(text, (MAX_WINDOW_X / 2) - (width / 2), 180);

			textSize = 30;
			brush.setFont(new Font("Trebuchet MS", Font.PLAIN, textSize));
			text = "Press Escape to exit to Main Menu";
			width = getTextWidth(brush, text);
			brush.drawString(text, (MAX_WINDOW_X / 2) - (width / 2), 440);
		}
	}

	public void drawWin(Graphics2D brush, Pong p) {
		String text;
		int width;

		if (p.p1Points == 11) {
			title.canvasClean(brush);
			text = "Player 1 has won!";
			brush.setFont(new Font("Trebuchet MS", Font.BOLD, 90));
			width = getTextWidth(brush, text);
			brush.drawString(text, (MAX_WINDOW_X / 2) - (width / 2), 200);

			int textSize = 30;
			brush.setFont(new Font("Trebuchet MS", Font.PLAIN, textSize));
			text = "Press Escape to exit to Main Menu";
			width = getTextWidth(brush, text);
			brush.drawString(text, (MAX_WINDOW_X / 2) - (width / 2), 400);
			p.isWinScreen = true;
		} else if (p.p2Points == 11) {
			title.canvasClean(brush);
			text = "Player 2 has won!";
			brush.setFont(new Font("Trebuchet MS", Font.BOLD, 90));
			width = getTextWidth(brush, text);
			brush.drawString(text, (MAX_WINDOW_X / 2) - (width / 2), 200);

			int textSize = 30;
			brush.setFont(new Font("Trebuchet MS", Font.PLAIN, textSize));
			text = "Press Escape to exit to Main Menu";
			width = getTextWidth(brush, text);
			brush.drawString(text, (MAX_WINDOW_X / 2) - (width / 2), 400);
			p.isWinScreen = true;
		}
	}

	public void drawPause(Graphics2D brush) {
		String text;
		int width;

		text = "Game is paused\nPress U to unpause";
		brush.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
		width = getTextWidth(brush, text);
		brush.drawString(text, (MAX_WINDOW_X / 2) - (width / 3), 150);
		// this.drawString(brush, text, (MAX_WINDOW_X / 2) - (width / 3), 150);
	}
}