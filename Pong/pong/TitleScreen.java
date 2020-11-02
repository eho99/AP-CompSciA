package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class TitleScreen {
	final int PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;
	final String fontStyle = "Trebuchet MS";

	// Global Draw String Function for multi-line strings
	public void drawString(Graphics2D g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	// Clear canvas
	public void canvasClean(Graphics2D brush) {
		brush.clearRect(0, 0, 800, 600);
	}

	// Get width of the text 
	public int getTextWidth(Graphics2D obj, String text) {
		Font f = obj.getFont();
		FontMetrics fm = obj.getFontMetrics(f);
		int width = fm.stringWidth(text);
		return width;
	}
	
	// Draw title screen
	public void drawTitle(Graphics2D brush) {
		brush.setColor(Color.WHITE);
		int textSize, centerAlignment, textWidth, yLevel;
		String text;

		textSize = 100;
		yLevel = 150;
		text = "PONG";
		brush.setFont(new Font(fontStyle, Font.BOLD, textSize));
		textWidth = getTextWidth(brush, text);
		centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
		brush.drawString(text, centerAlignment, yLevel);

		textSize = 25;
		yLevel = 220;
		text = "Made by Eric Ho";
		brush.setFont(new Font(fontStyle, Font.PLAIN, textSize));
		textWidth = getTextWidth(brush, text);
		centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
		brush.drawString(text, centerAlignment, yLevel);

		yLevel = 440;
		text = "Press 1 for the Rally Challenge";
		textWidth = getTextWidth(brush, text);
		centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
		brush.drawString(text, centerAlignment, yLevel);

		yLevel = 470;
		text = "Press 2 for Classic Pong";
		textWidth = getTextWidth(brush, text);
		centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
		brush.drawString(text, centerAlignment, yLevel);

		yLevel = 500;
		text = "Press H for Directions and Controls";
		textWidth = getTextWidth(brush, text);
		centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
		brush.drawString(text, centerAlignment, yLevel);
	}

	// Display controls screen
	public void displayControls(Graphics2D brush) {
		canvasClean(brush);
		brush.setColor(Color.WHITE);
		int textSize, centerAlignment, specificAlignment, textWidth, yLevel;
		String text;

		textSize = 40;
		yLevel = 100;
		text = "How to Play:";
		brush.setFont(new Font(fontStyle, Font.BOLD, textSize));
		textWidth = getTextWidth(brush, text);
		specificAlignment = (MAX_WINDOW_X / 4) - (textWidth / 2);
		brush.drawString(text, specificAlignment, yLevel);

		textSize = 20;
		text = "Controls:";
		textWidth = getTextWidth(brush, text);
		specificAlignment = ((MAX_WINDOW_X / 4) * 3) - (textWidth / 2);
		brush.drawString(text, specificAlignment, yLevel);

		text = "You and your opponent will move\npaddles and rally a ball back and forth.\nThe first to reach 11 points wins.";
		brush.setFont(new Font(fontStyle, Font.PLAIN, textSize));
		textWidth = getTextWidth(brush, text);
		specificAlignment = (MAX_WINDOW_X / 4) - (textWidth / 6);
		this.drawString(brush, text, specificAlignment, 150); // Draws while breaking up at "\n"
		
		text = "Press both of the respective\nmovement keys to start each rally.";
		textWidth = getTextWidth(brush, text);
		specificAlignment = (MAX_WINDOW_X / 4) - (textWidth / 4);
		this.drawString(brush, text, specificAlignment, 250);

		text = "Left Paddle: Use W and S\nto move up and down.";
		textWidth = getTextWidth(brush, text);
		specificAlignment = (MAX_WINDOW_X / 4) * 3 - (textWidth / 4);
		this.drawString(brush, text, specificAlignment, 150);

		text = "Right Paddle: Use the Up and Down\nArrow Keys to move up and down.";
		textWidth = getTextWidth(brush, text);
		this.drawString(brush, text, (MAX_WINDOW_X / 4) * 3 - (textWidth / 4), 220);

		textSize = 30;
		brush.setFont(new Font(fontStyle, Font.PLAIN, textSize));
		text = "Press Escape to exit to Main Menu";
		textWidth = getTextWidth(brush, text);
		centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
		this.drawString(brush, text, centerAlignment, 400);
	}
}