/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matopeli;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.Timer;
import matopeli.gui.Paivitettava;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author avihavai
 */
public class MatopeliTest {

    private String domainPakkaus = "matopeli.domain";
    private String peliPakkaus = "matopeli.peli";
    private String palaLuokka = "Pala";
    private String omenaLuokka = "Omena";
    private String matoLuokka = "Mato";
    private String matopeliLuokka = "Matopeli";
    private Class palaClass;
    private Class omenaClass;
    private Class matoClass;
    private Class matopeliClass;

    @Before
    public void setUp() {
        try {
            String pala = domainPakkaus + "." + palaLuokka;
            palaClass = ReflectionUtils.findClass(pala);
        } catch (Exception e) {
        }

        try {
            String omena = domainPakkaus + "." + omenaLuokka;
            omenaClass = ReflectionUtils.findClass(omena);
        } catch (Exception e) {
        }

        try {
            String mato = domainPakkaus + "." + matoLuokka;
            matoClass = ReflectionUtils.findClass(mato);
        } catch (Exception e) {
        }

        try {
            String matopeli = peliPakkaus + "." + matopeliLuokka;
            matopeliClass = ReflectionUtils.findClass(matopeli);
        } catch (Exception e) {
        }
    }

    @Test
    @Points(Tehtava.ID + ".3")
    public void onMatopeli() {
        assertNotNull("Olethan luonut luokan " + matopeliLuokka + " pakkaukseen " + peliPakkaus + "?", matopeliClass);
    }

    @Test
    @Points(Tehtava.ID + ".3")
    public void onRajapinnatJaToteutukset() {
        assertNotNull("Olethan luonut luokan " + matopeliLuokka + " pakkaukseen " + peliPakkaus + "?", matopeliClass);

        assertTrue("Periihän luokka " + matopeliLuokka + " luokan javax.swing.Timer?", javax.swing.Timer.class.isAssignableFrom(matopeliClass));
        assertTrue("Toteuttaahan luokka " + matopeliLuokka + " rajapinnan ActionListener?", ActionListener.class.isAssignableFrom(matopeliClass));

        Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().requireExists();
        Reflex.reflect(matopeliClass).method("setMato").returningVoid().taking(matoClass).requireExists();

        Reflex.reflect(matopeliClass).method("getOmena").returning(omenaClass).takingNoParams().requireExists();
        Reflex.reflect(matopeliClass).method("setOmena").returningVoid().taking(omenaClass).requireExists();
    }

    @Test
    @Points(Tehtava.ID + ".3")
    public void setInitialDelayJaSetDelayKutsut() throws Throwable {
        onRajapinnatJaToteutukset();

        Timer peli = (Timer) Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(20, 20);
        assertEquals("Asetathan luokan " + matopeliLuokka + " konstruktorissa alustavaksi viiveeksi 2000ms? (Kutsu setInitialDelay(2000)).", 2000, peli.getInitialDelay());
        assertEquals("Asetathan luokan " + matopeliLuokka + " konstruktorissa normaaliksi viiveeksi 1000ms? (Kutsu setDelay(1000)).", 1000, peli.getDelay());

        boolean loytyi = false;
        for (ActionListener listener : peli.getActionListeners()) {
            if (listener == peli) {
                loytyi = true;
                break;
            }
        }

        assertTrue("Asetathan luokan " + matopeliLuokka + " konstruktorissa sille itsensä tapahtumankuuntelijaksi? (Kutsu addActionListener(this)).", loytyi);
    }

    @Test
    @Points(Tehtava.ID + ".3")
    public void tapahtumanKuuntelija() throws Throwable {
        onRajapinnatJaToteutukset();

        Timer peli = (Timer) Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(20, 20);

        boolean loytyi = false;
        for (ActionListener listener : peli.getActionListeners()) {
            if (listener == peli) {
                loytyi = true;
                break;
            }
        }

        assertTrue("Asetathan luokan " + matopeliLuokka + " konstruktorissa sille itsensä tapahtumankuuntelijaksi? (Kutsu addActionListener(this)).", loytyi);
    }

