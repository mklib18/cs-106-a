/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int stop = 0;
	public void run() {
		
		println("Enter numbers: ");
		
		int first = readInt("num : ");
		
		if(first == stop) println("Invalid Input.");
		
		int small = first;
		int large = first;
		
		while(first != stop) {
			int num = readInt("num : ");
			
			if(num < small) {
				if(small != stop) {
					small = num;
				}
			}
			if(num > large) {
				if(large != stop) {
					large = num;
				}
			}
			if(num == stop) {
				println("smallest = " + small + "; largest = " + large);
				first = stop;
			}
		}
	}
}

