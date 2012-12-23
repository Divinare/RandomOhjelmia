package nathanw;

import java.awt.*;
import nathanw.*;

/**
 *  A class to draw a box with a score in it.
 */

class ScoreBox implements PosPaintable {
    String message;
    int score;
    

    public ScoreBox(String initMessage)
	{
        message=initMessage;
	}

    public void setScore(int newScore)
	{
	score=newScore;
	}

    public void paint(Graphics g, int sx, int sy)
	{
	int x,y;
	FontMetrics fm;
	String msg;

	
	fm=g.getFontMetrics();
	msg=message+score;
	y=fm.getHeight();
	x=fm.stringWidth(msg);
	g.drawString(msg,sx+5,sy+5+y/2);
        g.drawRect(sx,sy,x+5,y+5);
	}
}
