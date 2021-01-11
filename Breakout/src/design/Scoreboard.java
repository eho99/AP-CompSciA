package design;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import breakout.Breakout;
import utilities.DesignDriver;

public class Scoreboard extends DesignDriver {
    final String fontStyle = "Trebuchet MS";
    
    // Draw hearts
    public void drawHeart(Graphics2D brush, int x, int y, int width, int height) {
        int[] triangleX = {
                x - 2*width/18,
                x + width + 2*width/18,
                (x - 2*width/18 + x + width + 2*width/18)/2};
        int[] triangleY = { 
                y + height - 2*height/3, 
                y + height - 2*height/3, 
                y + height };
        brush.fillOval(
                x - width/12,
                y, 
                width/2 + width/6, 
                height/2); 
        brush.fillOval(
                x + width/2 - width/12,
                y,
                width/2 + width/6,
                height/2);
        brush.fillPolygon(triangleX, triangleY, triangleX.length);
    }

    // Draw lives
    public void drawLives(Graphics2D brush) {
        String alignment;
        int yLevel, fontSize;

        alignment = "leftJustified";
        yLevel = 25;
        fontSize = 20;
        String text = "Lives: "; // + lives;
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
        
        brush.setColor(Color.PINK);
        for (int i = 0; i < Breakout.getLives(); i++) {
        	drawHeart(brush, 90 + (25 * i), 12, 15, 15);
        }
    }

    // Draw Score
    public void drawScore(Graphics2D brush) {
        String score = Integer.toString(Breakout.getScore());
        String alignment;
        int yLevel, fontSize;

        alignment = "center";
        yLevel = 25;
        fontSize = 20;
        String text = "Score: " + score;
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
    }

    // Draw Bounces
    public void drawBounces(Graphics2D brush) {
        String bounces = Integer.toString(Breakout.getPaddleBounces());
        String alignment;
        int yLevel, fontSize;

        alignment = "rightJustified";
        yLevel = 25;
        fontSize = 20;
        String text = "Paddle Bounces: " + bounces;
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
    }

    // Draws press space at beginning of place
    public void drawSpace(Graphics2D brush) {
        String alignment;
        int yLevel, fontSize;

        alignment = "center";
        yLevel = 600;
        fontSize = 30;
        String text = "Press SPACE to start";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.PLAIN, fontSize, alignment, yLevel, text);
    }

    // Draw End Screen
    public void drawEndScreen(Graphics2D brush) {
        String score = Integer.toString(Breakout.getScore());
        String alignment;
        int yLevel, fontSize;

        alignment = "center";
        yLevel = 200;
        fontSize = 100;
        String text = "GAME OVER";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);

        yLevel = 300;
        fontSize = 30;
        text = "Final Score: " + score;
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);

        yLevel = 350;
        text = "Press Escape to Exit";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
    }

    // Draw Win Screen
    public void drawWinScreen(Graphics2D brush) {
        String score = Integer.toString(Breakout.getScore());
        String alignment;
        int yLevel, fontSize;

        alignment = "center";
        yLevel = 200;
        fontSize = 100;
        String text = "You Win!";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);

        yLevel = 300;
        fontSize = 30;
        text = "Final Score: " + score;
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);

        yLevel = 350;
        text = "Press Escape to Exit";
        setAndDraw(brush, Color.WHITE, fontStyle, Font.BOLD, fontSize, alignment, yLevel, text);
    }
}