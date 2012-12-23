package ohJa;


import java.util.Scanner;

public class VokaaliVaiKonsonantti extends Pelikehys {

    private static Scanner lukija = new Scanner(System.in);

    public boolean ekaVoittaa(String eka, String toka) {
        if (kumpiVoittaa(eka, toka) == true) {
        return true;
        }
        return false;
    }

    public boolean kumpiVoittaa(String vastaus1, String vastaus2) {

        if ("AEIOUYÅÄÖaeiouyäåö".indexOf(vastaus1.charAt(0)) != -1
                && "AEIOUYÅÄÖaeiouyäåö".indexOf(vastaus2.charAt(0)) != -1) {
            return true;
        }
        if ("AEIOUYÅÄÖaeiouyäåö".indexOf(vastaus1.charAt(0)) == -1
                && "AEIOUYÅÄÖaeiouyäåö".indexOf(vastaus2.charAt(0)) == -1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String vastaus1 = "b";
        String vastaus2 = "a";
        VokaaliVaiKonsonantti peli = new VokaaliVaiKonsonantti();

        while (!vastaus1.equals("") && !vastaus2.equals("")) {
            System.out.print("1. pelaajan sana? (Pelkkä enter lopettaa.)");
            vastaus1 = lukija.nextLine();

            if (!vastaus1.equals("")) {
                System.out.print("2. pelaajan sana? (Pelkkä enter lopettaa.)");
                vastaus2 = lukija.nextLine();
                    peli.tulos(vastaus1, vastaus2);
            }
        }
        peli.tulostaTilastot();
    }
}