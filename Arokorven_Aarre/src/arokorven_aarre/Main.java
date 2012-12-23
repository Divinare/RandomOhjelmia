package arokorven_aarre;

import javax.swing.JFrame;

public class Main extends JFrame {

    public static void main(String[] args) {

        Main AA = new Main();
        AA.setTitle("Arokorven Aarre");
        AA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the game board
        Gui pelilauta = new Gui();
        AA.add(pelilauta);
        // Pack and show
        AA.pack();
        AA.setLocationRelativeTo(null);
        AA.setVisible(true);
    }
}
