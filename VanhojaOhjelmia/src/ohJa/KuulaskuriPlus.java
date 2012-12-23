package ohJa;

public class KuulaskuriPlus extends Kuulaskuri {

    private static String[] kuunNimet = { // kaikille olioille sama!
        "tammi", "helmi", "maalis", "huhti", "touko", "kesä",
        "heinä", "elo", "syys", "loka", "marras", "joulu"};

    public KuulaskuriPlus() {
    }  // Ei mitään lisättävää...

    public String kuukaudenNimi() {
        return kuunNimet[this.moneskoKuu() - 1] + "kuu";
    }
    
    public String toString() {
        return kuunNimet[this.moneskoKuu() - 1] + "kuu";
    }
}
