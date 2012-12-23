package ohJa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tukkilaskin extends JFrame {

    // kenttä koneelle, joka osaa kuukausien käsittelyn monimutkaisen logiikan:
    private TLaskentalogiikka tukkilaskin;
    // käyttöliittymäkentät:
    private JTextField laskin;
    private JButton tukit;
    private JButton plus;
    private JButton miinus;
    private JButton yhtaKuin;
    private JButton pyyhi;

    public Tukkilaskin() { // konstruktori!

        // luodaan monimutkaisen laskentalogiikan osaava kone:

        tukkilaskin = new TLaskentalogiikka();

        // käyttöliittymäoliot alkutilaansa:

        laskin = new JTextField();
        laskin.setEditable(false);  // kirjoituskielto käyttäjälle
        laskin.setText(tukkilaskin.getPuskuri());

        tukit = new JButton("|");
        plus = new JButton("+");
        miinus = new JButton("-");
        yhtaKuin = new JButton("=");
        pyyhi = new JButton("C");

        JPanel paneeli1 = new JPanel(new GridLayout(1, 1));
        paneeli1.add(laskin);
        JPanel paneeli2 = new JPanel(new GridLayout(1, 1));
        paneeli2.add(tukit);
        JPanel paneeli3 = new JPanel(new GridLayout(1, 4));
        paneeli3.add(plus);
        paneeli3.add(miinus);
        paneeli3.add(yhtaKuin);
        paneeli3.add(pyyhi);

        //setLayout(new GridLayout(3, 4));  // rivejä - sarakkeita
        setLayout(new GridLayout(3, 1));
        add(paneeli1);
        add(paneeli2);
        add(paneeli3);


        tukit.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        tukkilaskin.lisaaNumero();
                        laskin.setText(tukkilaskin.getPuskuri());
                    }
                });

        plus.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        tukkilaskin.plus();
                        laskin.setText(tukkilaskin.getPuskuri());
                    }
                });

        miinus.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        tukkilaskin.miinus();
                        laskin.setText(tukkilaskin.getPuskuri());
                    }
                });

        yhtaKuin.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        tukkilaskin.tulosOn();
                        laskin.setText(tukkilaskin.getPuskuri());
                    }
                });

        pyyhi.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        tukkilaskin.nollaa();
                        laskin.setText(tukkilaskin.getPuskuri());
                    }
                });
    }

    public static void main(String args[]) {
        Tukkilaskin ikkuna = new Tukkilaskin();
        ikkuna.setTitle("Tukkilaskin");
        ikkuna.pack();
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // osaa loppua
        ikkuna.setVisible(true); // olio näkyviin
    }
}