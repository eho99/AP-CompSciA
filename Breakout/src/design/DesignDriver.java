package design;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.Serializable;

import javax.swing.text.AttributeSet.FontAttribute;

import java.awt.Color;

import utilities.GDV5;

public class DesignDriver implements Serializable {
    static int PADDING = GDV5.getPadding(), MIN_WINDOW = 0, MAX_WINDOW_X = GDV5.getMaxWindowX(),
            MAX_WINDOW_Y = GDV5.getMaxWindowY();

    // Global Draw String Function for multi-line strings
    public static void drawString(Graphics2D g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
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

    public static void setAndDraw(Graphics2D brush, Color color, String fontStyle, int typeface, int fontSize,
            String alignment, int yLevel, String text) {
        brush.setColor(color);
        brush.setFont(new Font(fontStyle, typeface, fontSize));
        int textWidth = getTextWidth(brush, text);
        int alignmentVal = 0;

        if (alignment.equals("center")) {
            alignmentVal = (MAX_WINDOW_X / 2) - (textWidth / 2);
        } else if (alignment.equals("leftCenter")) {
            alignmentVal = (MAX_WINDOW_X / 4) - (textWidth / 2);
        } else if (alignment.equals("rightCenter")) {
            alignmentVal = ((MAX_WINDOW_X / 4) * 3) - (textWidth / 2);
        }

        brush.drawString(text, alignmentVal, yLevel);
    }

}
