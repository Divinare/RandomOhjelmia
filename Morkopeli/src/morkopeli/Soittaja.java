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

    private void haeMusiikki(String filename) {

        URL url = Soittaja.class.getResource(filename);
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            this.aani = AudioSystem.getClip();
            this.aani.open(audioInputStream);
            FloatControl gainControl = (FloatControl) this.aani.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);

        } catch (Exception e) {
            System.out.println("Äänitiedoston avaus ei onnistunut.");
            throw new RuntimeException("Ei ääniä"+ new File(filename).getAbsolutePath());
//            this.aanetPaalla = false;
        }
    }
    
    public void soitaAani(String filename){
        haeMusiikki(filename);
        soitaAani();
    }

    public void soitaAani() {
        if (aani == null) {
            haeMusiikki("muumimusaa1.wav");
        
        }
        if (aanetPaalla) {
            try {
                this.aani.start();
            } catch (Exception e) {
            }
        }
    }
    
    public void play(){
        soitaAani();
    }
    
    public void play(String filename){
        soitaAani(filename);
    }
}
