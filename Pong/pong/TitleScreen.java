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

	// get width of the text 
	public int getTextWidth(Graphics2D obj, String text) {
		Font f = obj.getFont();
		FontMetrics fm = obj.getFontMetrics(f);
		int width = fm.stringWidth(text);
		return width;
	}
	
	// Draw title screen
	public void drawTitle(Graphics2D brush) {
		brush.setColor(Color.WHITE);

		brush.setFont(new Font(fontStyle, Font.BOLD, 100));
		int textWidth = getTextWidth(brush, "PONG");
		brush.drawString("PONG", (MAX_WINDOW_X / 2) - (textWidth / 2), 150);

		String text = "Press 1 for the Rally Challenge";
		brush.setFont(new Font(fontStyle, Font.PLAIN, 25));
		textWidth = getTextWidth(brush, text);
		brush.drawString(text, (MAX_WINDOW_X / 2) - (textWidth / 2), 440);

		text = "Press 2 for Classic Pong";
		textWidth = getTextWidth(brush, text);
		brush.drawString(text, (MAX_WINDOW_X / 2) - (textWidth / 2), 470);

		text = "Press H for How to Play and Controls";
		textWidth = getTextWidth(brush, text);
		brush.drawString(text, (MAX_WINDOW_X / 2) - (textWidth / 2), 500);
	}

	// Display controls screen
	public void displayControls(Graphics2D brush) {
		canvasClean(brush);

		int textSize = 40;
		brush.setColor(Color.WHITE);
		brush.setFont(new Font(fontStyle, Font.BOLD, textSize));

		String text = "How to Play:";

		int textWidth = getTextWidth(brush, text);
		brush.drawString(text, (MAX_WINDOW_X / 4) - (textWidth / 2), 100);

		textSize = 20;
		text = "Controls:";
		textWidth = getTextWidth(brush, text);
		brush.drawString(text, ((MAX_WINDOW_X / 4) * 3) - (textWidth / 2), 100);

		brush.setFont(new Font(fontStyle, Font.PLAIN, textSize));

		text = "You and your opponent will move\npaddles and rally a ball back and forth.\nThe first to reach 11 points wins.";
		textWidth = getTextWidth(brush, text);
		this.drawString(brush, text, (MAX_WINDOW_X / 4) - (textWidth / 6), 150);
		
		text = "Press both of the respective\nmovement keys to start each rally.";
		textWidth = getTextWidth(brush, text);
		this.drawString(brush, text, (MAX_WINDOW_X / 4) - (textWidth / 4), 250);

		text = "Left Paddle: Use W and S\nto move up and down.";
		textWidth = getTextWidth(brush, text);
		this.drawString(brush, text, (MAX_WINDOW_X / 4) * 3 - (textWidth / 4), 150);

		text = "Right Paddle: Use the Up and Down\nArrow Keys to move up and down.";
		textWidth = getTextWidth(brush, text);
		this.drawString(brush, text, (MAX_WINDOW_X / 4) * 3 - (textWidth / 4), 220);

		textSize = 30;
		brush.setFont(new Font(fontStyle, Font.PLAIN, textSize));
		text = "Press Escape to exit to Main Menu";
		textWidth = getTextWidth(brush, text);
		this.drawString(brush, text, (MAX_WINDOW_X / 2) - (textWidth / 2), 400);
	}
}