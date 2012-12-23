package morkopeli;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Tekstinlukija {

    public Tekstinlukija() {
    }
    
    public String[][] read(String luettava) throws IOException {
        StringBuilder text = new StringBuilder();
        String NL = System.getProperty("line.separator");
        Scanner scanner = new Scanner(new FileInputStream(luettava));
        try {
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine() + NL);
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
