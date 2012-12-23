
public class MalahaGame {

    private static int[] taulu = {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
    private static int pelaaja1 = 0;
    private static int pelaaja2 = 0;
    private int vuoro = 1; // vuoro on joko 1 tai 2

//    public MalahaGame() {
//        for (int i = 0; i < 12; i++) {
//            taulu[i] = 6;
//        }
//        
//    }
    public void pushButton(int kuppi) {

        if (vuoro == 1) {
            if (kuppi >= 6) {
                return;
            }
            for (int i = 0; i < kuppi; i++) {
                taulu[i]++;
            }
        } else {
            if (kuppi <= 5) {
                return;
            }

        }
    }

    public String getPelaaja1() {
        String s = Integer.toString(pelaaja1);
        return s;
    }

    public String getPelaaja2() {
        String s = Integer.toString(pelaaja2);
        return s;
    }

    public String getKuppi(int nro) {
        String s = Integer.toString(taulu[nro]);
        return s;
    }


    public static void main(String[] args) {
        MalahaGame peli = new MalahaGame();
        peli.pushButton(3);
        System.out.println(taulu[3]);
        System.out.println(taulu[2]);
        System.out.println(taulu[1]);
        System.out.println(taulu[4]);
    }
}