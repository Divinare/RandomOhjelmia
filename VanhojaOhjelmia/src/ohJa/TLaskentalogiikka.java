package ohJa;

public class TLaskentalogiikka {

    private String muisti;
    private String puskuri;
    private String plusVaiMiinus = "";

    public TLaskentalogiikka() {
        this.muisti = "";
        this.puskuri = "";
    }

    public void lisaaNumero() {
        if (muisti.equals("YLIVUOTO!") || puskuri.equals("ALIVUOTO!")) {
            return;
        }
        if (puskuri.length() < 21) {
            puskuri = puskuri + "|";
        }
    }

    public String getPuskuri() {
        return puskuri;
    }

    public String getMuisti() {
        return muisti;
    }

    public void plus() {
        if (muisti.equals("YLIVUOTO!") || puskuri.equals("ALIVUOTO!")) {
            return;
        }
        muisti = puskuri;
        puskuri = "";
        plusVaiMiinus = "+";

    }

    public void miinus() {
        if (muisti.equals("YLIVUOTO!") || puskuri.equals("ALIVUOTO!")) {
            return;
        }
        muisti = puskuri;
        puskuri = "";
        plusVaiMiinus = "-";
    }

    public void tulosOn() {
        if (plusVaiMiinus.equals("") || muisti.equals("YLIVUOTO!") || puskuri.equals("ALIVUOTO!")) {
            return;
        }
        int tukkeja;
        if (plusVaiMiinus.equals("+")) {
            tukkeja = muisti.length() + puskuri.length();
            if (tukkeja > 21) {
                muisti = "YLIVUOTO!";
                puskuri = "YLIVUOTO!";
                return;
            }
            puskuri = "";
            for (int i = 0; i < tukkeja; i++) {
                puskuri = puskuri + "|";
            }
            plusVaiMiinus = "";
        }
        if (plusVaiMiinus.equals("-")) {
            tukkeja = muisti.length() - puskuri.length();
            if (tukkeja < 0) {
                muisti = "ALIVUOTO!";
                puskuri = "ALIVUOTO!";
                return;
            }
            puskuri = "";
            for (int i = 0; i < tukkeja; i++) {
                puskuri = puskuri + "|";
            }
            plusVaiMiinus = "";
        }
    }

    public void nollaa() {
        puskuri = "";
        muisti = "";
    }
}