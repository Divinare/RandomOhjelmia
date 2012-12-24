package morkopeli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Timer;
import java.io.IOException;
import javax.swing.JFileChooser;


public class Luola extends Timer implements ActionListener {

    private int leveys, korkeus, morkoM, pisteet;
    private long alkuaika, pauseihinKulunutAika;
    private ArrayList<Hirvio> hirviot;
    private Pelaaja pelaaja;
    private boolean jatkuu, voitit, paused;
    private String[][] kentta;
    private Tekstinlukija lukija;
    public Paivitettava paivitettava;

    public Luola(int morkoM) {
        super(1000, null);
        this.morkoM = morkoM;
        this.pisteet = 0;
        voitit = false;
        this.jatkuu = true;
        this.pauseihinKulunutAika = 0;
        hirviot = new ArrayList<>();
        paused = false;
        this.lukija = new Tekstinlukija();
        lueKentta();
        this.leveys = kentta.length;
        this.korkeus = kentta[0].length;
        pelaaja = new Pelaaja(Suunta.ALAS, leveys, korkeus, this);
        alkuaika = System.currentTimeMillis();

        System.out.println("Kentän alustus");
        alustaKentta(morkoM);
        System.out.println("ohi");


        addActionListener(this);
        setInitialDelay(2000);
    }

    private void lueKentta() {
        try {

            JFileChooser jfc = new JFileChooser();
//        jfc.setFileFilter(new FileNameExtensionFilter("Tekstitiedostot", "level"));
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
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                System.out.println(korkeus);
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (paused) {
            piirra();
            return;
        }
        if (!jatkuu) {
            System.out.println("hävisit hähää!");
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
        if (1000 - (1 + getAika()) * 50 <= 101) {
            setDelay(80);
        } else {
            setDelay(1000 - ((1 + getAika()) * 50));
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
