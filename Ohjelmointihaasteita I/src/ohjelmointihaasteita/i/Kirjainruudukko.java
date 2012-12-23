// Tehtävä 3. Joe Niemi
package ohjelmointihaasteita.i;

import java.util.Scanner;

public class Kirjainruudukko {

    private int ruudukko[][];
    private static Scanner lukija = new Scanner(System.in);

    public void teeRuudukko(int koko) {

        int etaisyysX, etaisyysY;
        
        for(int rivi = 0; rivi < koko*2+1;rivi++) { // Ruudukon leveys on 2x koko+1
            for(int sarake = 0; sarake < koko*2+1;sarake++ ) { // Ruudukon korkeus on 2x koko+1
                etaisyysX = Math.abs(rivi - koko); // Rivin etäisyys keskustasta itseisarvo
                etaisyysY = Math.abs(sarake - koko); // Sarakkeen etäisyys keskustasta itseisarvo
                if (etaisyysX > etaisyysY) { // Jos rivin etäisyys on kauempana kuin sarakkeen
                    System.out.print((char) (65+etaisyysX));
                } else {
                    System.out.print((char) (65+etaisyysY));
                }
            }
            System.out.println();
        }
 
    }

    public static void main(String[] args) {


        System.out.print("Kirjainmäärä: ");
        int maara = lukija.nextInt();
        maara = maara-1;
        
        Kirjainruudukko Ruudukko = new Kirjainruudukko();
        Ruudukko.teeRuudukko(maara);
        
        System.out.print(Math.max(-5, 2));
    }
}