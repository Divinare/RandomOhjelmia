
import java.util.*;

public class jeejee {

    public static void main(String[] args) {
        int kysymystenMaara = 5;
        ArrayList<String[]> kyseltavat = new ArrayList<String[]>();

        for (int i = 0; i < kysymystenMaara - 1; i++) {
            String vuosi = kyseltavat.get(i)[0];
            String fakta = kyseltavat.get(i)[1];
            System.out.println((i + 1) + ".\n" + fakta);
            String vastaus = "lol";
            if (vastaus.equals(vuosi)) {
                System.out.print("Oikein! ");
            } else {
                System.out.print("Väärin! ");
            }
            System.out.println("Vastaus oli " + vuosi + ".");
        }
    }
}
