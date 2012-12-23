package ohjelmointihaasteita.i;
import java.util.Scanner;
 
public class Shakki {
 
    public static Scanner lukija = new Scanner(System.in);
    private int[][] lauta;
 
    public Shakki(int koko) {
 
        lauta = new int[koko][koko];
    }
 
    public void maalaaLauta() {
 
        for (int rivi = 0; rivi < lauta.length; rivi++) {
            for (int sarake = 0; sarake < lauta.length; sarake++) {
 
                if (rivi % 2 == 0 && sarake % 2 == 0) {
                    lauta[rivi][sarake] = 1;
                } else if (rivi % 2 != 0 && sarake % 2 != 0) {
                    lauta[rivi][sarake] = 1;
                }
            }
        }
    }  
 
    public String toString() {
 
        String tuloste = "";
 
        for (int rivi = 0; rivi < lauta.length; rivi++) {
            for (int sarake = 0; sarake < lauta.length; sarake++) {
                tuloste += lauta[rivi][sarake];
            }
            tuloste += "\n";
        }
        return tuloste;
    }
 
    public static void main(String[] args) {
 
        int koko;
        System.out.println("Minkä kokoisen laudan haluat?");
 
        while (true) {
            if (lukija.hasNextInt()) {
                koko = lukija.nextInt();
                break;
            } else {
                System.out.println("Antamasi syöte " + lukija.next()
                        + " ei kelpaa taulukon kooksi. Yritä uudelleen.");
            }
        }
        Shakki peli = new Shakki(koko);
        peli.maalaaLauta();
        System.out.println(peli);
    }
}