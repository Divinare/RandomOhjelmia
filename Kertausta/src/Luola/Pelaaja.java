package Luola;

public class Pelaaja {

    private int x;
    private int y;
    private int leveys;
    private int korkeus;
    private Suunta suunta;

    public Pelaaja(Suunta alkusuunta, int kentanLeveys, int kentanKorkeus) {
        this.x = 0;
        this.y = 0;
        this.korkeus = kentanKorkeus;
        this.leveys = kentanLeveys;
        this.suunta = alkusuunta;
    }

    public void liiku() {

        if (this.suunta.equals(Suunta.YLOS)) {
            // Tarkistetaan ettei mennä pelikentän ulkopuolelle
            if (getY() - 1 >= 0) {
                setY(this.y - 1);
            }
        } else if (this.suunta.equals(Suunta.OIKEA)) {
            // Tarkistetaan ettei mennä pelikentän ulkopuolelle
            if (getX() + 1 < leveys) {
                setX(this.x + 1);
            }
        } else if (this.suunta.equals(Suunta.ALAS)) {
            // Tarkistetaan ettei mennä pelikentän ulkopuolelle
            if (getY() + 1 < korkeus) {
                setY(this.y + 1);
            }
        } else if (this.suunta.equals(Suunta.VASEN)) {
            // Tarkistetaan ettei mennä pelikentän ulkopuolelle
            if (getX() - 1 >= 0) {
                setX(this.x - 1);
            }
        }
    }

    public Suunta getSuunta() {
        return suunta;
    }

    public void setSuunta(Suunta suunta) {
        this.suunta = suunta;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
