
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class Matopeli extends Timer implements ActionListener {

    private int leveys;
    private int korkeus;
    private boolean jatkuu;
    private Paivitettava paivitettava;
    private Mato mato;
    private Omena omena;

    public Matopeli(int leveys, int korkeus) {
        super(1000, null);

        this.leveys = leveys;
        this.korkeus = korkeus;
        this.jatkuu = true;

        this.omena = arvoOmena();
        this.mato = new Mato(leveys / 2, korkeus / 2, Suunta.ALAS);

        addActionListener(this);
        setInitialDelay(2000);
    }

    public boolean jatkuu() {
        return jatkuu;
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public Mato getMato() {
        return mato;
    }

    public void setMato(Mato mato) {
        this.mato = mato;
    }

    public Omena getOmena() {
        return omena;
    }

    public void setOmena(Omena omena) {
        this.omena = omena;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!jatkuu) {
            return;
        }

        liiku();
        syoOmena();
        tarkistaTormaykset();
        piirra();

        setDelay(1000 / this.mato.getPituus());
    }
// Apumetodeita:

    private void liiku() {
        mato.liiku();
    }

    private void syoOmena() {
        if (!mato.osuu(omena)) {
            return;
        }

        omena = arvoOmena();
        mato.kasva();
    }

    private void tarkistaTormaykset() {
        List<Pala> palat = mato.getPalat();

        Pala karki = mato.getPalat().get(0);
        for (int i = 1; i < mato.getPalat().size(); i++) {
            if (karki.osuu(palat.get(i))) {
                jatkuu = false;
            }
        }
    }

    private void piirra() {
        paivitettava.paivita();
    }

    private Omena arvoOmena() {
        Random random = new Random();
        return new Omena(random.nextInt(leveys), random.nextInt(korkeus));
    }
}