/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		for(int i = 0; i < BRICKS_IN_BASE; i++) {
			int  next = BRICKS_IN_BASE - i ; // show how many bricks are there in next row
			for(int j = 0; j < next; j ++) { // j = current number of brick
				int x = (getWidth() / 2) - (next * BRICK_WIDTH) / 2 + (j * BRICK_WIDTH);
				int y = getHeight() - BRICK_HEIGHT * (i + 1);				
				GRect rect = new GRect (x , y , BRICK_WIDTH, BRICK_HEIGHT);
				add(rect);
			}
		}
	}
}

