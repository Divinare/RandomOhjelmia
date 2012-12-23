package ohJa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
public class Sanasuodatin {
   
    private File tiedosto;
    private Scanner lukija;
    private boolean pieniksi;
 
    public Sanasuodatin(File luettava) {
        this.tiedosto = luettava;
        try {
            lukija = new Scanner(tiedosto);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy.");
            System.exit(0);
        }
    }
   
    private static String poistaMerkit(String sana) {
        String vastaus;
        int alkuindeksi = 0; 
        while (!Character.isLetter(sana.charAt(alkuindeksi))) {
            alkuindeksi++;
        }   
        int loppuindeksi = sana.length() - 1;    
        while (!Character.isLetter(sana.charAt(loppuindeksi))) {
            loppuindeksi--;
        }
        vastaus = sana.substring(alkuindeksi, loppuindeksi + 1);
        return vastaus;
    }
   
    public String seuraava() {
        String seuraava = lukija.next();
        String valmisSana = poistaMerkit(seuraava);
        if (pieniksi == true) {
            return valmisSana.toLowerCase();
        }
        return valmisSana;
    }
   
    public boolean onVielaSanoja() {
        return lukija.hasNext();
    }
   
    public void isotPieniksi(boolean jooko) {
       
        pieniksi = jooko;
       
//        if (jooko == true) {
//            pieniksi = true;
//        }
//        else {
//            pieniksi = false;
//        }
    }
   
}