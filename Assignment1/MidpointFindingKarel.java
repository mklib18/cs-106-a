import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	public void run() {
		put();
		
		putBeeper();
		turnAround();
		move();
		
		tmp();
		
		turnAround();
		move();
		
		put();
		
		turnAround();
		
		pick();
		
	}
	private void tmp() {
		while(noBeepersPresent()) {
			move();
			if(beepersPresent()) {
				turnAround();
				move();
				putBeeper();
				move();
			}	
		}
	}

	private void put() {
		putBeeper();
		while(frontIsClear()) {
			move();
		}
		// puts beeper and goes to the wall
	}

	private void pick() {
		while(frontIsClear()) {
			pickBeeper();
			move();
		}
		pickBeeper();
		// after karel finishes the work there is two beepers on the mid pile, karel takes needless one 	
	}

}
	

	


