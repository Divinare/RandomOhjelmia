
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Nappaimistonkuuntelija extends KeyAdapter {

    private Mato mato;

    public Nappaimistonkuuntelija(Mato mato) {
        this.mato = mato;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            mato.setSuunta(Suunta.YLOS);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            mato.setSuunta(Suunta.OIKEA);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            mato.setSuunta(Suunta.ALAS);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            mato.setSuunta(Suunta.VASEN);
        }
    }
}