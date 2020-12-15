/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private static final int PAUSE = 10;
	
	int bricks = NBRICK_ROWS * NBRICKS_PER_ROW;
	
	private GRect brick;

	private GOval ball;

	private GRect paddle;

	private double vx, vy;

	private RandomGenerator rgen = RandomGenerator.getInstance();
	

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		ClickToStart();
		initial();
		for(int i = 0; i < NTURNS; i ++) {
			game();
			drawBall();
			if(bricks == 0) {
				i = NTURNS;
				remove(ball);
			}
		}
		while(true) {
			if(bricks > 0) {
				removeAll();
				lose();
				break;
			}
		}
	}
	
	private void ClickToStart() {
		  waitForClick();
}

	private void initial() {
    	drawBricks();
    	drawBall();
    	drawPaddle();
	}	
	
	private void game() {
    	waitForClick();
    	velocity();
		while (true) {
			if (ball.getY() + 2 * BALL_RADIUS > HEIGHT) {
				remove(ball);
				break;
			}
			moving();
			if (bricks == 0) {
				remove(ball);
				paddle.setVisible(false);
				Win();
				break;
			}
		}
    }	

	private void moving() {
		ball.move(vx, vy);
		pause(PAUSE);
		walls();
		GObject collider = getCollidingObject();
		if (collider == paddle) {
			vy = -vy;
			double wentFar = ball.getY() + ball.getWidth() - paddle.getY();
			ball.move(0, -wentFar);
		} else if (collider != null && collider != paddle) {
			remove(collider);
			bricks--;
			AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
			bounceClip.play();
			vy = -vy;
		}
	}

	private void walls() {
		if (ball.getX() <= 0) {
			vx = -vx;
			audio();
		} else if ((ball.getX() + BALL_RADIUS * 2) >= WIDTH) {
			vx = -vx;
			audio();
		} else if (ball.getY() <= 0) {
			vy = -vy;
			audio();
		}
	}

	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			audio();
			return getElementAt(ball.getX(), ball.getY());
		} else if (getElementAt((ball.getX() + BALL_RADIUS * 2), ball.getY()) != null) {
			audio();
			return getElementAt((ball.getX() + BALL_RADIUS * 2), ball.getY());
		} else if (getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS * 2)) != null) {
			audio();
			return getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS * 2));
		} else if (getElementAt((ball.getX() + BALL_RADIUS * 2), (ball.getY() + BALL_RADIUS * 2)) != null) {
			audio();
			return getElementAt((ball.getX() + BALL_RADIUS * 2), (ball.getY() + BALL_RADIUS * 2));
		} else {
			return null;
		}
	}
	
	private void audio() {
		AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
		bounceClip.play();

	}

	public void mouseMoved(MouseEvent e) {
		if ((e.getX() < WIDTH - PADDLE_WIDTH / 2) && (e.getX() > PADDLE_WIDTH / 2)) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		} else if (e.getX() >= WIDTH - PADDLE_WIDTH) {
			paddle.setLocation(WIDTH - PADDLE_WIDTH, HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		}
	}	
	
	private void drawBricks() {
		for (int row = 1; row <= NBRICK_ROWS; row++) {
			for (int col = 0; col < NBRICKS_PER_ROW ; col++) {
				double x = WIDTH / 2 - (NBRICKS_PER_ROW * BRICK_WIDTH / 2) - ((NBRICKS_PER_ROW - 1) * BRICK_SEP / 2)
						+ (col * (BRICK_WIDTH + BRICK_SEP));
				double y = BRICK_Y_OFFSET + (row * (BRICK_HEIGHT + BRICK_SEP));

				brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
				brick.setFilled(true);

				if (row < 3) {
					brick.setColor(Color.RED);
				} else if (row < 5) {
					brick.setColor(Color.ORANGE);
				} else if (row < 7) {
					brick.setColor(Color.YELLOW);
				} else if (row < 9) {
					brick.setColor(Color.GREEN);
				} else if (row <= 10) {
					brick.setColor(Color.CYAN);
				}
			}
		}		
	}
	
	private void drawPaddle() {
		double x = WIDTH / 2 - PADDLE_WIDTH / 2;
		double y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();		
	}	
	
	private void drawBall() {
		double x = WIDTH / 2 - BALL_RADIUS;
		double y = HEIGHT / 2 - BALL_RADIUS;
		ball = new GOval(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
		add(ball);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
	}

	private void velocity() {
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
	}
	
    private void lose() {
		GLabel Lose = new GLabel("YOU LOSE!", WIDTH / 2, HEIGHT / 2);
		Lose.move(-Lose.getWidth() / 2, -Lose.getHeight() / 2);
		Lose.setColor(Color.RED);
		add(Lose);
    }	
    
	private void Win() {
		GLabel win = new GLabel("YOU WIN!", WIDTH / 2, HEIGHT / 2);
		win.move(-win.getWidth() / 2, -win.getHeight() / 2);
		win.setColor(Color.GREEN);
		add(win);
	}
}
