package ohJa;
public class KuulaskuriPlusPlus extends KuulaskuriPlus {

  private int vuosi;

  public KuulaskuriPlusPlus(int vuosi) {
    this.vuosi = vuosi;
  }

//  public int mikaVuosi() {
//    return this.vuosi;
//  }

    @Override
  public void seuraavaKuu() {  // Syrjäyttää perityn!
    super.seuraavaKuu();       // Kasvatetaan kuukautta.
    if (moneskoKuu() == 1)     // Syrjäyttämättömiä perittyjä aksessoreita
      vuosi = vuosi + 1;       // voi kutsua sellaisinaan suoraan.
  }

    @Override
  public String toString() {   // Syrjäyttää perityn!
    return super.toString() + ", " + vuosi;
  }
}