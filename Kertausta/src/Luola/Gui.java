package Luola;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.io.*;
import java.awt.Toolkit;
import javax.swing.*;

public class Gui extends JFrame implements Runnable {

    private JFrame frame;
    private int SivunPituus;
    private Luola game;
    private Piirtoalusta piirtoalusta;

    public Gui(int SivunPituus, Luola luola) {
        this.SivunPituus = SivunPituus;
        this.game = luola;
    }

    @Override
    public void run() {
        frame = new JFrame("Luolapeli");
        frame.setPreferredSize(new Dimension(715, 738));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta(game, SivunPituus);
        
        BorderLayout layout = new BorderLayout();
        container.setLayout(layout);

        JLabel siirrot = new JLabel("Mörköjä jäljellä: " + game.getHirvio().size());
        container.add(siirrot, BorderLayout.SOUTH);
        container.add(piirtoalusta);
        container.add(luoMenubar(), BorderLayout.NORTH);
        Nappaimistonkuuntelija kuuntelija = new Nappaimistonkuuntelija(game.getPelaaja());
        frame.addKeyListener(kuuntelija);
    }

        private JMenuBar luoMenubar() {
        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");

        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem tilastot = new JMenuItem("Scores");
        JMenuItem help2 = new JMenuItem("Rules");

        file.add(newGame);
        file.add(settings);
        file.add(tilastot);
        help.add(help2);

        mb.add(file);
        mb.add(help);
        
        newGame.addActionListener(new MenubarKuuntelija());
        help2.addActionListener(new MenubarKuuntelija());
        settings.addActionListener(new MenubarKuuntelija());
        tilastot.addActionListener(new MenubarKuuntelija());
        return mb;
    }
    public Paivitettava getPaivitettava() {
        return piirtoalusta;
    }
    
    public JFrame getFrame() {
        return frame;
    }

//    public static void main(String[] args) {
//        Gui kayttoliittyma = new Gui();
//    }
}