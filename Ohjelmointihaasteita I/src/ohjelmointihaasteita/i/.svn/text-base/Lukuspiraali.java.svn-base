package ohjelmointihaasteita.i;

import java.util.Scanner;

public class Lukuspiraali {

    private static Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {

        int koko;
        System.out.println("Anna koko ");

        while (true) {
            if (lukija.hasNextInt()) {
                koko = lukija.nextInt();
                break;
            } else {
                System.out.println("Antamasi syöte ei ollut kokonaisluku. Yritä uudelleen.");
                lukija.next();
            }
        }

        int kohta = 1;
        int keskiLuku = 1;
        int lisays = 0;
        int miinustus = 0;
        int paikka = 1;
        int yhteinenPaikka = 1;
        int yhteinenKeskiLuku = 1;
        int yhteinenLisays = 0;
        int rajoitus = 1;
        int yhteinenMiinustus = 0;

        do {
            yhteinenKeskiLuku += yhteinenLisays;
            for (int i = 0; i < koko; i++) { // Tehdään keskiluvulle rajoitus
                rajoitus += lisays;
                lisays += 2;
            }
            //System.out.println("Rajoitus on " + rajoitus);
            lisays = 0;

            if (kohta % 2 != 0) {
                keskiLuku = yhteinenKeskiLuku;
                for (int i = 1; i <= koko; i++) {
                    if (keskiLuku < rajoitus && paikka > yhteinenPaikka) { // Rajoitetaan ettei kasva yli
                        keskiLuku = keskiLuku + lisays;
                    }
                    if (keskiLuku < rajoitus) {
                        lisays = lisays + 2;
                    }
                    if (paikka < kohta) {
                        System.out.print(keskiLuku - (miinustus + yhteinenMiinustus) + " ");
                        if (keskiLuku - (miinustus + yhteinenMiinustus) < 10) {
                            System.out.print(" ");
                        }
                        miinustus -= 2;
                    }
                    if (paikka == kohta) {
                        System.out.print(keskiLuku + " ");
                        if (keskiLuku - (miinustus + yhteinenMiinustus) < 10) {
                            System.out.print(" ");
                        }
                    }
                    if (paikka > kohta) {
                        if (paikka % 2 == 0) {
                            System.out.print(keskiLuku - (miinustus + yhteinenMiinustus) + " ");
                            if (keskiLuku - (miinustus + yhteinenMiinustus) < 10) {
                                System.out.print(" ");
                            }
                        }
                        if (paikka % 2 != 0) {
                            System.out.print(keskiLuku + (miinustus + yhteinenMiinustus) + " ");
                            if (keskiLuku - (miinustus + yhteinenMiinustus) < 10) {
                                System.out.print(" ");
                            }
                        }
                    }
//                    System.out.println("+++" + miinustus + "+++");
//                    System.out.println("===" + keskiLuku + "===");
                    miinustus++;
                    paikka++;
                }
                //System.out.println("keskiluku on" + keskiLuku);
                paikka = 1;
                miinustus = 0;
                lisays = 0;
                keskiLuku = 1;
            }

            if (kohta % 2 == 0) {
                keskiLuku = yhteinenKeskiLuku;
                for (int i = 1; i <= koko; i++) {
                    if (keskiLuku < rajoitus && paikka > yhteinenPaikka) { // Rajoitetaan ettei kasva yli
                        keskiLuku = keskiLuku + lisays;
                    }
                    if (keskiLuku < rajoitus) {
                        lisays = lisays + 2;
                    }
                    if (paikka < kohta) {
                        System.out.print(keskiLuku + (miinustus + yhteinenMiinustus) + " ");
                        if (keskiLuku - (miinustus + yhteinenMiinustus) < 10) {
                            System.out.print(" ");
                        }
                        miinustus -= 2;
                    }
                    if (paikka == kohta) {
                        System.out.print(keskiLuku + " ");
                        if (keskiLuku - (miinustus + yhteinenMiinustus) < 10) {
                            System.out.print(" ");
                        }
                    }
                    if (paikka > kohta) {
                        if (paikka % 2 == 0) {
                            System.out.print(keskiLuku - (miinustus + yhteinenMiinustus) + " ");
                            if (keskiLuku - (miinustus + yhteinenMiinustus) < 10) {
                                System.out.print(" ");
                            }
                        }
                        if (paikka % 2 != 0) {
                            System.out.print(keskiLuku + (miinustus + yhteinenMiinustus) + " ");
                            if (keskiLuku - (miinustus + yhteinenMiinustus) < 10) {
                                System.out.print(" ");
                            }
                        }
                    }
//                    System.out.println("+++" + miinustus + "+++");
//                    System.out.println("===" + keskiLuku + "===");
                    miinustus++;
                    paikka++;
                }
                //System.out.println("keskiluku on" + keskiLuku);
                paikka = 1;
                miinustus = 0;
                lisays = 0;
                keskiLuku = 1;
            }
            System.out.println();

            rajoitus = 1;
            kohta++;
            yhteinenLisays += 2;
            yhteinenMiinustus++;
            yhteinenPaikka++;
        } while (kohta <= koko);
    }
}