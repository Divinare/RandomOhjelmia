package matopeli;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.ReflectionUtils;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import matopeli.gui.Kayttoliittyma;
import matopeli.gui.Paivitettava;
import org.junit.*;
import static org.junit.Assert.*;

public class UITest {

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
    String uiPakkaus = "matopeli.gui";
    String nappaimistonkuuntelijaLuokka = "Nappaimistonkuuntelija";
    Class nappaimistonKuuntelijaClass;
    String piirtoalustaLuokka = "Piirtoalusta";
    Class piirtoalustaClass;
    Method piirtoalustaPaintComponentMethod;
    String kayttoliittymaLuokka = "Kayttoliittyma";
    Method kayttoliittymaLuoKomponentitMethod;

    @Before
    public void setUp() {
        try {
            String pala = domainPakkaus + "." + palaLuokka;
            palaClass = ReflectionUtils.findClass(pala);
        } catch (Throwable t) {
        }

        try {
            String omena = domainPakkaus + "." + omenaLuokka;
            omenaClass = ReflectionUtils.findClass(omena);
        } catch (Throwable e) {
        }

        try {
            String mato = domainPakkaus + "." + matoLuokka;
            matoClass = ReflectionUtils.findClass(mato);
        } catch (Throwable e) {
        }

        try {
            String matopeli = peliPakkaus + "." + matopeliLuokka;
            matopeliClass = ReflectionUtils.findClass(matopeli);
        } catch (Throwable e) {
        }
        try {
            nappaimistonKuuntelijaClass = ReflectionUtils.findClass(uiPakkaus + "." + nappaimistonkuuntelijaLuokka);
        } catch (Throwable e) {
        }

        try {
            piirtoalustaClass = ReflectionUtils.findClass(uiPakkaus + "." + piirtoalustaLuokka);

            for (Method m : piirtoalustaClass.getDeclaredMethods()) {
                if (m.getName().equals("paintComponent")) {
                    piirtoalustaPaintComponentMethod = m;
                    break;
                }
            }

            piirtoalustaPaintComponentMethod.setAccessible(true);


        } catch (Throwable e) {
        }


        try {
            for (Method m : Kayttoliittyma.class.getDeclaredMethods()) {
                if (m.getName().equals("luoKomponentit")) {
                    kayttoliittymaLuoKomponentitMethod = m;
                    break;
                }
            }

            kayttoliittymaLuoKomponentitMethod.setAccessible(true);


        } catch (Throwable e) {
        }
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    @Points(Tehtava.ID + ".5")
    public void nappaimistonKuuntelija() throws Throwable {
        assertNotNull("Olethan luonut luokan " + nappaimistonkuuntelijaLuokka + ", joka sijaitsee pakkauksessa " + uiPakkaus + "?", nappaimistonKuuntelijaClass);

        assertTrue("Toteuttaahan luokka " + nappaimistonkuuntelijaLuokka + " rajapinnan KeyListener?", KeyListener.class.isAssignableFrom(nappaimistonKuuntelijaClass));

        Reflex.reflect(nappaimistonKuuntelijaClass).constructor().taking(matoClass).requireExists();

        Object mato = Reflex.reflect(matoClass).constructor().taking(int.class, int.class, Suunta.class).invoke(1, 1, Suunta.ALAS);

        Suunta nykyinenSuunta = (Suunta) Reflex.reflect(matoClass).method("getSuunta").returning(Suunta.class).takingNoParams().invokeOn(mato);
        assertEquals("Kun madolle annetaan konstruktorin parametrina Suunta.ALAS, tulee madon metodin getSuunta() palauttaa sama suunta, eli Suunta.ALAS.", Suunta.ALAS, nykyinenSuunta);


        KeyListener listener = (KeyListener) Reflex.reflect(nappaimistonKuuntelijaClass).constructor().taking(matoClass).invoke(mato);
        MockKeyEvent ylos = new MockKeyEvent(KeyEvent.VK_UP);
        listener.keyPressed(ylos);

        nykyinenSuunta = (Suunta) Reflex.reflect(matoClass).method("getSuunta").returning(Suunta.class).takingNoParams().invokeOn(mato);
        assertEquals("Kun näppäimistönkuuntelijalla painetaan ylöspäin-nuolinäppäintä (metodi keyPressed, KeyEvent.VK_UP), tulee madon suunnaksi asettaa Suunta.YLOS.", nykyinenSuunta, Suunta.YLOS);

        MockKeyEvent oikealle = new MockKeyEvent(KeyEvent.VK_RIGHT);
        listener.keyPressed(oikealle);

        nykyinenSuunta = (Suunta) Reflex.reflect(matoClass).method("getSuunta").returning(Suunta.class).takingNoParams().invokeOn(mato);
        assertEquals("Kun näppäimistönkuuntelijalla painetaan oikealle-nuolinäppäintä (metodi keyPressed, KeyEvent.VK_RIGHT), tulee madon suunnaksi asettaa Suunta.OIKEA.", nykyinenSuunta, Suunta.OIKEA);



        MockKeyEvent alas = new MockKeyEvent(KeyEvent.VK_DOWN);

        listener.keyPressed(alas);

        nykyinenSuunta = (Suunta) Reflex.reflect(matoClass).method("getSuunta").returning(Suunta.class).takingNoParams().invokeOn(mato);
        assertEquals("Kun näppäimistönkuuntelijalla painetaan alas-nuolinäppäintä (metodi keyPressed, KeyEvent.VK_DOWN), tulee madon suunnaksi asettaa Suunta.ALAS.", nykyinenSuunta, Suunta.ALAS);

        MockKeyEvent vasemmalle = new MockKeyEvent(KeyEvent.VK_LEFT);

        listener.keyPressed(vasemmalle);

        nykyinenSuunta = (Suunta) Reflex.reflect(matoClass).method("getSuunta").returning(Suunta.class).takingNoParams().invokeOn(mato);
        assertEquals("Kun näppäimistönkuuntelijalla painetaan vasemmalle-nuolinäppäintä (metodi keyPressed, KeyEvent.VK_LEFT), tulee madon suunnaksi asettaa Suunta.VASEN.", nykyinenSuunta, Suunta.VASEN);

    }

    @Test
    @Points(Tehtava.ID + ".6")
    public void onPiirtoalusta() throws Throwable {

        int sivunPituus = 10;
        assertNotNull("Olethan luonut luokan " + piirtoalustaLuokka + ", joka sijaitsee pakkauksessa " + uiPakkaus + "?", piirtoalustaClass);
        Reflex.reflect(piirtoalustaClass).constructor().taking(matopeliClass, int.class).requireExists();

        assertTrue("Periihän luokka " + piirtoalustaLuokka + " luokan JPanel?", JPanel.class.isAssignableFrom(piirtoalustaClass));

        assertTrue("Toteuttaahan luokka " + piirtoalustaLuokka + " rajapinnan Paivitettava?", Paivitettava.class.isAssignableFrom(piirtoalustaClass));



        int leveys = 20;
        int korkeus = 10;

        assertTrue("Toteuttaahan luokka " + matopeliLuokka + " rajapinnan ActionListener?", ActionListener.class.isAssignableFrom(matopeliClass));

        Object matopeli = Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(leveys, korkeus);
        MockPaivitettava mockPaivitettava = new MockPaivitettava();
        Reflex.reflect(matopeliClass).method("setPaivitettava").returningVoid().taking(Paivitettava.class).invokeOn(matopeli, mockPaivitettava);

        Object mato = Reflex.reflect(matopeliClass).method("getMato").returning(matoClass).takingNoParams().invokeOn(matopeli);
        Reflex.reflect(matoClass).method("setSuunta").returningVoid().taking(Suunta.class).invokeOn(mato, Suunta.ALAS);

        ActionListener actionListener = (ActionListener) matopeli;
        actionListener.actionPerformed(null);
        actionListener.actionPerformed(null);
        actionListener.actionPerformed(null);

        List<Point> madonPalat = new ArrayList<Point>();

        List palat = (List) Reflex.reflect(matoClass).method("getPalat").returning(List.class).takingNoParams().invokeOn(mato);

        for (Object pala : palat) {
            int x = (Integer) Reflex.reflect(palaClass).method("getX").returning(int.class).takingNoParams().invokeOn(pala);
            int y = (Integer) Reflex.reflect(palaClass).method("getY").returning(int.class).takingNoParams().invokeOn(pala);

            Point p = new Point(x, y);
            madonPalat.add(p);
        }

        Object omena = Reflex.reflect(matopeliClass).method("getOmena").returning(omenaClass).takingNoParams().invokeOn(matopeli);

        int omenaX = (Integer) Reflex.reflect(omenaClass).method("getX").returning(int.class).takingNoParams().invokeOn(omena);
        int omenaY = (Integer) Reflex.reflect(omenaClass).method("getY").returning(int.class).takingNoParams().invokeOn(omena);

        Point omenaPoint = new Point(omenaX, omenaY);


        JPanel alusta = (JPanel) Reflex.reflect(piirtoalustaClass).constructor().taking(matopeliClass, int.class).invoke(matopeli, sivunPituus);
        MockGraphics mc = new MockGraphics();
        ReflectionUtils.invokeMethod(void.class, piirtoalustaPaintComponentMethod, alusta, mc);

        assertTrue("Käytäthän Graphics-olion fillOval-metodia omenan piirtämiseen. Omenan piirtämisen tulee tapahtua piirtoalustan paintComponent-metodia kutsuttaessa.", mc.fillOvalKutsut.size() == 1);
        assertTrue("Käytäthän Graphics-olion fill3DRect-metodia madon piirtämiseen. Madon piirtämisen tulee tapahtua piirtoalustan paintComponent-metodia kutsuttaessa.", mc.fill3DRectKutsut.size() > 2);

        int omenanVasenYlaKulmaX = omenaPoint.x * sivunPituus;
        int omenanVasenYlaKulmaY = omenaPoint.y * sivunPituus;
        Rectangle omenaOval = mc.fillOvalKutsut.get(0);
        assertEquals("Kun palan sivun pituus on " + sivunPituus + ", ja omenan x-koordinaatti on " + omenaPoint.x + ", tulee piirretyn fillOval-kutsulle annetun x-koordinaatin olla " + omenanVasenYlaKulmaX, omenanVasenYlaKulmaX, omenaOval.x);
        assertEquals("Kun palan sivun pituus on " + sivunPituus + ", ja omenan y-koordinaatti on " + omenaPoint.y + ", tulee piirretyn fillOval-kutsulle annetun y-koordinaatin olla " + omenanVasenYlaKulmaY, omenanVasenYlaKulmaY, omenaOval.y);

        assertTrue("Kun palan sivun pituus on " + sivunPituus + ", tulee omenaa piirrettäessä fillOval-metodille antaa leveydeksi ja korkeudeksi " + sivunPituus, sivunPituus == omenaOval.width && sivunPituus == omenaOval.height);

        for (Point pala : madonPalat) {
            int palanVasenYlaKulmaX = pala.x * sivunPituus;
            int palanVasenYlaKulmaY = pala.y * sivunPituus;

            boolean loytyi = false;

            for (Rectangle matoPala : mc.fill3DRectKutsut) {
                if (matoPala.x == palanVasenYlaKulmaX && matoPala.y == palanVasenYlaKulmaY) {
                    loytyi = true;
                    break;
                }
            }

            if (!loytyi) {
                fail("Kun madon palan x-koordinaatti on " + pala.x + " ja sivun pituus on " + sivunPituus + ", tulee palan fill3DRect-metodille annetun x-koordinaatin olla " + palanVasenYlaKulmaX + ". Vastaavasti jos palan y-koordinaatti on " + pala.y + ", tulee sen fill3DRect-kutsulle annetun y-koordinaatin olla " + palanVasenYlaKulmaY);
            }
        }
    }

    @Test
    @Points(Tehtava.ID + ".7")
    public void onKali() throws Throwable {
        Constructor ctr = null;
        try {
            ctr = ReflectionUtils.requireConstructor(Kayttoliittyma.class, matopeliClass, int.class);
        } catch (Throwable t) {
        }

        if (ctr == null) {
            fail("Onhan luokalla Kayttoliittyma konstruktori public Kayttoliittyma(Matopeli matopeli, int sivunLeveys)?");
        }

        int leveys = 20;
        int korkeus = 10;

        int sivunLeveys = 20;
        Object matopeli = Reflex.reflect(matopeliClass).constructor().taking(int.class, int.class).invoke(leveys, korkeus);
        Kayttoliittyma kali = (Kayttoliittyma) ReflectionUtils.invokeConstructor(ctr, matopeli, sivunLeveys);
        MockContainer mockContainer = new MockContainer();

        try {
            ReflectionUtils.invokeMethod(void.class, kayttoliittymaLuoKomponentitMethod, kali, mockContainer);
        } catch (Exception e) {
        }

        assertTrue("Lisääthän käyttöliittymän luoKomponentit-metodissa parametrina saatuun Container-olioon luodun piirtoalustan? Lisääthän myös tapahtumankäsittelijät frame-oliolle vasta luoKomponentit-metodin lopussa?", mockContainer.lisatyt.size() > 0);
        Paivitettava paivitettava = (Paivitettava) Reflex.reflect(Kayttoliittyma.class).method("getPaivitettava").returning(Paivitettava.class).takingNoParams().invokeOn(kali);

        assertTrue("Palauttaahan luokan Kayttoliittyma metodikutsu getPaivitettava() container-oliolle lisätyn piirtoalustan?", mockContainer.lisatyt.contains(paivitettava));

    }

    private class MockKeyEvent extends KeyEvent {

        public MockKeyEvent(int keyCode) {
            super(new MockComponent(), keyCode, 1, 1, keyCode);
        }
    }

    private class MockComponent extends Component {
    }

    private class MockContainer extends Container {

        List<Component> lisatyt = new ArrayList<Component>();

        @Override
        public Component add(Component cmpnt) {
            Component cp = super.add(cmpnt);
            lisatyt.add(cmpnt);
            return cp;
        }
    }

    private class MockPaivitettava implements Paivitettava {

        boolean paivitetty = false;

        @Override
        public void paivita() {
            paivitetty = true;
        }
    }

    private class MockGraphics extends Graphics {

        List<Rectangle> fillOvalKutsut = new ArrayList<Rectangle>();
        List<Rectangle> fill3DRectKutsut = new ArrayList<Rectangle>();
        List<Color> varit = new ArrayList();

        @Override
        public Graphics create() {
            return this;
        }

        @Override
        public void translate(int i, int i1) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Color getColor() {
            if (varit.isEmpty()) {
                return Color.WHITE;
            }

            return varit.get(varit.size() - 1);
        }

        @Override
        public void setColor(Color color) {
            varit.add(color);
        }

        @Override
        public void setPaintMode() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setXORMode(Color color) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Font getFont() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setFont(Font font) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FontMetrics getFontMetrics(Font font) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Rectangle getClipBounds() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void clipRect(int i, int i1, int i2, int i3) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setClip(int i, int i1, int i2, int i3) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Shape getClip() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setClip(Shape shape) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void copyArea(int i, int i1, int i2, int i3, int i4, int i5) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void drawLine(int i, int i1, int i2, int i3) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void fillRect(int i, int i1, int i2, int i3) {
        }

        @Override
        public void clearRect(int i, int i1, int i2, int i3) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void drawRoundRect(int i, int i1, int i2, int i3, int i4, int i5) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void fillRoundRect(int i, int i1, int i2, int i3, int i4, int i5) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void drawOval(int i, int i1, int i2, int i3) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void fillOval(int i, int i1, int i2, int i3) {
            fillOvalKutsut.add(new Rectangle(i, i1, i2, i3));
        }

        @Override
        public void drawArc(int i, int i1, int i2, int i3, int i4, int i5) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void fillArc(int i, int i1, int i2, int i3, int i4, int i5) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void drawPolyline(int[] ints, int[] ints1, int i) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void drawPolygon(int[] ints, int[] ints1, int i) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void fillPolygon(int[] ints, int[] ints1, int i) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void drawString(String string, int i, int i1) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void drawString(AttributedCharacterIterator aci, int i, int i1) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean drawImage(Image image, int i, int i1, ImageObserver io) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean drawImage(Image image, int i, int i1, int i2, int i3, ImageObserver io) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean drawImage(Image image, int i, int i1, Color color, ImageObserver io) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean drawImage(Image image, int i, int i1, int i2, int i3, Color color, ImageObserver io) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean drawImage(Image image, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, ImageObserver io) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean drawImage(Image image, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7, Color color, ImageObserver io) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void dispose() {
            return;
        }

        @Override
        public void fill3DRect(int i, int i1, int i2, int i3, boolean bln) {
            fill3DRectKutsut.add(new Rectangle(i, i1, i2, i3));
        }
    }
}
