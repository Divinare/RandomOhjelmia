public class KiviSaksetPaperi {

    public int voitot = 0;
    public int häviöt = 0;
    public int tasapelit = 0;

    public int pelaa(int luku) { // 1 = kivi, 2 = sakset, 3 = paperi
        int arvottu = (int) (3 * Math.random()+1);
        int tulos = 0;
        if (luku == arvottu) {
            tulos = 0;
            tasapelit++;
        } else if (luku == 1 && arvottu == 2) {
            tulos = 1;
            voitot++;
        } else if (luku == 2 && arvottu == 3) {
            tulos = 1;
            voitot++;
        } else if (luku == 3 && arvottu == 1) {
            tulos = 1;
            voitot++;
        } else {
            tulos = 2;
            häviöt++;
        }
        return tulos; // 0 = tasapeli, 1 = pelaaja voittaa, 2 = tietokone voittaa
    }

    public String getVoitot() {
        String mj = Integer.toString(voitot);
        return mj;
    }

    public String getHaviot() {
        String mj = Integer.toString(häviöt);
        return mj;
    }
        public String getTasapelit() {
        String mj = Integer.toString(tasapelit);
        return mj;
    }
}
