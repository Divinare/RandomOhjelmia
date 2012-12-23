package Kalaha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.io.File;
import java.io.IOException;;
import javax.imageio.ImageIO;

public class KalahaMain extends JFrame {

    private Kalaha game;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JTextField pisteet1;
    private JTextField pisteet2;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b10;
    private JButton b11;
    private JButton b12;
    private ArrayList<JButton> napit;
    private GridBagConstraints c;

    public KalahaMain() {

        gui();
    }

    /**
     * Luodaan graafinen toiminnallisuus sekä paneelit p3, p4 ja p5
     */
    public void gui() {

        game = new Kalaha();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        luoNapit();
        luoPaneelit12();

        luoMenubar();
        luoPaneelit345();

        setSize(475, 196);

        setVisible(true);

    }

    private void alustaPisteetP3Vasen(Image image2) {
        p3 = new JPanel(new BorderLayout());
        p3.setPreferredSize(new Dimension(120, 61));
        JLabel label3 = new JLabel(new ImageIcon(image2));
        label3.setLayout(new GridBagLayout());
        p3.add(label3);
        pisteet1 = new JTextField(game.getPelaaja1() + "  " + game.getPelaajan1nimi() + "  [" + game.getVuoroPelaaja1() + "]", 0);
        pisteet1.setEditable(false);
        label3.add(pisteet1);
        p3.add(label3);
    }

    private void alustaPisteetP4Oikea(Image image2) {
        p4 = new JPanel(new BorderLayout());
        p4.setPreferredSize(new Dimension(120, 61));
        JLabel label2 = new JLabel(new ImageIcon(image2));
        label2.setLayout(new GridBagLayout());
        p4.add(label2);
        pisteet2 = new JTextField("[" + game.getVuoroPelaaja2() + "]" + "  " + game.getPelaajan2nimi() + "  " + game.getPelaaja2(), 0);
        pisteet2.setEditable(false);
        label2.add(pisteet2);
        p4.add(label2);
    }

    private void alustaP5Keski(Image image) {
        p5 = new JPanel(new BorderLayout());
        JLabel label = new JLabel(new ImageIcon(image));
        p5.add(label);
    }
    
    private void luoMenubar() {
        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help1 = new JMenu("Help");

        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem help2 = new JMenuItem("Rules");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem tilastot = new JMenuItem("Scores");

        file.add(newGame);
        file.add(settings);
        file.add(tilastot);
        help1.add(help2);

        mb.add(file);
        mb.add(help1);

        setJMenuBar(mb);
        newGame.addActionListener(new Kuuntelija());
        help2.addActionListener(new Kuuntelija());
        settings.addActionListener(new Kuuntelija());
        tilastot.addActionListener(new Kuuntelija());
    }

