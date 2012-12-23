// Tehtävä 1. Joe Niemi
package ohjelmointihaasteita.i;

import java.util.Scanner;

public class Shakkilauta {

    private static Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Anna shakkilaudan koko");
        int laudanKoko = lukija.nextInt();
        int seuraavaLuku = 0;

        for (int i = 0; i < laudanKoko; i++) {
            int rivinPituus = 0;
            for (int j = 0; j < laudanKoko; j++) {

                if (seuraavaLuku % 2 == 1) {
                    if (rivinPituus < laudanKoko) {
                        System.out.print("1");
                    }
                    rivinPituus++;
                    if (rivinPituus < laudanKoko) {
                        System.out.print("0");
                    }
                    rivinPituus++;
                }
                if (seuraavaLuku % 2 != 1) {
                    if (rivinPituus < laudanKoko) {
                        System.out.print("0");
                    }
                    rivinPituus++;
                    if (rivinPituus < laudanKoko) {
                        System.out.print("1");
                    }
                    rivinPituus++;
                }
            }
            System.out.println();
            seuraavaLuku++;
        }
    }
}