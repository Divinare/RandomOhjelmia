
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Matopeli matopeli;
    private int palanSivunPituus;

    public Piirtoalusta(Matopeli matopeli, int palanSivunPituus) {
        super.setBackground(Color.BLACK);
        this.matopeli = matopeli;
        this.palanSivunPituus = palanSivunPituus;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        piirraMato(graphics);
        piirraOmena(graphics);
    }

    private void piirraMato(Graphics graphics) {
        graphics.setColor(Color.PINK);
        Mato mato = matopeli.getMato();
        for (Pala pala : mato.getPalat()) {
            graphics.fill3DRect(
                    pala.getX() * this.palanSivunPituus,
                    pala.getY() * this.palanSivunPituus,
                    this.palanSivunPituus,
                    this.palanSivunPituus,
                    true);
        }
    }

    private void piirraOmena(Graphics graphics) {
        Omena omena = matopeli.getOmena();
        graphics.setColor(Color.RED);
        graphics.fillOval(
                omena.getX() * this.palanSivunPituus,
                omena.getY() * this.palanSivunPituus,
                this.palanSivunPituus,
                this.palanSivunPituus);
    }

    @Override
    public void paivita() {
        repaint();
    }
}