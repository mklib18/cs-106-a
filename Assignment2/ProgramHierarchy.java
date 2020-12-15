
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	

	private static final int RECT_WIDTH =120;
//Rectangle widht
	private static final int RECT_HEIGHT = 60;
//Rectangle height 

	private static final int MIDDLE_LINE = 60;
// space between rows and rectangles
	
	public void run() {
		Program();
		Graphics();
		Console();
		Dialog();
		FirstLine();
		SecondLine();
		ThirdLine();
	}
	private void ThirdLine() {
	    double x1 = getWidth()/2;
        double y1 = getHeight()/2 - MIDDLE_LINE / 2;
        double x2 = getWidth()/2 + RECT_WIDTH + MIDDLE_LINE;
        double y2 = (getHeight() / 2) + ( MIDDLE_LINE / 2);
        GLine drawLine = new GLine (x1, y1, x2, y2);
        add(drawLine);
		// third line which connects to the dialog rectangle
	}
	private void SecondLine() {
	    double x1 = getWidth()/2;
        double y1 = getHeight()/2 - MIDDLE_LINE / 2;
        double x2 = getWidth()/2 - RECT_WIDTH - MIDDLE_LINE;
        double y2 = (getHeight() / 2) + ( MIDDLE_LINE / 2);
        GLine drawLine = new GLine (x1, y1, x2, y2);
        add(drawLine);
	}
	private void FirstLine() {
		    double x1 = getWidth()/2;
	        double y1 = getHeight()/2 - MIDDLE_LINE / 2;
	        double y2 = getHeight()/2 + MIDDLE_LINE / 2;
	        GLine drawLine = new GLine (x1, y1, x1, y2);
	        add(drawLine);
	}
	private void Dialog() {
		double x = ((getWidth() / 2) + (RECT_WIDTH / 2) + MIDDLE_LINE );
		double y = (getHeight() / 2) + ( MIDDLE_LINE / 2) ; 
		GRect dialogRectangle =  new GRect (x, y, RECT_WIDTH , RECT_HEIGHT );
		add(dialogRectangle);
		GLabel dialogprogram = new GLabel ("DialogProgram");
		add( dialogprogram , (x + RECT_WIDTH / 2 - dialogprogram.getWidth()/2) , (y + RECT_HEIGHT/ 2 + dialogprogram.getAscent()/2 ) );
	}
	private void Console() {
		double x = ((getWidth() / 2) - (RECT_WIDTH / 2));
		double y = (getHeight() / 2) + ( MIDDLE_LINE / 2) ;
		GRect consoleRectangle =  new GRect (x, y, RECT_WIDTH , RECT_HEIGHT );
		add(consoleRectangle);
		GLabel consoleprogram = new GLabel ("ConsoleProgram");
		add( consoleprogram , (x + RECT_WIDTH / 2 - consoleprogram.getWidth()/2) , (y + RECT_HEIGHT/ 2 + consoleprogram.getAscent()/2 ) );
	}
	private void Graphics() {
			double x = ((getWidth() / 2) - (RECT_WIDTH * 3 / 2) - MIDDLE_LINE );
			double y = (getHeight() / 2) + ( MIDDLE_LINE / 2) ; 
			GRect graphicsRectangle =  new GRect (x, y, RECT_WIDTH , RECT_HEIGHT );
			add(graphicsRectangle);
			GLabel graphicsprogram = new GLabel ("GraphicsProgram");
			add( graphicsprogram , (x + RECT_WIDTH / 2 - graphicsprogram.getWidth()/2) , (y + RECT_HEIGHT/ 2 + graphicsprogram.getAscent()/2 ) );
	}
	private void Program() {
	double x = ((getWidth() / 2) - (RECT_WIDTH / 2));
	double y= ((getHeight() / 2) - RECT_HEIGHT - ( MIDDLE_LINE / 2));
		GRect programRectangle = new GRect (x , y, RECT_WIDTH , RECT_HEIGHT );
		add(programRectangle);
		GLabel Program = new GLabel ("Program");
		add( Program , (x + RECT_WIDTH / 2 - Program.getWidth()/2) , (y + RECT_HEIGHT/ 2 + Program.getAscent()/2 )  );
	}
}