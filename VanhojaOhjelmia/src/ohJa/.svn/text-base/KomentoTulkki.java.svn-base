package ohJa;
import java.util.Scanner;

public class KomentoTulkki {

    private static Scanner lukija = new Scanner(System.in);
    private static IntJoukko A = new IntJoukko();
    private static IntJoukko B = new IntJoukko();
    private static IntJoukko C = new IntJoukko();

    private static void poista() {
    }

    private static void lisaa() {
        System.out.println("Mihin joukkoon?");
        System.out.print("?> ");
        String mihin = lukija.next();
        System.out.println("Mikä luku lisätään?");
        System.out.print("?> ");
        int luku = lukija.nextInt();
        lukija.nextLine();

        if (mihin.equalsIgnoreCase("A")) {
            A.lisaa(luku);
        }
        if (mihin.equalsIgnoreCase("B")) {
            B.lisaa(luku);
        }
        if (mihin.equalsIgnoreCase("C")) {
            C.lisaa(luku);
        }
    }

    private static void kuuluu() {

        System.out.println("Mihin joukkoon?");
        System.out.print("?> ");
        String mihin = lukija.next();
        System.out.println("Mikä luku?");
        System.out.print("?> ");
        int luku = lukija.nextInt();
        lukija.nextLine();

        if (mihin.equalsIgnoreCase("A") && A.kuuluu(luku) == true) {
            System.out.println(luku + " kuuluu joukkoon A");
        } else if (mihin.equalsIgnoreCase("A") && A.kuuluu(luku) == false) {
            System.out.println(luku + " ei kuulu joukkoon A");
        } else if (mihin.equalsIgnoreCase("B") && B.kuuluu(luku) == true) {
            System.out.println(luku + " kuuluu joukkoon B");
        } else if (mihin.equalsIgnoreCase("B") && B.kuuluu(luku) == false) {
            System.out.println(luku + " ei kuulu joukkoon B");
        } else if (mihin.equalsIgnoreCase("C") && C.kuuluu(luku) == true) {
            System.out.println(luku + " kuuluu joukkoon C");
        } else if (mihin.equalsIgnoreCase("C") && C.kuuluu(luku) == false) {
            System.out.println(luku + " ei kuulu joukkoon C");
        }
    }

    private static void yhdiste() {

        IntJoukko ekaJoukko = new IntJoukko();
        IntJoukko tokaJoukko = new IntJoukko();

        System.out.println("Mikä on ensimmäinen joukko?");
        System.out.print("?> ");
        String eka = lukija.next();
        lukija.nextLine();
        System.out.println("Mikä on toinen joukko?");
        System.out.print("?> ");
        String toka = lukija.next();
        lukija.nextLine();


        if (eka.equalsIgnoreCase("A")) {
            ekaJoukko = A;
        }
        if (eka.equalsIgnoreCase("B")) {
            ekaJoukko = B;
        }
        if (eka.equalsIgnoreCase("C")) {
            ekaJoukko = C;
        }
        if (toka.equalsIgnoreCase("A")) {
            tokaJoukko = A;
        }
        if (toka.equalsIgnoreCase("B")) {
            tokaJoukko = B;
        }
        if (toka.equalsIgnoreCase("C")) {
            tokaJoukko = C;
        }

        System.out.println("Joukkojen " + eka + " ja " + toka + " yhdiste on " + JoukkoOp.yhdiste(ekaJoukko, tokaJoukko));

    }

    private static void leikkaus() {

        IntJoukko ekaJoukko = new IntJoukko();
        IntJoukko tokaJoukko = new IntJoukko();

        System.out.println("Mikä on ensimmäinen joukko?");
        System.out.print("?> ");
        String eka = lukija.next();
        lukija.nextLine();
        System.out.println("Mikä on toinen joukko?");
        System.out.print("?> ");
        String toka = lukija.next();
        lukija.nextLine();

        if (eka.equalsIgnoreCase("A")) {
            ekaJoukko = A;
        }
        if (eka.equalsIgnoreCase("B")) {
            ekaJoukko = B;
        }
        if (eka.equalsIgnoreCase("C")) {
            ekaJoukko = C;
        }
        if (toka.equalsIgnoreCase("A")) {
            tokaJoukko = A;
        }
        if (toka.equalsIgnoreCase("B")) {
            tokaJoukko = B;
        }
        if (toka.equalsIgnoreCase("C")) {
            tokaJoukko = C;
        }

        System.out.println("Joukkojen " + eka + " ja " + toka + " leikkaus on " + JoukkoOp.leikkaus(ekaJoukko, tokaJoukko));

    }

    public static void main(String[] args) {

        String valinta = "";

        System.out.println("Tervetuloa joukkolaboratorioon!" + "\n"
                + "Käytössäsi ovat joukot A, B ja C." + "\n"
                + "Komennot ovat lisää, poista, kuuluu, yhdiste, leikkaus ja lopeta." + "\n"
                + "Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");


        while (!valinta.equals("lopeta")) {
            System.out.print("?> ");
            valinta = lukija.nextLine();
            if (valinta.equalsIgnoreCase("lisää")) {
                lisaa();
            }
            if (valinta.equalsIgnoreCase("kuuluu")) {
                kuuluu();
            }
            if (valinta.equalsIgnoreCase("yhdiste")) {
                yhdiste();
            }
            if (valinta.equalsIgnoreCase("leikkaus")) {
                leikkaus();
            }
            if (valinta.equalsIgnoreCase("A")) {
                System.out.println(A);
            }
            if (valinta.equalsIgnoreCase("B")) {
                System.out.println(B.toString());
            }
            if (valinta.equalsIgnoreCase("C")) {
                System.out.println(C.toString());
            }
        }
    }
}
