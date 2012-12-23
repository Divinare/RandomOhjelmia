import java.util.*;
public class Opiskelijalista {
    private static Scanner lukija = new Scanner(System.in);
    public static void main(String[] args) {
	ArrayList<Opiskelija> lista = new ArrayList<Opiskelija>();

	lista.add(new Opiskelija("Olli Olio", "013632164"));
	lista.add(new Opiskelija("Melissa Metodi", "013401780"));
	lista.add(new Opiskelija("Saku Silmukka", "015696234"));
	lista.add(new Opiskelija("Essi Ehto", "013924429"));
	lista.add(new Opiskelija("Pasi Parametri", "016956834"));
	lista.add(new Opiskelija("Lasse Luokka", "012835085"));
	lista.add(new Opiskelija("Taina Taulukko", "014662803"));
	lista.add(new Opiskelija("Iiro Indeksi", "012807013"));

        System.out.println("Anna hakusana: ");
        String hakusana = lukija.nextLine();
	for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNimi().contains(hakusana)) {
                System.out.println(lista.get(i));
            }   
        }
    }
}