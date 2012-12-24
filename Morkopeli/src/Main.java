
import java.io.File;
import javax.swing.SwingUtilities;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import morkopeli.Gui;
import morkopeli.Luola;

public class Main {


    public static void main(String[] args) {

   

        Luola peli = new Luola(5);
        Gui kayttoliittyma = new Gui(35, peli);
        kayttoliittyma.run();
        peli.setPaivitettava(kayttoliittyma.getPaivitettava());
        peli.start();

        while (kayttoliittyma.getPaivitettava() == null) {
            System.out.println("haha");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
//        LuolaMenu menu = new LuolaMenu(kayttoliittyma);
//        SwingUtilities.invokeLater(kayttoliittyma);
//        menu.setPaivitettava(kayttoliittyma.getPaivitettava());

//        Luola luola = new Luola(15, true,5);

    }
}