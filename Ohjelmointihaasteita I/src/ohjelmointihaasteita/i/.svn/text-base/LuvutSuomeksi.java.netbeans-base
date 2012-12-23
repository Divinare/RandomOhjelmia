// Tehtävä 4. Joe Niemi
package ohjelmointihaasteita.i;

import java.util.Scanner;

public class LuvutSuomeksi {

    private static Scanner lukija = new Scanner(System.in);

    public static String lueLukuKymmenet(int luku) {

        String teksti = "";

        if (luku < 10 && luku > 0) {
            teksti = sanoLuku(luku);
        }
        if (luku > 9 && luku < 20) {
            teksti = sanoLukuToistat(luku);
        }
        if (luku % 10 == 0 && luku != 10 && luku > 0) {
            teksti = sanoLuku(luku / 10) + "kymmentä";
        }
        if (luku > 19 && luku < 100 && luku % 10 != 0 && luku > 0) {
            teksti = sanoLuku(luku / 10) + "kymmentä" + sanoLuku(luku - ((luku / 10) * 10));
        }
        return teksti;
    }

    public static String lueLukuSadat(int luku) {
        String teksti = "";

        if (luku < 200 && luku >= 100) {
            teksti = "sata";
        }
        if (luku >= 200) {
            teksti = sanoLuku(luku / 100) + "sataa";
        }
        return teksti;
    }

    public static String lueLukuTuhannet(int luku) {
        String teksti = "";
        if (luku < 2000 && luku >= 1000) {
            teksti = "tuhat";
        }
        if (luku >= 2000) {
            teksti = sanoLuku(luku / 1000) + "tuhatta";
        }
        return teksti;
    }

    public static String sanoLuku(int luku) {
        String numero = "";
        if (luku == 0) {
            numero = "nolla";
        }
        if (luku == 1) {
            numero = "yksi";
        }
        if (luku == 2) {
            numero = "kaksi";
        }
        if (luku == 3) {
            numero = "kolme";
        }
        if (luku == 4) {
            numero = "neljä";
        }
        if (luku == 5) {
            numero = "viisi";
        }
        if (luku == 6) {
            numero = "kuusi";
        }
        if (luku == 7) {
            numero = "seitsemän";
        }
        if (luku == 8) {
            numero = "kahdeksan";
        }
        if (luku == 9) {
            numero = "yhdeksän";
        }
        return numero;
    }

    public static String sanoLukuToistat(int luku) {
        String numero = "";
        if (luku == 10) {
            numero = "kymmenen";
        }
        if (luku == 11) {
            numero = "yksitoista";
        }
        if (luku == 12) {
            numero = "kaksitoista";
        }
        if (luku == 13) {
            numero = "kolmetoista";
        }
        if (luku == 14) {
            numero = "neljätoista";
        }
        if (luku == 15) {
            numero = "viisitoista";
        }
        if (luku == 16) {
            numero = "kuusitoista";
        }
        if (luku == 17) {
            numero = "seitsemäntoista";
        }
        if (luku == 18) {
            numero = "kahdeksantoista";
        }
        if (luku == 19) {
            numero = "yhdeksäntoista";
        }
        return numero;
    }

    public static void main(String[] args) {

        System.out.println("Anna luku: ");
        int luku = lukija.nextInt();

        if (luku < 10) {
            System.out.println(sanoLuku(luku));
        }
        if (luku < 100 && luku >= 10) {
            System.out.println(lueLukuKymmenet(luku));
        }
        if (luku < 1000 && luku >= 100 && luku % 100 != 0) {
            System.out.print(lueLukuSadat(luku));
            System.out.print(lueLukuKymmenet(luku - ((luku / 100) * 100)));
        }
        if (luku < 1000 && luku >= 100 && luku % 100 == 0) {
            System.out.print(lueLukuSadat(luku));
        }
        if (luku < 10000 && luku >= 1000) {
            System.out.print(lueLukuTuhannet(luku));
            System.out.print(lueLukuSadat(luku - ((luku / 1000) * 1000)));
            System.out.print(lueLukuKymmenet(luku - ((luku / 100) * 100)));
        }
        if (luku < 100000 && luku >= 10000) {
            System.out.print(lueLukuKymmenet(luku / 1000) + "tuhatta");
            System.out.print(lueLukuSadat(luku - ((luku / 1000) * 1000)));
            int apuLuku = luku - ((luku / 1000) * 1000); // esim 5 000
            System.out.print(lueLukuKymmenet(apuLuku - (apuLuku / 100) * 100));
        }
        if (luku < 1000000 && luku >= 100000) {
            System.out.print(lueLukuSadat(luku / 1000));
            int apuLuku = luku - (luku / 100000) * 100000; // esim 76 326
            System.out.print(lueLukuKymmenet(apuLuku / 1000) + "tuhatta");
            System.out.print(lueLukuSadat(apuLuku - ((apuLuku / 1000) * 1000)));
            int apuLuku2 = apuLuku - ((apuLuku / 1000) * 1000);
            System.out.print(lueLukuKymmenet(apuLuku2 - (apuLuku2 / 100) * 100));
        }
        if (luku == 1000000) {
        System.out.println("miljoona");
        }
    }
}