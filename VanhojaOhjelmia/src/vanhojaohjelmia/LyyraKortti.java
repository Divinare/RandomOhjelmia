package vanhojaohjelmia;


public class LyyraKortti {

    private double saldo;

    public LyyraKortti(double saldo) {
        this.saldo = saldo;
    }

    public double saldo() {
        return saldo;
    }

    public void lataaRahaa(double lisays) {
        this.saldo += lisays;
    }

    public boolean otaRahaa(double maara) {
        // toteuta metodi siten että se ottaa kortilta rahaa vain jos saldo on vähintään maara
        // onnistuessaan metodi palauttaa true ja muuten false

        if (saldo >= maara) {
            saldo = saldo - maara;
            return true;
        }
        
        
        
        return false;
    }
}