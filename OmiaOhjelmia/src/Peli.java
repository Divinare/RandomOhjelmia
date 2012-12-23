
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Peli extends JApplet {

    // kenttä koneelle, joka osaa kuukausien käsittelyn monimutkaisen logiikan:
    private KiviSaksetPaperi KPS;
    // käyttöliittymäkentät:
    private JTextField KPSikkuna;
    private JTextField tilastotV;
    private JTextField tilastotH;
    private JTextField tilastotT;
    private JButton kivi;
    private JButton sakset;
    private JButton paperi;

    public void init() { // konstruktori!

        KPS = new KiviSaksetPaperi();

        tilastotV = new JTextField();
        tilastotV.setEditable(false);
        //tilastotV.setText(KPS.getVoitot());

        tilastotH = new JTextField();
        tilastotH.setEditable(false);
        //tilastotH.setText(KPS.getVoitot());

        tilastotT = new JTextField();
        tilastotT.setEditable(false);
        //tilastotH.setText(KPS.getVoitot());

        KPSikkuna = new JTextField();
        KPSikkuna.setEditable(false);  // kirjoituskielto käyttäjälle
        KPSikkuna.setText("Kivi Sakset Paperi Peli");

        kivi = new JButton("Kivi");
        sakset = new JButton("Sakset");
        paperi = new JButton("Paperi");

        // tapahtumankuuntelijoiden asetukset:

        kivi.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        KPS.pelaa(1);
                        tilastotV.setText("Voittoja " + KPS.getVoitot());
                        tilastotH.setText("Häviöitä " + KPS.getHaviot());
                        tilastotT.setText("Tasapelejä " + KPS.getTasapelit());
                        if (KPS.voitot - KPS.häviöt >= 5) {
                            JOptionPane.showMessageDialog(null, "Tuurilla sä vaan vedät hahah");
                        }
                        if (KPS.voitot - KPS.häviöt <= -5 && KPS.voitot - KPS.häviöt >= -9) {
                            JOptionPane.showMessageDialog(null, "Alkaa tilastot olemaa omalla tasollas :P");
                        }
                        if (KPS.voitot - KPS.häviöt <= -10) {
                            JOptionPane.showMessageDialog(null, "Vähänkö oot huono!");
                        }
                    }
                });

        sakset.addActionListener( // kuukausi etenee
                new ActionListener() {

            public void actionPerformed(ActionEvent tapahtuma) {
                KPS.pelaa(2);
                tilastotV.setText("Voittoja " + KPS.getVoitot());
                tilastotH.setText("Häviöitä " + KPS.getHaviot());
                tilastotT.setText("Tasapelejä " + KPS.getTasapelit());
                if (KPS.voitot - KPS.häviöt >= 5) {
                    JOptionPane.showMessageDialog(null, "Tuurilla sä vaan vedät hahah");
                }
                if (KPS.voitot - KPS.häviöt <= -5 && KPS.voitot - KPS.häviöt >= -9) {
                    JOptionPane.showMessageDialog(null, "Alkaa tilastot olemaa omalla tasollas :P");
                }
                if (KPS.voitot - KPS.häviöt <= -10) {
                    JOptionPane.showMessageDialog(null, "Vähänkö oot huono!");
                }
            }
        });

        paperi.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent tapahtuma) {
                        KPS.pelaa(3);
                        tilastotV.setText("Voittoja " + KPS.getVoitot());
                        tilastotH.setText("Häviöitä " + KPS.getHaviot());
                        tilastotT.setText("Tasapelejä " + KPS.getTasapelit());
                        if (KPS.voitot - KPS.häviöt >= 5) {
                            JOptionPane.showMessageDialog(null, "Tuurilla sä vaan vedät hahah");
                        }
                        if (KPS.voitot - KPS.häviöt <= -5 && KPS.voitot - KPS.häviöt >= -9) {
                            JOptionPane.showMessageDialog(null, "Alkaa tilastot olemaa omalla tasollas :P");
                        }
                        if (KPS.voitot - KPS.häviöt <= -10) {
                            JOptionPane.showMessageDialog(null, "Vähänkö oot huono!");
                        }
                    }
                });


        //setLayout(new GridLayout(3, 3));  // rivejä - sarakkeita

        JPanel p1 = new JPanel(new GridLayout(1, 3));
        p1.add(kivi);
        p1.add(sakset);
        p1.add(paperi);
        JPanel p2 = new JPanel(new GridLayout(1, 3));
        p2.add(tilastotV);
        p2.add(tilastotH);
        p2.add(tilastotT);

        this.setLayout(new BorderLayout());
        this.add("North", KPSikkuna);
        this.add("Center", p1);
        this.add("South", p2);
    }
}