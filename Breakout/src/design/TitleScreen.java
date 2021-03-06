package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import utilities.DesignDriver;

public class TitleScreen extends DesignDriver {
    final String fontStyle = "Bauhaus 93";// "Trebuchet MS";
    int fontSize, yLevel;
    String text, alignment;

    // Draw title screen
    public void drawTitle(Graphics2D brush) {
        fontSize = 100;
        yLevel = 125;
        text = "Breakout";
        alignment = "center";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
        
        fontSize = 25;
        yLevel = 175;
        text = "Made by Eric Ho";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.PLAIN, fontSize, alignment, yLevel, text);
        
        
        fontSize = 35;
        yLevel = 600;
        text = "Press ENTER to Start";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.PLAIN, fontSize, alignment, yLevel, text);       
        

    }

    // Display controls screen 
    public void drawHelpScreen(Graphics2D brush) {
        fontSize = 50;
        yLevel = 300;
        text = "How to Play:";
        alignment = "leftCenter";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.PLAIN, fontSize, alignment, yLevel, text);
        
        text = "Controls:";
        alignment = "rightCenter";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.PLAIN, fontSize, alignment, yLevel, text);        

        fontSize = 25;
        yLevel = 320;
        alignment = "leftCenter";
        text = "In breakout, you will work to move\na paddle to bounce back and forth.\nThe goal is to break all the bricks.";
        drawMultiLineString(brush, Color.WHITE, fontStyle, Font.PLAIN, fontSize, alignment, yLevel, text);
        
        alignment = "rightCenter";
        text = "Paddle: Use the WASD or the\narrow keys to move across.";
        drawMultiLineString(brush, Color.WHITE, fontStyle, Font.PLAIN, fontSize, alignment, yLevel, text);
        
        yLevel = 450;
        alignment = "center";
        text = "Press SPACE to serve the ball across.";
        drawMultiLineString(brush, Color.WHITE, fontStyle, Font.PLAIN, fontSize, alignment, yLevel, text);
        
        yLevel = 700;
        text = "Hint: There are some powerups. Use the colors to help you determine which ones to hit.";
        drawMultiLineString(brush, Color.WHITE, fontStyle, Font.PLAIN, fontSize, alignment, yLevel, text);
        
    }
    
}
