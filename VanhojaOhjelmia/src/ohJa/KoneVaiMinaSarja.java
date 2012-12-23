package ohJa;


import java.util.Scanner;
public class KoneVaiMinaSarja extends Pelikehys {
    private static Scanner lukija = new Scanner(System.in);

  public boolean ekaVoittaa(String eka, String toka) {
     return Math.random() < 0.5;  
  }

    public static void main(String[] args) {

    KoneVaiMina peli = new KoneVaiMina();
    while (true) {
        System.out.print("Kumpi voittaa, sinä vai minä? Enter jatkaa, muu lopettaa!");
        String syote = lukija.nextLine();
        if (!syote.isEmpty()) break;
        boolean ekaVoitti = peli.tulos(null, null);
        if (ekaVoitti) {
            System.out.println("Minä voitin! Tilanne "+peli.ekallaVoittoja()+"-"
                    +peli.tokallaVoittoja());
        }
        else
            System.out.println("Sinä voitit! Tilanne "+peli.ekallaVoittoja()+"-"+peli.tokallaVoittoja());
    }
    peli.tulostaTilastot();
  }
}

//
//import java.util.Scanner;
//
//public class KoneVaiMinaSarja extends Pelikehys {
//
//    private static Scanner lukija = new Scanner(System.in);
//
//    public boolean ekaVoittaa(String eka, String toka) {
//        if (Math.random() < 0.5) {
//            System.out.print("Minä voitin! ");
//            return true;
//        }
//        System.out.print("Sinä voitit! ");
//        return false;
//    }
//
//    public static void main(String[] args) {
//
//        KoneVaiMinaSarja peli = new KoneVaiMinaSarja();
//        //Onnetar peli = new Onnetar();
//        String jatkuuko = "";
//        while (jatkuuko.equals("")) {
//            System.out.print("Kumpi voittaa, sinä vai minä? Enter jatkaa, muu lopettaa!");
//            jatkuuko = lukija.nextLine();
//            peli.tulos("", "");
//            //System.out.println("Tilanne " + peli.ekallaVoittoja() + "-" + peli.tokallaVoittoja());
//        }
//        peli.tulostaTilastot();
//    }
//}
