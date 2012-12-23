package morkoapplet;

import javax.swing.SwingUtilities;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
<<<<<<< HEAD
        Luola luola = new Luola(20, 20, 15, 15, true, true);
        Gui kayttoliittyma = new Gui(35, luola);
=======
//        Luola luola = new Luola(20, 20, 15, 15, true, true);
        Gui kayttoliittyma = new Gui();
>>>>>>> f40623b491a46d5d8cdcccbd527b763b0c6bed1e
        SwingUtilities.invokeLater(kayttoliittyma);

        while (kayttoliittyma.getPaivitettava() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

<<<<<<< HEAD
        luola.setPaivitettava(kayttoliittyma.getPaivitettava());
        luola.start();
=======
//        luola.setPaivitettava(kayttoliittyma.getPaivitettava());
//        luola.start();
>>>>>>> f40623b491a46d5d8cdcccbd527b763b0c6bed1e
        //SwingUtilities.invokeLater(kayttoliittyma);
    }
}