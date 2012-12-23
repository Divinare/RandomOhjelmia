package vanhojaohjelmia;


public class OnkoPalindromi {
    public static void main(String[] args) {
    
        
     String merkkiJono = Pop.kysyString("Palindromintesti" + "\n" + "Anna merkkijono!(tyhjä merkkijono lopettaa)");
     
     String merkit = merkkiJono;
     int pituus = merkkiJono.length();
     
     String sana = "";
     
     for (int i = pituus-1; i >= 0; i--) {
         sana = sana + merkit.charAt(i);
     }
     
     if (merkkiJono.equals(sana)) {
         Pop.ilmoita(merkkiJono + " on palindromi.");
     }
     else 
         Pop.ilmoita(merkkiJono + " ei ole palindromi.");
     
    }
}
