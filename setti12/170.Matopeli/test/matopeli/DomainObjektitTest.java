package matopeli;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class DomainObjektitTest {

    private String pakkaus = "matopeli.domain";
    private String palaLuokka = "Pala";
    private String omenaLuokka = "Omena";
    private String matoLuokka = "Mato";
    private Class palaClass;
    private Class omenaClass;
    private Class matoClass;

    @Before
    public void setUp() {
        try {
            String pala = pakkaus + "." + palaLuokka;
            palaClass = ReflectionUtils.findClass(pala);
        } catch (Throwable t) {
        }

        try {
            String omena = pakkaus + "." + omenaLuokka;
            omenaClass = ReflectionUtils.findClass(omena);
        } catch (Throwable e) {
        }


        try {
            String mato = pakkaus + "." + matoLuokka;
            matoClass = ReflectionUtils.findClass(mato);
        } catch (Throwable e) {
        }
    }

    @Test
    @Points(Tehtava.ID + ".1")
    public void onPala() throws Throwable {
        assertNotNull("Loithan luokan " + palaLuokka + " pakkaukseen " + pakkaus + "?", palaClass);
        Reflex.reflect(palaClass).constructor().taking(int.class, int.class).requireExists();
        Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().requireExists();
        Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().requireExists();
        Reflex.reflect(palaClass).method("osuu").returning(boolean.class).taking(palaClass).requireExists();

        Object kakkosNelonen = Reflex.reflect(palaClass).constructor().taking(int.class, int.class).invoke(2, 4);

        int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(kakkosNelonen);
        assertEquals("Palauttaahan metodikutsu getX konstruktorille public Pala(int x, int y) annetun x-muuttujan?", 2, x);
        int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(kakkosNelonen);
        assertEquals("Palauttaahan metodikutsu getY konstruktorille public Pala(int x, int y) annetun y-muuttujan?", 4, y);

        Object toinenKakkosNelonen = Reflex.reflect(palaClass).constructor().taking(int.class, int.class).invoke(2, 4);

        boolean samat = (Boolean) Reflex.reflect(palaClass).method("osuu").returning(boolean.class).taking(palaClass).invokeOn(kakkosNelonen, toinenKakkosNelonen);
        assertTrue("Jos kahdella palalla on samat x ja y -koordinaatit, metodin osuu pitäisi palauttaa arvo true.", samat);

        Object kolmosNelonen = Reflex.reflect(palaClass).constructor().taking(int.class, int.class).invoke(3, 4);
        samat = (Boolean) Reflex.reflect(palaClass).method("osuu").returning(boolean.class).taking(palaClass).invokeOn(kolmosNelonen, toinenKakkosNelonen);
        assertFalse("Jos kahdella palalla on eri x ja y -koordinaatit, metodin osuu pitäisi palauttaa arvo false.", samat);

        Object nelosKolmonen = Reflex.reflect(palaClass).constructor().taking(int.class, int.class).invoke(4, 3);
        samat = (Boolean) Reflex.reflect(palaClass).method("osuu").returning(boolean.class).taking(palaClass).invokeOn(nelosKolmonen, toinenKakkosNelonen);
        assertFalse("Jos kahdella palalla on eri x ja y -koordinaatit, metodin osuu pitäisi palauttaa arvo false.", samat);
    
        String tulostus = nelosKolmonen.toString();
        
        assertEquals("Onhan Pala-luokan toString-metodikutsun palauttama merkkijono täsmälleen muotoa (x,y)? Nyt oli " + tulostus + " kun x-koordinaatin arvo oli 4 ja y-koordinaatin arvo oli 3.", "(4,3)", tulostus.replaceAll("\\s+", ""));
    }

    @Test
    @Points(Tehtava.ID + ".1")
    public void onOmena() {
        assertNotNull("Loithan luokan " + omenaLuokka + " pakkaukseen " + pakkaus + "?", omenaClass);
        assertTrue("Periihän luokka " + omenaLuokka + " luokan " + palaLuokka + "?", palaClass.isAssignableFrom(omenaClass));
        Reflex.reflect(omenaClass).constructor().taking(int.class, int.class).requireExists();
        Reflex.reflect(omenaClass).method("getX").returning(int.class).takingNoParams().requireExists();
        Reflex.reflect(omenaClass).method("getY").returning(int.class).takingNoParams().requireExists();
        Reflex.reflect(omenaClass).method("osuu").returning(boolean.class).taking(palaClass).requireExists();

        assertTrue("Onhan luokalla " + omenaLuokka + " 0 oliomuuttujaa?", omenaClass.getDeclaredFields().length == 0);
        assertTrue("Onhan luokalla " + omenaLuokka + " 0 omaa metodia?", omenaClass.getDeclaredMethods().length == 0);
    }

    @Test
    @Points(Tehtava.ID + ".2")
    public void onMato() throws Throwable {
        assertNotNull("Loithan luokan " + matoLuokka + " pakkaukseen " + pakkaus + "?", matoClass);
        Reflex.reflect(matoClass).constructor().taking(int.class, int.class, Suunta.class).requireExists();
        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).requireExists();
        Reflex.reflect(matoClass).method("getSuunta").returning(Suunta.class).takingNoParams().requireExists();
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().requireExists();
        Reflex.reflect(matoClass).method("kasva").returningVoid().takingNoParams().requireExists();
        Reflex.reflect(matoClass).method("getPituus").returning(int.class).takingNoParams().requireExists();
        Reflex.reflect(matoClass).method("osuuItseensa").returning(boolean.class).takingNoParams().requireExists();
        Reflex.reflect(matoClass).method("osuu").returning(boolean.class).taking(palaClass).requireExists();
        Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().requireExists();

        Object mato = Reflex.reflect(matoClass).constructor().taking(int.class, int.class, Suunta.class).invoke(1, 1, Suunta.OIKEA);

        List palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        if (palat == null || palat.size() != 1 || !palat.get(0).getClass().equals(palaClass)) {
            fail("Metodin getPalat tulee palauttaa yhden alkion kokoinen lista juuri luodulle madolle. Madon kasvaminen tulee toteuttaa liiku-metodiin.");
        }
        Object pala = palat.get(0);
        assertTrue("Kun madon konstruktorille annetaan parametrina arvot 1, 1, Suunta.OIKEA, tulee sen ensimmäisen palan x-koordinaatin arvon olla 1.", ((Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(pala)) == 1);
        assertTrue("Kun madon konstruktorille annetaan parametrina arvot 1, 1, Suunta.OIKEA, tulee sen ensimmäisen palan y-koordinaatin arvon olla 1.", ((Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(pala)) == 1);

        liikuYmpyra(mato, 1, 1);
    }

    @Test
    @Points(Tehtava.ID + ".2")
    public void matoEiOsuItseensa() throws Throwable {
        Object mato = Reflex.reflect(matoClass).constructor().taking(int.class, int.class, Suunta.class).invoke(1, 1, Suunta.OIKEA);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        assertFalse("Madon ei pitäisi törmätä kun se on liikkunut vain yhteen suuntaan.", ((Boolean) Reflex.reflect(matoClass).method("osuuItseensa").returning(boolean.class).takingNoParams().invokeOn(mato)));
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        assertFalse("Madon ei pitäisi törmätä kun se on liikkunut vain yhteen suuntaan.", ((Boolean) Reflex.reflect(matoClass).method("osuuItseensa").returning(boolean.class).takingNoParams().invokeOn(mato)));
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        assertFalse("Madon ei pitäisi törmätä kun se on liikkunut vain yhteen suuntaan.", ((Boolean) Reflex.reflect(matoClass).method("osuuItseensa").returning(boolean.class).takingNoParams().invokeOn(mato)));

        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.VASEN);
        assertFalse("Madon ei pitäisi törmätä kun se on liikkunut vain yhteen suuntaan. Madon ei myöskään pitäisi liikkua kun sille asetetaan suunta.", ((Boolean) Reflex.reflect(matoClass).method("osuuItseensa").returning(boolean.class).takingNoParams().invokeOn(mato)));
    }

    private void liikuYmpyra(Object mato, int origX, int origY) throws Throwable {
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);

        int pituus = (Integer) Reflex.reflect(matoClass).method("getPituus").returning(int.class).takingNoParams().invokeOn(mato);
        assertTrue("Madon oletuspituuden pitäisi olla 3. Se ei saa kasvaa pidemmäksi ilman että kasva-metodia kutsutaan.", pituus == 3);
        Reflex.reflect(matoClass).method("kasva").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        pituus = (Integer) Reflex.reflect(matoClass).method("getPituus").returning(int.class).takingNoParams().invokeOn(mato);
        assertTrue("Kun 3 palan pituinen mato kasvaa ja liikkuu yhden, sen pituuden pitäisi olla 4. Kasvaminen kannattaa toteuttaa liiku-metodin yhteyteen.", pituus == 4);

        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        pituus = (Integer) Reflex.reflect(matoClass).method("getPituus").returning(int.class).takingNoParams().invokeOn(mato);
        assertTrue("Madon tulee kasvaa vain yhdellä yhtä kasva-kutsua kohti. Kasvaminen kannattaa toteuttaa liiku-metodin yhteyteen.", pituus == 4);

        List palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        if (palat == null || palat.size() != 4 || !palat.get(0).getClass().equals(palaClass)) {
            fail("Kun madon pituus on 4, tulee metodin getPalat palauttaa lista, jossa on 4 Pala-tyyppistä oliota.");
        }

        for (Object p : palat) {
            int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(p);
            assertTrue("Kun palan suunta on Suunta.OIKEA, ei sen y-akselin arvon pitäisi muuttua liikuttaessa.", y == 1);
            int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(p);
            assertTrue("Kun palan suunta on Suunta.OIKEA, sen x-akselin arvojen tulee kasvaa liikuttaessa.", x > 1);
        }


        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.ALAS);

        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);


        palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        int alkupX = -100;
        for (Object p : palat) {
            int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(p);
            assertTrue("Kun madon suunta on Suunta.ALAS, tulee sen y-akselin arvojen kasvaa alas mentäessä.", y > 1);
            int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(p);
            if (alkupX == -100) {
                alkupX = x;
                continue;
            }

            assertTrue("Kun madon suunta on Suunta.ALAS, sen x-akselin arvojen tulee pysyä samana liikuttaessa.", x == alkupX);
        }


        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.VASEN);

        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);


        palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        int alkupY = -100;
        for (Object p : palat) {
            int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(p);
            if (alkupY == -100) {
                alkupY = y;
                continue;
            }
            assertTrue("Kun madon suunta on Suunta.VASEN, tulee sen y-akselin arvojen pysyä samana.", y == alkupY);
            int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(p);

            assertTrue("Kun madon suunta on Suunta.VASEN, sen x-akselin arvojen tulee pienentyä liikuttaessa.", x < 5);
        }

        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.YLOS);

        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);
        Reflex.reflect(matoClass).method("liiku").returningVoid().takingNoParams().invokeOn(mato);


        palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        boolean jokuAlkupisteessa = false;
        for (Object p : palat) {
            int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(p);
            assertTrue("Kun madon suunta on Suunta.YLOS, tulee sen y-akselin arvojen pienentyä.", y < 5);
            int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(p);
            assertTrue("Kun madon suunta on Suunta.YLOS, sen x-akselin arvojen tulee pysyä samana.", x < 5);

            if (x == origX && y == origY) {
                jokuAlkupisteessa = true;
            }
        }

        if (!jokuAlkupisteessa) {
            fail("Kun liikutaan 5 kertaa jokaiseen ilmansuuntaan, madon pitäisi päätyä takaisin alkupisteeseen.");
        }

    }
}
