package KahalaTest;

import Kalaha.Kalaha;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class kalahaTest {

    private Kalaha peli;

    public kalahaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void toimiikoLisaysPelaajan1Puolella() {
        peli = new Kalaha();
        peli.setKuppi(1, 4); // Laitetaan ekaan kuppiin kuulien määräksi 4
        peli.setVuoro(1); // Pelaajan 1 vuoro
        peli.pushButton(1); // Painetaan 1. nappulaa
        assertEquals(2, peli.getVuoro()); // Onko pelaajan 2 vuoro pelikierroksen jälkeen
        assertEquals(0, peli.getPelaaja1()); // Onko pelaajalla 1 0 pistettä
        assertEquals(0, peli.getKuppi(1)); // Onko kupissa 1 0 kuulaa
        assertEquals(7, peli.getKuppi(2));
        assertEquals(7, peli.getKuppi(3));
        assertEquals(7, peli.getKuppi(4));
        assertEquals(7, peli.getKuppi(5));
        assertEquals(6, peli.getKuppi(6));
    }

    @Test
    public void toimiikoJosPaastaanTasanPelaajan1Kuppiin() {
        peli = new Kalaha();
        peli.setKuppi(1, 6);
        peli.setVuoro(1);
        peli.pushButton(1);
        assertEquals(1, peli.getVuoro());
        assertEquals(1, peli.getPelaaja1());
        assertEquals(0, peli.getKuppi(1));
        assertEquals(7, peli.getKuppi(2));
        assertEquals(7, peli.getKuppi(3));
        assertEquals(7, peli.getKuppi(4));
        assertEquals(7, peli.getKuppi(5));
        assertEquals(7, peli.getKuppi(6));
        assertEquals(6, peli.getKuppi(7));
    }

    @Test
    public void toimiikoJosLisataanYliPelaajan1Kupista() {
        peli = new Kalaha();
        peli.setKuppi(5, 4);
        peli.setVuoro(1);
        peli.pushButton(5);
        assertEquals(2, peli.getVuoro());
        assertEquals(1, peli.getPelaaja1());
        assertEquals(0, peli.getKuppi(5));
        assertEquals(7, peli.getKuppi(6));
        assertEquals(7, peli.getKuppi(7));
        assertEquals(7, peli.getKuppi(8));
        assertEquals(6, peli.getKuppi(9));
    }

    @Test
    public void toimiikoJosEdetaan2Kierrosta() {
        peli = new Kalaha();
        peli.setKuppi(5, 14);
        peli.setVuoro(1);
        peli.pushButton(5);
        assertEquals(2, peli.getVuoro());
        assertEquals(1, peli.getPelaaja1());
        assertEquals(7, peli.getKuppi(4));
        assertEquals(1, peli.getKuppi(5));
        assertEquals(8, peli.getKuppi(6));
        assertEquals(7, peli.getKuppi(7));
        assertEquals(7, peli.getKuppi(8));
        assertEquals(7, peli.getKuppi(9));
    }

    @Test
    public void toimiikoJosEdetaan2KierrostaJaPaadytaanTasanPelaajan1Kuppiin() {
        peli = new Kalaha();
        peli.setKuppi(5, 15);
        peli.setVuoro(1);
        peli.pushButton(5);
        assertEquals(1, peli.getVuoro());
        assertEquals(2, peli.getPelaaja1());
        assertEquals(7, peli.getKuppi(4));
        assertEquals(1, peli.getKuppi(5));
        assertEquals(8, peli.getKuppi(6));
        assertEquals(7, peli.getKuppi(7));
        assertEquals(7, peli.getKuppi(8));
        assertEquals(7, peli.getKuppi(9));
    }

    @Test
    public void toimiikoJosEdetaan3Kierrosta() {
        peli = new Kalaha();
        peli.setKuppi(5, 18);
        peli.setVuoro(1);
        peli.pushButton(5);
        assertEquals(2, peli.getVuoro());
        assertEquals(2, peli.getPelaaja1());
        assertEquals(7, peli.getKuppi(4));
        assertEquals(1, peli.getKuppi(5));
        assertEquals(8, peli.getKuppi(6));
        assertEquals(8, peli.getKuppi(7));
        assertEquals(8, peli.getKuppi(8));
        assertEquals(8, peli.getKuppi(9));
        assertEquals(7, peli.getKuppi(10));
    }

    @Test
    public void toimiikoLisaysPelaajan2Puolella() {
        peli = new Kalaha();
        peli.setKuppi(8, 3);
        peli.setVuoro(2);
        peli.pushButton(8);
        assertEquals(1, peli.getVuoro());
        assertEquals(0, peli.getPelaaja2());
        assertEquals(6, peli.getKuppi(7));
        assertEquals(0, peli.getKuppi(8));
        assertEquals(7, peli.getKuppi(9));
        assertEquals(7, peli.getKuppi(10));
        assertEquals(7, peli.getKuppi(11));
        assertEquals(6, peli.getKuppi(12));
    }

    @Test
    public void toimiikoJosPelaaja2Etenee2Kierrosta() {
        peli = new Kalaha();
        peli.setKuppi(10, 14);
        peli.setVuoro(2);
        peli.pushButton(10);
        assertEquals(1, peli.getVuoro());
        assertEquals(1, peli.getPelaaja2());
        assertEquals(7, peli.getKuppi(7));
        assertEquals(7, peli.getKuppi(8));
        assertEquals(7, peli.getKuppi(9));
        assertEquals(1, peli.getKuppi(10));
        assertEquals(8, peli.getKuppi(11));
        assertEquals(7, peli.getKuppi(12));
        assertEquals(7, peli.getKuppi(1));
    }

    @Test
    public void toimiikoJosPelaaja2Etenee2KierrostaJaPaatyyPisteKuppiin() {
        peli = new Kalaha();
        peli.setKuppi(10, 16);
        peli.setVuoro(2);
        peli.pushButton(10);
        assertEquals(2, peli.getVuoro());
        assertEquals(2, peli.getPelaaja2());
        assertEquals(7, peli.getKuppi(7));
        assertEquals(7, peli.getKuppi(8));
        assertEquals(7, peli.getKuppi(9));
        assertEquals(1, peli.getKuppi(10));
        assertEquals(8, peli.getKuppi(11));
        assertEquals(8, peli.getKuppi(12));
        assertEquals(7, peli.getKuppi(1));
    }

    @Test
    public void toimiikoJosPelaaja2Etenee5Kierrosta() {
        peli = new Kalaha();
        peli.setKuppi(10, 30);
        peli.setVuoro(2);
        peli.pushButton(10);
        assertEquals(1, peli.getVuoro());
        assertEquals(3, peli.getPelaaja2());
        assertEquals(8, peli.getKuppi(7));
        assertEquals(8, peli.getKuppi(8));
        assertEquals(8, peli.getKuppi(9));
        assertEquals(2, peli.getKuppi(10));
        assertEquals(9, peli.getKuppi(11));
        assertEquals(9, peli.getKuppi(12));
        assertEquals(9, peli.getKuppi(1));
        assertEquals(8, peli.getKuppi(2));
        assertEquals(8, peli.getKuppi(3));
    }

    @Test
    public void toimiikoJosPäädytäänTyhjäänKuppiinJaOtetaanToiseltaPuoleltaToisenKuulat() {
        peli = new Kalaha();
        peli.setKuppi(2, 0);
        peli.setKuppi(1, 1);
        peli.setKuppi(11, 10);
        peli.setVuoro(1);
        peli.pushButton(1);
        assertEquals(11, peli.getPelaaja1());
        assertEquals(0, peli.getKuppi(1));
        assertEquals(0, peli.getKuppi(2));
        assertEquals(0, peli.getKuppi(11));
        assertEquals(1, peli.getVuoro());
    }

    @Test
    public void toimiikoPelinLopetus() {
        peli = new Kalaha();
        peli.alustaKupit(1);
        peli.setKuppi(6, 3);
        peli.setKuppi(1, 0);
        peli.setKuppi(2, 0);
        peli.setKuppi(3, 0);
        peli.setKuppi(4, 0);
        peli.setKuppi(5, 0);
        peli.setVuoro(1);
        assertEquals(1, peli.getKuppi(11));
        peli.pushButton(6);
        assertEquals(0, peli.getKuppi(11));
        assertEquals(1, peli.getPelaaja1());
        assertEquals(8, peli.getPelaaja2()); // pelaaja2 saa kaikki 6x1 kuulaa ja 2 kuulaa jotka menevät sen puolelle kupista 6
        assertEquals(0, peli.getKuppi(1));
        assertEquals(0, peli.getKuppi(11));
    }

    @Test
    public void toimiikoNimienAsettaminen() {
        peli = new Kalaha();
        peli.setNimet("nimi1", "nimi2");
        assertEquals("nimi1", peli.getPelaajan1nimi());
        assertEquals("nimi2", peli.getPelaajan2nimi());
    }
}