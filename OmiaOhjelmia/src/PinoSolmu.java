
public class PinoSolmu {

    int key;
    PinoSolmu next;

    PinoSolmu(int k, PinoSolmu seur) {
        key = k;
        next = seur;
    }

    public String toString() {

        return (" key on " + key + " ja next on " + next);
    }
}
