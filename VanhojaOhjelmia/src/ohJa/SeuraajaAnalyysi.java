package ohJa;

//2.5
import java.io.File;
import java.util.Scanner;
 
public class SeuraajaAnalyysi {
   
    public static void main(String[] args) {
        SananSeuraajat seuraajat = new SananSeuraajat();
        Sanasuodatin suodatin;
//         Scanner luk;
        File f;
        String nimi = "alku";
        Scanner l = new Scanner(System.in);
        String eka, toka, tutkittava = "öö";
        while (!nimi.equals("")) {
            System.out.println("Minkä tiedoston sanojen seuraajat haluat tutkia? Tyhjä lopettaa.");
            while (l.hasNext()) {
                nimi = l.nextLine();
                try {
                    f = new File(nimi);
                    suodatin = new Sanasuodatin(f);
                } catch (Exception e) {
                    System.out.println("Tiedostoa " + nimi + " Ei löydy!");
                    return;
                }
                suodatin.isotPieniksi(true);
                eka = suodatin.seuraava();
                while (suodatin.onVielaSanoja()) {
                    suodatin.isotPieniksi(true);
                    toka = suodatin.seuraava();
                    seuraajat.lisaaSanalleSeuraaja(eka, toka);
                    eka = toka;
//                    System.out.println(eka);
//                    System.out.println(toka);
                }
                while (true) {
                    System.out.println("Minkä sanan seuraajat haluat tietaa? Tyhjä lopettaa.");
                    tutkittava = l.nextLine();
                    if (tutkittava.equals("")) {
                        break;
                    }
                    System.out.println(seuraajat.mitkaSeuraavatSanaa(tutkittava));
                }
                System.out.println("Aloitetaanko alusta? k == kyllä");
            }
            break;
           
           
           
        }
       
    }
}