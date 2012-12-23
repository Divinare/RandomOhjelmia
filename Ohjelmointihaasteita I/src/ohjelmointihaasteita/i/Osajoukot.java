package ohjelmointihaasteita.i;

import java.util.*;

public class Osajoukot {

    private static Scanner lukija = new Scanner(System.in);

    public static void muodosta(String mj, int koko) {
        String tulostus = "";
        int[] tauluLuku = new int[koko];
        for (int i = 1; i <= koko; i++) {
            tauluLuku[i - 1] = (i);
        }
        char[] tauluBin = mj.toCharArray();
        for (int i = 0; i < tauluBin.length; i++) {
            if (tauluBin[i] == '1') {
                tulostus += "" + tauluLuku[i] + " ";
            } else if (tauluBin[i] == 0) {
                tulostus += "" + -1;
            }
        }
        System.out.println(tulostus);
    }

    public static void main(String[] args) {
        String mj = "";
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
        for (int i = 0; i < Math.pow(2, koko); i++) {
            mj = Integer.toBinaryString(i);
            // System.out.println(mj);
            while (mj.length() != koko) {
                mj = "0" + mj;
            }
            muodosta(mj, koko);
        }
    }
}