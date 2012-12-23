import java.util.*;

public class NumeroNelio {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int suurinNumero, deltaX, deltaY;
        System.out.print("Suurin numero: ");
        suurinNumero = Integer.parseInt(input.nextLine());
        for (int i = 0; i < suurinNumero * 2 + 1; i++) {
            for (int j = 0; j < suurinNumero * 2 + 1; j++) {
                deltaX = Math.abs(i - suurinNumero);
                deltaY = Math.abs(j - suurinNumero);
                if (deltaX > deltaY) {
                    System.out.print(deltaX);
                } else {
                    System.out.print(deltaY);
                }
            }
            System.out.println();
        }
    }
}