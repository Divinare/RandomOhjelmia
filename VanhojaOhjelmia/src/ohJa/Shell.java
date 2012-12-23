package ohJa;

import java.util.Scanner;
 
public class Shell {
    private static Scanner laite = new Scanner(System.in);
   
    public static String kysyString(String kysymys) {
        System.out.print(kysymys);
        while (true) {
            try {
                return laite.nextLine();
            } catch (java.util.NoSuchElementException e) {
                return null;
            } catch (IllegalStateException e) {
                continue;
            }
        }
    }
   
    public static int kysyInt(String kysymys) {
        while (true) {
            String vastaus = kysyString(kysymys).trim();
            try {    
                return Integer.parseInt(vastaus);
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }
   
    public static int kysyInt(int alaraja, String kysymys) {
        int vastaus = kysyInt(kysymys);
        while (vastaus < alaraja) {
            vastaus = kysyInt(kysymys);
        }
        return vastaus;
    }
   
    public static int kysyInt(String kysymys, int ylaraja) {
        int vastaus = kysyInt(kysymys);
        while (vastaus > ylaraja) {
            vastaus = kysyInt(kysymys);
        }
        return vastaus;
    }
   
    public static int kysyInt(int alaraja, String kysymys, int ylaraja) throws IllegalArgumentException {
        if (alaraja > ylaraja) throw new IllegalArgumentException("Tyhjä lukualue!");
        int vastaus = kysyInt(kysymys);
        while ((vastaus > ylaraja) || (vastaus < alaraja)) {
            vastaus = kysyInt(kysymys);
        }
        return vastaus;
    }
   
    public static double kysyDouble(String kysymys) {
        while (true) {
            String vastaus = kysyString(kysymys);
            try {    
                return Double.parseDouble(vastaus);
            } catch (NumberFormatException e) {
                continue;
            }
        }
    }
}