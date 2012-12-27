package morkopeli;

//package morkopeli;
//
//import java.awt.Canvas;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import javax.swing.Timer;
//
//public class LuolaMenu extends Canvas implements MouseListener {
//
////    private HiirenKuuntelija HiirenKuuntelija;
//    private Paivitettava Piirtoalusta;
//    private Gui Kayttoliittyma;
////    private HiirenKuuntelija hiirenkuuntelija;
//
//    public LuolaMenu(Gui kayttoliittyma) {
////        super(1000, null);
//        this.Kayttoliittyma = kayttoliittyma;
//        this.Piirtoalusta = kayttoliittyma.getPaivitettava();
//        if (kayttoliittyma.getPaivitettava() == null) {
//            System.out.println("NO VOI PASKAAAAAAAA");
//        }
//        if (Piirtoalusta == null) {
//            System.out.println("LOL");
//        }
////        hiirenkuuntelija = new HiirenKuuntelija(this);
////        if (hiirenkuuntelija != null) {
////            System.out.println("onnistu hiiri");
////        }
//        addMouseListener(this);
////        addActionListener(this);
////        setInitialDelay(2000);
//
//        //        Piirtoalusta.paivita();
//
////        Luola luola = new Luola(15, true, 5);
////        luola.setPaivitettava(kayttoliittyma.getPaivitettava());
////        luola.start();
//
//    }
//
//    public void mouseClicked(MouseEvent e) {
//        System.out.println("haha");
//        int xPos = e.getPoint().x;
//        int yPos = e.getPoint().y;
//        if (xPos > 190 && xPos < 690 && yPos > 240 && yPos < 640) {
//            newGame();
//        }
//    }
////                 if (xPos > 100 && yPos > 100 && xPos < 725 && yPos < 725) {
//
//    public void mouseReleased(MouseEvent e) {
//    }
//
//    public void mouseEntered(MouseEvent e) {
//    }
//
//    public void mouseExited(MouseEvent e) {
//    }
//
//    public void mousePressed(MouseEvent e) {
//    }
//
////    @Override
////    public void actionPerformed(ActionEvent ae) {
////        if (Piirtoalusta == null) {
////            System.out.println("lol");
////        }
////        piirra();
////
////    }
//    public void newGame() {
//        System.out.println("lolzzz");
//        Luola luola = new Luola(5);
//        Kayttoliittyma.setGame(luola);
//        Kayttoliittyma.luoKomponentit(Kayttoliittyma);
//
//    }
//
//    private void piirra() {
//        Piirtoalusta.paivita();
//    }
//
//    public void setPaivitettava(Paivitettava paivitettava) {
//        this.Piirtoalusta = paivitettava;
//    }
////    public void actionPerformed(ActionEvent ae) {
////        
////    }
////    public void mouseClicked(MouseEvent e) {
////
////        int xPos = e.getPoint().x;
////        int yPos = e.getPoint().y;
////
////    }
////
////    public void mouseReleased(MouseEvent e) {
////    }
////
////    public void mouseEntered(MouseEvent e) {
////    }
////
////    public void mouseExited(MouseEvent e) {
////    }
////
////    public void mousePressed(MouseEvent e) {
////    }
//}
