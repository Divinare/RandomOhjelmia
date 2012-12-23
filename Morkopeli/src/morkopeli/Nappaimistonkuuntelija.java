package morkopeli;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Nappaimistonkuuntelija extends KeyAdapter {

    private Pelaaja pelaaja;
    private Luola luola;
    private long pausenAlkuAika;
    boolean pausePaalla;
    
    public Nappaimistonkuuntelija(Pelaaja pelaaja, Luola luola) {
        this.pelaaja = pelaaja;
        this.luola = luola;
        this.pausePaalla = false;
       }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pelaaja.setSuunta(Suunta.YLOS);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pelaaja.setSuunta(Suunta.OIKEA);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pelaaja.setSuunta(Suunta.ALAS);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pelaaja.setSuunta(Suunta.VASEN);
        }
        else if (e.getKeyCode() == KeyEvent.VK_P) {
            if (pausePaalla == false) {
                pausePaalla = true;
            }
            else {
                pausePaalla = false;
            }
            if (pausePaalla) {
                pausenAlkuAika = System.currentTimeMillis();
            }
            long pausenLopetusAika = System.currentTimeMillis();
            luola.lisaaAikaaPauseenKuluneeseenAikaan(pausenLopetusAika - pausenAlkuAika);
            luola.pause();
            
        }
    }
}