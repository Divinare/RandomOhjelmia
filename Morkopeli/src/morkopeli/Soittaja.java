package morkopeli;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Soittaja {

    private Clip aani;
    private boolean aanetPaalla = true;

    /*
     * Olettaa filename -tiedoston olevan samassa pakkauksessa kuin Soittaja (this)
     * 
     */
    private void haeMusiikki(String filename) { // "sounds\\" +
        URL url = morkopeli.Soittaja.class.getResource(filename + ".wav");
        if (url == null) {
            throw new RuntimeException("no audio file");
        }
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            this.aani = AudioSystem.getClip();
            this.aani.open(audioInputStream);
            FloatControl gainControl = (FloatControl) this.aani.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);

        } catch (Exception e) {
            throw new RuntimeException("Ei ääniä " + url.getPath());
        }
    }

    public void play(String filename) {
        haeMusiikki(filename);
        if (aanetPaalla) {
            try {
                this.aani.start();
            } catch (Exception e) {
            }
        }
    }
    
    public void stop() {
        this.aani.stop();
    }
    
    public boolean soitetaankoTallaHetkellaMitaan(){
        return this.aani.isRunning();
    }
}
