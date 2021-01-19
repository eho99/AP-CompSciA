package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.ldap.ExtendedRequest;

import breakout.Breakout;

class RevertPaddle extends TimerTask {
	Paddle p;
	private int ORIGINAL_WIDTH = 175;

	public RevertPaddle(Paddle p) {
		this.p = p;
	}

	public void run() {
		this.p.width = ORIGINAL_WIDTH;
	}
}

public class Powerup extends Rectangle {
	private static final int SPEED = 7;
	private static int id;

	private static Timer timer = new Timer(true);

	// Powerup constructor to be defined in other classes
	public Powerup(int x, int y, int id) {
		super(x, y, 15, 15);
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/*
	 * ID KEY: 1. Extends the width of the paddle, color is white 2. Increases ball
	 * size, color is cyan 3. Decreases ball speed, color is yellow 4. Decreases
	 * paddle width, color is red
	 */

	public static Color getColorByID() {
		if (id == 1) {
			return Color.WHITE;
		} else if (id == 2) {
			return Color.CYAN;
		} else if (id == 3) {
			return Color.YELLOW;
		} else if (id == 4) {
			return Color.RED;
		}

		return Color.BLACK;
	}

	public static String getPowerByID() {
		if (id == 1) {
			return "extendPaddle";
		} else if (id == 2) {
			return "increaseBallSize";
		} else if (id == 3) {
			return "decreaseBallSpeed";
		} else if (id == 4) {
			return "shortenPaddle";
		}

		return null;
	}

	public static void calcChances() {
		int range = 10, min = 1;
		int num = (int) (Math.random() * range) + min;
		if (num == 1) {
			id = 1;
		} else if (num == 2) {
			id = 2;
		} else if (num == 3) {
			id = 3;
		} else if (num == 4) {
			id = 4;
		}
	}

	public static void performPowerUp(Paddle p, Ball b) {
		if (getPowerByID().equals("extendPaddle")) {
			TimerTask revertPaddle = new RevertPaddle(p);
			p.width += 30;
			timer.schedule(revertPaddle, 10000);
		} else if (getPowerByID().equals("increasedBallSize")) {
			b.width += 15;
			b.height += 15;
		} else if (getPowerByID().equals("decreaseBallSpeed")) {
			b.setDx(b.getDx() - 3);
			b.setDy(b.getDy() - 3);
		} else if (getPowerByID().equals("shortenPaddle")) {
			TimerTask revertPaddle = new RevertPaddle(p);
			p.width -= 20;
			timer.schedule(revertPaddle, 10000);
		}
	}

	// Checks for ball paddle collision
	public void collision(Paddle p, Ball b) {
		if (this.intersects(p)) {
			performPowerUp(p, b);
		}
	}

	// Ball movement
	public void update() {
		translate(0, SPEED);
	}

	// Draws ball
	public void draw(Graphics2D brush) {
		brush.setColor(getColorByID());
		brush.fill(this);
	}

}
