/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joe
 */
public class testi {

    public static void tulostaBinaarina(int n) {
        if (n == 0) {
            return;
        }
        System.out.println("OK " + n + " ja N/2 on " + n/2);
        tulostaBinaarina(n / 2);
        System.out.print(n % 2);
    }

    public static void main(String[] args) {
        tulostaBinaarina(20);
        System.out.println("LOL" + 0 % 2);
        System.out.println("LOL" + 1 % 2);
    }
}
