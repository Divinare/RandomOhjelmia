package ohJa;


public abstract class Pelikehys {

    private int ekanVoitot;
    private int tokanVoitot;
    private int ekanTappiot;
    private int tokanTappiot;
    private int kierrosLkm;

    // Tähän koukkuun ripustetaan todellinen päätössääntö, jolla voittajan valitaan:
    public abstract boolean ekaVoittaa(String eka, String toka);

    // Kehyksen tarjoamat valmiit palvelut:
    public void tulostaTulos(String eka, String toka) {
        if (ekaVoittaa(eka, toka)) {
            System.out.println("Ensimmäinen voittaa!");
            ekanVoitot++;
            tokanTappiot++;
            kierrosLkm++;
        } else {
            System.out.println("Toinen voittaa!");
            kierrosLkm++;
            ekanTappiot++;
            tokanVoitot++;
        }
    }

    public int ekallaVoittoja() {
        return ekanVoitot;
    }

    public int tokallaVoittoja() {
        return tokanVoitot;
    }

    public int ekallaTappioita() {
        return ekanTappiot;
    }

    public int tokallaTappioita() {
        return tokanTappiot;
    }

    public int kierrosLkm() {

        return kierrosLkm;
    }
    
    public void tulostaTilastot() {
        System.out.println("Kierroksia: " + kierrosLkm
                + "\n" + "1-voittoja: " + ekanVoitot
                + "\n" + "2-voittoja: " + tokanVoitot);
        
    }

    // palauttaa arvonaan pelattujen kierrosten lukumäärän.
    public boolean tulos(String eka, String toka) {
        if (ekaVoittaa(eka, toka) == true) {
            ekanVoitot++;
            tokanTappiot++;
            kierrosLkm++;
            //System.out.println("Tilanne " + ekallaVoittoja() + "-" + tokallaVoittoja());
            return true;
        } else {
            tokanVoitot++;
            ekanTappiot++;
            kierrosLkm++;
            //System.out.println("Tilanne " + ekallaVoittoja() + "-" + tokallaVoittoja());
            return false;
        }
    }
}