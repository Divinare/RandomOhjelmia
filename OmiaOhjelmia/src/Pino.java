
public class Pino {

    private PinoSolmu top; // viite pinon päällä olevaan alkioon

    public Pino() {
        top = null;
    } // alussa pinossa ei ole mitään

    public void push(int k) {
// uuden solmun x kentille asetetaan arvot konstruktorissa
// uuden huipun alapuolella siis pinon vanha huippu
        PinoSolmu x = new PinoSolmu(k, top);
        top = x; // asetetaan uusi solmu x huipuksi
    }

    public int pop() {
        PinoSolmu x = top;
        top = x.next; // uusi huippu on vanhan huipun x alla oleva solmu
        return x.key;
    }

    public boolean empty() {
        return (top == null);
    }

    public static void main(String[] args) {
        Pino pino = new Pino();
        pino.push(10);
        pino.push(15);
        pino.push(8);
        System.out.println("pinon top" + pino.top);
        while (!pino.empty()) {
            System.out.println(" pinosta otettiin: " + pino.pop());
        }
    }
}