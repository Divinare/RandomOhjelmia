
import java.util.ArrayList;
import java.util.List;

public class Mato {

    private static final int MINIMIPITUUS = 3;
    private List<Pala> palat;
    private Suunta suunta;
    private boolean kasva;

    public Mato(int alkuX, int alkuY, Suunta alkusuunta) {
        this.palat = new ArrayList<Pala>();
        this.palat.add(new Pala(alkuX, alkuY));
        this.suunta = alkusuunta;
        this.kasva = false;
    }

    public Suunta getSuunta() {
        return suunta;
    }

    public void setSuunta(Suunta suunta) {
        this.suunta = suunta;
    }

    public List<Pala> getPalat() {
        return palat;
    }

    public int getPituus() {
        return this.palat.size();
    }

    public void liiku() {
        Pala paa = palat.get(0);

        int uusiX = paa.getX();
        int uusiY = paa.getY();

        if (this.suunta.equals(Suunta.YLOS)) {
            uusiY = uusiY - 1;
        } else if (this.suunta.equals(Suunta.OIKEA)) {
            uusiX = uusiX + 1;
        } else if (this.suunta.equals(Suunta.ALAS)) {
            uusiY = uusiY + 1;
        } else if (this.suunta.equals(Suunta.VASEN)) {
            uusiX = uusiX - 1;
        }

        palat.add(0, new Pala(uusiX, uusiY));

        if (!this.kasva && this.palat.size() > MINIMIPITUUS) {
            palat.remove(palat.size() - 1);
        } else {
            this.kasva = false;
        }
    }

    public void kasva() {
        this.kasva = true;
    }

    public boolean osuuItseensa() {
        return osuu(palat.get(0));
    }

    public boolean osuu(Pala pala) {
        for (Pala p : this.palat) {
            if (p != pala && p.osuu(pala)) {
                return true;
            }
        }

        return false;
    }
}