    /**
     * Luodaan 2 ensimmäistä paneelia p1 ja p2
     */
    private void luoPaneelit12() {
        p1 = new JPanel(new GridBagLayout());
        p1.setBackground(new Color(45, 17, 19));
        p2 = new JPanel(new GridBagLayout());
        p2.setBackground(new Color(165, 20, 10));

        c = new GridBagConstraints();
        c.insets = new Insets(8, 16, 8, 16);
        lisaaNapit(c);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);
    }

    /**
     * Luodaan paneelit p3, p4 ja p5
     */
    private void luoPaneelit345() {

        Image image = null;
        Image image2 = null;
        try {
            File f = new File("kalahaTeksti.jpg");
            File f2 = new File("kalahaTeksti2.jpg");
            image = ImageIO.read(f);
            image2 = ImageIO.read(f2);
        } catch (IOException e) {
        }
        alustaPisteetP3Vasen(image2);
        add(p3, BorderLayout.WEST);

        alustaPisteetP4Oikea(image2);
        add(p4, BorderLayout.EAST);

        alustaP5Keski(image);
        add(p5, BorderLayout.CENTER);

    }

    /**
     * Lisätään napit pelialustaan
     */
    private void lisaaNapit(GridBagConstraints c) {
        p1.add(b6, c);
        c.gridx = 1;
        c.gridy = 0;
        p1.add(b5, c);
        c.gridx = 2;
        c.gridy = 0;
        p1.add(b4, c);
        c.gridx = 3;
        c.gridy = 0;
        p1.add(b3, c);
        c.gridx = 4;
        c.gridy = 0;
        p1.add(b2, c);
        c.gridx = 5;
        c.gridy = 0;
        p1.add(b1, c);
        c.gridx = 6;
        c.gridy = 0;

        p2.add(b12, c);
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
    }

    /**
     * Luodaan napit
     */
    private void luoNapit() throws HeadlessException {
        napit = new ArrayList();
        b1 = new JButton(game.getKuppiString(1));
        napit.add(b1);
        b2 = new JButton(game.getKuppiString(2));
        napit.add(b2);
        b3 = new JButton(game.getKuppiString(3));
        napit.add(b3);
        b4 = new JButton(game.getKuppiString(4));
        napit.add(b4);
        b5 = new JButton(game.getKuppiString(5));
        napit.add(b5);
        b6 = new JButton(game.getKuppiString(6));
        napit.add(b6);
        b7 = new JButton(game.getKuppiString(7));
        napit.add(b7);
        b8 = new JButton(game.getKuppiString(8));
        napit.add(b8);
        b9 = new JButton(game.getKuppiString(9));
        napit.add(b9);
        b10 = new JButton(game.getKuppiString(10));
        napit.add(b10);
        b11 = new JButton(game.getKuppiString(11));
        napit.add(b11);
        b12 = new JButton(game.getKuppiString(12));
        napit.add(b12);

        int i = 1;
        for (JButton nappi : napit) {
            nappi.addActionListener(luoActionListener(this));
            nappi.setName("" + i);
            i++;

        }
    }

    /**
     * Luodaan actionListener joka on sama jokaiselle kupille 1-12
     */
    public ActionListener luoActionListener(final KalahaMain main) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent tapahtuma) {
                JButton tamaNappi = (JButton) tapahtuma.getSource();
                int nro = Integer.parseInt(tamaNappi.getName());
                game.pushButton(nro);
                
                pisteet1.setText(game.getPelaaja1String() + "  " + game.getPelaajan1nimi() + "  [" + game.getVuoroPelaaja1() + "]");
                pisteet2.setText("[" + game.getVuoroPelaaja2() + "]" + "  " + game.getPelaajan2nimi() + "  " + game.getPelaaja2String());


                if (game.getVoittaja() == 1) {
                    JOptionPane.showMessageDialog(null, "Onnittelut " + game.getPelaajan1nimi() + ", voitit pelin!");
                    game.alustaKupit(game.getKuulienMaara());
                }
                if (game.getVoittaja() == 2) {
                    JOptionPane.showMessageDialog(null, "Onnittelut " + game.getPelaajan2nimi() + ", voitit pelin!");
                    game.alustaKupit(game.getKuulienMaara());
                }
                if (game.getVoittaja() == 3) {
                    JOptionPane.showMessageDialog(null, "Peli päättyi tasan!");
                    game.alustaKupit(game.getKuulienMaara());
                }
                paivitaNapit();
                paivitaNimet();
            }
        };
    }

    /**
     * Päivittää graafisen käyttöliittymän napit
     */
    public void paivitaNapit() {
        for (JButton n : napit) {
            int nro = Integer.parseInt(n.getName());
            String teksti = game.getKuppiString(nro);
            n.setText(teksti);
        }
    }

    /**
     * Päiittää graafisen käyttöliitymä nimet
     */
    public void paivitaNimet() {
        pisteet1.setText(game.getPelaaja1() + "  " + game.getPelaajan1nimi() + "  [" + game.getVuoroPelaaja1() + "]");
        pisteet2.setText("[" + game.getVuoroPelaaja2() + "]" + "  " + game.getPelaajan2nimi() + "  " + game.getPelaaja2());
    }

    /**
     * Luokka joka kuuntelee yläpaneelin valintoja
     */
    private class Kuuntelija implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent tapahtuma) {
            JMenuItem item = (JMenuItem) tapahtuma.getSource();
            String nimi = item.getText();

            if (nimi.equals("Rules")) {
                runRules();
            } else if (nimi.equals("Settings")) {
                runSettings();
            } else if (nimi.equals("New Game")) {
                runNewGame();
            } else if (nimi.equals("Scores")) {
                runScores();
            }
        }

        private void runRules() {
            JOptionPane.showMessageDialog(null, "Pelin tavoite on saada kuulat siirtymään pelilaudalla " + "\n"
                    + "niin, että mahdollisimman moni kuula päätyy omaan kulhoon. Pelilaudan yläpuolella on pelaajan 1 " + "\n"
                    + "kipot, alapuolella pelaajan 2 kipot. Kuulat siirtyvät vastapäivään " + "\n"
                    + "yksi kuula per kippo. Jos pelaajan viimeisin kuula päätyy hänen omaan kulhoon, hän saa uuden " + "\n"
                    + "pelivuoron. Jos pelaajan viimeisin kuula päätyy hänen omaan kippoonsa joka on tyhjä ja vastapuolen" + "\n"
                    + " kipossa on kuulia, pelaaja saa vastapuolen pelaajan kuulat itselleen sekä uuden vuoron. Peli päättyy kun " + "\n"
                    + "pelilaudalla kuulat loppuvat jommalta kummalta puolelta. Silloin se, jonka puolella on kuulia saa kaikki kuulat " + "\n"
                    + "itselleen. Pelin voittaa se, jolla on enemmän kuulia.");
        }

        private void runSettings() {
            int maara = 6;
            int valintaKupit1 = JOptionPane.showConfirmDialog(null, "Haluatko vaihtaa kuulien määrää per kuppi ja aloittaa uuden pelin?", null, JOptionPane.YES_NO_OPTION);
            if (valintaKupit1 == 0) {
                String valintaKupit2 = JOptionPane.showInputDialog(null, "Montako kuulaa (4-10) laitetaan per kuppi?");
                while (!valintaKupit2.equals("4") && !valintaKupit2.equals("5") && !valintaKupit2.equals("6")
                        && !valintaKupit2.equals("7") && !valintaKupit2.equals("8") && !valintaKupit2.equals("9")
                        && !valintaKupit2.equals("10")) {
                    JOptionPane.showMessageDialog(null, "Et antanut lukua väliltä 4-10");

                    valintaKupit2 = JOptionPane.showInputDialog(null, "Montako kuulaa (4-10) laitetaan per kuppi?");
                }
                maara = Integer.parseInt(valintaKupit2);
                JOptionPane.showMessageDialog(null, "Valitsit " + maara + " kuulaa");
                game.alustaKupit(maara);
                paivitaNapit();
            }
            int valintaNimet = JOptionPane.showConfirmDialog(null, "Haluatko vaihtaa pelaajien nimiä?", null, JOptionPane.YES_NO_OPTION);
            if (valintaNimet == 0) {
                String nimi1 = JOptionPane.showInputDialog(null, "Anna pelaajan 1 nimi, enintään 6 merkkiä");
                while (nimi1.length() <= 0 || nimi1.length() > 6) {
                    JOptionPane.showMessageDialog(null, "Annoit väärän pituisen nimen");
                    nimi1 = JOptionPane.showInputDialog(null, "Anna pelaajan 1 nimi, enintään 6 merkkiä");
                }
                String nimi2 = JOptionPane.showInputDialog(null, "Anna pelaajan 2 nimi, enintään 6 merkkiä");
                while (nimi2.length() <= 0 || nimi2.length() > 6) {
                    JOptionPane.showMessageDialog(null, "Annoit väärän pituisen nimen");
                    nimi2 = JOptionPane.showInputDialog(null, "Anna pelaajan 2 nimi, enintään 6 merkkiä");
                }
                JOptionPane.showMessageDialog(null, "Pelaajan 1: " + nimi1 + ", pelaajan 2 nimi: " + nimi2);
                game.setNimet(nimi1, nimi2);
                paivitaNimet();
            }
        }

        private void runNewGame() {
            game.alustaKupit(game.getKuulienMaara());
            paivitaNapit();
            paivitaNimet();
        }

        private void runScores() {
            JOptionPane.showMessageDialog(null, game.getTilastot());
        }
    }

    public static void main(String[] args) {
       new KalahaMain();
    }
}