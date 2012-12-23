package ohJa;
public class IntJoukko {
    /* 
     * HUOM:  älä lisää luokalle mitään muita muuttujia, niitä ei tarvita
     *        ja testit valittavat niistä
     *        älä myöskään uudelleennimeä ilmentymämuuttujia
     *     
     *        metodeille voit lisätä apumuuttujia vapaasti
     * 
     * HUOM2: tiedäthän miten voit ajaa yksittäistä tehtävää koskevia testejä?
     *        jos et, lue https://wiki.helsinki.fi/display/s2011paja/Automaattinen+paja-apina
     * 
     * HUOM3: ala tee ohjelmia siten että testaat koodia pelkästään tmc:n
     *        testeillä. helpoimmat asiat voit toki tehdä näinkin
     * 
     *        jos joudut ongelmiin, kannattaa ensin kirjoittaa pieniä 
     *        testikoodinpätkiä mainiin
     * 
     *        ohman viikon 1 laskareissa esiteltävällä debuggerillakin voi olla
     *        käyttöä tässä tehtävässä
     */

    public final static int KAPASITEETTI = 5;   // aloitustalukon koko
    public final static int OLETUSKASVATUS = 5; // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int[] taulukko;                     // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;
    // tehtävän toisessa osassa tarvitaan seuraava:
    private int kasvatuskoko;                   // Uusi taulukko on tämän verran vanhaa suurempi.

    // tehtävä 2.1
    // tehtävää vastaavat testit Osa1Test
    public IntJoukko() {
        taulukko = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        kasvatuskoko = OLETUSKASVATUS;
    }

    // tehtävä 2.2
    // tehtävää vastaavat testit Osa2Test
    // HUOM: kun tehtävän yhteydessä muutat tehtävän 1 koodia, aja myös
    // Osa1Test-testit jotta varmistat että jo toimunut koodi ei mene rikki
    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti <= 0) {
            taulukko = new int[1];
            alkioidenLkm = 0;
            kasvatuskoko = OLETUSKASVATUS;
        } else {
            taulukko = new int[kapasiteetti];
            alkioidenLkm = 0;
            kasvatuskoko = OLETUSKASVATUS;
        }
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
//        if (kapasiteetti <= 0) {
//            taulukko = new int[1];
//        }
//        if (kasvatuskoko < 0) {
//            this.kasvatuskoko = OLETUSKASVATUS;
//        }
        if (kapasiteetti <= 0) {
            taulukko = new int[1];
            alkioidenLkm = 0;
            this.kasvatuskoko = kasvatuskoko;
        } else {
            alkioidenLkm = 0;
            taulukko = new int[kapasiteetti];
            this.kasvatuskoko = kasvatuskoko;
        }
    }

    public boolean lisaa(int luku) {

        if (kuuluu(luku) == true) {
            return false;
        }
        if (luku == 0) {
            alkioidenLkm++;
            return true;
        }

        if (alkioidenLkm == taulukko.length) {
            int uusiTaulu[] = new int[alkioidenLkm + kasvatuskoko];
            for (int i = 0; i < taulukko.length; i++) {
                uusiTaulu[i] = taulukko[i];
            }
            taulukko = uusiTaulu;
        }



        for (int i = 0; i < taulukko.length; i++) {
            if (taulukko[i] > luku) {
                for (int j = alkioidenLkm; j > i; j--) {
                    taulukko[j] = taulukko[j - 1];
                }
                taulukko[i] = luku;
                alkioidenLkm++;
                return true;
            }
        }
        
        taulukko[alkioidenLkm] = luku;
        alkioidenLkm++;
        return true;



        
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {

        if (alkioidenLkm == 0) {
            return "{}";
        }
        String luvut = "";
        for (int i = 0; i < alkioidenLkm; i++) {
            if (i < alkioidenLkm - 1) {
                luvut = luvut + taulukko[i] + ", ";
            } else {
                luvut = luvut + taulukko[i];
            }
        }
        return "{" + luvut + "}";
    }
    // tehtävä 2.3
    // tehtävää vastaavat testit Osa3Test

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (taulukko[i] == luku) {
                for (int j = i; j < alkioidenLkm - 1; j++) {
                    taulukko[j] = taulukko[j + 1];
                }
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    public boolean kuuluu(int luku) {


        if (binHae(taulukko, luku) != -1) {
            return true;
        }
        return false;
    }

    // tehtävä 2.4
    // tehtävää vastaavat testit Osa4Test   
    public int[] toIntArray() {
        int[] tauluggo = new int[alkioidenLkm];
        for (int i = 0; i < alkioidenLkm; i++) {
            tauluggo[i] = taulukko[i];
        }
        return tauluggo;
    }

    public int binHae(int[] taulu, int haettava) {
        int vasen = 0;
        int oikea = alkioidenLkm - 1;
        int keski;
        while (vasen <= oikea) {
            keski = (vasen + oikea) / 2;

            if (haettava == taulu[keski]) {
                return keski;     // löytyi ja lopetetaan!
            }
            if (haettava < taulu[keski]) {
                oikea = keski - 1;
            } else {
                vasen = keski + 1;
            }
        }
        // jos tähän päästiin, hakualue tuli tyhjäksi eikä löytynyt!
        return -1;
    }
}