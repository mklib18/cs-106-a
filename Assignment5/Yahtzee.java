/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import acmx.export.java.util.ArrayList;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		scoreBoard = new int[N_CATEGORIES][nPlayers];
		for(int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for(int j = 1; j <= nPlayers; j++) {
				first(j);
				next();
				select(j);
			}
		}
		tot();
		win();
	}
		
	private void win() {
		int winner = 0;
		int scr = 0;
		
		for (int n = 0; n < nPlayers; n++) {
			if(scoreBoard[TOTAL  - 1][n] > scr) {
				scr = scoreBoard[TOTAL - 1][n];
				winner = n;
			}
		}
		display.printMessage("CONGRATULATIONS, " + playerNames[winner] 
				+ ", you are the winner with a total score of " + scr);
	}

	private void tot() {
		int total = 0;
		for (int n = 0; n < nPlayers; n++) {
			for(int cat = 0; cat < SIXES; cat ++) {
				total += scoreBoard[cat][n];
			}
			scoreBoard[UPPER_SCORE - 1][n] = total;
			display.updateScorecard(UPPER_SCORE, n + 1, total);
			
			if (scoreBoard[UPPER_SCORE - 1][n] > 63) {
				scoreBoard[UPPER_SCORE -1][n] = 35;
				display.updateScorecard(UPPER_SCORE, n + 1, total);

			}else {
				scoreBoard[UPPER_SCORE - 1][n] = 0;
				display.updateScorecard(UPPER_SCORE, n + 1, 0);

			}
			total = 0;
			for( int cat = 8 ; cat < CHANCE; cat ++ ) {
				total += scoreBoard[cat][n];
			}
			scoreBoard[LOWER_SCORE - 1][n] = total;
			display.updateScorecard(LOWER_SCORE - 1, n + 1, total);
			scoreBoard[TOTAL - 1][n] = scoreBoard[UPPER_SCORE - 1][n] + 
					scoreBoard[UPPER_BONUS - 1][n] + scoreBoard[LOWER_SCORE - 1][n]  ;
			display.updateScorecard(TOTAL, n + 1, scoreBoard[TOTAL - 1][n]);
			total = 0;
		}
	}

	private void select(int j) {
		display.printMessage("Select a category for this roll.");
		while(true) {
			int category = display.waitForPlayerToSelectCategory();
			if (check(j, category)) {
				score(j, category);
				break;
			}
		}
	}

	private void score(int j, int category) {
		int tmp = 0; 
		
		if( category >= ONES && category <= SIXES) {
			for( int i=0 ; i < N_DICE; i++ ) {
				if(roll[i] == category) {
					tmp += category;
				}
			}
		} else if( category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND) {
			if(checkCat(category)) {
				for(int i = 0; i < N_DICE; i++ ) {
					tmp += roll[i];
				}
				}else {
					tmp = 0;
				}
		}else if(category == FULL_HOUSE ) {
			if(checkCat(category)) {
				category = 25;
			}else {
				category = 0;
			}
		}else if(category == SMALL_STRAIGHT) {
			if(checkCat(category)) {
				tmp = 30;
			}else {
				tmp = 0;
			}
		}else if(category == LARGE_STRAIGHT) {
			if(checkCat(category)) {
				tmp = 40;
			}else {
				tmp = 0;
			}
		}else if(category == YAHTZEE) {
			if(checkCat(category)) {
				tmp = 50;
			}else {
				tmp = 0;
			}
		}else if(category == CHANCE) {
			for(int i = 0; i < N_DICE; i++) {
				tmp += roll[i];
			}
		}
		display.updateScorecard(category, j, tmp);
		scoreBoard[category - 1][j - 1] = tmp;
	}

	private boolean checkCat(int category) {
		ArrayList one = new ArrayList();
		ArrayList two = new ArrayList();
		ArrayList three = new ArrayList();
		ArrayList four = new ArrayList();
		ArrayList five = new ArrayList();
		ArrayList six = new ArrayList();
		
		for(int i =0; i < N_DICE; i++) {
			if(roll[i] == 1) {
				one.add(1);
			}else if(roll[i] == 2) {
				two.add(2);
			}else if(roll[i] == 3) {
				three.add(3);
			}else if(roll[i] == 4) {
				four.add(4);
			}else if(roll[i] == 5) {
				five.add(5);
			}else if(roll[i] == 6) {
				six.add(6);
			}
		}
		
		if( category == THREE_OF_A_KIND) {
			if(one.size() >= 3 || two.size() >= 3 || three.size() >= 3 
					|| four.size() >= 3 || five.size() >= 3 || six.size() >= 3 ) return true;
		}else if( category == FOUR_OF_A_KIND) {
			if(one.size() >= 4 || two.size() >= 4 || three.size() >= 4 
					|| four.size() >= 4 || five.size() >= 4 || six.size() >= 4 ) return true;	
		}else if( category == FULL_HOUSE) {
			if(one.size() == 3 || two.size() == 3 || three.size() == 3 
					|| four.size() == 3 || five.size() == 3 || six.size() == 3 ){
				if(one.size() == 2 || two.size() == 2 || three.size() == 2 
						|| four.size() == 2 || five.size() == 2 || six.size() == 2 ) return true;
			}
		}else if( category == SMALL_STRAIGHT) {
			if (one.size() >= 1 && two.size() >= 1 && three.size() >= 1 && four.size() >= 1) {
				return true;
			}else if(two.size() >= 1 && three.size() >= 1 && four.size() >= 1 && five.size() >= 1) {
				return true;
			}else if(three.size() >= 1 && four.size() >= 1 && five.size() >= 1 && six.size() >= 1) {
				return true;
			}
		}else if( category == LARGE_STRAIGHT) {
			if(one.size() == 1 && two.size() == 1 && three.size() == 1 && four.size() == 1 && five.size() == 1) {
				return true;
			}else if(two.size() == 1 && three.size() == 1 && four.size() == 1 && five.size() == 1 && six.size() == 1) {
				return true;
			}
		}else if( category == YAHTZEE) {
			if(one.size() == 5 || two.size() == 5 || three.size() == 5 
					|| four.size() == 5 || five.size() == 5 || six.size() == 5 ) return true;
		}
		return false;
	}

	private boolean check(int j, int category) {
		if(scoreBoard[category - 1][j - 1 ] == 0 
				&& category != UPPER_BONUS && category != UPPER_SCORE 
				&& category != LOWER_SCORE && category != TOTAL) return true;
		return false;
	}

	private void next() {
		for(int i = 1; i <= 2; i++) {
			display.printMessage("Select the dices you wish to re-roll and click \"Roll Again\".");
			display.waitForPlayerToSelectDice();
			for (int j = 0; j < N_DICE; j++) {
				if( display.isDieSelected(j)) {
				roll[j] = rgen.nextInt(1 ,6) ;
				}
		}
			display.displayDice(roll);
		}
	}

	private void first(int j) {
		display.printMessage(playerNames[j - 1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(j);
		for(int i = 0; i <N_DICE; i++) {
			roll[i] = rgen.nextInt(1 , 6);
		}
		display.displayDice(roll);
	}
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] roll = new int[N_DICE];
	private int [][] scoreBoard;
}
