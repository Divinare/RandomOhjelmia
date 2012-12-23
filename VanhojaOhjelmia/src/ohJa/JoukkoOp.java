package ohJa;
public class JoukkoOp {

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {

        IntJoukko yhdiste = new IntJoukko();
        int aa[] = a.toIntArray();
        int bb[] = b.toIntArray();
        for (int i = 0; i < a.mahtavuus(); i++) {
            yhdiste.lisaa(aa[i]);
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            yhdiste.lisaa(bb[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {

        IntJoukko leikkaus = new IntJoukko();
        int aa[] = a.toIntArray();
        int bb[] = b.toIntArray();
        for (int i = 0; i < a.mahtavuus(); i++) {
            for (int j = 0; j < b.mahtavuus(); j++) {
                if (aa[i] == bb[j]) {
                    leikkaus.lisaa(aa[i]);
                }
            }
        }
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int aa[] = a.toIntArray();
        int bb[] = b.toIntArray();
        for (int i = 0; i < aa.length; i++) {
            erotus.lisaa(aa[i]);
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            erotus.poista(bb[i]);
        }


        return erotus;
    }
}
