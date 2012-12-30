
import java.io.File;
import javax.swing.SwingUtilities;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import morkopeli.Gui;
import morkopeli.Luola;
import morkopeli.Soittaja;
import morkopeli.Game;

public class Main {

    public static void main(String[] args) {

        Game morkopeli = new Game();
        morkopeli.run();
    }
}