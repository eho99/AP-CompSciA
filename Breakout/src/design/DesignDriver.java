package design;

/**
 * A class that draws text onto the Canvas. 
 * 
 * Note: This class relies on the max and min window sizes set in GDV5. For the ideal implementation, add static variables
 * and accessor methods in GDV5 for these values. 
 * @author Eric Ho
 */

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Color;

import utilities.GDV5;

public class DesignDriver {
	final static int MIN_WINDOW = 0, MAX_WINDOW_X = GDV5.getMaxWindowX(),
			MAX_WINDOW_Y = GDV5.getMaxWindowY(); // If GDV5 is unavailable, these variables can be set to specific values

	// Global Draw String Function for multi-line strings - implements the Graphics2D drawString
	public static void drawMultiLineString(Graphics2D brush, Color color, String fontStyle, int typeface, int fontSize,
			String alignment, int yLevel, String text) {
		String[] widthJudge = text.split("\n");
		brush.setColor(color);
		brush.setFont(new Font(fontStyle, typeface, fontSize));
		
		int textWidth = getTextWidth(brush, widthJudge[0]);
		for (int i = 1; i < widthJudge.length; ++i) {
			int current = getTextWidth(brush, widthJudge[i]);
			if (textWidth < current) {
				textWidth = current;
			}
		}		
		int alignmentVal = 25; // Default spacing for left/right justification, can be changed

		if (alignment.equals("center")) {
			alignmentVal = (MAX_WINDOW_X / 2) - (textWidth / 2);
		} else if (alignment.equals("leftCenter")) {
			alignmentVal = (MAX_WINDOW_X / 4) - (textWidth / 2);
		} else if (alignment.equals("rightCenter")) {
			alignmentVal = ((MAX_WINDOW_X / 4) * 3) - (textWidth / 2);
		} else if (alignment.equals("leftJustified")) {
			;
		} else if (alignment.equals("rightJustified")) {
			alignmentVal = (MAX_WINDOW_X - textWidth - alignmentVal);
		}
		
		
		for (String line : widthJudge) // splits the text by \n, new line character 
			brush.drawString(line, alignmentVal, yLevel += brush.getFontMetrics().getHeight());	// uses Graphics2D drawString and changes the y value by adding the height of each of lines
	}

	// Clear canvas
	public static void canvasClean(Graphics2D brush) {
		brush.clearRect(0, 0, MAX_WINDOW_X, MAX_WINDOW_Y);
	}

	// Get width of the text
	public static int getTextWidth(Graphics2D obj, String text) {
		Font f = obj.getFont();
		FontMetrics fm = obj.getFontMetrics(f);
		int width = fm.stringWidth(text);
		return width;
	}

	// Set the specifications of the font and draws the text at the specified alignment
	public static void setAndDraw(Graphics2D brush, Color color, String fontStyle, int typeface, int fontSize,
			String alignment, int yLevel, String text) {		
		brush.setColor(color);
		brush.setFont(new Font(fontStyle, typeface, fontSize));
		int textWidth = getTextWidth(brush, text);
		int alignmentVal = 25; // Default spacing for left/right justification, can be changed

		if (alignment.equals("center")) {
			alignmentVal = (MAX_WINDOW_X / 2) - (textWidth / 2);
		} else if (alignment.equals("leftCenter")) {
			alignmentVal = (MAX_WINDOW_X / 4) - (textWidth / 2);
		} else if (alignment.equals("rightCenter")) {
			alignmentVal = ((MAX_WINDOW_X / 4) * 3) - (textWidth / 2);
		} else if (alignment.equals("leftJustified")) {
			;
		} else if (alignment.equals("rightJustified")) {
			alignmentVal = (MAX_WINDOW_X - textWidth - alignmentVal);
		}

		brush.drawString(text, alignmentVal, yLevel);
	}

}
