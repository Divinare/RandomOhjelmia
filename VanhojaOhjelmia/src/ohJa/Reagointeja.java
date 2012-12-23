package ohJa;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Reagointeja
               extends JFrame
               implements ComponentListener, MouseMotionListener, 
                          MouseListener, MouseWheelListener,
                          KeyListener, FocusListener {

   private TextArea alue =
     new TextArea ("\n    Reagoiva alue:\n\n" + 
                   "   Kokeile erilaisia\n" +
                   "   tapahtumia ja seuraa\n" +
                   "   systeemin ikkunaa!",
                   10, 20, TextArea.SCROLLBARS_NONE);

   private int laskuri = 0;
   private Label laskuriOtsikko = new Label("", Label.CENTER);

   public Reagointeja() {

      alue.setEditable(false);

      add ("Center", alue);
      add ("South", laskuriOtsikko);

      addComponentListener(this);

      alue.addFocusListener(this);
      alue.addKeyListener(this);
      alue.addMouseListener(this);
      alue.addMouseMotionListener(this);
      alue.addMouseWheelListener(this);
   }

   private void kirjaaTapahtuma(AWTEvent tapahtuma) {
      laskuri++;
      laskuriOtsikko.setText("Tapahtumia: " + laskuri + " kpl");
      System.out.println(tapahtuma.toString().substring(15));
      // ei tulosteta alkuosaa "java.awt.event."!
   }

   public void componentMoved(ComponentEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void componentHidden(ComponentEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void componentResized(ComponentEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   } 
   public void componentShown(ComponentEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void mouseDragged(MouseEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void mouseMoved(MouseEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void mousePressed(MouseEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void mouseReleased(MouseEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void mouseEntered(MouseEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void mouseExited(MouseEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void mouseClicked(MouseEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void keyPressed(KeyEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void keyReleased(KeyEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void keyTyped(KeyEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void focusGained(FocusEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void focusLost(FocusEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }
   public void mouseWheelMoved(MouseWheelEvent tapahtuma) {
      kirjaaTapahtuma(tapahtuma);
   }

   public static void main(String args[]) {
        Reagointeja ikkuna = new Reagointeja();
        ikkuna.setTitle("Tapahtuu ...");
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setSize(200, 200);
        ikkuna.setVisible(true);
    }
}