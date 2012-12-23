// Java Tetris
// Created on a boring sunday.....
//
// (c)2001 by Brian Postma
// e-mail: B.Postma@HetNet.nl
// homepage: http://www.homepages.hetnet.nl/~brianpostma
//
package Tetris2;

import java.awt.*;
import java.applet.Applet;

public class Tetris2 extends Applet implements Runnable {

    Dimension d;
    Font largefont = new Font("Helvetica", Font.BOLD, 28);
    Font smallfont = new Font("Helvetica", Font.BOLD, 10);
    Color textcolor1 = new Color(96, 128, 255);
    Color textcolor2 = new Color(255, 160, 64);
    FontMetrics fmsmall, fmlarge;
    Graphics goff;
    Image ii;
    Thread thethread;
    boolean ingame = false;
    final short xblocks = 10;
    final short yblocks = 20;
    final int blocksize = 16;
    final int width = xblocks * blocksize;
    final int height = yblocks * blocksize;
    short[][] screendata;
    final short maxcolors = 6;
    Color[] blocks;
    final int barwidth = 8;
    final Color barcolor = new Color(128, 255, 64);
    final Color background = new Color(0, 0, 0);
    int score;
    short emptyline;
    int objectx, objecty;
    int objectdx;
    short objecttype;
    short objectcolor;
    int objectrotation;
    int objectrotationd = 0;
    short objectptr;
    short checkptr;
    final short itemcount = 7;
    final short itemrotlen = 8;
    final short itemlen = itemrotlen * 4;
    short count;
    final short maxcount = 5;
    short curcount;
    boolean fast = false;
    final short screendelay = 40;
    short screencount = 40;
    boolean showtitle = true;
    int           items[]={
                          0,0,   -1,0,  0,-1, -1,-1,     // cube, normal
                          0,0,   -1,0,  0,1,  -1,1,      // rotated 90 degrees
			  0,0,   1,0,   0,1,   1,1,      // rotated 180 degrees
                          0,0,   1,0,   0,-1,  1,-1,     // rotated 270 degrees

                          0,0,   0,-1,  0,-2, 0,-3,      // straight line
                          0,0,  -1,0,  -2,0, -3,0,
                          0,0,   0,1,   0,2,  0,3, 
                          0,0,   1,0,   2,0,  3,0,

			  0,0,   1,0,   0,-1, -1,-1,     // stupid block
                          0,0,   0,-1,  -1,0, -1, 1,
			  0,0,  -1,0,   0,1,  1,1,
                          0,0,   0,1,   1,0,  1,-1,

                          0,0,   -1,0,  0,-1,  1,-1,     // stupid block 2
			  0,0,   0,1,   -1,0,  -1,-1,
			  0,0,   1,0,   0,1,   -1,1,
			  0,0,   0,-1,  1,0,   1,1,

			  0,0,   1,0,   -1,0,  0,-1,     // another one
                          0,0,   0,1,   0,-1,  -1,0,
			  0,0,   0,1,   -1,0,  1,0,
			  0,0,   1,0,   0,-1,  0,1,

			  0,0,   0,-1,  1,-1,  0,1,	// hook one
			  0,0,   -1,0,  -1,-1, 1,0,
			  0,0,   -1,1,  0,1,   0,-1,
			  0,0,   -1,0,  1,0,   1,1,

			  0,0,   0,1,  0,-1,  -1,-1,	// hook two
			  0,0,   1,0, -1,0,   -1,1,
			  0,0,   0,-1,  0,1,  1,1,
			  0,0,   -1,0, 1,0,   1,-1
                        };

