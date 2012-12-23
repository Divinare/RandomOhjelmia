package nathanw;

import java.lang.*;
import java.awt.*;
import nathanw.*;

class TetrisGame implements PosPaintable {

    Grid playfield;
    TetrisPiece piece;
    int curScore;
    int px, py;
    intNonZero testNonZero;
    intOr putDown;

    public TetrisGame(int xs, int ys, boolean randomCrap) {
        playfield = new Grid(xs, ys, 1);
        clear();
        piece = new TetrisPiece();
        py = 0;
        px = (xs - piece.sizex()) / 2;
        testNonZero = new intNonZero();
        putDown = new intOr();
        curScore = 0;
    }

    void clear() {
        playfield.fill(0, 0, playfield.sizex(), playfield.sizey(), 0);
    }

    public void paint(Graphics g, int x, int y) {
        g.drawRect(x, y, 2 + playfield.sizex() * 10, 2 + playfield.sizey() * 10);
        for (int j = 0; j < playfield.sizey(); j++) {
            for (int i = 0; i < playfield.sizex(); i++) {
                if ((playfield.grid[i][j] != 0)
                        || ((i >= px) && (i < px + piece.sizex())
                        && (j >= py) && (j < py + piece.sizey())
                        && (piece.grid[i - px][j - py] != 0))) {
                    g.fillRect(3 + x + i * 10, 3 + y + j * 10, 8, 8);
                }
            }
        }
    }

    public boolean step() {
        if (playfield.compare(piece, px, py + 1, testNonZero)) {
            // Put down the piece
            playfield.put_on(piece, px, py, putDown);
            // Clear out full lines
            for (int j = playfield.sizey() - 1; j >= 0; j--) {
                while (testFullLine(j) == true) {
                    deleteLine(j);
                    // Simple scoring function
                    curScore += 10;
                }
            }

            // Put on a new piece
            piece = new TetrisPiece();
            py = 0;
            px = (playfield.sizex() - piece.sizex()) / 2;
            if (playfield.compare(piece, px, py, testNonZero)) {
                return true;
            }
        }
        py++;
        return false;
    }

    private boolean testFullLine(int y) {
        for (int i = 0; i < playfield.sizex(); i++) {
            if (playfield.grid[i][y] == 0) {
                return false;
            }
        }
        return true;
    }

    private void deleteLine(int y) {
        for (int j = y; j > 0; j--) {
            for (int i = 0; i < playfield.sizex(); i++) {
                playfield.grid[i][j] = playfield.grid[i][j - 1];
            }
        }
        for (int i = 0; i < playfield.sizex(); i++) {
            playfield.grid[i][0] = 0;
        }
    }

// Data-returning methods
    public int score() {
        return curScore;
    }

// Game-play interface methods
    public void move_left(int i) {
        if (playfield.compare(piece, px - i, py, testNonZero)) {
            return; // Should we throw an exception here?
        }
        px -= i;
    }

    public void move_left() {
        move_left(1);
    }

    public void move_right(int i) {
        if (playfield.compare(piece, px + i, py, testNonZero)) {
            return; // Should we throw an exception here?
        }
        px += i;
    }

    public void move_right() {
        move_right(1);
    }

    public void rotate_cw() {
        TetrisPiece test = new TetrisPiece(piece);
        test.rotate_cw();
        if (!playfield.compare(test, px, py, testNonZero)) {
            piece = test;
        }
    }

    public void rotate_ccw() {
        TetrisPiece test = new TetrisPiece(piece);
        test.rotate_ccw();
        if (!playfield.compare(test, px, py, testNonZero)) {
            piece = test;
        }
    }

    public void drop() {
        while (!playfield.compare(piece, px, py + 1, testNonZero)) {
            py++;
        }
    }
}
