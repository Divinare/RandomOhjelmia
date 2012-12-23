package ohJa;


import java.io.*;
import java.util.*;

public class Testilaboratorio {

    private static String tiedostonNimi;
    private static File tiedosto, suurennosTmp;
    private static Scanner filu;
    private static Scanner lukija = new Scanner(System.in);
    private static PrintWriter tallennettava, p;//suurennoksen kirjoittamista varten

    public static void lueTiedosto() {
        System.out.print("Mikä?");
        //lukija.nextLine();    
        tiedostonNimi = lukija.nextLine();
        //  filu = new Scanner(tiedostonNimi);//eka luodaan file olio, jonka scanner syö!
        try {
            tiedosto = new File(tiedostonNimi);
            filu = new Scanner(tiedosto);//ja filu on se scanner jolla sit luetaan, kuten lukija mut lukee tiedostosta.
            //tiedosto = new File(tiedostonNimi);
        } catch (Exception e) {
            if (!tiedosto.exists()) {
                System.out.println("Ei tiedostoa " + tiedostonNimi);
            }
        }
    }

    public static void lueTiedosto(File t) {

        try {
            filu = new Scanner(t);//ja filu on se scanner jolla sit luetaan, kuten lukija mut lukee tiedostosta.
            //tiedosto = new File(tiedostonNimi);
        } catch (Exception e) {
            if (!tiedosto.exists()) {
                System.out.println("Ei tiedostoa " + tiedostonNimi);
            }
        }
    }

    private static void tallenna() {
        System.out.print("Millä nimellä talteen? ");
        String tallNimi = lukija.nextLine();
        if (tallNimi.equals(tiedostonNimi)) {
            return; //tiedoston nimi ei muutu
        } else {
            File nimi = new File(tallNimi);//luodaan nimi niminen file olio joka luo koneelle tallNimi -nimisen tiedoston tai luo jo olemassa olevsta oliosta fileolion
            if (nimi.exists()) {//jos se on tarkisteetaan halutaanko korvata
                System.out.println(tallNimi + " on jo olemassa. Korvataanko se? (k=kyllä)");
                String vastaus = lukija.nextLine();
                if (!vastaus.equalsIgnoreCase("k")) {
                    System.out.println("Tiedostoon ei koskettu!");
                    return;
                }
            }//tiedosto joko haluttiin korvata tai se luo uuden tiedoston.
            try {
                tallennettava = new PrintWriter(nimi);//Scanner syö fileolion - joka on nyt nimeltään nimi - File nimi = new File(tallNimi);
            } catch (FileNotFoundException ex) {
                System.out.println("Virhe tallennuksessa.");
            }
            while (filu.hasNext()) {
                String kopio = filu.nextLine();
                System.out.println("siirrettävä: " + kopio);
                tallennettava.println(kopio);
            }

            tiedostonNimi = tallNimi;
            tallennettava.close();
            return;
        }
    }

    public static boolean lopeta() {
// Kannattaa luoda boolean muuttuja sekä Suurennetuille että numeroiduille tiedostoille ja kattoa, niillä pitää kirjaa, et ono tallennettu.
        System.out.println("Tiedostoa ei ole tallennettu. Lopetetaanko silti? (k=kyllä)");
        String lopetetaanko = lukija.nextLine();
        if (!lopetetaanko.equalsIgnoreCase("k")) {
            return false;
        }
        return true;
    }

    public static void laskeMerkit() {
        int kirjaimet = 0;

        while (filu.hasNextLine()) {
            String vastaus = filu.nextLine();
            kirjaimet += vastaus.length();
        }
        System.out.println(kirjaimet);
    }

