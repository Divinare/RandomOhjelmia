package ohjelmointihaasteita.i;

public class Hevonen {

    private static int mahdollisuudet = 0;

    public static void kulje(int x, int y, int maara) {



        if (x > 25 || y > 25) {
            return;
        }
        if (maara == 25) {
            mahdollisuudet++;
            return;
        }

        for (int i = 0; i < 25; i++) {
            for (int xx = 1; xx < 3; xx++) {
                for (int yy = 1; yy < 3; yy++) {
                    kulje(x, y, maara + 1);
                }
            }
        }
    }

    public static void kulkemiset() {
    }

    public static void main(String[] args) {
        kulje(0, 0, 0);
        System.out.println(mahdollisuudet);
    }
}
