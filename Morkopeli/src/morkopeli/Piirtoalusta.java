package morkopeli;

import java.awt.*;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Luola luola;
    private int palaP;
    private boolean peliPaalla;
    private Image morko;
    private Image pelaaja;
    private Image tyhja;
    private Image seina;
    private Image loppu;
    private Image pause;
    private Image rip;
    private Image menu;
    private JLabel siirrot;

    public Piirtoalusta(Luola luola, int palanSivunPituus, JLabel siirrot) {
        super.setBackground(Color.WHITE);
        this.luola = luola;
        this.palaP = palanSivunPituus;
        this.siirrot = siirrot;
        this.peliPaalla = true;
        haeKuvat();
    }

    private void haeKuvat() {

        URL url = Gui.class.getResource("morko.png");
        this.morko = Toolkit.getDefaultToolkit().getImage(url);
        URL url2 = Gui.class.getResource("pelaaja.png");
        this.pelaaja = Toolkit.getDefaultToolkit().getImage(url2);
        URL url3 = Gui.class.getResource("tyhja.png");
        this.tyhja = Toolkit.getDefaultToolkit().getImage(url3);
        URL url4 = Gui.class.getResource("seina.png");
        this.seina = Toolkit.getDefaultToolkit().getImage(url4);
        URL url5 = Gui.class.getResource("end.png");
        this.loppu = Toolkit.getDefaultToolkit().getImage(url5);
        URL url6 = Gui.class.getResource("pause.png");
        this.pause = Toolkit.getDefaultToolkit().getImage(url6);
        URL url7 = Gui.class.getResource("rip.png");
        this.rip = Toolkit.getDefaultToolkit().getImage(url7);
        URL url8 = Gui.class.getResource("menu.png");
        this.menu = Toolkit.getDefaultToolkit().getImage(url8);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        piirraKentta(graphics);
    }

    public void vaihaPeliPaalleTaiPoisPaalta() {
        if (peliPaalla) {
            peliPaalla = false;
        } else {
            peliPaalla = true;
        }
    }

    private void piirraKentta(Graphics graphics) {
        // Jos halutaan näyttää pausenäkymä
        if (luola != null) {
            if (luola.getPause()) {
                graphics.drawImage(pause, (int) ((luola.getLeveys() * 10 - 100) * 1.75), (int) ((luola.getKorkeus() * 10 - 100) * 1.75), 350, 200, this);
                return;
            }
        }
        // Jos halutaan näyttää pelinäkymä
        if (peliPaalla) {
            String[][] taulu = luola.getKentta();
            for (int i = 0; i < taulu.length; i++) {
                for (int j = 0; j < taulu[0].length; j++) {
                    if (taulu[i][j].equals("M")) {
                        graphics.drawImage(morko, j * palaP, i * palaP, palaP, palaP, this);
                    }
                    if (taulu[i][j].equals("O")) {
                        graphics.drawImage(tyhja, j * palaP, i * palaP, palaP, palaP, this);
                    }
                    if (taulu[i][j].equals("P")) {
                        if (luola.getJatkuu()) {
                            graphics.drawImage(pelaaja, j * palaP, i * palaP, palaP, palaP, this);
                        } else {
                            graphics.drawImage(rip, j * palaP, i * palaP, palaP, palaP, this);
                        }
                    }
                    if (taulu[i][j].equals("S")) {
                        graphics.drawImage(seina, j * palaP, i * palaP, palaP, palaP, this);
                    }
                    if (taulu[i][j].equals("E")) {
                        graphics.drawImage(loppu, j * palaP, i * palaP, palaP, palaP, this);
                    }
                }
            }
        }
        if (!peliPaalla) {
            System.out.println("WWWWWWWWWWWWWWWW");
            graphics.drawImage(menu, 190, 240, 500, 400, this);
        } // menu, 200, 275, 500, 400, this
    }

    public void paivita() {
        if (luola != null) {
        siirrot.setText("Time : " + luola.getAika() + " Boogies: " + luola.getHirvio().size());
        }
        System.out.println("paivitettiin");
        repaint();
    }
}