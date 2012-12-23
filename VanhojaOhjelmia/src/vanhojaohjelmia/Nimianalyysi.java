package vanhojaohjelmia;

public class Nimianalyysi {
    

public static void main(String []args) {
    

    
    String nimi = Pop.kysyString("Nimianalyysi" + "\n" + "Anna nimi!(tyhjä merkkijono lopettaa)");
    
        String merkit = nimi;
        int pituus = nimi.length();
        
        Pop.ilmoita("Nimianalyysi" + "\n" + "nimi: " + nimi + "\n" +
                    "kirjaimia: " + pituus
                +"\n" + "ensimmäinen: " + merkit.charAt(0) + "\n" +
                    "viimeinen: " + merkit.charAt(nimi.length()-1));
}
}