import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {
    private JFrame frame;
    // BEGIN SOLUTION
    private Piirtoalusta piirtoalusta;
    // END SOLUTION
    private Matopeli matopeli;
    private int sivunPituus;

    public Kayttoliittyma(Matopeli matopeli, int sivunPituus) {
        this.matopeli = matopeli;
        this.sivunPituus = sivunPituus;
    }

    @Override
    public void run() {
        frame = new JFrame("Matis!");
        frame.setPreferredSize(new Dimension(600, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        // BEGIN SOLUTION
        piirtoalusta = new Piirtoalusta(matopeli, sivunPituus);
        container.add(piirtoalusta);
        
        Nappaimistonkuuntelija kuuntelija = new Nappaimistonkuuntelija(matopeli.getMato());
        frame.addKeyListener(kuuntelija);
        // END SOLUTION
        
        // Huom! Luo ensin piirtoalusta jonka lisäät container-olioon
        // Luo vasta tämän jälkeen näppäimistönkuuntelija, jonka lisäät frame-oliolle
    }

    // BEGIN SOLUTION
    public Paivitettava getPaivitettava() {
        return piirtoalusta;
    }
    // END SOLUTION

    public JFrame getFrame() {
        return frame;
    }
}