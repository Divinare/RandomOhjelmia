package ohJa;


import java.util.Scanner;

public class PidempiVoittaa extends Pelikehys {

    private static Scanner lukija = new Scanner(System.in);

    public boolean ekaVoittaa(String eka, String toka) {
        return eka.length() > toka.length();
    }

    public boolean tulos(String eka, String toka) {
        if (ekaVoittaa(eka, toka)) {
            System.out.println("First wins!");
           return true;
            //ekaVoittaa();
        } else {
            System.out.println("Second wins!");
            //super.tokaVoittaa();
            return false;
        }
    }

    public static void main(String[] args) {

        PidempiVoittaa peli = new PidempiVoittaa();
        String eka, toka;

        System.out.print("1. answer: ");
        eka = lukija.nextLine();
        System.out.print("2. answer: ");
        toka = lukija.nextLine();

        peli.tulos(eka, toka);
    }
}
