package ohJa;


import java.util.Scanner;

public class Onnetar extends Pelikehys {

    private static Scanner lukija = new Scanner(System.in);

    public boolean ekaVoittaa(String eka, String toka) {
        return Math.random() < 0.5;  // arvotaan voittaja,
                                     // molemmat yhtä todennäköisiä
    }

    public static void main(String[] args) {

        Onnetar peli = new Onnetar();

        System.out.print("1. pelaajan vastaus: ");
        lukija.nextLine();   // vastauksella ei väliä!
        System.out.print("2. pelaajan vastaus: ");
        lukija.nextLine();

        peli.tulostaTulos("", "");  // vastauksilla ei väliä!
    }
}
