package morkopeli;

public class Game {

    private String[] randomMusic;
    private String menuMusic;
    private int[] peliTilanne;
    private boolean onkoPeliPaalla;
    private boolean onkoPausePaalla;
    private int menuValinta;     // missä kohtaa on "focus" menuruudussa
    private Luola luola;
    private Soittaja musiikinSoittaja;

    public Game() {
        this.peliTilanne = new int[3];
        peliTilanne[0] = 0; // 0 = hävittiinkö, 1 = voitettiinko
        peliTilanne[1] = 0; // peliin kulunut aika sekuntteina
        peliTilanne[2] = 0; // pisteet
        this.onkoPeliPaalla = false;
        this.onkoPausePaalla = false;
        this.menuValinta = 1;

        // Musat:
        this.musiikinSoittaja = new Soittaja();
        this.randomMusic = new String[10];
        this.randomMusic[0] = "muumimusaa1";
        this.randomMusic[1] = "muumimusaa2";
        this.randomMusic[2] = "patmat";
        this.menuMusic = "tristram";

    }

    public void run() {

        musiikinSoittaja.play(menuMusic);
        Luola luola = new Luola(5, musiikinSoittaja, randomMusic);
        int kentanKoko = luola.getKentta().length;
        Gui kayttoliittyma = new Gui(35, luola, this);
        kayttoliittyma.run(kentanKoko);
        luola.setPaivitettava(kayttoliittyma.getPaivitettava());
        this.luola = luola;

        //if (menusta new game) { :


//        while(true) {
//            if (onkoPeliPaalla == false && kayttoliittyma.getPiirtoalusta(). {



//        }
    }

    public void aloitaPeli() {
        onkoPeliPaalla = true;
        luola.asetaAlkuAika(System.currentTimeMillis());
        musiikinSoittaja.stop();
        this.luola.start();
    }

//        while (kayttoliittyma.getPaivitettava() == null) {
//            System.out.println("haha");
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException ex) {
//                System.out.println("Piirtoalustaa ei ole vielä luotu.");
//            }
//        }
    public void muutaPeliTilannetta(int[] uusiPeliTilanne) {
        this.peliTilanne = uusiPeliTilanne;
    }

    public boolean getOnkoPeliPaalla() {
        return onkoPeliPaalla;
    }
    
    public void vaihaPeliPaalleTaiPois(boolean paalleTaiPois) {
        this.onkoPeliPaalla = paalleTaiPois;
    }
    
    public boolean getOnkoPausePaalla() {
        return onkoPausePaalla;
    }
    
    public void vaihdaPausePaalleTaiPois(boolean paalleTaiPois) {
        this.onkoPausePaalla = paalleTaiPois;
    }

    public int getMenuValinta() {
        return menuValinta;
    }

    public void setMenuValinta(int kohta) {
        this.menuValinta = kohta;
    }
//    public void lopetaPeli() {
//       
//    }
}