    @Test
    @Points(Tehtava.ID + ".3")
    public void matoAloittaaAinaKeskelta() throws Throwable {
        onRajapinnatJaToteutukset();

        int leveys = 20;
        int korkeus = 10;

        for (int i = 0; i < 10; i++) {
            Object peli = Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(leveys, korkeus);
            Object mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(peli);

            if (mato == null) {
                fail("Onhan luokan " + matopeliLuokka + " metodilla getMato() määre public, ja luothan uuden madon konstruktorissa.");
            }


            List palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

            if (palat == null || palat.size() != 1 || !palat.get(0).getClass().equals(palaClass)) {
                fail("Juuri luodun madon pituuden tulee olla 1. Tällöin madon metodin getPalat tulee palauttaa lista, jossa on 1 Pala-tyyppistä oliota.");
            }

            int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(palat.get(0));
            assertTrue("Juuri luodun madon ensimmäisen palan tulee olla keskellä ruutua. Jos leveys on " + leveys + ", tulee ensimmäisen palan x-koordinaatin olla " + (leveys / 2), x == (leveys / 2));
            int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(palat.get(0));
            assertTrue("Juuri luodun madon ensimmäisen palan tulee olla keskellä ruutua. Jos korkeus on " + korkeus + ", tulee ensimmäisen palan y-koordinaatin olla " + (korkeus / 2), y == (korkeus / 2));
        }
    }

    @Test
    @Points(Tehtava.ID + ".3")
    public void konstruktoriArpooOmenanSijainnin() throws Throwable {
        onRajapinnatJaToteutukset();

        int leveys = 20;
        int korkeus = 10;

        Set<Integer> xArvoja = new HashSet();
        Set<Integer> yArvoja = new HashSet();

        for (int i = 0; i < 10; i++) {
            Object peli = Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(leveys, korkeus);
            Object omena = Reflex.reflect(matopeliClass).method("getOmena").returning(omenaClass).takingNoParams().invokeOn(peli);

            if (omena == null) {
                fail("Onhan luokan " + matopeliLuokka + " metodilla getOmena() määre public, ja luothan uuden omenan konstruktorissa.");
            }

            int x = (Integer) Reflex.reflect(omenaClass).method("getX").returning(int.class).takingNoParams().invokeOn(omena);
            int y = (Integer) Reflex.reflect(omenaClass).method("getY").returning(int.class).takingNoParams().invokeOn(omena);

            if (x < 0 || x >= leveys) {
                fail("Onhan arvotun omenan x-koordinaatti aina matopelille annetun alueen sisällä? Matopelille annettiin konstruktorina leveys = 20, korkeus = 10, mutta omenan x-koordinaatti oli " + x);
            }

            if (y < 0 || y >= korkeus) {
                fail("Onhan arvotun omenan y-koordinaatti aina matopelille annetun alueen sisällä? Matopelille annettiin konstruktorina leveys = 20, korkeus = 10, mutta omenan y-koordinaatti oli " + y);
            }

            xArvoja.add(x);
            yArvoja.add(y);

            Object samaOmena = Reflex.reflect(matopeliClass).method("getOmena").returning(omenaClass).takingNoParams().invokeOn(peli);
            int samaX = (Integer) Reflex.reflect(omenaClass).method("getX").returning(int.class).takingNoParams().invokeOn(samaOmena);
            int samaY = (Integer) Reflex.reflect(omenaClass).method("getY").returning(int.class).takingNoParams().invokeOn(samaOmena);

            if (samaX != x || samaY != y) {
                fail("Ethän arvo uutta omenaa kutsussa getOmena?");
            }
        }

        if (xArvoja.size() < 3) {
            fail("Arvothan omenan sijainnin? 10 arvontakerralla luotiin vain " + xArvoja.size() + " erilaista x-arvoa vaikka alueen leveys on " + leveys);
        }


        if (yArvoja.size() < 3) {
            fail("Arvothan omenan sijainnin? 10 arvontakerralla luotiin vain " + yArvoja.size() + " erilaista y-arvoa vaikka alueen korkeus on " + korkeus);
        }
    }

    @Points(Tehtava.ID + ".4")
    @Test
    public void paivitettava() throws Throwable {
        assertTrue("Toteuttaahan luokka " + matopeliLuokka + " rajapinnan ActionListener?", ActionListener.class.isAssignableFrom(matopeliClass));

        Object matopeli = Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(20, 10);
        MockPaivitettava mockPaivitettava = new MockPaivitettava();

        Reflex.reflect(matopeliClass).method("setPaivitettava").returningVoid().taking(Paivitettava.class).invokeOn(matopeli, mockPaivitettava);
    }

    @Points(Tehtava.ID + ".4")
    @Test
    public void actionListener() throws Throwable {
        int leveys = 20;
        int korkeus = 10;

        assertTrue("Toteuttaahan luokka " + matopeliLuokka + " rajapinnan ActionListener?", ActionListener.class.isAssignableFrom(matopeliClass));

        ActionListener actionListener = (ActionListener) Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(leveys, korkeus);
        MockPaivitettava mockPaivitettava = new MockPaivitettava();

        Object mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(actionListener);

        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.ALAS);
        Reflex.reflect(matopeliClass).method("setPaivitettava").returningVoid().taking(Paivitettava.class).invokeOn(actionListener, mockPaivitettava);

