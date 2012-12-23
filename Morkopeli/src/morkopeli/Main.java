package morkopeli;

import javax.swing.SwingUtilities;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Luola luola = new Luola(5);
        Gui kayttoliittyma = new Gui(35, luola);
        kayttoliittyma.run();
        luola.setPaivitettava(kayttoliittyma.getPaivitettava());
        luola.start();

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