// Tehtävä 2. Joe Niemi
package ohjelmointihaasteita.i;

import java.util.Scanner;

public class Kuningattaret {

    private int[][] lauta;
    private int tavat = 0;
    private static Scanner lukija = new Scanner(System.in);

    public Kuningattaret(int koko) {

        lauta = new int[koko][koko];

    }

    public void nollaaTaulu(int[][] taulu) {

        for (int rivi = 0; rivi < lauta.length; rivi++) {
            for (int sarake = 0; sarake < lauta.length; sarake++) {
                lauta[rivi][sarake] = 0;
            }
        }

    }

    public int laskeTavat(int[][] taulu) {
        int tavat = 0;
        for (int rivi = 0; rivi < lauta.length; rivi++) {
            for (int sarake = 0; sarake < lauta.length; sarake++) {
                if (lauta[rivi][sarake] == 1) {
                    tavat++;
                }
            }
        }
        return tavat;
    }

    public String tulostaTaulu(int[][] taulu) {
        String tulostus = "";

        for (int rivi = 0; rivi < lauta.length; rivi++) {
            for (int sarake = 0; sarake < lauta.length; sarake++) {
                tulostus += lauta[rivi][sarake];
            }
            tulostus += "\n";
        }
        return tulostus;
    }

    public int teeShakkilautoja(int koko) {

        int rivinPaikka = 0;
        int sarakkeenPaikka = 0;
        int x = 1;
        int y = 1;

        while (rivinPaikka < koko) {
            while (sarakkeenPaikka < koko) {

                for (int rivi = 0; rivi < lauta.length; rivi++) {
                    for (int sarake = 0; sarake < lauta.length; sarake++) {
                        lauta[rivinPaikka][sarakkeenPaikka] = 2; // näytetään 1. kuningattaren paikka


                        if (rivi > rivinPaikka) { // ylin rivi pois

                            //Jos kuningatar on [0,0]

                            if (rivinPaikka == sarakkeenPaikka && sarakkeenPaikka == 0
                                    && rivi != sarake // Viisto mahdollisuus pois
                                    && sarake != sarakkeenPaikka) { // Pysty mahdollisuus pois
                                lauta[rivi][sarake] = 1;
                            }
                            //Jos kuningatar on [1,1] tai [2,2] jne (ei [0,0])

                            if (rivinPaikka == sarakkeenPaikka && sarakkeenPaikka > 0
                                    && rivi != sarake // Viisto mahdollisuus pois
                                    && sarake != sarakkeenPaikka) { // Pysty mahdollisuus pois
                                lauta[rivi][sarake] = 1;
                                if (rivi == rivinPaikka + x && sarake == sarakkeenPaikka - x) {
                                    lauta[rivi][sarake] = 0;
                                    x++; // kasvatetaan x, "liikutaan vasemmalle ja alas"
                                }
                            }

                            if (rivinPaikka != sarakkeenPaikka // Ei oo esim [2,2] tai [3,3]
                                    && sarakkeenPaikka != sarake) { // Pysty pois
                                lauta[rivi][sarake] = 1;
                                if (sarakkeenPaikka == sarake - y && rivinPaikka == rivi - y) { // Viistosti oikealle pois
                                    lauta[rivi][sarake] = 0;
                                    y++;
                                }
                                if (sarakkeenPaikka - x == sarake && rivinPaikka + x == rivi) {
                                    lauta[rivi][sarake] = 0;
                                    x++;
                                }
                            }
                        }
                    }
                }
                x = 1;
                y = 1;
                //JOS HALUAT TULOSTAA TAULUT, AKTIVOI TÄHÄN:
                System.out.println(tulostaTaulu(lauta));
                tavat = tavat + laskeTavat(lauta);
                nollaaTaulu(lauta);
                sarakkeenPaikka++;
            }
            sarakkeenPaikka = 0;
            y = 1;
            rivinPaikka++;
        }
        return tavat;
    }

    public int getTavat() {
        return tavat;
    }

    public String toString() {

        return "Tapoja laittaa kuningatar on " + getTavat();
    }

    public static void main(String[] args) {

        System.out.println("Anna shakkilaudan koko, suurempi kuin 3");
        int koko = lukija.nextInt();

        Kuningattaret Shakki = new Kuningattaret(koko);

        Shakki.teeShakkilautoja(koko);

        System.out.println(Shakki);
    }
}
