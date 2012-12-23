package morkoapplet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Nappaimistonkuuntelija extends KeyAdapter {

    private Pelaaja pelaaja;

    public Nappaimistonkuuntelija(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
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
    }
}