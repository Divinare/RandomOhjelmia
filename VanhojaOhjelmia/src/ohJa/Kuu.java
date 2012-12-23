package ohJa;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kuu extends JApplet {

  // kenttä koneelle, joka osaa kuukausien käsittelyn monimutkaisen logiikan:

  private Kuulaskuri kuuKone;

  // käyttöliittymäkentät:

  private JTextField kuuikkuna;
  private JTextField vuosiikkuna;
  private JButton    kuunappula;
  private JButton    kuunnollaus;
  private JButton    lopetus;
  private static String[] kuunNimet = { // kaikille olioille sama!
        "tammi", "helmi", "maalis", "huhti", "touko", "kesä",
        "heinä", "elo", "syys", "loka", "marras", "joulu"};
  
  
  public void init() { // konstruktori!

    // luodaan monimutkaisen laskentalogiikan osaava kone:

    kuuKone = new Kuulaskuri();
    // käyttöliittymäoliot alkutilaansa:

    vuosiikkuna = new JTextField();
    vuosiikkuna.setEditable(false);
    vuosiikkuna.setText(kuuKone.mikaVuosi());
    
    kuuikkuna  = new JTextField();
    kuuikkuna.setEditable(false);  // kirjoituskielto käyttäjälle
    kuuikkuna.setText(kuunNimet[kuuKone.moneskoKuu()-1] + "kuu");

    kuunappula  = new JButton("Seuraava!");
    kuunnollaus = new JButton(" Alkuun!");
    lopetus     = new JButton(" Lopetus!");

    // tapahtumankuuntelijoiden asetukset:

    kuunappula.addActionListener(   // kuukausi etenee
      new ActionListener () {
        public void actionPerformed(ActionEvent tapahtuma) {
          kuuKone.seuraavaKuu();
          kuuikkuna.setText(kuunNimet[kuuKone.moneskoKuu()-1] + "kuu");
          if (kuuKone.moneskoKuu()==1)
            JOptionPane.showMessageDialog(null, "Hyvää uutta vuotta!"); // ponnahdus!
          vuosiikkuna.setText(kuuKone.mikaVuosi());
        }
      }
    );

    kuunnollaus.addActionListener(  // aloitetaan puhtaalta pöydältä uudella laskurilla
      new ActionListener () {
        public void actionPerformed(ActionEvent tapahtuma) {
          kuuKone = new Kuulaskuri();
          kuuikkuna.setText(kuunNimet[kuuKone.moneskoKuu()-1] + "kuu");
          vuosiikkuna.setText(kuuKone.mikaVuosi());
        }
      }
    );

    lopetus.addActionListener(   // varmistetaan lopetushalu ponnahdusikkunalla
      new ActionListener () {
        public void actionPerformed(ActionEvent tapahtuma) {
          if (JOptionPane.showConfirmDialog(null, "Lopetetaanko todella?")==0) // ponnahdus!
            System.exit(0);
        }
      }
    );

    // valitaan asemointityyli:

     setLayout(new GridLayout(5,1));  // rivejä - sarakkeita

    // käyttöliittymäelementit näkyviin valitussa lay-outissa:

    add(vuosiikkuna);
    add(kuuikkuna);
    add(kuunappula);
    add(kuunnollaus);
    add(lopetus);
  }

//  public static void main(String args[]) {
//    Kuu ikkuna = new Kuu();
//    ikkuna.setTitle("Kuu");
//    ikkuna.pack();
//    ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // osaa loppua
//    ikkuna.setVisible(true); // olio näkyviin
//  }
}