package ohJa;

public class CDLevy implements Talletettava {

    private double paino;
    private String esittaja;
    private String nimi;
    private int vuosi;
    //ArrayList<Talletettava> tavarat;
    
    public CDLevy(String esittaja, String nimi, int vuosi) {
        paino = 0.1;
        this.esittaja = esittaja;
        this.nimi = nimi;
        this.vuosi = vuosi;
    }
    public double getPaino() {
        return paino;
    }
    public String toString() {
        return esittaja + ": " + nimi + " (" + vuosi + ")";
    }
}