    public static void suurenna() {
        //Luodaan private static muuttujaksi tmp.tekstitiedosto suurennosTmp
        try {
            suurennosTmp = new File("suurennosTmp.txt");
            suurennosTmp.deleteOnExit(); //poistetaan väliaikais filu.
            //Nyt tarret tallenna methodin, joka osaa tallentaa ton suurennosTmp fileolion käyttäjän antamalla nimellä loin itse toisen lähes identtisen tallenna methodin.
            p = new PrintWriter(suurennosTmp);
        } catch (Exception e) {
            System.out.println("Virhe, jota ei pitäisi koskaan tulla :P ");
        }
        while (filu.hasNext()) {
            String rivi = filu.nextLine();
            rivi = rivi.toUpperCase();
            System.out.println(rivi);
            p.println(rivi);
            //Tän pitäis tulostaa se suurennos mun ymmärryksen mukaan tiedostoon

        }
        p.close();
        lueTiedosto(suurennosTmp);
        return;
    }

    public static void suodata() {
        //Luodaan private static muuttujaksi tmp.tekstitiedosto suurennosTmp
        try {
            suurennosTmp = new File("suurennosTmp.txt");
            suurennosTmp.deleteOnExit(); //poistetaan väliaikais filu.
            //Nyt tarret tallenna methodin, joka osaa tallentaa ton suurennosTmp fileolion käyttäjän antamalla nimellä loin itse toisen lähes identtisen tallenna methodin.
            p = new PrintWriter(suurennosTmp);
        } catch (Exception e) {
            System.out.println("Virhe, jota ei pitäisi koskaan tulla :P ");
        }
        while (filu.hasNext()) {
            System.out.println("Anna suodatinsana!");
            String sana = lukija.nextLine();
            while (filu.hasNextLine()) {
                String rivi = filu.nextLine();
                if (rivi.contains(sana)) {
                    System.out.println(rivi);
                    p.println(rivi);
                }
            }
            //Tän pitäis tulostaa se suurennos mun ymmärryksen mukaan tiedostoon
        }
        p.close();
        lueTiedosto(suurennosTmp);
        return;
    }

    public static void numeroi() {
        //Luodaan private static muuttujaksi tmp.tekstitiedosto suurennosTmp
        int rivinPaikka = 1;
        try {
            suurennosTmp = new File("suurennosTmp.txt");
            suurennosTmp.deleteOnExit(); //poistetaan väliaikais filu.
            //Nyt tarret tallenna methodin, joka osaa tallentaa ton suurennosTmp fileolion käyttäjän antamalla nimellä loin itse toisen lähes identtisen tallenna methodin.
            p = new PrintWriter(suurennosTmp);
        } catch (Exception e) {
            System.out.println("Virhe, jota ei pitäisi koskaan tulla :P ");
        }
        while (filu.hasNext()) {
            String rivi = filu.nextLine();
            rivi = rivinPaikka + ": " + rivi;
            System.out.println(rivi);
            rivinPaikka++;
            p.println(rivi);
            //Tän pitäis tulostaa se suurennos mun ymmärryksen mukaan tiedostoon
        }
        p.close();
        lueTiedosto(suurennosTmp);
        return;
    }

    public static void main(String[] args) throws FileNotFoundException {
        tiedostonNimi = "Ei tiedostoa";
        int valinta = 0;

        while (valinta != 3) {
            System.out.println(tiedostonNimi);
            System.out.println("1 lue" + "\n" + "2 tallenna" + "\n"
                    + "3 lopeta" + "\n" + "4 merkkejä" + "\n" + "5 suurenna"
                    + "\n" + "6 suodata" + "\n" + "7 numeroi");

            valinta = lukija.nextInt();
            lukija.nextLine();

            if (valinta == 1) {
                lueTiedosto();
            } else if (valinta == 2) {
                tallenna();
            } else if (valinta == 3) {
                if (lopeta() == false) {
                    valinta = 0;
                }
            } else if (valinta == 4) {
                laskeMerkit();
            } else if (valinta == 5) {
                suurenna();
            } else if (valinta == 6) {
                suodata();
            } else if (valinta == 7) {
                numeroi();
            } else {
                System.out.println("Virheellinen komento!");
            }
        }
    }
}