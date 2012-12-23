package ohJa;

public class Kuulaskuri {

    private int kuu;
    private static String[] kuunNimet = {
        "tammi", "helmi", "maalis", "huhti", "touko", "kesä",
        "heinä", "elo", "syys", "loka", "marras", "joulu"};
    private int vuosi = 2010;

    // ----- konstruktori: -------
    public Kuulaskuri() {
        kuu = 1;
    }
    // ----- aksessorit: -----------

    public int moneskoKuu() {
        return kuu;
    }

    public void seuraavaKuu() {
        ++kuu;
        if (kuu == 13) {
            kuu = 1;
        }
        if (moneskoKuu() == 1) {
            vuosi = vuosi + 1;
        }
    }
    
    public void seuraavaVuosi() {
        vuosi++;
    }

    public String kuukaudenNimi() {
        return kuunNimet[this.moneskoKuu() - 1] + "kuu";
    }

    public String mikaVuosi() {
        String vuosi = "" + this.vuosi;
        return vuosi;
    }
}