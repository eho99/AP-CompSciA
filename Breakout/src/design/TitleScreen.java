package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import breakout.Breakout;

public class TitleScreen extends DesignDriver {
    final String fontStyle = "Trebuchet MS";

    // Draw title screen
    public void drawTitle(Graphics2D brush) {
        int fontSize, yLevel;
        String text, alignment;

        fontSize = 100;
        yLevel = 150;
        text = "Breakout";
        alignment = "center";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
        
        fontSize = 25;
        yLevel = 220;
        text = "Made by Eric Ho";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
        

        yLevel = 440;
        text = "Press 1 for Breakout";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
        
        yLevel = 480;
        text = "Press H for Directions";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);       

    }

    // Display controls screen NEEDS TO BE UPDATED FOR BREAKOUT
    public void drawHelpScreen(Graphics2D brush) {
        canvasClean(brush);
        int fontSize, yLevel;
        String text, alignment;

        fontSize = 40;
        yLevel = 100;
        text = "How to Play:";
        alignment = "leftCenter";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
        

        fontSize = 40;
        text = "Controls:";
        alignment = "rightCenter";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);        

        fontSize = 20;
        yLevel = 150;
        alignment = "leftCenter";
        text = "In breakout, you will work to\nmove a paddle to bounce back and forth.\nThe goal is to break all the bricks.";
        drawMultiLineString(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
        
        yLevel = 250;
        text = "Press SPACE to serve the ball across.";
        drawMultiLineString(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
        
        yLevel = 150;
        alignment = "rightCenter";
        text = "Paddle: Use the WASD or the\narrow keys to move across.";
        drawMultiLineString(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);

        fontSize = 30;
        yLevel = 600;
        alignment = "center";
        text = "Press Escape to exit to Main Menu";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);  

        
    }
}