        actionListener.actionPerformed(null);

        assertTrue("Kutsuthan Paivitettava-rajapinnan toteuttavan olion paivita-metodia Matopeli-luokan actionPerformed-metodin lopussa?", mockPaivitettava.paivitetty);

        if (mato == null) {
            fail("Onhan luokan " + matopeliLuokka + " metodilla getMato() määre public, ja luothan uuden madon konstruktorissa.");
        }


        List palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        if (palat == null || palat.size() != 2 || !palat.get(0).getClass().equals(palaClass)) {
            fail("Liikutathan matoa matopeli-luokan actionPerformed -metodissa? Lisääthän ensimmäisissä liikutuksissa myös paloja matoon.");
        }

        int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(palat.get(0));
        int tokaX = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(palat.get(1));
        assertTrue("Alaspäin liikkuvan madon x-koordinaattien ei pitäisi muuttua.", x == tokaX);
        int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(palat.get(0));
        int tokaY = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(palat.get(1));
        assertTrue("Alaspäin liikkuvan madon y-koordinaatin pitäisi kasvaa. Kahden palan pituisen madon y-koordinaattien eron tulee olla korkeintaan yksi.", Math.abs(y - tokaY) == 1);
    }

    @Points(Tehtava.ID + ".4")
    @Test
    public void kunOmenaanEiOsutaSitaEiSyodaJaSenPaikkaEiMuutu() throws Throwable {
        int leveys = 20;
        int korkeus = 10;

        assertTrue("Toteuttaahan luokka " + matopeliLuokka + " rajapinnan ActionListener?", ActionListener.class.isAssignableFrom(matopeliClass));

        ActionListener actionListener = (ActionListener) Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(leveys, korkeus);
        MockPaivitettava mockPaivitettava = new MockPaivitettava();


        Object omena = Reflex.reflect(omenaClass).constructor().taking(int.class, int.class).invoke(0, 0);
        int alkupX = (Integer) Reflex.reflect(omenaClass).method("getX").returning(int.class).takingNoParams().invokeOn(omena);
        int alkupY = (Integer) Reflex.reflect(omenaClass).method("getY").returning(int.class).takingNoParams().invokeOn(omena);

        Reflex.reflect(matopeliClass).method("setOmena").returningVoid().taking(omenaClass).invokeOn(actionListener, omena);
        Reflex.reflect(matopeliClass).method("setPaivitettava").returningVoid().taking(Paivitettava.class).invokeOn(actionListener, mockPaivitettava);

        actionListener.actionPerformed(null);

        assertTrue("Kutsuthan Paivitettava-rajapinnan toteuttavan olion paivita-metodia Matopeli-luokan actionPerformed-metodin lopussa?", mockPaivitettava.paivitetty);

        Object mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(actionListener);

        List palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        if (palat == null || palat.size() != 2 || !palat.get(0).getClass().equals(palaClass)) {
            fail("Liikutathan matoa matopeli-luokan actionPerformed -metodissa? Lisääthän ensimmäisissä liikutuksissa myös paloja matoon.");
        }

        Object haettuOmena = Reflex.reflect(matopeliClass).method("getOmena").returning(omenaClass).takingNoParams().invokeOn(actionListener);

        int x = (Integer) Reflex.reflect(omenaClass).method("getX").returning(int.class).takingNoParams().invokeOn(haettuOmena);
        int y = (Integer) Reflex.reflect(omenaClass).method("getY").returning(int.class).takingNoParams().invokeOn(haettuOmena);


        assertEquals("Kun matoa liikutetaan actionPerformed-kutsussa ja se ei osu omenaan, omenaa ei pitäisi syödä.", alkupX, x);
        assertEquals("Kun matoa liikutetaan actionPerformed-kutsussa ja se ei osu omenaan, omenaa ei pitäisi syödä.", alkupY, y);
    }

    @Points(Tehtava.ID + ".4")
    @Test
    public void kunOmenaanOsutaanSeSyodaanJaLuodaanUusiOmena() throws Throwable {
        int leveys = 20;
        int korkeus = 10;

        assertTrue("Toteuttaahan luokka " + matopeliLuokka + " rajapinnan ActionListener?", ActionListener.class.isAssignableFrom(matopeliClass));

        ActionListener matopeli = (ActionListener) Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(leveys, korkeus);
        MockPaivitettava mockPaivitettava = new MockPaivitettava();
        Reflex.reflect(matopeliClass).method("setPaivitettava").returningVoid().taking(Paivitettava.class).invokeOn(matopeli, mockPaivitettava);

        Object mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(matopeli);
        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.ALAS);

        matopeli.actionPerformed(null);
        matopeli.actionPerformed(null);
        matopeli.actionPerformed(null);


        List palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);
        if (palat == null || palat.size() < 3) {
            fail("Kolmen liikkumiskerran jälkeen madon palojen määrä oli " + palat.size() + " vaikka sen pitäisi olla " + 3 + ", luodaanhan mato vain konstruktorissa?");
        }

        int palaX = -1;
        int palaY = -1;

        for (Object pala : palat) {
            int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(pala);
            int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(pala);

            if (y > palaY) {
                palaY = y;
            }

            if (x > palaX) {
                palaX = x;
            }
        }

        if (palaY <= 4) {
            fail("Kasvaahan madon y-koordinaatti sen alaspäin kulkiessa.");
        }

        Object omena = Reflex.reflect(omenaClass).constructor().taking(int.class, int.class).invoke(palaX, palaY + 1);
        Reflex.reflect(matopeliClass).method("setOmena").returningVoid().taking(omenaClass).invokeOn(matopeli, omena);

        int alkupX = (Integer) Reflex.reflect(omenaClass).method("getX").returning(int.class).takingNoParams().invokeOn(omena);
        int alkupY = (Integer) Reflex.reflect(omenaClass).method("getY").returning(int.class).takingNoParams().invokeOn(omena);

        matopeli.actionPerformed(null);

        assertTrue("Kutsuthan Paivitettava-rajapinnan toteuttavan olion paivita-metodia Matopeli-luokan actionPerformed-metodin lopussa?", mockPaivitettava.paivitetty);

        mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(matopeli);

        palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        if (palat == null || palat.size() == 4) {
            fail("Kun mato syö omenan, sen pituuden pitäisi olla kasvanut yhdellä seuraavan liiku-kutsun jälkeen.");
        }

        Object haettuOmena = Reflex.reflect(matopeliClass).method("getOmena").returning(omenaClass).takingNoParams().invokeOn(matopeli);

        int x = (Integer) Reflex.reflect(omenaClass).method("getX").returning(int.class).takingNoParams().invokeOn(haettuOmena);
        int y = (Integer) Reflex.reflect(omenaClass).method("getY").returning(int.class).takingNoParams().invokeOn(haettuOmena);

        assertTrue("Kun mato löytää omenan actionPerformed-kutsussa, tulee omena syödä ja sille arpoa uusi sijanti.", alkupX != x || alkupY != y);

        // ei törmäys
        boolean jatkuu = (Boolean) Reflex.reflect(matopeliClass).method("jatkuu").returning(boolean.class).takingNoParams().invokeOn(matopeli);
        assertTrue("Pelin tulee jatkua kun mato ei ole törmännyt itseensä. Varmista että metodi jatkuu() palauttaa arvon true kun peli on käynnissä.", jatkuu);

        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.YLOS);
        matopeli.actionPerformed(null);
        jatkuu = (Boolean) Reflex.reflect(matopeliClass).method("jatkuu").returning(boolean.class).takingNoParams().invokeOn(matopeli);
        assertFalse("Pelin tulee loppua, eli metodin jatkuu() tulee palauttaa false, kun mato törmää itseensä. Varmista että mato voi törmätä itseensä siten, että se on ensin liikkunut alas, ja liikkuu sen jälkeen heti ylös tulosuuntaansa kohti.", jatkuu);
    }

    @Points(Tehtava.ID + ".4")
    @Test
    public void omenanSyominenKasvattaaMatoa() throws Throwable {
        int leveys = 20;
        int korkeus = 10;

        assertTrue("Toteuttaahan luokka " + matopeliLuokka + " rajapinnan ActionListener?", ActionListener.class.isAssignableFrom(matopeliClass));

        ActionListener matopeli = (ActionListener) Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(leveys, korkeus);
        MockPaivitettava mockPaivitettava = new MockPaivitettava();
        Reflex.reflect(matopeliClass).method("setPaivitettava").returningVoid().taking(Paivitettava.class).invokeOn(matopeli, mockPaivitettava);

        Object mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(matopeli);
        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.OIKEA);

        matopeli.actionPerformed(null);
        matopeli.actionPerformed(null);
        matopeli.actionPerformed(null);


        List palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);
        if (palat == null || palat.size() < 3) {
            fail("Kolmen liikkumiskerran jälkeen madon palojen määrä oli " + palat.size() + ", luodaanhan mato vain konstruktorissa?");
        }

        int matoX = -1;
        int matoY = -1;

        for (Object pala : palat) {
            int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(pala);
            int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(pala);

            if (y > matoY) {
                matoY = y;
            }

            if (x > matoX) {
                matoX = x;
            }
        }

        Reflex.reflect(matopeliClass).method("setOmena").returningVoid().taking(omenaClass).invokeOn(matopeli, Reflex.reflect(omenaClass).constructor().taking(int.class, int.class).invoke(matoX + 1, matoY));

        int uusiMatoX = -1;
        int uusiMatoY = -1;


        matopeli.actionPerformed(null);
        matopeli.actionPerformed(null);
        
        
        if (palat == null || palat.size() != 4) {
            fail("Kun mato löytää actionPerformed-metodissa omenan, tulee sen pituuden kasvaa yhdellä. Kun kolmen palan pituinen mato söi omenan, ja sen liiku-metodia kutsuttiin, sen pituuden olisi pitänyt olla 4. Nyt oli " + palat.size());
        }

        
        mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(matopeli);
        palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        if (palat == null || palat.size() != 4) {
            fail("Kun mato löytää actionPerformed-metodissa omenan, tulee sen pituuden kasvaa yhdellä. Kun kolmen palan pituinen mato söi omenan, ja sen liiku-metodia kutsuttiin, sen pituuden olisi pitänyt olla 4. Nyt oli " + palat.size());
        }

        for (Object pala : palat) {
            int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(pala);
            int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(pala);

            if (y > uusiMatoY) {
                uusiMatoY = y;
            }

            if (x > uusiMatoX) {
                uusiMatoX = x;
            }
        }

        assertEquals("Kun mato liikkuu oikealle, ei sen y-koordinaatin tule muuttua.", uusiMatoY, matoY);
        assertEquals("Kun mato liikkuu oikealle kaksi kertaa, tulee sen x-koordinaatin kasvaa kahdella.", uusiMatoX, matoX + 2);

        Reflex.reflect(matopeliClass).method("setOmena").returningVoid().taking(omenaClass).invokeOn(matopeli, Reflex.reflect(omenaClass).constructor().taking(int.class, int.class).invoke(uusiMatoX + 1, matoY));

        matopeli.actionPerformed(null);
        matopeli.actionPerformed(null);

        assertTrue("Kutsuthan Paivitettava-rajapinnan toteuttavan olion paivita-metodia Matopeli-luokan actionPerformed-metodin lopussa?", mockPaivitettava.paivitetty);
        mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(matopeli);
        palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        if (palat == null || palat.size() != 5) {
            fail("Kun mato löytää actionPerformed-metodissa omenan, tulee sen pituuden kasvaa yhdellä. Kun neljän palan pituinen mato söi omenan, ja sen liiku-metodia kutsuttiin, sen pituuden olisi pitänyt olla 5. Nyt oli " + palat.size());
        }
    }

    @Test
    @Points(Tehtava.ID + ".4")
    public void delayMuuttuuKunPeliEtenee() throws Throwable {
        onRajapinnatJaToteutukset();

        Timer peli = (Timer) Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(20, 20);
        assertEquals("Asetathan luokan " + matopeliLuokka + " konstruktorissa alustavaksi viiveeksi 2000ms? (Kutsu setInitialDelay(2000)).", 2000, peli.getInitialDelay());
        assertEquals("Asetathan luokan " + matopeliLuokka + " konstruktorissa normaaliksi viiveeksi 1000ms? (Kutsu setDelay(1000)).", 1000, peli.getDelay());

        boolean loytyi = false;
        for (ActionListener listener : peli.getActionListeners()) {
            if (listener == peli) {
                loytyi = true;
                break;
            }
        }

        assertTrue("Asetathan Matopelin kuuntelemaan omia tapahtumiaan?", loytyi);

        ActionListener actionListener = (ActionListener) peli;
        MockPaivitettava mockPaivitettava = new MockPaivitettava();
        Reflex.reflect(matopeliClass).method("setPaivitettava").returningVoid().taking(Paivitettava.class).invokeOn(actionListener, mockPaivitettava);

        Object mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(actionListener);
        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.ALAS);

        actionListener.actionPerformed(null);
        actionListener.actionPerformed(null);
        actionListener.actionPerformed(null);

        assertTrue("Nopeutuuhan pelin päivitystahti kun mato kasvaa? Pelin päivitystahti oli yksi päivitys" + peli.getDelay() + " millisekunnissa, vaikka madon pituus oli jo 3.", peli.getDelay() < 500);
    }

    class MockPaivitettava implements Paivitettava {

        boolean paivitetty = false;

        @Override
        public void paivita() {
            paivitetty = true;
        }
    }
}