  int           checks[]={ 
                           -1,1, 0,1, -1,1,  0,1,        // check cube, normal
                           -1,2, 0,2, -1,2,  0,2,        // check, rotated 90 degrees
			   0,2,  1,2,  0,2,  1,2,        // check, rotated 180 degrees
                           0,1,  1,1,  0,1,  1,1,        // check, rotated 270 degrees

                           0,1,  0,1,  0,1,  0,1,
                           0,1, -1,1, -2,1, -3,1,
                           0,4,  0,4,  0,4,  0,4,        // straight line
                           0,1,  1,1,  2,1,  3,1,
 
			   0,1,  -1,0, 1,1,  0,1,	// stupid block one
			   0,1,  -1,2, 0,1, -1,2,
			   0,2,  1,2,  -1,2, 0,2,
			   0,2,  1,1,  0,2,  1,1,

			   -1,1,  0,1, 1,0,  1,0,	// stupid block two
			   -1,1,  0,2, 0,2,  -1,1,
			   -1,2,  0,2, 1,1,  1,1,
			   0,1,   1,2,  0,1,  1,2,

			   -1,1,  0,1,  1,1, 1,1,	// block three
			   -1,1,  0,2,  0,2, -1,1,
			   -1,1,  0,2,  1,1, 1,1,
			   0,2,   1,1,  0,2, 1,1,

			   0,2,   1,0,  1,0, 0,2,	// hook one
			   -1,1,  0,1,  1,1, 1,1,
			   -1,2,  0,2,  0,2, -1,2,
			   -1,1,  0,1,  1,2, 1,2,

			   -1,0,  0,2,  0,2, -1,0,	// hook two
			   -1,2,  0,1,  1,1,  1,1,
			   0,2,   1,2,  1,2,  0,2,
			   -1,1,  0,1,  1,1,  1,1
                         };

    public String getAppletInfo() {
        return ("Jatris - by Brian Postma");
    }

    public void init() {
        short i;

        screendata = new short[xblocks][yblocks];
        blocks = new Color[maxcolors + 1];
        blocks[0] = background;
        blocks[1] = new Color(255, 0, 0);
        blocks[2] = new Color(0, 255, 0);
        blocks[3] = new Color(0, 0, 255);
        blocks[4] = new Color(255, 255, 0);
        blocks[5] = new Color(255, 0, 255);
        blocks[6] = new Color(0, 255, 255);

        Graphics g;
        resize(width + 2 * barwidth, height + 30);
        d = size();
        setBackground(background);
        g = getGraphics();
        g.setFont(smallfont);
        fmsmall = g.getFontMetrics();
        g.setFont(largefont);
        fmlarge = g.getFontMetrics();
        gameInit();
    }

    public void gameInit() {
        short i, j;

        for (i = 0; i < xblocks; i++) {
            for (j = 0; j < yblocks; j++) {
                screendata[i][j] = 0;
            }
        }

        score = 0;
        emptyline = -1;

        newObject();
        fast = false;
        curcount = maxcount;
    }

    public void newObject() {
        short i;
        int y;

        objectx = xblocks / 2 - 1;
        objectdx = 0;
        objecty = 0;
        objecttype = (short) (Math.random() * itemcount);
        if (objecttype >= itemcount) {
            objecttype = itemcount - 1;
        }

        objectptr = (short) (objecttype * itemlen);
        checkptr = (short) (objecttype * itemlen);

        objectcolor = (short) (Math.random() * maxcolors + 1);
        if (objectcolor > maxcolors) {
            objectcolor = maxcolors;
        }
        objectrotation = 0;
        count = maxcount;

        // check if game has ended
        for (i = 0; i < 4; i++) {
            y = items[objectptr + i * 2 + 1];
            if (y >= 0 && screendata[objectx + items[objectptr + i * 2]][y] != 0) {
                ingame = false;
                showtitle = true;
            }
        }
    }

    public boolean keyDown(Event e, int key) {
        if (ingame) {
            if (key == Event.LEFT) {
                objectdx = -1;
            } else if (key == Event.RIGHT) {
                objectdx = 1;
            } else if (key == Event.UP) {
                objectrotationd = 1;
            } else if (key == Event.DOWN) {
                fast = true;
            } else if (key == Event.ESCAPE) {
                ingame = false;
            }
        } else {
            if (key == 's' || key == 'S') {
                ingame = true;
                gameInit();
            }
        }
        return true;
    }

