package ohJa;
import java.io.*;
import java.util.Scanner;

public class EtsiKissarivitTiedostosta {
  private static Scanner lukija = new Scanner(System.in);

   public static void main(String[] args) throws FileNotFoundException {

     System.out.println("Minkä tiedoston kissarivit haluat nähdä?");
     String tiedostonNimi = lukija.nextLine();

     File tiedostoKahva = new File(tiedostonNimi);

     if (!tiedostoKahva.exists()) {
      System.out.println("Tiedostoa "+ tiedostonNimi +" ei löydy!");
      return; // keskeytetään kaikki!
     }

     Scanner syottotiedosto = new Scanner(tiedostoKahva);

     while (syottotiedosto.hasNextLine()) {
       String rivi = syottotiedosto.nextLine();
       String apurivi = rivi.toLowerCase();   //  samaistetaan isot ja pienet
       if (apurivi.indexOf("kissa") != -1)    //  kissa löytyi
         System.out.println(rivi);
     }

     syottotiedosto.close(); // Huom.!!
  }
}