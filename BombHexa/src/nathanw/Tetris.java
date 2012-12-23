package nathanw;

import java.lang.*;
import java.awt.*;
import nathanw.*;

/**
 * The Tetris class is the applet implementation. Two parameters are read in: X
 * and Y, which specify the size of the playing area.
 *
 * @author: Nathan J. Williams <nathanw@mit.edu>
 */
public class Tetris extends java.applet.Applet implements Runnable {

    /**
     * the state of the game
     */
    TetrisGame game;
    /**
     * the game loop thread
     */
    Thread gameRunner;
    /**
     * Size of the display area
     */
    int xs, ys;
    int x, y;
    ScoreBox box;
    boolean gameOver;

    /**
     * Overrides init() in java.applet.Applet Parses attributes, initializes
     * variables, calls resize() and getfocus().
     */
    public void init() {

        System.out.println("Class string:" + toString());

        try {
            x = Integer.parseInt(getParameter("X"));
        } catch (NullPointerException e) {
            x = 10;
        } catch (NumberFormatException e) {
            x = 10;
        }

        try {
            y = Integer.parseInt(getParameter("Y"));
        } catch (NullPointerException e) {
            y = 20;
        } catch (NumberFormatException e) {
            y = 20;
        }

        game = new TetrisGame(x, y, false);
        box = new ScoreBox("Score: ");
        xs = x * 10 + 16;  // Play area
        ys = y * 10 + 4 // Play area
                + 60;    // Score box
        resize(xs, ys);
        requestFocus();
    }

    public void start() {
        if (gameRunner == null) {
            gameRunner = new Thread(this);
            gameRunner.start();
        }
    }

    public void stop() {
        if (gameRunner.isAlive()) {
            gameRunner.stop();
        }
        gameRunner = null;
    }

    public void destroy() {
    }

    public void run() {
        gameOver = false;
        while (!gameOver) {
            gameOver = game.step();
            repaint();
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
            }
        }
    }

    public void paint(Graphics g) {
        game.paint(g, 2, 2);
        box.setScore(game.score());
        box.paint(g, 5, ys - 40);
        if (gameOver) {
            Font tmp = getFont();
            g.setColor(Color.red);
            g.setFont(new java.awt.Font("TimesRoman", Font.BOLD, 18));
            g.drawString("Game Over", 10, ys / 2);
            g.setFont(tmp);
            g.setColor(Color.black);
        }
    }

    public boolean keyDown(java.awt.Event evt, int key) {

        switch ((char) key) {
            case 'R':
                game = new TetrisGame(x, y, false);
                stop();
                start();
                break;
        }

        if (!gameOver) {
            switch ((char) key) {
                case 'h':
                case 'H':
                    game.move_left();
                    repaint();
                    break;
                case 'l':
                case 'L':
                    game.move_right();
                    repaint();
                    break;
                case 'j':
                case 'J':
                    game.rotate_ccw();
                    repaint();
                    break;
                case 'k':
                case 'K':
                    game.rotate_cw();
                    repaint();
                    break;
                case ' ':
                    game.drop();
                    repaint();
                    break;
            }
        }


        return true;
    }
}