    public boolean keyUp(Event e, int key) {
        if (key == Event.DOWN) {
            fast = false;
        }
        return true;
    }

    public void paint(Graphics g) {
        Graphics gg;

        if (goff == null && d.width > 0 && d.height > 0) {
            ii = createImage(d.width, d.height);
            goff = ii.getGraphics();
        }
        if (goff == null || ii == null) {
            return;
        }

        goff.setColor(background);
        goff.fillRect(0, 0, d.width, d.height);

        if (ingame) {
            playGame();
        } else {
            showIntro();
        }
        showScore();

        g.drawImage(ii, 0, 0, this);
    }

    public void playGame() {
        boolean bottomreached = false;
        boolean stillscrolling = false;

        if (emptyline < 0) {
            bottomreached = drawObject();
        } else {
            scrollDown();
            stillscrolling = true;
        }
        drawBars();
        drawBlocks();
        if (stillscrolling || bottomreached) {
            checkFull();
        }
    }

    public void showIntro() {
        String s;

        drawBars();
        drawBlocks();


        if (showtitle) {
            goff.setFont(largefont);
            s = "JATRIS";
            goff.setColor(textcolor1);
            goff.drawString(s, barwidth + (width - fmlarge.stringWidth(s)) / 2 - 2, height / 2 - 22);
            goff.setColor(Color.white);
            goff.drawString(s, barwidth + (width - fmlarge.stringWidth(s)) / 2, height / 2 - 20);

            goff.setFont(smallfont);
            s = "(c)2001 by Brian Postma";
            goff.setColor(textcolor2);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2 - 1, height / 2 + 9);
            goff.setColor(Color.white);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2, height / 2 + 10);

