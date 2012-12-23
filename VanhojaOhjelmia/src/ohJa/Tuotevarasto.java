package ohJa;

public class Tuotevarasto extends Varasto {

    private String nimi;

    public Tuotevarasto(String tuotenimi, double tilavuus) {

        super(tilavuus);
        nimi = tuotenimi;
    }

    public Tuotevarasto(String tuotenimi, double tilavuus, double alkuSaldo) {

        super(tilavuus, alkuSaldo);
        nimi = tuotenimi;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String uusiNimi) {
        nimi = uusiNimi;
    }

    public String toString() {
        return nimi + ": saldo = " + super.getSaldo() + ", vielä tilaa " + (super.getTilavuus() - super.getSaldo());
    }
}