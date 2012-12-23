package morkoapplet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;

public class Luola extends Timer implements ActionListener {

<<<<<<< HEAD
    private final int leveys;
=======
    private final int leveys = 20;
>>>>>>> f40623b491a46d5d8cdcccbd527b763b0c6bed1e
    private final int korkeus;
    private int siirtoja;
    private ArrayList<Hirvio> hirviot;
    private Pelaaja pelaaja;
<<<<<<< HEAD
=======
    private boolean hirviotLiikkuvat;
>>>>>>> f40623b491a46d5d8cdcccbd527b763b0c6bed1e
    private boolean jatkuu;
    private String[][] kentta;
    public Paivitettava paivitettava;
    

    public Luola(int leveys, int korkeus, int hirvioita, int siirtoja, boolean hirviotLiikkuvat, boolean jatkuu) {
        super(1000, null);
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.siirtoja = siirtoja;
<<<<<<< HEAD
=======
        this.hirviotLiikkuvat = hirviotLiikkuvat;
>>>>>>> f40623b491a46d5d8cdcccbd527b763b0c6bed1e
        this.jatkuu = jatkuu;
        hirviot = new ArrayList<>();
        pelaaja = new Pelaaja(Suunta.ALAS, leveys, korkeus);

        asetaHirviot(hirvioita);

        addActionListener(this);
        setInitialDelay(2000);
    }

    private void asetaHirviot(int maara) {
        int laitetut = 0;
        while (true) {
            int x = (int) (Math.random() * leveys);
            int y = (int) (Math.random() * korkeus);
            if (onkoMorkoSamassaPaikassa(x, y, false) == false && onkoPelaajaSamassaPaikassa(x, y) == false) {
                laitetut++;
                Hirvio hirvio = new Hirvio(x, y, this);
                hirviot.add(hirvio);
            }
            if (laitetut == maara) {
                return;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!jatkuu) {
            return;
        }

        liiku();
        onkoMorkoSamassaPaikassa(pelaaja.getX(), pelaaja.getY(), true);
        for (int i = 0; i < hirviot.size(); i++) {
            hirviot.get(i).liiku(korkeus, leveys);
        }
        piirra();

        setDelay(600 - (36*(16-this.hirviot.size())));
    }

    public void liiku() {
        pelaaja.liiku();
    }
    
    private void piirra() {
        paivitettava.paivita();
    }
    
    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public boolean onkoMorkoSamassaPaikassa(int xkoord, int ykoord, boolean poistetaan) {
        boolean onko = false;
        if (hirviot == null) {
            return false;
        }
        for (int i = 0; i < hirviot.size(); i++) {
            if (xkoord == hirviot.get(i).getX() && ykoord == hirviot.get(i).getY()) {
                onko = true;
                if (poistetaan == true) {
                    hirviot.remove(i);
                }
            }
        }
        return onko;
    }

    public boolean onkoPelaajaSamassaPaikassa(int xkoord, int ykoord) {
        boolean onko = false;
        if (hirviot == null) {
            return false;
        }
        if (xkoord == pelaaja.getX() && ykoord == pelaaja.getY()) {
            onko = true;
        }
        return onko;
    }
    // X = Morko, O = tyhja, @ = pelaaja
    public String[][] getKentta() {
        String[][] taulu = new String[korkeus][leveys];
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                if (onkoMorkoSamassaPaikassa(i, j, false)) {
                    taulu[i][j] = "X";
                }
                else {
                    taulu[i][j] = "O";
                }
            }
        }
        taulu[pelaaja.getX()][pelaaja.getY()] = "@";
        return taulu;
    }
    
    public ArrayList<Hirvio> getHirvio() {
        return hirviot;
    }

//    public void liiku(String askeleet) {
//        // Lasketaan minkä verran möröt liikkuvat jokaisen siirron jälkeen
//        int liikkeita = 0;
//        for (int i = 0; i < askeleet.length(); i++) {
//            // Jos liikutaan vasemmalle
//            if (askeleet.charAt(i) == 'a') {
//                liikkeita++;
//                // Tarkistetaan ettei mennä pelikentän ulkopuolelle
//                if (pelaaja.getX() - 1 >= 0) {
//                    pelaaja.setX(pelaaja.getX() - 1);
//                    boolean osuiko = onkoMorkoSamassaPaikassa(pelaaja.getX(), pelaaja.getY(), true);
//                    if (osuiko == true) {
//                        System.out.println("Tapoit mörön!");
//                    }
//                }
//            }
//            // Jos liikutaan oikealle
//            if (askeleet.charAt(i) == 'd') {
//                liikkeita++;
//                // Tarkistetaan ettei mennä pelikentän ulkopuolelle
//                if (pelaaja.getX() + 1 < leveys) {
//                    pelaaja.setX(pelaaja.getX() + 1);
//                    boolean osuiko = onkoMorkoSamassaPaikassa(pelaaja.getX(), pelaaja.getY(), true);
//                    if (osuiko == true) {
//                        System.out.println("Tapoit mörön!");
//                    }
//                }
//            }
//            // Jos liikutaan ylös
//            if (askeleet.charAt(i) == 'w') {
//                liikkeita++;
//                // Tarkistetaan ettei mennä pelikentän ulkopuolelle
//                if (pelaaja.getY() - 1 >= 0) {
//                    pelaaja.setY(pelaaja.getY() - 1);
//                    boolean osuiko = onkoMorkoSamassaPaikassa(pelaaja.getX(), pelaaja.getY(), true);
//                    if (osuiko == true) {
//                        System.out.println("Tapoit mörön!");
//                    }
//                }
//            }
//            // Jos liikutaan alas
//            if (askeleet.charAt(i) == 's') {
//                liikkeita++;
//                // Tarkistetaan ettei mennä pelikentän ulkopuolelle
//                if (pelaaja.getY() + 1 < korkeus) {
//                    pelaaja.setY(pelaaja.getY() + 1);
//                    boolean osuiko = onkoMorkoSamassaPaikassa(pelaaja.getX(), pelaaja.getY(), true);
//                    if (osuiko == true) {
//                        System.out.println("Tapoit mörön!");
//                    }
//                }
//            }
//        }
//        if (hirviotLiikkuvat) {
//            for (int i = 0; i < hirviot.size(); i++) {
//                hirviot.get(i).liiku(korkeus, leveys, liikkeita);
//            }
//        }
//    }

    public int getSiirrot() {
        return siirtoja;
    }

//    public static void main(String[] args) {
    // Leveys, korkeus, mörköjen määrä, siirrot
//        Luola peli = new Luola(15, 15, 3, 15, true);
//    }
}
