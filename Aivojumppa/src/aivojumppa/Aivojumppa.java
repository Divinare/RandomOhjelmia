package aivojumppa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Aivojumppa {

    private File tiedosto;
    private Scanner lukija1;
    private Scanner lukija2;
    private ArrayList<Character> kirjaimet;
    private ArrayList<Character> eiToivotutKirjaimet;
    private ArrayList<Sana> sanat;
    private int minimi;
    private int maksimi;

    public Aivojumppa() throws URISyntaxException {
//        URL path = ClassLoader.getSystemResource("sanalista.txt");
//        if (path == null) {
//            System.out.println("ei löytynyt sanalistaaa :((");
//            //The file was not found, insert error handling here
//        }
//        this.tiedosto = new File(path.toURI());
        this.tiedosto  = new File("./build/classes/sanalista.txt");
//        this.tiedosto = new File("sanalista.txt");
        this.lukija1 = new Scanner(System.in);
        this.kirjaimet = new ArrayList();
        this.eiToivotutKirjaimet = new ArrayList();
        this.sanat = new ArrayList();
    }

    public void aloita() throws FileNotFoundException {
        kysyRajoitukset();
        kysyKirjaimet();
        luoEiToivotutKirjaimetLista();
        muodostaSanat();
        tulostaSanat();
    }

    private void muodostaSanat() throws FileNotFoundException {
        this.lukija2 = new Scanner(tiedosto);
        while (lukija2.hasNextLine()) {
            String sana = lukija2.nextLine();
            if (sana.length() >= minimi && sana.length() <= maksimi
                    && !onkoEiToivottujaKirjaimia(sana)
                    && samojaKirjaimiaSallittuMaara(sana)) {
                Sana s = new Sana(sana);
                sanat.add(s);
            }
        }
        Collections.sort(sanat);
    }

    private boolean samojaKirjaimiaSallittuMaara(String sana) {
        for (int i = 0; i < sana.length(); i++) {
            if (haeKirjaintenMaaraAnnetustaSanasta(sana.charAt(i), sana) > haeKirjaintenMaaraArrayLististä(sana.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private int haeKirjaintenMaaraAnnetustaSanasta(char k, String sana) {
        int maara = 0;
        for (int i = 0; i < sana.length(); i++) {
            if (sana.charAt(i) == k) {
                maara++;
            }
        }
        return maara;
    }

    private int haeKirjaintenMaaraArrayLististä(char k) {
        int maara = 0;
        for (int i = 0; i < kirjaimet.size(); i++) {
            if (kirjaimet.get(i) == k) {
                maara++;
            }
        }
        return maara;
    }

    private void tulostaSanat() {
        for (int i = 0; i < sanat.size(); i++) {
            System.out.println(sanat.get(i));
        }
    }

    private boolean onkoEiToivottujaKirjaimia(String sana) {
        for (int i = 0; i < sana.length(); i++) {
            if (eiToivotutKirjaimet.contains(sana.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private void kysyKirjaimet() {
        while (true) {
            System.out.println("Anna kirjain, tyhjä lopettaa: ");
            String s = lukija1.nextLine();
            if (s.length() == 0) {
                break;
            }
            char c = s.charAt(0);
            kirjaimet.add(c);
        }
        System.out.println("Muodostetaan sanoja kirjaimista: ");
        for (int i = 0; i < kirjaimet.size(); i++) {
            System.out.print(kirjaimet.get(i) + " ");
        }
        System.out.println("");
    }

    private void luoEiToivotutKirjaimetLista() {
        String aakkosto = "abcdefghijklmnopqrstuvwxyzåäö";
        for (int i = 0; i < aakkosto.length(); i++) {
            if (!kirjaimet.contains(aakkosto.charAt(i))) {
                eiToivotutKirjaimet.add(aakkosto.charAt(i));
            }
        }
    }

    private void kysyRajoitukset() {
        System.out.println("Anna sanan minimipituus: ");
        this.minimi = Integer.parseInt(lukija1.nextLine());
        System.out.println("Anna sanan maksimipituus: ");
        this.maksimi = Integer.parseInt(lukija1.nextLine());
    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        Aivojumppa peli = new Aivojumppa();
        peli.aloita();
    }
}
