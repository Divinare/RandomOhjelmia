package nathanw;
import nathanw.*;

public class Grid {
	public int grid[][];
	public int def; // Default value
	protected int x,y;
	
	public Grid(int sx,int sy,int sinit)
		{
		x=sx;
		y=sy;
		def=sinit;
		grid = new int[x][y];
		for(int i=0;i<x;i++)
		     for(int j=0;j<y;j++)
		         grid[i][j]=def;
                }

       public Grid(int sx,int sy)
		{
		this(sx,sy,0);
		}

       public Grid(Grid g)
           {
           x=g.x;
           y=g.y;
	   grid = new int[x][y];
	   for(int i=0;i<x;i++)
	       for(int j=0;j<y;j++)
		   grid[i][j]=g.grid[i][j];
           }

    public void fill(int sx,int sy,int w,int h,int value)
        {
	for(int i=0;i<w;i++)
	    for(int j=0;j<h;j++)
                if((sx+i<x) && (sy+j<y) && (sx+i>=0) && (sy+j>=0))
                    {
		    grid[sx+i][sy+j]=value;
		    }
           }

    public void rotate_cw() // Clockwise 
	{
	    int newGrid[][] = new int[y][x];
            int s=y-1;
	    for(int i=0;i<y;i++)
		for(int j=0;j<x;j++)
		    {
                    newGrid[i  ][j  ]=grid[j][s-i];
                    }
            grid=newGrid;
	    int tmp=x;
            x=y;
            y=tmp; 
        }

    public void rotate_ccw() // Counterclockwise
	{
	    int newGrid[][] = new int[y][x];
            int s=x-1;
	    for(int i=0;i<y;i++)
		for(int j=0;j<x;j++)
		    newGrid[i  ][j  ]=grid[s-j][i];
            grid=newGrid;
	    int tmp=x;
            x=y;
            y=tmp; 
        }

       public int sizex() {return x;}
       public int sizey() {return y;}

       public void put_on(Grid model,int sx,int sy,intOp map)
	   {
           int mx,my;

	   mx=model.x;
	   my=model.y;
	   if(mx+sx>x) mx=x-sx;
	   if(my+sy>y) my=y-sy;

	   for(int i=0;i<mx;i++)
		for(int j=0;j<my;j++)
                    if((sx+i<x) && (sy+j<y) &&  (sx+i>=0) && (sy+j>=0))
	                grid[sx+i][sy+j]=map.op(grid[sx+i][sy+j],
						model.grid[i][j]);
           }

	public boolean compare(Grid model,int sx,int sy,intComp comp)
	    {
            int mx,my;

            mx=model.x;
            my=model.y;
	
	    for(int i=0;i<mx;i++)
		for(int j=0;j<my;j++)
		    {
                    if((sx+i<x) && (sy+j<y) &&  (sx+i>=0) && (sy+j>=0))
		        {
                        if(comp.comp(grid[sx+i][sy+j],model.grid[i][j]))
			    return true;
                        }
                    else
                        { 
                        if(comp.comp(def,model.grid[i][j]))
                            return true;
                        }
                    } 
	    return false;
	    }
}
	
	
