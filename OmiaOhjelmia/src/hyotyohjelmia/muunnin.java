package hyotyohjelmia;

import java.util.Scanner;

public class muunnin {

    public static void main(String[] args) {
        String mj;
        Scanner in = new Scanner(System.in);
        mj = "";
        while (!mj.equals(".")) {
           // System.out.println("anna merkkijono, piste lopettaa");
            mj = in.nextLine();
            for (int i = 0; i < mj.length(); i++) {
                System.out.print('"');
                System.out.print(mj.charAt(i));
                System.out.print('"');
                if (i != mj.length() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("},");
        }
        in.close();
    }
}
