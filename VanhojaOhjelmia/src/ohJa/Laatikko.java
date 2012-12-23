package ohJa;

import java.util.*;
public class Laatikko implements Talletettava {
   
    private double paino;
    private double saldo;
    ArrayList<Talletettava> tavarat = new ArrayList<Talletettava>();
    
    public Laatikko(double paino) {
        this.paino = paino;
        this.saldo = 0;
    }
    
    public void lisaa(Talletettava tavara) {
        if (saldo + tavara.getPaino() > paino) {
            return;
        }
        tavarat.add(tavara);
        saldo = saldo + tavara.getPaino();
    }
    
    public double getPaino() {
        double paino = 0;
        
        
        for (int i = 0; i < tavarat.size(); i++) {
            paino = paino + tavarat.get(i).getPaino();
        }
        
        // laske laatikkoon talletettujen yhteispaino 
        return paino;
    }
    
    public String toString() {
        return "Laatikko: " + tavarat.size() + " esinettä, paino yhteensä " + getPaino() + " kiloa";
    }
}
