package Luola;

public class Hirvio {

    private int x;
    private int y;
    private Luola luola;

    public Hirvio(int xkoord, int ykoord, Luola luola) {
        this.x = xkoord;
        this.y = ykoord;
        this.luola = luola;
    }

    public void liiku(int korkeus, int leveys) {
        int r = (int) (Math.random() * 4) + 1;
        if (r == 1 && this.x + 1 < leveys) {
            if (luola.onkoMorkoSamassaPaikassa(x + 1, y, false) == false
                    && luola.onkoPelaajaSamassaPaikassa(x + 1, y) == false) {
                this.x++;
            }
        }
        if (r == 2 && this.x - 1 > 0) {
            if (luola.onkoMorkoSamassaPaikassa(x - 1, y, false) == false
                    && luola.onkoPelaajaSamassaPaikassa(x - 1, y) == false) {
                this.x--;
            }
        }
        if (r == 3 && this.y + 1 < korkeus) {
            if (luola.onkoMorkoSamassaPaikassa(y + 1, y, false) == false
                    && luola.onkoPelaajaSamassaPaikassa(x + 1, y) == false) {
                this.y++;
            }
        }
        if (r == 4 && this.y - 1 > 0) {
            if (luola.onkoMorkoSamassaPaikassa(y - 1, y, false) == false
                    && luola.onkoPelaajaSamassaPaikassa(x - 1, y) == false) {
                this.y--;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
