import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		putBeeper();
		check();
		fill();
	}
	public void check() {
		if(frontIsBlocked()) {
			turnLeft();
			put();
		}
		//this is for 1 x something board
	}
	public void fill() {
		while(leftIsClear()) {
			put();
			turn();
			put();
			turnRight();
			
			if(frontIsBlocked()) turnRight();
			else turn();
		}
		
		if(beepersPresent()) put();
		// checks for next row to fill
	}
	public void turn() {
		if(beepersPresent()) {
			up();
			move();
			putBeeper();
		}else {
			up();
			putBeeper();
		}
		//takes U turn between rows
		
	}
	public void up() {
		if(facingEast()) {
			turnLeft();
			move();
			turnLeft();
		}else {
			move();
			turnRight();
		}
		//move to the upper row to fill
	}
	public void put() {
		while(frontIsClear()) {
			move();
			if(frontIsClear()) {
				move();
				putBeeper();
			}
		}
		//puts beepers within one missed place 
	}
	
}