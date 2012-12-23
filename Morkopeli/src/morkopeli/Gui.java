package morkopeli;

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

public class Gui extends JFrame {

    private JFrame frame;
    private int SivunPituus;
    private Luola game;
    private JLabel siirrot;
    private Piirtoalusta piirtoalusta; // "pelikenttä"

    public Gui(int SivunPituus, Luola luola) {
        this.SivunPituus = SivunPituus;
        this.game = luola;
    }

//    @Override
    public void run() {
        frame = new JFrame("Luolapeli");
        // game.getKentta().length * 35 + 15, game.getKentta().length * 35 + 77)
        frame.setPreferredSize(new Dimension(900, 950));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        BorderLayout layout = new BorderLayout();
        container.setLayout(layout);

//        if (game == null) { // luodaan peli
        this.siirrot = new JLabel("Time: - Boogies: - Score: - ");
        container.add(siirrot, BorderLayout.SOUTH);
        Nappaimistonkuuntelija kuuntelija = new Nappaimistonkuuntelija(game.getPelaaja(), game);
        frame.addKeyListener(kuuntelija);

//        if (piirtoalusta == null) {
        System.out.println("luotiin piirtoalusta");
        piirtoalusta = new Piirtoalusta(game, SivunPituus, siirrot);
        container.add(piirtoalusta);
        container.add(luoMenubar(), BorderLayout.NORTH);
        if (getPaivitettava() == null) {
            System.out.println("wtf?????????");

        }
    }

    private JMenuBar luoMenubar() {
        JMenuBar mb = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu sound = new JMenu("Sound");

        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem tilastot = new JMenuItem("Scores");
        JMenuItem help2 = new JMenuItem("Rules");
        JMenuItem soundOn = new JMenuItem("On");
        JMenuItem soundOff = new JMenuItem("Off");

        file.add(newGame);
        file.add(settings);
        file.add(tilastot);
        help.add(help2);
        sound.add(soundOn);
        sound.add(soundOff);

        mb.add(file);
        mb.add(help);
        mb.add(sound);

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

    public void setGame(Luola luola) {
        this.game = luola;
    }
}