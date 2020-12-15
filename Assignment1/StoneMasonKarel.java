/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	public void run() {
		while(frontIsClear()) {
			fill();
			goDown();
			next();
		}
		if(frontIsBlocked()) {
			fill();
		}
		goDown();
		
	}
	private void next() {
		if(frontIsClear()) {
			for(int i = 0; i < 4; i++) {
				move();
			}
		}
		//goes to the next column
	}
	private void goDown() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
		//just goes to the first line
	}
	private void fill() {
		turnLeft();
		while(frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();	
			}
			move();
		}
		if(frontIsBlocked()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
		}
		//this void fills the column if its damaged(no beepers)
	}

}
