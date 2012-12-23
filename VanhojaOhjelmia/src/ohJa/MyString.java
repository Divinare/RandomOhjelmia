package ohJa;
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

public class MyString {

    public static final int MAKSIMIPITUUS = 100; // - pisin mahdollinen merkkijono
    // - näkyy kaikkialle ilmauksella: MyString.MAKSIMIPITUUS
    private char[] mjono; // - merkkijononon esitys char-taulukkona
    private int pituus;   // - montako alkioita mjono:n alusta on käytössä
    //   eli ensimmäisen käyttämättömän alkion indeksi
    // - tyhjässä MyString-oliossa arvo on siis 0

    // tehtävä 4.1
    // tehtävää vastaavat testit Osa1Test
    public MyString(String sisalto) {

        this.mjono = new char[MAKSIMIPITUUS];
        if (sisalto.length() > 100) {
            this.pituus = 100;
        } else {
            this.pituus = sisalto.length();
        }
        if (pituus > MAKSIMIPITUUS) {
            String katkaistu = sisalto.substring(0, MAKSIMIPITUUS);
            this.mjono = katkaistu.toCharArray();
        } else {
            //this.mjono = sisalto.toCharArray();
            for (int i = 0; i < pituus; i++) {
                mjono[i] = sisalto.charAt(i);
            }
        }
    }

    public int length() {
        if (pituus > MAKSIMIPITUUS) {
            return MAKSIMIPITUUS;
        }
        return pituus;
    }

    public String toString() {

        String jono = new String(mjono);
        String tulostettava = jono.substring(0, pituus);
        return tulostettava;
    }

    // tehtävä 4.2
    // tehtävää vastaavat testit Osa2Test
    public MyString() {
        pituus = 0;
        this.mjono = new char[MAKSIMIPITUUS];
    }

    public char charAt(int index) {
        if (index >= 0 && index <= pituus - 1) {
            return mjono[index];
        }
        throw new IndexOutOfBoundsException("charAt-operaatiossa");
    }

    public void replace(int index, char newChar) {
        if (index >= 0 && index < pituus) {
            mjono[index] = newChar;
        } else {
            throw new IndexOutOfBoundsException("replace-operaatiossa");
        }
    }

    public void replaceAll(char oldChar, char newChar) {

        for (int i = 0; i < pituus; i++) {
            if (mjono[i] == oldChar) {
                mjono[i] = newChar;
            }
        }
    }
    // tehtävä 4.3
    // tehtävää vastaavat testit Osa3Test  

    public MyString(MyString m) {
        pituus = m.length();
        mjono = new char[MAKSIMIPITUUS];
        for (int i = 0; i < pituus; i++) {
            mjono[i] = m.charAt(i);
        }
    }

    public void deleteCharAt(int index) {

        if (index > pituus - 1 || index < 0) {
            throw new IndexOutOfBoundsException("deleteCharAt-operaatiossa");
        }
        for (int i = index; i < pituus - 1; i++) {
            mjono[i] = mjono[i + 1];
        }
        pituus--;
    }

    public void insert(int offset, char c) {
        if (offset > pituus || offset < 0 || pituus == MAKSIMIPITUUS) {
            throw new IndexOutOfBoundsException("insert-operaatiossa");
        }

        for (int i = pituus; i >= offset; i--) {
            mjono[i + 1] = mjono[i];
        }
        mjono[offset] = c;

        pituus++;
    }

    // tehtävä 4.4
    // tehtävää vastaavat testit Osa4Test   
    public int compareTo(MyString q) {

        if (this.length() == q.length()) {

            for (int i = 0; i < q.length(); i++) {
                if (this.mjono[i] < q.mjono[i]) {
                    return -1;
                } else if (this.mjono[i] > q.mjono[i]) {
                    return 1;
                }
            }
            return 0;
        }
        if (this.length() < q.length()) {

            for (int i = 0; i < this.length(); i++) {
                if (this.mjono[i] < q.mjono[i]) {
                    return -1;
                } else if (this.mjono[i] > q.mjono[i]) {
                    return 1;
                }
            }
            return -1;

        } else {

            for (int i = 0; i < q.length(); i++) {
                if (this.mjono[i] < q.mjono[i]) {
                    return -1;
                } else if (this.mjono[i] > q.mjono[i]) {
                    return 1;
                }
            }
            return 1;
        }
    }

    public int indexOf(char s) {

        for (int i = 0; i < pituus; i++) {
            if (mjono[i] == s) {
                return i;
            }
        }

        return -1;
    }

    // tehtävä 4.5
    // tehtävää vastaavat testit Osa5Test 
    public int indexOf(MyString etsittava) {
        for (int alku = 0; alku < pituus; alku++) {
            if ( loytyyAlkaenKohdasta(alku, etsittava) )
                return alku;
        }
  
        return -1;
    }
  
    private boolean loytyyAlkaenKohdasta(int alku, MyString etsittava) {
  
        for (int i = 0; i < etsittava.pituus; i++) {
            if ( alku+i>=pituus) return false;
  
            if ( mjono[alku+i] != etsittava.mjono[i] ) return false;
        }
  
        return true;
    }


    public static void main(String[] args) {
        String mj = "Ohjaa";
        MyString q = new MyString("Ohjaa");
        System.out.println(q.mjono[2]);
    }
}
