package morkopeli;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Tekstinlukija {

    public Tekstinlukija() {
    }
    
    public String[][] read(String luettava) throws IOException {
        StringBuilder text = new StringBuilder();
        String NL = System.getProperty("line.separator");
        
        FileInputStream fileolio = new FileInputStream(luettava);
     //   File f = new File(luettava);
       
//        Scanner scanner = new Scanner(new FileInputStream(luettava));
        Scanner scanner = new Scanner(fileolio);
        
        try {
            int i = 0;
            while (scanner.hasNext()) {
                String luettu = scanner.nextLine();
                text.append(luettu + NL);
            }
        } finally {
            scanner.close();
        } // int r = (int)Math.sqrt(a);
        String[][] taulu = new String[(int)Math.sqrt(text.length())][(int)Math.sqrt(text.length())];
        Scanner scan = new Scanner(text.toString());
        int i = 0;
        while (scan.hasNextLine()) {
            String rivi = scan.nextLine();
            for (int j = 0; j < rivi.length(); j++) {
                taulu[i][j] = "" + rivi.charAt(j);
            }
            i++;

        }
        return taulu;
    }
}
