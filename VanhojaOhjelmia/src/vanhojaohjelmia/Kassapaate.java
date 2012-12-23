package vanhojaohjelmia;


public class Kassapaate {

    private double rahaa;  // kassassa olevan käteisen määrä
    private int edulliset;
    private int maukkaat;
    private double hintaE = 2.40;
    private double hintaM = 4.00;

    public Kassapaate() {
        rahaa = 1000;
        // kassassa on aluksi 1000 euroa rahaa
    }

    public double syoEdullisesti(double maksu) {
        // edullinen lounas maksaa 2.40 euroa. 
        // päivitetään kassan rahamäärää ja palautetaan vaihtorahat
        maksaRahalla(hintaE, maksu);
        edulliset++;
        
        if (maksu - hintaE < 0) {
            return maksu; // vaihtorahat
        }
        
        return maksu - hintaE; // vaihtorahat

    }

    public double syoMaukkaasti(double maksu) {
        // maukas lounas maksaa 4.00 euroa. 
        // päivitetään kassan rahamäärää ja palautetaan vaihtorahat
        maksaRahalla(hintaM, maksu);
        maukkaat++;
        
        if (maksu - hintaM < 0) {
            return maksu; // vaihtorahat
        }
        
        return maksu - hintaM; // vaihtorahat

    }

    public boolean syoEdullisesti(LyyraKortti kortti) {
        // edullinen lounas maksaa 2.40 euroa. 
        // jos kortilla on tarpeeksi rahaa, vähennetään hinta kortilta ja palautetaan true
        // muuten palautetaan false
        if (kortti.saldo() >= hintaE) {
            kortti.otaRahaa(hintaE);
            edulliset++;
            return true;
        } else {
            return false;
        }

    }

    public boolean syoMaukkaasti(LyyraKortti kortti) {
        // maukas lounas maksaa 4.00 euroa.
        // jos kortilla on tarpeeksi rahaa, vähennetään hinta kortilta ja palautetaan true
        // muuten palautetaan false
        if (kortti.saldo() >= hintaM) {
            kortti.otaRahaa(hintaM);
            maukkaat++;
            return true;
        } else {
            return false;
        }


    }

    private void maksaRahalla(double hinta, double maksu) {
        if (maksu - hinta >= 0) {
            rahaa = rahaa + hinta;
        }
    }

    public void lataaRahaaKortille(LyyraKortti kortti, double summa) {
        if (summa > 0) {
        kortti.lataaRahaa(summa);
        rahaa = rahaa + summa;
        }
    }

    public String toString() {
        // palautetaan merkkijonona kassan rahamäärä sekä tieto myydyistä lounaista
        // esimerkki:
        // kassassa rahaa 1008.8 edullisia lounaita myyty 2 maukkaita lounaita myyty 1
        return "kassassa rahaa " + rahaa + " edullisia lounaita myyty " + edulliset + " maukkaita lounaita myyty " + maukkaat;
    }
}