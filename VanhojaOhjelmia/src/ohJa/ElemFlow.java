package ohJa;
import javax.swing.*;
import java.awt.*;

public class ElemFlow extends JFrame {

  // käyttöliittymäkentät:

  private JTextField kentta;
  private JButton nappula;
  private JTextArea alue;
  private JLabel otsikko;
  private JTextField viestikentta;

  public ElemFlow() { // konstruktori!

    // luodaan käyttöliittymäelementit, oliot:

    kentta = new JTextField("Kentta");
    nappula = new JButton("Nappula");
    alue = new JTextArea(1,2);
    otsikko = new JLabel("Otsikko");

    viestikentta = new JTextField("Viestikentta");
    viestikentta.setEditable(false);  // muuttamattomaksi!

    // valitaan asemointi:

    setLayout(new FlowLayout());

    // lisätään kentät näkymään:

    add(kentta);
    add(nappula);
    add(alue);
    add(otsikko);
    add(viestikentta);
  }

  public static void main(String args[]) {
    ElemFlow ikkuna = new ElemFlow();
    ikkuna.setTitle("ElemFlow");
    ikkuna.pack();
    ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ikkuna.setVisible(true);
  }
}
