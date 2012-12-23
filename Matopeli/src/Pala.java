public class Pala {

    private int x;
    private int y;

    public Pala(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean osuu(Pala pala) {
        return this.x == pala.x && this.y == pala.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}