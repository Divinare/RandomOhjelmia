
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class aantenTestaus extends Thread {

    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 



    public aantenTestaus(String wavfile) {


    }

    public void play(String filename) {

        File soundFile = new File(filename);
        if (!soundFile.exists()) {
            System.err.println("Wave file not found: " + filename);
            return;
        }

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            auline.drain();
            auline.close();
        }

    }

    public static void main(String[] args) {
        aantenTestaus testi = new aantenTestaus("muumimusaa1.wav");
        testi.play("muumimusaa1.wav");
    }
}
//
//import java.io.File;
//import java.io.IOException;
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine;
//import javax.sound.sampled.FloatControl;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.SourceDataLine;
//import javax.sound.sampled.UnsupportedAudioFileException;
//
//public class aantenTestaus extends Thread {
//
//    private String filename;
//    private Position curPosition;
//    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 
//
//    enum Position {
//
//        LEFT, RIGHT, NORMAL
//    };
//
//    public aantenTestaus(String wavfile) {
//        filename = wavfile;
//        curPosition = Position.NORMAL;
//    }
//
//    public aantenTestaus(String wavfile, Position p) {
//        filename = wavfile;
//        curPosition = p;
//    }
//
//    public void run() {
//
//        File soundFile = new File(filename);
//        if (!soundFile.exists()) {
//            System.err.println("Wave file not found: " + filename);
//            return;
//        }
//
//        AudioInputStream audioInputStream = null;
//        try {
//            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
//        } catch (UnsupportedAudioFileException e1) {
//            e1.printStackTrace();
//            return;
//        } catch (IOException e1) {
//            e1.printStackTrace();
//            return;
//        }
//
//        AudioFormat format = audioInputStream.getFormat();
//        SourceDataLine auline = null;
//        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
//
//        try {
//            auline = (SourceDataLine) AudioSystem.getLine(info);
//            auline.open(format);
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//            return;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//
//        if (auline.isControlSupported(FloatControl.Type.PAN)) {
//            FloatControl pan = (FloatControl) auline
//                    .getControl(FloatControl.Type.PAN);
//            if (curPosition == Position.RIGHT) {
//                pan.setValue(1.0f);
//            } else if (curPosition == Position.LEFT) {
//                pan.setValue(-1.0f);
//            }
//        }
//
//        auline.start();
//        int nBytesRead = 0;
//        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
//
//        try {
//            while (nBytesRead != -1) {
//                nBytesRead = audioInputStream.read(abData, 0, abData.length);
//                if (nBytesRead >= 0) {
//                    auline.write(abData, 0, nBytesRead);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        } finally {
//            auline.drain();
//            auline.close();
//        }
//
//    }
//
//    public static void main(String[] args) {
//        aantenTestaus testi = new aantenTestaus("muumimusaa1.wav");
//        testi.start();
//        testi.stop();
//    }
//}
//import java.io.FileInputStream;
//import java.io.InputStream;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//
//public class aantenTestaus {
//
//    private InputStream muumimusaa1I;
//    private AudioStream muumimusaa1A;
//    private boolean aanetPaalla;
//    private double aika;
//
//    public aantenTestaus() {
//        this.aanetPaalla = true;
//        this.aika = 0;
//
//    }
//
//    private void haeMusiikki() {
//        try {
//            muumimusaa1I = new FileInputStream("sounds\\muumimusaa1.wav");
//        } catch (Exception e) {
//            System.out.println("Ei löytynyt InputStreamia");
//        }
//        System.out.println("hmm");
//        try {
//            muumimusaa1A = new AudioStream(muumimusaa1I);
//        } catch (Exception e) {
//            System.out.println("Ei löytynyt AudioStreamia");
//        }
//        AudioPlayer.player.start(muumimusaa1A);
//    }
//
//    public void soitaAani() {
//        if (aanetPaalla) {
//            aika = System.currentTimeMillis();
//            if (System.currentTimeMillis() + 10 < aika) {
//                AudioPlayer.player.stop(muumimusaa1A);
//                
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        aantenTestaus aani = new aantenTestaus();
//        aani.haeMusiikki();
//        System.out.println("soitetaan");
//        aani.soitaAani();
//        while (true) {
//            aani.soitaAani();
//        }
//
//    }
//}
//
//import javax.swing.*;
//import sun.audio.*;
//import java.awt.event.*;
//import java.io.*;
//
//public class aantenTestaus {
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setSize(200, 200);
//        JButton button = new JButton("Clcik me");
//        frame.add(button);
//        button.addActionListener(new AL());
//        frame.show(true);
//    }
//
//    public static class AL implements ActionListener {
//
//        public final void actionPerformed(ActionEvent e) {
//            music();
//        }
//    }
//
//    public static void music() {
//        AudioPlayer MGP = AudioPlayer.player;
//        AudioStream BGM;
//        AudioData MD;
//        ContinuousAudioDataStream loop = null;
//        try {
//            BGM = new AudioStream(new FileInputStream("src\\gameover.wav"));
//            MD = BGM.getData();
//            loop = new ContinuousAudioDataStream(MD);
//        } catch (IOException error) {
//            System.out.print("file not found");
//        }
//        MGP.start(loop);
//    }
//}