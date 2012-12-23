package nathanw;

import nathanw.Grid;
import java.lang.*;


public class TetrisPiece extends Grid {

    static Grid pieces[]= new Grid[7];
    static {
	pieces[0]= new Grid(3,3);
	pieces[0].grid[0][0]=0;
	pieces[0].grid[1][0]=1;
	pieces[0].grid[2][0]=0;
	pieces[0].grid[0][1]=1;
	pieces[0].grid[1][1]=1;
	pieces[0].grid[2][1]=1;
	pieces[0].grid[0][2]=0;
	pieces[0].grid[1][2]=0;
	pieces[0].grid[2][2]=0;

	pieces[1]= new Grid(2,2);
	pieces[1].grid[0][0]=1;
	pieces[1].grid[1][0]=1;
	pieces[1].grid[0][1]=1;
	pieces[1].grid[1][1]=1;

	pieces[2]= new Grid(3,3);
	pieces[2].grid[0][0]=0;
	pieces[2].grid[1][0]=1;
	pieces[2].grid[2][0]=0;
	pieces[2].grid[0][1]=0;
	pieces[2].grid[1][1]=1;
	pieces[2].grid[2][1]=1;
	pieces[2].grid[0][2]=0;
	pieces[2].grid[1][2]=0;
	pieces[2].grid[2][2]=1;

	pieces[3]= new Grid(3,3);
	pieces[3].grid[0][0]=0;
	pieces[3].grid[1][0]=1;
	pieces[3].grid[2][0]=0;
	pieces[3].grid[0][1]=1;
	pieces[3].grid[1][1]=1;
	pieces[3].grid[2][1]=0;
	pieces[3].grid[0][2]=1;
	pieces[3].grid[1][2]=0;
	pieces[3].grid[2][2]=0;

        pieces[4]= new Grid(3,3);
	pieces[4].grid[0][0]=0;
	pieces[4].grid[1][0]=0;
	pieces[4].grid[2][0]=1;
	pieces[4].grid[0][1]=1;
	pieces[4].grid[1][1]=1;
	pieces[4].grid[2][1]=1;
	pieces[4].grid[0][2]=0;
	pieces[4].grid[1][2]=0;
	pieces[4].grid[2][2]=0;

        pieces[5]= new Grid(3,3);
	pieces[5].grid[0][0]=1;
	pieces[5].grid[1][0]=0;
	pieces[5].grid[2][0]=0;
	pieces[5].grid[0][1]=1;
	pieces[5].grid[1][1]=1;
	pieces[5].grid[2][1]=1;
	pieces[5].grid[0][2]=0;
	pieces[5].grid[1][2]=0;
	pieces[5].grid[2][2]=0;

	pieces[6]= new Grid(3,4);
	pieces[6].grid[0][0]=0;
	pieces[6].grid[1][0]=1;
	pieces[6].grid[2][0]=0;
	pieces[6].grid[0][1]=0;
	pieces[6].grid[1][1]=1;
	pieces[6].grid[2][1]=0;
	pieces[6].grid[0][2]=0;
	pieces[6].grid[1][2]=1;
	pieces[6].grid[2][2]=0;
	pieces[6].grid[0][3]=0;
	pieces[6].grid[1][3]=1;
	pieces[6].grid[2][3]=0;
	}
    
	TetrisPiece(int i)
	    {
	    super(pieces[i]);
            }
       
        TetrisPiece()
            {
	    this((int)(Math.random()*7.0));
	    }
        TetrisPiece(TetrisPiece p)
	    {
	    super(p);
	    }

}

