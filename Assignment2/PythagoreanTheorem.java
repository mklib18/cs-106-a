/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values:");
		
		int a = readInt("a = ");
		int b = readInt("b = ");
		
		if(a > 0 && b > 0) {
			int x = a*a + b*b;
			double c = Math.sqrt(x);
			println( "c = " + c );
			
		}else println("Invalid Input.");
	
	}
}
