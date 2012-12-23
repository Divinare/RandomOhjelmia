package Kalaha;

public class Kalaha {

    private int[] taulu; // jokaisessa kupissa aluksi 6 kuulaa
    private int pelaaja1;  // pelaajan 1 kuulat
    private int pelaaja2; // pelaajan 2 kuulat
    private int vuoro; // vuoro on joko 1 tai 2
    private int voittaja;
    private String pelaajan1nimi = "pelaaja1";
    private String pelaajan2nimi = "pelaaja2";
    private int kuulienMaara = 6; // Oletuksena kuulien määrä on 6
    private int voitot1 = 0; // pelaajan 1 voitot
    private int voitot2 = 0; // pelaajan 2 voitot
    private int tasapelit = 0;

    /**
     * Alustaa pelin, oletuksena 6 kuulaa per kuppi ja vuoro on pelaajan 1
     */
    public Kalaha() {
        taulu = new int[]{0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
        pelaaja1 = 0;
        pelaaja2 = 0;
        vuoro = 1;
        voittaja = 0;
    }

    /**
     * Laitetaan kuppeihin haluttu määrä kuulia
     */
    public void alustaKupit(int maara) {
        for (int i = 1; i <= 12; i++) {
            taulu[i] = maara;
        }
        pelaaja1 = 0;
        pelaaja2 = 0;
        voittaja = 0;
        vuoro = 1;
        if (voitot1 > voitot2) {
            vuoro = 2;
        }
        kuulienMaara = maara;
    }

    /**
     * Pelaaja painaa jotakin kuppia, joka toteuttaa metodit pelaajan1vuoro tai
     * pelaajan2vuoro
     *
     * @param kuppi Kuppi jota painetaan
     */
    public void pushButton(int kuppi) {

        if (vuoro == 1) {
            pelaajan1vuoro(kuppi);
        } else {
            pelaajan2vuoro(kuppi);
        }
    }

    /**
     * Tehdään pelaajan 1 kupin painalluksesta seuraavat toimenpiteet
     *
     * @param kuppi Kuppi jota painettiin
     */
    public void pelaajan1vuoro(int kuppi) {
        if (onkoPeliOhi() == true) {
            selvitaVoittaja();
        }
        if (taulu[kuppi] == 0) { // onko kuppi tyhjä
            return;
        }
        if (kuppi >= 7) { // jos painetaan vääriä kuppeja
            return;
        }
        vuoro = 2; // vaihetaan pelaajan vuoroa
        int maaraYht = taulu[kuppi]; // montako kuulaa siirrellään
        int maaraK = taulu[kuppi];
        int kohta = kuppi; // mistä otetaan kuulat
        taulu[kuppi] = 0; // otetaan kuulat kupista mikä valittiin
        int kohta1 = 0;
        if (kuppi == 6) { // 1. erikoistapaus jos painetaan 6. kuppia
            kohta1 = 1;
        }
        if (kohta == 6 && maaraK == 1) { // 2. erikoistapaus jos 6. kupissa on 1 kuula
            pelaaja1++;
            vuoro = 1;
            if (onkoPeliOhi() == true) {
                selvitaVoittaja();
            }
            return;
        }
        if (kohta == 6 && maaraK == 2) { // 3. erikoistapaus jos 6. kupissa on 2 kuulaa
            pelaaja1++;
            taulu[7]++;
            if (onkoPeliOhi() == true) {
                selvitaVoittaja();
            }
            return;
        }

        for (int i = 0; i < maaraYht; i++) {
            kohta++;
            if (i == maaraYht - 1 && taulu[kohta] == 0 && taulu[7 + (6 - kohta)] != 0
                    && kohta >= 1 && kohta <= 6) {
                pelaaja1 = pelaaja1 + taulu[7 + (6 - kohta)];
                taulu[7 + (6 - kohta)] = 0;
                taulu[kohta] = 0;
                vuoro = 1;
                pelaaja1++; // Pelaaja1 saa myös sen viimeisen kuulan kippoonsa.
                if (onkoPeliOhi() == true) {
                    selvitaVoittaja();
                }
                return;
            }

            if (kohta == 6 && maaraK > 1 || kohta1 == 1) {
                kohta1 = 0; // palautetaan nollaksi
                if (maaraK == 2) { // jos päästään tasan pelaajan 1 kuppiin
                    pelaaja1++;
                    vuoro = 1;
                    taulu[kohta]++;
                    if (onkoPeliOhi() == true) {
                        selvitaVoittaja();
                    }
                    return;
                }
                pelaaja1++;
                maaraK--;
                i++; //i kasvaa koska pelaaja1 saa pisteen
            }
            maaraK--;
            taulu[kohta]++;
            if (kohta == 12) {
                kohta = 0;
            }
        }
        if (onkoPeliOhi() == true) {
            selvitaVoittaja();
        }
    }

    /**
     * Tehdään pelaajan 2 kupin painalluksesta seuraavat toimenpiteet
     *
     * @param kuppi Kuppi jota painettiin
     */
    public void pelaajan2vuoro(int kuppi) {
        if (onkoPeliOhi() == true) {
            selvitaVoittaja();
        }

        if (taulu[kuppi] == 0) { // onko kuppi tyhjä
            return;
        }
        if (kuppi <= 6) { // jos painetaan vääriä kuppeja
            return;
        }
        vuoro = 1; // vaihetaan pelaajan vuoroa
        int maaraYht = taulu[kuppi]; // montako kuulaa siirrellään
        int maaraK = taulu[kuppi];
        int kohta = kuppi; // mistä otetaan kuulat
        taulu[kuppi] = 0; // otetaan kuulat kupista mikä valittiin
        int kohta1 = 0;
        if (kuppi == 12) { // 1. erikoistapaus jos painetaan 12. kuppia
            kohta1 = 1;
        }
        if (kohta == 12 && maaraK == 1) { // 2. erikoistapaus jos 12. kupissa on 1 kuula
            pelaaja2++;
            vuoro = 2;
            if (onkoPeliOhi() == true) {
                selvitaVoittaja();
            }
            return;
        }
        if (kohta == 12 && maaraK == 2) { // 3. erikoistapaus jos 12. kupissa on 2 kuulaa
            pelaaja2++;
            taulu[1]++;
            if (onkoPeliOhi() == true) {
                selvitaVoittaja();
            }
            return;
        }

        for (int i = 0; i < maaraYht; i++) {
            kohta++;
            if (kohta == 13) {
                kohta = 1;
            }
            if (i == maaraYht - 1 && taulu[kohta] == 0 && taulu[7 + (6 - kohta)] != 0
                    && kohta >= 7 && kohta <= 12) {
                pelaaja2 = pelaaja2 + taulu[7 + (6 - kohta)];
                taulu[7 + (6 - kohta)] = 0;
                  /**
     * @return pelaajan 1 pisteen stringinä
     */taulu[kohta] = 0;
                vuoro = 2;
                pelaaja2++; // Pelaaja2 saa myös sen viimeisen kuulan kippoonsa.
                if (onkoPeliOhi() == true) {
                    selvitaVoittaja();
                }
                return;
            }

            if (kohta == 12 && maaraK > 1 || kohta1 == 1) {
                kohta1 = 0; // palautetaan nollaksi
                if (maaraK == 2) { // jos päästään tasan pelaajan 2 kuppiin
                    pelaaja2++;
                    vuoro = 2;
                    taulu[kohta]++;
                    if (onkoPeliOhi() == true) {
                        selvitaVoittaja();
                    }
                    return;
                }
                pelaaja2++;
                maaraK--;
                i++; //i kasvaa koska pelaaja2 saa pisteen
            }
            maaraK--;
            taulu[kohta]++;
        }
        if (onkoPeliOhi() == true) {
            selvitaVoittaja();
        }
    }

    /**
     * @return voittaja, joko 0, 1, 2 tai 3
     */
    public int getVoittaja() {
        return voittaja;
    }

    /**
     * Metodi tarkistaa onko toisella pelaajista kaikki kupit tyhjiä
     */
    public boolean onkoPeliOhi() {
        if (taulu[1] == 0 && taulu[2] == 0 && taulu[3] == 0
                && taulu[4] == 0 && taulu[5] == 0 && taulu[6] == 0) { // Jos pelaajan 1 kuulat ovat loppu
            pelaaja2 = pelaaja2 + taulu[7] + taulu[8]
                    + taulu[9] + taulu[10] + taulu[11] + taulu[12];
            for (int i = 7; i <= 12; i++) {
                taulu[i] = 0;
            }
            return true;
        }
        if (taulu[7] == 0 && taulu[8] == 0 && taulu[9] == 0
                && taulu[10] == 0 && taulu[11] == 0 && taulu[12] == 0) { // Jos pelaajan 2 kuulat ovat loppu
            pelaaja1 = pelaaja1 + taulu[1] + taulu[2]
                    + taulu[3] + taulu[4] + taulu[5] + taulu[6];
            for (int i = 1; i <= 6; i++) {
                taulu[i] = 0;
            }
            return true;
        }
        return false;
    }

    /**
     * Selvitetään kuka voittaa vai tuleeko tasapeli
     */
    public void selvitaVoittaja() {
        if (pelaaja1 > pelaaja2) {
            voittaja = 1;
            voitot1++;
        }
        if (pelaaja2 > pelaaja1) {
            voittaja = 2;
            voitot2++;
        }
        if (pelaaja1 == pelaaja2) {
            voittaja = 3; // Tasapeli
            tasapelit++;
        }
    }

    /**
     * @return pelaajan 1 pisteet
     */
    public int getPelaaja1() {
        return pelaaja1;
    }

    /**
     * @return pelaajan 2 pisteet
     */
    public int getPelaaja2() {
        return pelaaja2;
    }

    /**
     * @param nro annettu kupin numero
     *
     * @return kupin arvo
     */
    public int getKuppi(int nro) {
        return taulu[nro];
    }

    /**
     * @return pelaajan 1 vuoro Stringinä
     */
    public String getVuoroPelaaja1() {
        if (vuoro == 1) {
            return "X";
        }
        return "_";
    }
    /**
     * @return pelaajan 2 vuoro Stringinä 
     */
    public String getVuoroPelaaja2() {
        if (vuoro == 2) {
            return "X";
        }
        return "_";
    }
    /**
     * @return pelaajan vuoro 
     */
    public int getVuoro() {
        return vuoro;
    }

    /**
     * @return kuulien määrä per kuppi pelin alussa
     */
    public int getKuulienMaara() {
        return kuulienMaara;
    }

    /**
     * @return pelaajan 1 pisteen stringinä
     */
    public String getPelaaja1String() {
        String s = Integer.toString(pelaaja1);
        return s;
    }

    /**
     * @return pelaajan 2 pisteen stringinä
     */
    public String getPelaaja2String() {
        String s = Integer.toString(pelaaja2);
        return s;
    }

    /**
     * @param nro kupin numero
     *
     * @return kupin arvo stringinä
     */
    public String getKuppiString(int nro) {
        String s = Integer.toString(taulu[nro]);
        return s;
    }

    /**
     * @return pelaajan 1 nimi
     */
    public String getPelaajan1nimi() {
        return pelaajan1nimi;
    }

    /**
     * @return pelaajan 2 nimi
     */
    public String getPelaajan2nimi() {
        return pelaajan2nimi;
    }

    /**
     * @param nro kupin numero
     * @param luku kuulien määrä
     */
    public void setKuppi(int nro, int luku) { // metodi ohjelman testaamista varten
        taulu[nro] = luku;
    }

    /**
     * @param luku miksikä pelaajan vuoro halutaan laittaa, joko 1 tai 2
     */
    public void setVuoro(int luku) { // 1 on pelaaja 1, 2 on pelaaja 2
        vuoro = luku;
    }

    public String getTilastot() {


        return "Pelaajan " + pelaajan1nimi + " voitot: " + voitot1 + "\n"
                + "Pelaajan " + pelaajan2nimi + " voitot: " + voitot2 + "\n"
                + "Tasapelit: " + tasapelit;
    }

    /**
     * @param pelaaja1 pelaajan 1 nimi
     * @param pelaaja2 pelaajan 2 nimi
     */
    public void setNimet(String pelaaja1, String pelaaja2) {
        pelaajan1nimi = pelaaja1;
        pelaajan2nimi = pelaaja2;
    }
}