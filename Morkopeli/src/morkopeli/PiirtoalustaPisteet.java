package morkopeli;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;
import javax.swing.JPanel;

public class PiirtoalustaPisteet extends JPanel implements Paivitettava {

    private Luola luola;
    private int palaP;
    private Image morko;
    private Image pelaaja;
    private Image tyhja;
    private Image seina;
    private Image loppu;
    
    public PiirtoalustaPisteet(Luola luola, int palanSivunPituus) {
        super.setBackground(Color.BLACK);
        this.luola = luola;
        this.palaP = palanSivunPituus;
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
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        piirraKentta(graphics);
    }

    private void piirraKentta(Graphics graphics) {
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
                    graphics.drawImage(pelaaja, j * palaP, i * palaP, palaP, palaP, this);
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

    public void paivita() {
        repaint();
    }
}