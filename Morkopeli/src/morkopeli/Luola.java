package morkopeli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.Timer;

public class Luola extends Timer implements ActionListener {

    private int leveys, korkeus, morkoM, pisteet, taso;
    private long alkuaika, pauseihinKulunutAika, aikaFailAantaVarten;
    private ArrayList<Hirvio> hirviot;
    private Pelaaja pelaaja;
    private boolean jatkuu, voitit, paused, havitty;
    private String[][] kentta;
    private Tekstinlukija lukija;
    public Paivitettava paivitettava;
    private Soittaja musiikinSoittaja;
    private String[] randomMusic;

    public Luola(int morkoM, Soittaja musiikinSoittaja, String[] randomMusic) {
        super(1000, null);
        this.morkoM = morkoM;
        this.musiikinSoittaja = musiikinSoittaja;
        this.randomMusic = randomMusic;
        this.pisteet = 0;
        voitit = false;
        this.jatkuu = true;
        this.havitty = false; // älkää välittäkö tästä
        this.pauseihinKulunutAika = 0;
        hirviot = new ArrayList<>();
        paused = false;
        this.lukija = new Tekstinlukija();
        Kentat tasot = new Kentat();
        this.kentta = tasot.taso1;
        this.taso = 1;
//        lueKentta();
        this.leveys = kentta.length;
        this.korkeus = kentta[0].length;
        pelaaja = new Pelaaja(Suunta.ALAS, leveys, korkeus, this);
        alustaKentta(morkoM);
        addActionListener(this);
        setInitialDelay(2000);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
      //  System.out.println();
        if (!jatkuu) {
            System.out.println("hävisit hähää!");
            if (havitty == false) {
                havitty = true;
                musiikinSoittaja.stop();
                musiikinSoittaja.play("aww");
                this.aikaFailAantaVarten = System.currentTimeMillis();
            }
            System.out.println("" + ((System.currentTimeMillis() - aikaFailAantaVarten)));
            if ((System.currentTimeMillis() - aikaFailAantaVarten) > 1000) {
                if (!musiikinSoittaja.soitetaankoTallaHetkellaMitaan()) {
                    musiikinSoittaja.play("aaa");
                }
            }
            return;
        }

        if (!musiikinSoittaja.soitetaankoTallaHetkellaMitaan()) {
            musiikinSoittaja.play(randomMusic[2]);
        }
        if (paused) {
            piirra();
            return;
        }
        liiku();
        if (onkoMorkoSamassaPaikassa(pelaaja.getX(), pelaaja.getY())) {
            havio();
            piirra();
            return;
        }
        for (int i = 0; i < hirviot.size(); i++) {
            hirviot.get(i).liiku(korkeus, leveys);
        }
        if (onkoMorkoSamassaPaikassa(pelaaja.getX(), pelaaja.getY())) {
            havio();
            piirra();
            return;
        }
        piirra();
        if (taso == 1) {
            setDelay(800 - (getAika() * 5));
            System.out.println(500 - (getAika() * 5));
        }
//        if (1000 - (1 + getAika()) * 50 <= 101) {
//            setDelay(80);
//        } else {
//            setDelay(1000 - ((1 + getAika()) * 50));
//        }
    }

    private void lueKentta() {
        try {
            JFileChooser jfc = new JFileChooser();
            int valinta = jfc.showOpenDialog(null);
            //Nyt tää siis kysyy et mikä filu avataan. jolloin .txt filu varmasti löytyy .
            //Tässä tavallaan turhaa toistoa, nyt luodaan Fileolio, jolta kysytään polku, jotta voidaan 
            //luoda uus File olio. mut katotaan jos saadaan tällee toimii
            File valittu = jfc.getSelectedFile();
            System.out.println(valittu.getAbsolutePath() + "asdasdasdad");
            kentta = lukija.read(valittu.getAbsolutePath()/*"level.txt"*/);
        } catch (IOException e) {
            System.out.println("lol");
        }
    }

    private void alustaKentta(int maara) {
        System.out.println("KORK " + korkeus);
        System.out.println("LEVE " + leveys);
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if ("M".equalsIgnoreCase(kentta[i][j])) {
                    int lisatty = 0;
                    while (lisatty < maara) {
                        Hirvio hirvio = new Hirvio(j, i, this);
                        hirviot.add(hirvio);
                        lisatty++;
                    }
                }
                if (kentta[i][j].equals("P")) {
                    pelaaja.setX(j);
                    pelaaja.setY(i);
                }
            }
        }
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

    public int getAika() {
        return (int) ((System.currentTimeMillis() - (alkuaika + 2000)) / 1000 - (pauseihinKulunutAika / 1000));
    }

    public void havio() {
        jatkuu = false;
    }

    public boolean getJatkuu() {
        return jatkuu;
    }

    public int getPisteet() {
        return pisteet;
    }

    public void pause() {
        if (paused) {
            paused = false;
        } else {
            paused = true;
        }
    }

    public boolean getPause() {
        return paused;
    }

    public void lisaaAikaaPauseenKuluneeseenAikaan(long maara) {
        this.pauseihinKulunutAika = pauseihinKulunutAika + maara;
    }

    public void asetaAlkuAika(long maara) {
        this.alkuaika = maara;
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public boolean onkoMorkoSamassaPaikassa(int xkoord, int ykoord) {
        boolean onko = false;
        if (hirviot == null) {
            return false;
        }
        for (int i = 0; i < hirviot.size(); i++) {
            if (xkoord == hirviot.get(i).getX() && ykoord == hirviot.get(i).getY()) {
                onko = true;
            }
        }
        return onko;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public int getLeveys() {
        return leveys;
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
    // X = Morko, O = tyhja, P = pelaaja, S = seinä, E = end

    public String[][] getKentta() {
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                if (!kentta[i][j].equals("S") && !kentta[i][j].equals("E")) {
                    if (onkoMorkoSamassaPaikassa(j, i)) {
                        kentta[i][j] = "M";
                    } else {
                        kentta[i][j] = "O";
                    }
                }
            }
        }
        kentta[pelaaja.getY()][pelaaja.getX()] = "P";
        return kentta;
    }

    public ArrayList<Hirvio> getHirvio() {
        return hirviot;
    }
}
