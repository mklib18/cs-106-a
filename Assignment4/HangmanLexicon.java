/*

 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import acm.util.*;
import java.util.ArrayList;

public class HangmanLexicon {
    private BufferedReader lexicon = null;
    
    private ArrayList<String> words = new ArrayList<String>();
    private String reading;
    
    
	public HangmanLexicon () {
		try {
			lexicon = new BufferedReader (new FileReader("HangmanLexicon.txt"));
			while(true){
				reading = lexicon.readLine();
				if(reading != null) {
					words.add(reading);
				}else break;
			}
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	//hangman lexicon picks words from HangmanLexicon.txt and adds to array list
	}
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		int counter = words.size();
		return counter;
	}
/** Returns the word at the specified index. */
	public String getWord(int index) {
	String word = words.get(index);
	return word;
	
	}
}
