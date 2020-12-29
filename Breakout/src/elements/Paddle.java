package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import utilities.GDV5;

public class Paddle extends Rectangle {
    private int xPos, yPos, dx, dy;
    final int PADDING = 10, MIN_WINDOW = 0, MAX_WINDOW_X = 800, MAX_WINDOW_Y = 600;

    // Paddle constructor to be defined in other classes
    public Paddle(int x, int y, int height, int width) {
        super(x, y, height, width);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

        xPos = x;
        yPos = y;
    }

    // Moves the paddle and checks for edge
    public void move() {
        this.translate(dx, dy);
        if (this.getMinY() < MIN_WINDOW) {
            this.setLocation(x, 0);
        } else if (this.getMaxY() > MAX_WINDOW_Y) {
            this.setLocation(x, MAX_WINDOW_Y - this.height);
        }
    }

    // Checks for ball paddle collision
    public void ballBrickCol(Ball b) {
        if (this.intersects(b)) {
            if (GDV5.collisionDirection(this, b, b.getDx(), b.getDy()) == 1) {
                int newDy = -1 * b.getDy();
                b.setDy(newDy);
            } else {
                System.out.println("past");
            }

            /*
             * if (GDV5.collisionDirection(this, b, b.getDx(), b.getDy()) == 0 ||
             * GDV5.collisionDirection(this, b, b.getDx(), b.getDy()) == 2) { // intersects
             * from right/left int newDx = -1 * b.getDx(); b.setDx(newDx); } else if
             * (GDV5.collisionDirection(this, b, b.getDx(), b.getDy()) == 1) { // intersects
             * from top int newDy = -1 * b.getDy(); b.setDy(newDy); }
             */
        }

    }

}
