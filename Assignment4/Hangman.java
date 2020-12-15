/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	private HangmanCanvas canvas;
	private HangmanLexicon lex; 
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int guess; // number of guesses
	private String hidden; // hidden word
	private String word; // random word from lexicon
	private String incorrect;
	

    public void run() {
    	while(true) {
    		incorrect = "";
    		canvas.reset();
    		greet();
    		game();
    		pause(2000);
    	}
	}
    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
	private void game() {
		while(guess > 0) {
			String input = readLine("Your Guess: ");
			input = input.toUpperCase();
			
			while(invalid(input) == false) {
				input = readLine("Your Guess: ");
				input = input.toUpperCase();			
				}
			check(input);
			if(hidden.equals(word)) {
                println("You guessed the word: " + word);
                println("YOU WIN!!!");
                break;				
			}else if(hidden != word) {
				println("The word now looks like this: " + hidden);
				println("You have " + guess + " guesses left.");
			}
		}
		if (guess == 0) {
			println("You're completely hung.");
			println("The word was: " + word);
			println("YOU LOSE.");
		}
	}
	private void check(String input) {
		if(word.indexOf(input) < 0) {
			println("There are no " + input + "'s in the word.");
			guess -- ;
			incorrect += input;	
			canvas.noteIncorrectGuess(incorrect);
			drawBody();
		}else {
			println("Your guess is CORRECT");
		}
		for(int i = 0; i < word.length(); i ++) {
			if(input.charAt(0) == word.charAt(i)) {
				if (i>0) {
					hidden = hidden.substring(0,i) + input + hidden.substring(i+1);
				}else if(i == 0) {
					hidden = input + hidden.substring(1);
				}
				canvas.displayWord(hidden);
			}
		}
	}
	private void drawBody() {
	 	if(incorrect.length() == 1) {
			canvas.drawHead();
		}else if(incorrect.length() == 2) {
			canvas.drawBodyLine();
		}else if(incorrect.length() == 3) {
			canvas.drawLeftHand();
		}else if(incorrect.length() == 4) {
			canvas.drawRightHand();
		}else if(incorrect.length() == 5) {
			canvas.drawLeftLeg();
		}else if(incorrect.length() == 6) {
			canvas.drawRightLeg();
		}else if(incorrect.length() == 7) {
			canvas.drawLeftFoot();
		}else if(incorrect.length() == 8) {
			canvas.drawRightFoot();
		}
	}
	private boolean invalid(String input) {
		if(input.length() > 1) {
			println("Ivnalid Input.");
			return false;
		}else if(Character.isDigit(input.charAt(0))) {
			println("Ivnalid Input.");
			return false;
		}else return true;
	}
	private void greet() {
		word = random();
		guess = 8;
		hidden = spacing();
		
		println("Welcome to HangMan!");
		println("The word now looks like this: " + hidden);
		println("You have " + guess + " guesses left.");
		canvas.displayWord(hidden);
		canvas.noteIncorrectGuess(incorrect);
	}
	private String random() {
		lex = new HangmanLexicon();
		int  rand = rgen.nextInt(0, lex.getWordCount());
		String choosen = lex.getWord(rand);
		return choosen;
	}
	private String spacing() {
		String space = "";
		for(int i = 0 ; i < word.length(); i ++) {
			space += "-";
		}
		return space;
	}

}
