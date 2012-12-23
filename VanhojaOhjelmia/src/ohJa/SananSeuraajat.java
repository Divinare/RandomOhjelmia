package ohJa;


import java.util.*;

public class SananSeuraajat {
    
    private HashMap<String, ArrayList<String>> luettelo;
    // 2.3

    public SananSeuraajat() {
        luettelo = new HashMap<String, ArrayList<String>>();
    }

    // 2.3
    public void lisaaSanalleSeuraaja(String sana, String seuraaja) {
        ArrayList<String> taulu = new ArrayList<String>();
        if (luettelo.containsKey(sana)) {
            taulu = luettelo.get(sana);
            if (!taulu.contains(seuraaja)) {
                taulu.add(seuraaja);
            }
            luettelo.put(sana, taulu);
        } else {
            taulu = new ArrayList<String>();
            taulu.add(seuraaja);
            luettelo.put(sana, taulu);
        }
    }

    public HashSet<String> mitkaSeuraavatSanaa(String sana) {

        if (!luettelo.containsKey(sana)) {
            return null;
        }
        HashSet taulu = new HashSet<String>();
        ArrayList apulista = luettelo.get(sana);
        for (int i = 0; i < apulista.size(); i++) {
            taulu.add(apulista.get(i));
        }
        
        return taulu;
    }


    // 2.3
    @Override
    public String toString() {
        
        return luettelo.toString();
    }
}
