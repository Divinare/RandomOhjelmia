package matopeli;

import javax.swing.SwingUtilities;
import matopeli.gui.Kayttoliittyma;
import matopeli.peli.Matopeli;

public class Main {

    public static void main(String[] args) {
        Matopeli matopeli = new Matopeli(20, 20);

        Kayttoliittyma kali = new Kayttoliittyma(matopeli, 20);
        SwingUtilities.invokeLater(kali);

        matopeli.start();
    }
}
