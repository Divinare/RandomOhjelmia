package Tetris;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends Applet {

    private static final String[][] PARAMETER = {{"tetris.color.background", "color", "The overall background color."}, {"tetris.color.label", "color", "The text color of the labels."}, {"tetris.color.button", "color", "The start and pause button bolor."}, {"tetris.color.board.background", "color", "The background game board color."}, {"tetris.color.board.message", "color", "The game board message color."}, {"tetris.color.figure.square", "color", "The color of the square figure."}, {"tetris.color.figure.line", "color", "The color of the line figure."}, {"tetris.color.figure.s", "color", "The color of the 's' curved figure."}, {"tetris.color.figure.z", "color", "The color of the 'z' curved figure."}, {"tetris.color.figure.right", "color", "The color of the right angle figure."}, {"tetris.color.figure.left", "color", "The color of the left angle figure."}, {"tetris.color.figure.triangle", "color", "The color of the triangle figure."}};
    private Game game = null;

    public static void main(String[] args) {
        Frame frame = new Frame("Tetris");
        Game game = new Game();

        frame.add(game.getComponent());
        frame.pack();

        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.show();
    }

    public String[][] getParameterInfo() {
        return PARAMETER;
    }

    public void init() {
        for (int i = 0; i < PARAMETER.length; i++) {
            String value = getParameter(PARAMETER[i][0]);
            if (value != null) {
                Configuration.setValue(PARAMETER[i][0], value);
            }

        }

        this.game = new Game();

        setLayout(new BorderLayout());
        add(this.game.getComponent(), "Center");
    }

    public void stop() {
        this.game.quit();
    }

    public static class COMClassObject {
    }
}
