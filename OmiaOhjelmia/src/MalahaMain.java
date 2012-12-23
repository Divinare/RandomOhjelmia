
import javax.swing.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.*;

public class MalahaMain extends JApplet {

    private MalahaGame game;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JTextField pisteet1;
    private JTextField pisteet2;
    private JTextField malahaText;
    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;
    private Button b11;

    public MalahaMain() {

        gui();
    }

    public void gui() {

        game = new MalahaGame();

        JFrame f = new JFrame("Malaha Game");
        f.setVisible(true);
        f.setSize(300, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        p1 = new JPanel(new GridBagLayout());
        p1.setBackground(Color.LIGHT_GRAY);
        p2 = new JPanel(new GridBagLayout());
        p2.setBackground(Color.LIGHT_GRAY);
        p3 = new JPanel(new GridBagLayout());
        p3.setBackground(Color.PINK);
        p4 = new JPanel(new GridBagLayout());
        p4.setBackground(Color.PINK);
        p5 = new JPanel(new GridBagLayout());
        p5.setBackground(Color.PINK);

        malahaText = new JTextField("Malaha");
        malahaText.setEditable(false);
        p5.add(malahaText);
        f.add(p5, BorderLayout.CENTER);

        pisteet1 = new JTextField(game.getPelaaja1());
        pisteet1.setEditable(false);
        p3.add(pisteet1);
        f.add(p3, BorderLayout.WEST);

        pisteet2 = new JTextField(game.getPelaaja1());
        pisteet2.setEditable(false);
        p4.add(pisteet2);
        f.add(p4, BorderLayout.EAST);


        b0 = new Button(game.getKuppi(0));
        b1 = new Button(game.getKuppi(1));
        b2 = new Button(game.getKuppi(2));
        b3 = new Button(game.getKuppi(3));
        b4 = new Button(game.getKuppi(4));
        b5 = new Button(game.getKuppi(5));
        b6 = new Button(game.getKuppi(6));
        b7 = new Button(game.getKuppi(7));
        b8 = new Button(game.getKuppi(8));
        b9 = new Button(game.getKuppi(9));
        b10 = new Button(game.getKuppi(10));
        b11 = new Button(game.getKuppi(11));

        b0.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        game.pushButton(0);
                        b0.setName(game.getKuppi(0));
                    }
                });
        b1.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        game.pushButton(1);
                        b1.setName(game.getKuppi(1));
                    }
                });
        b2.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        game.pushButton(2);
                        b2.setName(game.getKuppi(2));
                    }
                });
        b3.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        game.pushButton(3);
                        b3 = new Button(game.getKuppi(3));
                    }
                });

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        p1.add(b0, c);
        c.gridx = 1;
        c.gridy = 0;
        p1.add(b1, c);
        c.gridx = 2;
        c.gridy = 0;
        p1.add(b2, c);
        c.gridx = 3;
        c.gridy = 0;
        p1.add(b3, c);
        c.gridx = 4;
        c.gridy = 0;
        p1.add(b4, c);
        c.gridx = 5;
        c.gridy = 0;
        p1.add(b5, c);
        c.gridx = 6;
        c.gridy = 0;

        p2.add(b6, c);
        c.gridx = 1;
        c.gridy = 0;
        p2.add(b7, c);
        c.gridx = 2;
        c.gridy = 0;
        p2.add(b8, c);
        c.gridx = 3;
        c.gridy = 0;
        p2.add(b9, c);
        c.gridx = 4;
        c.gridy = 0;
        p2.add(b10, c);
        c.gridx = 5;
        c.gridy = 0;
        p2.add(b11, c);
        c.gridx = 6;
        c.gridy = 0;

        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.SOUTH);


    }

    public static void main(String[] args) {
        MalahaMain peli = new MalahaMain();
        peli.setVisible(true);

    }
}