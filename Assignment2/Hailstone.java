/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		
		int times = 0;   //remembers how many times it took to reach 1
		int n = readInt("Enter n : ");
		
		while(n != 0 && n != 1) {
			if(n % 2 == 0 ) {
				int tmp1 = n;
				n /= 2;
				println( tmp1 + " is even, so I take half: " + n); // for even nums
			}else {
				int tmp2 = n;
				n = 3 * n + 1 ;
				println(tmp2 + " is odd, so I make 3n + 1: " + n); //for odd nums
			}
			times++;
		}
		if(n == 0) {
			println("Invalid Input.");
		}else println("The process took " + times + " to reach " + n);
	}
}

