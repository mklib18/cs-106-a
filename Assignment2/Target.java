/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		big();
		middle();
		small();
	}
	private void big() {
		int rad = 72;
		double x = getWidth() / 2 - rad;
		double y = getHeight() / 2 - rad;
		GOval bigOne = new GOval(x, y, rad *2, rad *2);
		bigOne.setColor(Color.red);
		bigOne.setFilled(true);
		bigOne.setFillColor(Color.red);
		add(bigOne);
	}
	private void middle() {
		double rad = (72*1.65) / 2.54;
		double x = getWidth() / 2 - rad;
		double y = getHeight() / 2 - rad;
		GOval midOne = new GOval(x, y, rad *2, rad *2);
		midOne.setColor(Color.white);
		midOne.setFilled(true);
		midOne.setFillColor(Color.white);
		add(midOne);
	}
	private void small() {
		double rad = (72*0.76) / 2.54;
		double x = getWidth() / 2 - rad;
		double y = getHeight() / 2 - rad;
		GOval smallOne = new GOval(x, y, rad *2, rad *2);
		smallOne.setColor(Color.red);
		smallOne.setFilled(true);
		smallOne.setFillColor(Color.red);
		add(smallOne);
	}
}
