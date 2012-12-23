//
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Random;
//import javax.imageio.ImageIO;
//import javax.swing.JApplet;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//
//public class Ristinolla extends JApplet {
//
//    Ristinolla ristinolla;
//    Pelilauta pelilauta;
//
//    @Override
//    public void init() {
//        ristinolla = new Ristinolla();
//        pelilauta = new Pelilauta();
//        ristinolla.add(pelilauta);
//        ristinolla.setVisible(true);
//        setLayout(new BorderLayout());
//        add("Center", pelilauta);
//        setSize(875, 875);
//
//    }
//
//    private static class Pelilauta extends Canvas {
//
//        private boolean gameRunning;
//        private boolean xTurn;
//        private static final int Tyhjä = 0, X_PALA = 1, O_PALA = 2;
//        private int gameResult; // 0 = peli ei ole päättynyt, 1 = X voitti, 2 = O voitti
//        private int[][] Ruudukko = new int[25][25]; // Pelin ruudukko
//        private int[] koordinaattiA = new int[2]; // kertoo mistä kohtaa 5. tai useamman suora lähtee
//        private int[] koordinaattiB = new int[2]; // -||- päättyy
//        private Random random; // Vuoron arpomiseen
//        private int voitotX;
//        private int voitotO;
//        Pelilauta.GameListener gamelistener;
//        Ristinolla ristinolla;
//        Pelilauta pelilauta;
//        Image xImage;
//        Image oImage;
//        Image oWins;
//        Image xWins;
//        Image Xturn;
//        Image Oturn;
//        Image newgame;
//        InputStream oturn;
//        InputStream xturn;
//        InputStream gameover;
//        AudioStream oturnA;
//        AudioStream xturnA;
//        AudioStream gameoverA;
//
//        public class GameListener implements MouseListener {
//
////            public void init() {
////                System.out.println("käytii gamelistener");
////            }
//            public void mouseClicked(MouseEvent e) {
//
//                int xPos = e.getPoint().x;
//                int yPos = e.getPoint().y;
//
//                if (xPos > 100 && yPos > 100 && xPos < 725 && yPos < 725) {
//                    if (gameRunning == false) {
//                        // Uusi Peli
//                        tyhjennaRuudukko();
//                        gameRunning = true;
//                        repaint();
//                        return;
//                    }
//                    if (Ruudukko[(xPos - 100) / 25][(yPos - 100) / 25] != Tyhjä) {
//                        return;
//                    }
//                    if (xTurn) {
//                        Ruudukko[(xPos - 100) / 25][(yPos - 100) / 25] = X_PALA;
//                        AudioPlayer.player.start(oturnA);
//                        xTurn = false;
//                    } else {
//                        Ruudukko[(xPos - 100) / 25][(yPos - 100) / 25] = O_PALA;
//                        AudioPlayer.player.start(xturnA);
//                        xTurn = true;
//                    }
//                    gameOver((xPos - 100) / 25, (yPos - 100) / 25);
//                    if (gameResult != 0) {
//                        gameRunning = false;
//                        AudioPlayer.player.start(gameoverA);
//                    }
//                    repaint();
//
//                }
//                if (xPos > 700 && xPos < 781 && yPos > 50 && yPos < 61) {
//                    tyhjennaRuudukko();
//                    voitotX = 0;
//                    voitotO = 0;
//                    repaint();
//                }
//            }
//
//            public void mouseReleased(MouseEvent e) {
//            }
//
//            public void mouseEntered(MouseEvent e) {
//            }
//
//            public void mouseExited(MouseEvent e) {
//            }
//
//            public void mousePressed(MouseEvent e) {
//            }
//        }
//
//        public Pelilauta() {
//            setPreferredSize(new Dimension(825, 825));
//            addMouseListener(new Pelilauta.GameListener());
//            setBackground(Color.WHITE);
//            random = new Random();
//            gameRunning = true;
//
//        }
//
//        public void readFiles() {
//            try {
//                File f = new File("risti.jpg");
//                File f2 = new File("nolla.jpg");
//                File f3 = new File("xWins.jpg");
//                File f4 = new File("oWins.jpg");
//                File f5 = new File("Xturn.jpg");
//                File f6 = new File("Oturn.jpg");
//                File f7 = new File("Newgame.jpg");
//                xImage = ImageIO.read(f);
//                oImage = ImageIO.read(f2);
//                xWins = ImageIO.read(f3);
//                oWins = ImageIO.read(f4);
//                Xturn = ImageIO.read(f5);
//                Oturn = ImageIO.read(f6);
//                newgame = ImageIO.read(f7);
//            } catch (IOException e) {
//            }
//            try {
//                xturn = new FileInputStream("xturn.wav");
//                oturn = new FileInputStream("oturn.wav");
//                gameover = new FileInputStream("gameover.wav");
//            } catch (Exception e) {
//                System.out.println("Ei löytynyt InputStreamia");
//            }
//            try {
//                xturnA = new AudioStream(xturn);
//                oturnA = new AudioStream(oturn);
//                gameoverA = new AudioStream(gameover);
//            } catch (Exception e) {
//                System.out.println("Ei löytynyt AudioStreamia");
//            }
//        }
//
//        public void tyhjennaRuudukko() {
//            for (int y = 0; y < 25; y++) {
//                for (int x = 0; x < 25; x++) {
//                    Ruudukko[x][y] = Tyhjä;
//                }
//            }
//            gameResult = 0; // Peli ei ole päättynyt
//            // Aseta vuoro
//            if (random.nextInt(100) < 50) {
//                xTurn = true;
//            } else {
//                xTurn = false;
//            }
//        }
//
//        public void gameOver(int x, int y) {
//            int ketju; // Mittaa tuleeko 5 peräkkäin
//            int luku; // Joko 1 (risti) tai 2 (nolla)
//            ketju = 1; // 1, koska keskimmäistä ei lasketa muuten
//            // Tarkastetaan löytyykö 5 peräkkäistä
//            luku = Ruudukko[x][y];
//            int d = 1;
//            // Jos ei edetä lainkaan johonkin suuntaan
//            // Vaaka-akseli:
//            // Edetään oikealle
//            if (x + d >= 0 && x + d < 25) {
//                if (Ruudukko[x + d][y] == luku) {
//                    while (x + d >= 0 && x + d < 25 && Ruudukko[x + d][y] == luku) {
//                        ketju++;
//                        d++;
//                    }
//                }
//            }
//            d = 1;
//            // Edetään vasemmalle
//            if (x - d >= 0 && x - d < 25) {
//                if (Ruudukko[x - d][y] == luku) {
//                    while (x - d >= 0 && x - d < 25 && Ruudukko[x - d][y] == luku) {
//                        ketju++;
//                        d++;
//                    }
//                }
//            }
//            if (ketju >= 5) {
//                gameResult = luku;
//
////                for (int i = 0; i < 25; i++) {
////                    for (int j = 0; j < 25; j++) {
////                        System.out.print(Ruudukko[j][i] + " ");
////                    }
////                    System.out.println("");
////                }
//
//
//                selvitaPisteet("vaaka", x, y, luku);
//                return;
//            } else {
//                ketju = 1;
//            }
//            // Pystyakseli
//            // Edetään oikealle
//            if (y + d >= 0 && y + d < 25) {
//                if (Ruudukko[x][y + d] == luku) {
//                    while (y + d >= 0 && y + d < 25 && Ruudukko[x][y + d] == luku) {
//                        ketju++;
//                        d++;
//                    }
//                }
//            }
//            d = 1;
//            // Edetään vasemmalle
//            if (y - d >= 0 && y - d < 25) {
//                if (Ruudukko[x][y - d] == luku) {
//                    while (y - d >= 0 && y - d < 25 && Ruudukko[x][y - d] == luku) {
//                        ketju++;
//                        d++;
//                    }
//                }
//            }
//            if (ketju >= 5) {
//                gameResult = luku;
//                selvitaPisteet("pysty", x, y, luku);
//                return;
//            } else {
//                ketju = 1;
//            }
//            // Viisto vasen ylä -> oikea ala
//            // -
//            if (x + d >= 0 && x + d < 25 && y + d >= 0 && y + d < 25) {
//                if (Ruudukko[x + d][y + d] == luku) {
//                    while (x + d >= 0 && x + d < 25 && y + d >= 0 && y + d < 25 && Ruudukko[x + d][y + d] == luku) {
//                        ketju++;
//                        d++;
//                    }
//                }
//            }
//            d = 1;
//            // -
//            if (x - d >= 0 && x - d < 25 && y - d >= 0 && y - d < 25) {
//                if (Ruudukko[x - d][y - d] == luku) {
//                    while (x - d >= 0 && x - d < 25 && y - d >= 0 && y - d < 25 && Ruudukko[x - d][y - d] == luku) {
//                        ketju++;
//                        d++;
//                    }
//                }
//            }
//            if (ketju >= 5) {
//                gameResult = luku;
//                return;
//            } else {
//                ketju = 1;
//            }
//
//            // Viisto vasen ala -> oikea ylä
//            // -
//            if (x + d >= 0 && x + d < 25 && y - d >= 0 && y - d < 25) {
//                if (Ruudukko[x + d][y - d] == luku) {
//                    while (x + d >= 0 && x + d < 25 && y - d >= 0 && y - d < 25 && Ruudukko[x + d][y - d] == luku) {
//                        ketju++;
//                        d++;
//                    }
//                }
//            }
//            d = 1;
//            // -
//            if (x - d >= 0 && x - d < 25 && y + d >= 0 && y + d < 25) {
//                if (Ruudukko[x - d][y + d] == luku) {
//                    while (x - d >= 0 && x - d < 25 && y + d >= 0 && y + d < 25 && Ruudukko[x - d][y + d] == luku) {
//                        ketju++;
//                        d++;
//                    }
//                }
//            }
//            if (ketju >= 5) {
//                gameResult = luku;
//            } else {
//                ketju = 1;
//            }
//        }
//
//        public void selvitaPisteet(String suunta, int x, int y, int luku) {
//            int d = 1;
//            koordinaattiA[0] = x;
//            koordinaattiA[1] = y;
//            koordinaattiB[0] = x;
//            koordinaattiB[1] = y;
//            if (suunta.equals("vaaka")) {
//                while (x + d >= 0 && x + d < 25 && Ruudukko[x + d][y] == luku) {
//                    d++;
//                }
//                koordinaattiA[0] = x + d;
//                koordinaattiA[1] = y;
//                d = 1;
//                while (x - d >= 0 && x - d < 25 && Ruudukko[x - d][y] == luku) {
//                    d++;
//                }
//                koordinaattiB[0] = x - d + 1;
//                koordinaattiB[1] = y;
//            }
//            d = 1;
//            if (suunta.equals("pysty")) {
//                while (y + d >= 0 && y + d < 25 && Ruudukko[x][y + d] == luku) {
//                    d++;
//                }
//                koordinaattiA[0] = x;
//                koordinaattiA[1] = y + d;
//                d = 1;
//                while (y - d >= 0 && y - d < 25 && Ruudukko[x][y - d] == luku) {
//                    d++;
//                }
//                koordinaattiB[0] = x;
//                koordinaattiB[1] = y - d;
//            }
//            d = 1;
//        }
//
//        public void piirra(Graphics g) {
//            // g.drawLine(x1, y1, x2, y2);
//            g.drawLine((koordinaattiA[0] * 25) + 105, (koordinaattiA[1] * 25) + 111, (koordinaattiB[0] * 25) + 100, (koordinaattiB[1] * 25) + 111);
//            g.drawLine((koordinaattiA[0] * 25) + 100, (koordinaattiA[1] * 25) + 112, (koordinaattiB[0] * 25) + 100, (koordinaattiB[1] * 25) + 112);
//            g.drawLine((koordinaattiA[0] * 25) + 105, (koordinaattiA[1] * 25) + 113, (koordinaattiB[0] * 25) + 100, (koordinaattiB[1] * 25) + 113);
//            System.out.println("piirrän kohtiin:");
//            System.out.println(koordinaattiA[0] + "   " + ((koordinaattiA[0] * 25) + 100));
//            System.out.println(koordinaattiA[1] + "   " + ((koordinaattiA[1] * 25) + 100));
//            System.out.println(koordinaattiB[0] + "   " + ((koordinaattiB[0] * 25) + 100));
//            System.out.println(koordinaattiB[1] + "   " + ((koordinaattiB[1] * 25) + 100));
//        }
//
//        public void paint(Graphics g) {
//            String Voitotx = Integer.toString(voitotX);
//            String Voitoto = Integer.toString(voitotO);
//            g.drawString("Player X's wins: " + Voitotx, 700, 20);
//            g.drawString("Player O's wins: " + Voitoto, 700, 35);
//            g.drawImage(newgame, 700, 50, this);
//            g.setColor(Color.BLACK);
//            readFiles();
//            // Ruudukon piirto:
//            for (int y = 0; y <= 25; y++) {
//                g.drawLine(100, y * 25 + 100, 100 + 25 * 25, y * 25 + 100);
//            }
//            for (int x = 0; x <= 25; x++) {
//                g.drawLine(x * 25 + 100, 100, x * 25 + 100, 100 + 25 * 25);
//            }
//            // X ja Y piirto:
//            for (int y = 0; y < 25; y++) {
//                for (int x = 0; x < 25; x++) {
//                    if (Ruudukko[x][y] == X_PALA) {
//                        g.drawImage(xImage, (x * 25 + 100) + 1, (y * 25 + 100) + 1, this);
//                    }
//                    if (Ruudukko[x][y] == O_PALA) {
//                        g.drawImage(oImage, (x * 25 + 100) + 1, (y * 25 + 100) + 1, this);
//                    }
//                }
//            }
//            if (gameRunning) {
//                if (xTurn) {
//                    g.drawImage(Xturn, 30, 30, this);
//                } else {
//                    g.drawImage(Oturn, 30, 29, this);
//                }
//            } else {
//                // End message
//                if (gameResult == X_PALA) {
//                    g.drawImage(xWins, 30, 30, this);
//                    voitotX++;
//                    piirra(g);
//                }
//                if (gameResult == O_PALA) {
//                    g.drawImage(oWins, 30, 30, this);
//                    voitotO++;
//                    piirra(g);
//                }
//            }
//        }
//
//        public int[][] getRuudukko() {
//            return Ruudukko;
//        }
//    }
//}
////public static void main(String[] args) {
////Ristinolla ristinolla = new Ristinolla();
////Pelilauta pelilauta = new Pelilauta(); 
////ristinolla.add(pelilauta);
////ristinolla.setVisible(true);
////}
//// 
////Ristinolla ristinolla = new Ristinolla();
////ristinolla.setTitle("Ristinolla"); 
////ristinolla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////Pelilauta pelilauta = new Pelilauta()
////;ristinolla.add(pelilauta);
////ristinolla.pack(); 
////ristinolla.setLocationRelativeTo(null);
////ristinolla.setVisible(true);