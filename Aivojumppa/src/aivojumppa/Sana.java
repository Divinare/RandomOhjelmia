package aivojumppa;

public class Sana implements Comparable<Sana> {

    private String sana;
    private int kirjaintenMaara;

    public Sana(String sana) {
        this.sana = sana;
        this.kirjaintenMaara = 1;
    }

    public String getSana() {
        return sana;
    }

    public int getKirjaintenMaara() {
        return kirjaintenMaara;
    }

    public void kasvataKirjaintenMaaraa() {
        this.kirjaintenMaara++;
    }

    @Override
    public String toString() {
        return sana;
    }

    @Override
    public int compareTo(Sana s) {
        return this.sana.length() - s.getSana().length();
    }
}
