package ohjelmointihaasteita.i;

import java.util.*;

public class HassuLukusarja {

    private static Scanner lukija = new Scanner(System.in);

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
        if (koko > 25) {
            koko = 25;
        }
        String tulostus = "1";

        for (int i = 0; i < koko; i++) {
            System.out.println(tulostus);
            String uusiTulostus = "";
            char kohta = tulostus.charAt(0);
            tulostus = tulostus.substring(1) + " ";
            int maara = 1;
            char[] rivi = tulostus.toCharArray();
            for (int j = 0; j < rivi.length; j++) {
                if (rivi[j] != kohta) {
                    uusiTulostus += maara + "" + kohta;
                    maara = 1;
                    kohta = rivi[j];
                } else {
                    maara++;
                }
            }
            tulostus = uusiTulostus;
        }
    }
}
