
import java.util.Scanner;

public class Palindromit {

    private static String kirjaimet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void palindromit(int kirjaimia, int pituus) {

            muodosta("", kirjaimia, pituus, 0);
//            muodostaToisinpain("", pituus, kirjaimia, pituus - 1);

    }

    public static void muodosta(String sana, int kirjaimia, int pituus, int kohta) {


        if (kohta == pituus / 2) {
            System.out.println(sana);
//            muodostaToisinpain(sana, pituus, kirjaimia, kohta - 1);
            return;
        }
        for (int i = 0; i < kirjaimia; i++) {
            muodosta(sana+kirjaimet.charAt(i), kirjaimia, pituus, kohta + 1);
        }
    }

//    public static void muodostaToisinpain(String sana, int pituus, int kirjaimia, int kohta) {
//        if (sana.length() == pituus) {
//            if (palindromi(sana)) {
//            System.out.println(sana);
//            }
//            return;
//        }
//        for (int i = kirjaimia-1; i >= 0; i--) {
//        muodostaToisinpain(sana+kirjaimet.charAt(i), pituus, kirjaimia, kohta - 1);
//        }
//    }
    
        static boolean palindromi(String sana) {
        String vertailtava = "";
        for (int i = sana.length()-1; i >= 0; i--) {
            vertailtava = vertailtava + sana.charAt(i);
        }
        if (sana.equalsIgnoreCase(vertailtava)) {
        return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Kirjaimia? ");
        int kirjaimia = sc.nextInt();
        System.out.print("Pituus? ");
        int pituus = sc.nextInt();

        palindromit(kirjaimia, pituus);
    }
}