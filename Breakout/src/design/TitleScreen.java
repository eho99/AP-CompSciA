package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import breakout.Breakout;

public class TitleScreen extends DesignDriver {
    final String fontStyle = "Trebuchet MS";

    // Draw title screen
    public void drawTitle(Graphics2D brush) {
        brush.setColor(Color.WHITE);
        int textSize, centerAlignment, textWidth, yLevel;
        String text;

        textSize = 100;
        yLevel = 150;
        text = "Breakout";
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
        // text = "Press 1 for the Rally Challenge";
        text = "Press 1 for Breakout";
        textWidth = getTextWidth(brush, text);
        centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
        brush.drawString(text, centerAlignment, yLevel);

        /*
         * yLevel = 470; text = "Press 2 for Classic Pong"; textWidth =
         * getTextWidth(brush, text); centerAlignment = (MAX_WINDOW_X / 2) - (textWidth
         * / 2); brush.drawString(text, centerAlignment, yLevel);
         * 
         * yLevel = 500; text = "Press H for Directions and Controls"; textWidth =
         * getTextWidth(brush, text); centerAlignment = (MAX_WINDOW_X / 2) - (textWidth
         * / 2); brush.drawString(text, centerAlignment, yLevel);
         */
    }

    // Display controls screen NEEDS TO BE UPDATED FOR BREAKOUT
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
        drawString(brush, text, specificAlignment, 150); // Draws while breaking up at "\n"

        text = "Press both of the respective\nmovement keys to start each rally.";
        textWidth = getTextWidth(brush, text);
        specificAlignment = (MAX_WINDOW_X / 4) - (textWidth / 4);
        drawString(brush, text, specificAlignment, 250);

        text = "Left Paddle: Use W and S\nto move up and down.";
        textWidth = getTextWidth(brush, text);
        specificAlignment = (MAX_WINDOW_X / 4) * 3 - (textWidth / 4);
        drawString(brush, text, specificAlignment, 150);

        text = "Right Paddle: Use the Up and Down\nArrow Keys to move up and down.";
        textWidth = getTextWidth(brush, text);
        drawString(brush, text, (MAX_WINDOW_X / 4) * 3 - (textWidth / 4), 220);

        textSize = 30;
        brush.setFont(new Font(fontStyle, Font.PLAIN, textSize));
        text = "Press Escape to exit to Main Menu";
        textWidth = getTextWidth(brush, text);
        centerAlignment = (MAX_WINDOW_X / 2) - (textWidth / 2);
        drawString(brush, text, centerAlignment, 400);
    }
}
