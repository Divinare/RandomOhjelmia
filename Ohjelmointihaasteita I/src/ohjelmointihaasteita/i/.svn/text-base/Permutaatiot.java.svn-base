package ohjelmointihaasteita.i;

import java.util.Scanner;

public class Permutaatiot {

    private static Scanner lukija = new Scanner(System.in);
    private static int maarax = 1;

    public static void muodosta(int maara, String numerot, int koko) {
        // onko määrä liian suuri?
        int luku = 0;

        if (maara > koko + (maarax - 1)) {
            return;
        }
        // onko määrä oikea?
        if (maara == koko + (maarax - 1)) {
            for (int i = 0; i < koko - 1; i++) {
                String kirjain = String.valueOf(i);
                for (int j = i + 1; j <= koko; j++) {
                    String kirjain2 = String.valueOf(j);
                    if (!numerot.contains(kirjain) && !numerot.contains(kirjain2)) {
                        luku++;
                    } else {
                    }
                }
            }
            if (luku < 1) {
                System.out.println(numerot);
            }
            return;
        }
        for (int i = 1; i <= koko; i++) {
            muodosta(maara + 1, numerot + " " + (i), koko);
        }
    }

    public static void main(String[] args) {

        int koko;
        System.out.println("Anna koko ");

        while (true) {
            if (lukija.hasNextInt()) {
                koko = lukija.nextInt();
                break;
            } else {
                System.out.println("Antamasi syöte ei ollut kokonaisluku. Yritä uudelleen.");
                lukija.next();
            }
        }
        for (int i = 1; i <= koko; i++) {
            String string = Integer.toString(i);
            muodosta(i, string, koko);
            maarax++;
        }
    }
}