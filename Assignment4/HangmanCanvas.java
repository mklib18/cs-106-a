/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		scaffold();
		/* You fill this in */
	}

	private void scaffold() {
		double x1 = getWidth() / 2 - BEAM_LENGTH;
		double y1 = getHeight() / 2 - 220;
		double y2 = y1 + SCAFFOLD_HEIGHT ;
		double x2 = x1 + BEAM_LENGTH;
		GLine line = new GLine(x1, y1, x1, y2);
		GLine beam = new GLine(x1, y1, x2, y1);
		GLine rope = new GLine(x2, y1, x2, y1 + ROPE_LENGTH);
		add(rope);
		add(beam);
		add(line);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		double x = getWidth() / 2 - BEAM_LENGTH;
		double y = getHeight() - 40;
		GLabel hiddenWord = new GLabel(word, x , y);
		if(getElementAt(x, y) != null) {
            remove(getElementAt(x, y));
        }
		hiddenWord.setFont("TIMES_ROMAN-25");
		add(hiddenWord);
	}
	public void drawHead() {
		double x2 = getWidth() / 2 - HEAD_RADIUS ;
		double topY = getHeight() / 2 - 220;
		GOval o = new GOval(x2, topY + ROPE_LENGTH, HEAD_RADIUS * 2, HEAD_RADIUS * 2 );
		add(o);
	}
	public void drawBodyLine() {
		double x = getWidth() / 2;
		double y = getHeight() / 2 - 220 + ROPE_LENGTH + HEAD_RADIUS * 2;
		GLine body = new GLine(x, y, x, y + BODY_LENGTH);
		add(body);
	}
	public void drawLeftHand() {
		double x1 = getWidth() / 2 - UPPER_ARM_LENGTH;
		double x2 = getWidth() / 2;
		double y1 = getHeight() / 2 - 220 + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		double y2 = y1 + LOWER_ARM_LENGTH;
		GLine left = new GLine (x1, y1 ,x2  ,y1);
		GLine arm = new GLine (x1, y1, x1, y2);
		add(left);
		add(arm);
	}
	public void drawRightHand() {
		double x1 = getWidth() / 2;
		double x2 = getWidth() / 2 + UPPER_ARM_LENGTH;
		double y1 = getHeight() / 2 - 220 + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		double y2 = y1 + LOWER_ARM_LENGTH;
		GLine right = new GLine (x1, y1, x2, y1);
		GLine arm = new GLine (x2, y1, x2, y2);
		add(right);
		add(arm);
		}
	public void drawLeftLeg() {
		double x1 = getWidth() / 2;
		double x2 = x1 - HIP_WIDTH;
		double y1 = getHeight() / 2 - 220 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		double y2 = y1 + LEG_LENGTH;
		GLine left = new GLine(x1, y1, x2, y1);
		GLine leg = new GLine (x2, y1, x2, y2);
		add(left);
		add(leg);
	}
	public void drawRightLeg() {
		double x1 = getWidth() / 2;
		double x2 = x1 + HIP_WIDTH;
		double y1 = getHeight() / 2 - 220 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		double y2 = y1 + LEG_LENGTH;
		GLine right = new GLine(x2, y1, x1, y1);
		GLine leg = new GLine (x2, y1, x2, y2);
		add(right);
		add(leg);		
		}
	public void drawLeftFoot() {
		double x1 = getWidth() / 2 - HIP_WIDTH;
		double x2 = x1 -  FOOT_LENGTH;
		double y = getHeight() / 2 - 220 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine leftFoot = new GLine(x1, y, x2, y);
		add(leftFoot);
	}
	public void drawRightFoot() {
		double x1 = getWidth() / 2 + HIP_WIDTH;
		double x2 = x1 + FOOT_LENGTH;
		double y = getHeight() / 2 - 220 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine rightFoot = new GLine(x1, y, x2, y);
		add(rightFoot);
	}
	

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(String incorrect) {
		double x = getWidth() / 2 - BEAM_LENGTH ;
        double y = getHeight() - 15;
		GLabel incorrectOnes = new GLabel(incorrect, x, y);
		if(getElementAt(x,y) != null) {
            remove(getElementAt(x,y));
        }
		incorrectOnes.setFont("TIMES_ROMAN-15");
		add(incorrectOnes);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
