package morkopeli;

import java.awt.*;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Luola luola;
    private int palaP;
    private Image morko;
    private Image pelaaja;
    private Image tyhja;
    private Image seina;
    private Image loppu;
    private Image pause;
    private Image rip;
    private Image menu1;
    private Image menu2;
    private Image menu3;
    private Image menu4;
    private Image menu5;
    private Image morkopeli;
    private JLabel siirrot;
    private Game game;

    public Piirtoalusta(Luola luola, int palanSivunPituus, JLabel siirrot, Game game) {
        super.setBackground(Color.WHITE);
        this.luola = luola;
        this.palaP = palanSivunPituus;
        this.siirrot = siirrot;
        this.game = game;
        haeKuvat();
    }

    private void haeKuvat() {

        URL url1 = Gui.class.getResource("morko.png");
        this.morko = Toolkit.getDefaultToolkit().getImage(url1);
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
        URL url8 = Gui.class.getResource("menu1.png");
        this.menu1 = Toolkit.getDefaultToolkit().getImage(url8);
        URL url9 = Gui.class.getResource("menu2.png");
        this.menu2 = Toolkit.getDefaultToolkit().getImage(url9);
        URL url10 = Gui.class.getResource("menu3.png");
        this.menu3 = Toolkit.getDefaultToolkit().getImage(url10);
        URL url11 = Gui.class.getResource("menu4.png");
        this.menu4 = Toolkit.getDefaultToolkit().getImage(url11);
        URL url12 = Gui.class.getResource("menu5.png");
        this.menu5 = Toolkit.getDefaultToolkit().getImage(url12);
        URL url13 = Gui.class.getResource("morkopeli.png");
        this.morkopeli = Toolkit.getDefaultToolkit().getImage(url13);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        piirraKentta(graphics);
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
        if (game.getOnkoPeliPaalla()) {
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
        // Jos ollaan menussa
        if (!game.getOnkoPeliPaalla()) {
            if (game.getMenuValinta() == 1) {
                graphics.drawImage(menu1, 75, 0, 500, 500, this);
            } else if (game.getMenuValinta() == 2) {
                graphics.drawImage(menu2, 75, 0, 500, 500, this);
            } else if (game.getMenuValinta() == 3) {
                graphics.drawImage(menu3, 75, 0, 500, 500, this);
            } else if (game.getMenuValinta() == 4) {
                graphics.drawImage(menu4, 75, 0, 500, 500, this);
            } else if (game.getMenuValinta() == 5) {
                graphics.drawImage(menu5, 75, 0, 500, 500, this);
            }
            graphics.drawImage(morkopeli, 50, 500, this);
        }

    }

    public void paivita() {
        if (luola != null && game.getOnkoPausePaalla() == false) {
            siirrot.setText("Time : " + luola.getAika() + " Boogies: " + luola.getHirvio().size());
        }
        System.out.println("paivitettiin");
        repaint();
    }
}