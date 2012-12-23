package ohJa;
import javax.swing.*;
import java.awt.event.*;
import java.awt.font.*;  // vain fonttia varten
import java.awt.*;       //        "

public class HiiriTuliJaMeni extends JFrame
                                implements MouseListener {

  private JTextArea alue = new JTextArea (1, 11);
  private int puremia = 0;

  public HiiriTuliJaMeni() {

    alue.setEditable(false);
    add ("Center", alue);

    alue.setFont(new Font("Serif", Font.BOLD, 20));
    alue.setText(" Hiirtä ei ole näkynyt.");

    alue.addMouseListener(this);  // luokka ITSE toteuttaa
                                  // kuuntelijan
  }

  public void mouseEntered(MouseEvent tapahtuma) {
    alue.setText("      Hiiri tuli!");
  }
   public void mouseExited(MouseEvent tapahtuma) {
    alue.setText("      Hiiri meni!");
  }

  public void mouseClicked(MouseEvent tapahtuma) {
    ++puremia;
    alue.setText(" Hiiri puri! ("+puremia+". kerta)"); 
  }

  // KAIKKI luvatut metodit on toteutettava (edes tyhjinä):

  public void mousePressed(MouseEvent tapahtuma)  { }
  public void mouseReleased(MouseEvent tapahtuma) { }


 public static void main(String[] args) {
        HiiriTuliJaMeni ikkuna = new HiiriTuliJaMeni();
        ikkuna.setTitle("Hiirielämää");
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.pack();
        ikkuna.setVisible(true);
    }
}