package Luola;
import javax.swing.SwingUtilities;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Luola luola = new Luola(20, 20, 15, 15, true, true);
        Gui kayttoliittyma = new Gui(35, luola);
        SwingUtilities.invokeLater(kayttoliittyma);
        
        while (kayttoliittyma.getPaivitettava() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        luola.setPaivitettava(kayttoliittyma.getPaivitettava());
        luola.start();
        //SwingUtilities.invokeLater(kayttoliittyma);
    }
}