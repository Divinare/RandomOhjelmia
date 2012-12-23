import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;

public class aantenTestaus {

    private Clip aani;
    private boolean aanetPaalla;

    public aantenTestaus() {
        this.aanetPaalla = true;
        
    }
    private void haeMusiikki() {
        URL url = aantenTestaus.class.getResource("gameover.wav");

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            this.aani = AudioSystem.getClip();
            this.aani.open(audioInputStream);

            FloatControl gainControl = (FloatControl)this.aani.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); 
        } catch (Exception e) {
            System.out.println("Äänitiedoston avaus ei onnistunut.");
            this.aanetPaalla = false; } }

    public void soitaAani() {
        if (aanetPaalla) {
            System.out.println("jee");
            try {
                this.aani.start(); } catch (Exception e) {} } } 


    public static void main(String[] args) {
        aantenTestaus aani = new aantenTestaus();
        aani.haeMusiikki();
        aani.soitaAani();
    }
}
    
