package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import breakout.Breakout;
import utilities.GDV5;

public class Paddle extends Rectangle {
    private int dx, dy;
    private static final int PADDING = GDV5.getPadding(), MIN_WINDOW = 0, MAX_WINDOW_X = GDV5.getMaxWindowX(),
            MAX_WINDOW_Y = GDV5.getMaxWindowY();
    private static int STARTING_X, STARTING_Y;

    // Paddle constructor to be defined in other classes
    public Paddle(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

        STARTING_X = x;
        STARTING_Y = y;
    }

    // Moves the paddle and checks for edge
    public void move() {
        this.translate(dx, dy);
        if (this.getMinX() < MIN_WINDOW) {
            this.setLocation(0, y);
        } else if (this.getMaxX() > MAX_WINDOW_X) {
            this.setLocation(MAX_WINDOW_X - width, y);
        }
    }

    // Checks for ball paddle collision
    public void ballPadCol(Ball b) {
    	int bDx = (int) b.getDx();
		int bDy = (int) b.getDy();
		int collDir = GDV5.collisionDirection(this, b, bDx, bDy);
    	
        if (this.intersects(b)) {
            if (collDir == 1) {
                if (dx == 0) {
                    double newDy = b.getDy() * -1.1;
                    b.setDy(newDy);
                } else if (((b.getDx() > 0) && (dx > 0)) || (b.getDx() < 0) && (dx < 0)) {
                    double newDy = -1.15 * b.getDy();
                    b.setDy(newDy);
                    double newDx = b.getDx() * 1.25;
                    b.setDx(newDx);
                } else {
                    double newDy = -1.3 * b.getDy();
                    b.setDy(newDy);
                    double newDx = b.getDx() * -.86;
                    b.setDx(newDx);
                }
                Breakout.setPaddleBounces(Breakout.getPaddleBounces() + 1);
                Breakout.setBrickStreak(0);
            }
        }

    }

    public void reset() {
        setLocation(STARTING_X, STARTING_Y);
    }

    // calls movement
    public void update() {
        move();
    }

    // Draws paddle object
    public void draw(Graphics2D win) {
        Color padClr = Color.white;

        win.setColor(padClr);
        win.fill(this);
    }

    // ACCESSOR METHODS
    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
}