            s = "b.postma@hetnet.nl";
            goff.setColor(textcolor2);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2 - 1, height / 2 + 29);
            goff.setColor(Color.white);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2, height / 2 + 30);
        } else {
            goff.setFont(smallfont);
            s = "'S' to start game";
            goff.setColor(textcolor1);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2 - 1, height / 2 - 31);
            goff.setColor(Color.white);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2, height / 2 - 30);

            s = "Use cursor left+right to move";
            goff.setColor(textcolor2);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2 - 1, height / 2 - 11);
            goff.setColor(Color.white);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2, height / 2 - 10);

            s = "Use cursor up to rotate";
            goff.setColor(textcolor2);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2 - 1, height / 2 + 9);
            goff.setColor(Color.white);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2, height / 2 + 10);

            s = "Use cursor down to drop";
            goff.setColor(textcolor2);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2 - 1, height / 2 + 29);
            goff.setColor(Color.white);
            goff.drawString(s, barwidth + (width - fmsmall.stringWidth(s)) / 2, height / 2 + 30);
        }
        screencount--;
        if (screencount <= 0) {
            screencount = screendelay;
            showtitle = !showtitle;
        }
    }

    public void drawBars() {
        goff.setColor(barcolor);
        goff.fillRect(0, 0, barwidth, blocksize * yblocks);
        goff.fillRect(barwidth + blocksize * xblocks, 0, barwidth, blocksize * yblocks);
        goff.fillRect(0, blocksize * yblocks, xblocks * blocksize + 2 * barwidth, barwidth);
    }

    public boolean drawObject() {
        short i;
        boolean bottomreached = false;
        int x, y, checkx, checky;

        // clear old
        for (i = 0; i < 4; i++) {
            x = objectx + items[objectptr + i * 2 + objectrotation * itemrotlen];
            y = objecty + items[objectptr + i * 2 + objectrotation * itemrotlen + 1];
            checkx = objectx + checks[objectptr + i * 2 + objectrotation * itemrotlen];
            checky = objecty + checks[objectptr + i * 2 + objectrotation * itemrotlen + 1];
            if (y >= 0) {
                screendata[x][y] = 0;
            }
            if (screendata[checkx][checky] != 0) {
                bottomreached = true;
            }

        }

        if (!bottomreached) {
            count--;
            if (count <= 0 || fast) {
                objecty++;
                count = curcount;
            }
            checkRotation();
            objectdx = 0;
            objectrotationd = 0;
        }

        // draw new
        for (i = 0; i < 4; i++) {
            x = objectx + items[objectptr + i * 2 + objectrotation * itemrotlen];
            y = objecty + items[objectptr + i * 2 + objectrotation * itemrotlen + 1];

            if (y >= 0) {
                screendata[x][y] = objectcolor;
            }
            if (y >= (yblocks - 1)) {
                bottomreached = true;
            }
        }
        if (bottomreached) {
            score++;
            newObject();
        }
        return bottomreached;
    }

    public void checkRotation() {
        int dummyx;
        int dummyrot;
        int x, y;
        short i;
        boolean cando = true;

        dummyrot = (objectrotation + objectrotationd) % 4;
        dummyx = objectx + objectdx;

        // make sure the part doesn't rotate of the playscreen
        for (i = 0; i < 4; i++) {
            x = dummyx + items[objectptr + i * 2 + dummyrot * itemrotlen];
            if (x >= xblocks) {
                dummyx -= (x - xblocks + 1);
            } else if (x < 0) {
                dummyx -= x;
            }
        }

        for (i = 0; (i < 4 && cando); i++) {
            x = dummyx + items[objectptr + i * 2 + dummyrot * itemrotlen];
            y = objecty + items[objectptr + i * 2 + dummyrot * itemrotlen + 1];
            if (y >= 0) {
                cando = cando && (screendata[x][y] == 0);
            }
            if (y >= yblocks || x < 0 || x >= xblocks) {
                cando = false;
            }
        }
        if (cando) {
            objectrotation = dummyrot;
            objectx = dummyx;
        }
    }

    public void drawBlocks() {
        short x, y;

        for (x = 0; x < xblocks; x++) {
            for (y = 0; y < yblocks; y++) {
                goff.setColor(blocks[screendata[x][y]]);
                goff.drawRect(x * blocksize + barwidth, y * blocksize, blocksize - 1, blocksize - 1);
                goff.fillRect(x * blocksize + barwidth + 3, y * blocksize + 3, blocksize - 6, blocksize - 6);
            }
        }
    }

    public void checkFull() {
        short x, y;
        boolean found = false;

        for (y = yblocks - 1; (y >= 0 && !found); y--) {
            found = true;
            for (x = 0; x < xblocks; x++) {
                if (screendata[x][y] == 0) {
                    found = false;
                }
            }
            if (found) {
                score += 10;

                // increase speed when you've got a lot of points
                if (score > 800) {
                    curcount = 1;
                } else if (score > 600) {
                    curcount = 2;
                } else if (score > 400) {
                    curcount = 3;
                } else if (score > 200) {
                    curcount = 4;
                }

                for (x = 0; x < xblocks; x++) {
                    screendata[x][y] = 0;
                }
                emptyline = y;
            }
        }
    }

    public void scrollDown() {
        short x, y;

        for (y = emptyline; y > 0; y--) {
            for (x = 0; x < xblocks; x++) {
                screendata[x][y] = screendata[x][y - 1];
            }
        }
        for (x = 0; x < xblocks; x++) {
            screendata[x][0] = 0;
        }
        emptyline = -1;
    }

    public void showScore() {
        String s;
        goff.setFont(smallfont);
        goff.setColor(Color.white);

        s = "Score: " + score;
        goff.drawString(s, width / 2 - 40, (yblocks + 1) * blocksize + 10);
    }

    public void run() {
        long starttime;
        Graphics g;

        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        g = getGraphics();

        while (true) {
            starttime = System.currentTimeMillis();
            try {
                paint(g);
                starttime += 60;
                Thread.sleep(Math.max(0, starttime - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void start() {
        if (thethread == null) {
            thethread = new Thread(this);
            thethread.start();
        }
    }

    public void stop() {
        if (thethread != null) {
            thethread.stop();
            thethread = null;
        }
    }
}
