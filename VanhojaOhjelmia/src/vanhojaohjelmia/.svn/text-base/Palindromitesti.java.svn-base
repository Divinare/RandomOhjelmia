package vanhojaohjelmia;


public class Palindromitesti {

    public static void main(String[] args) {
    
        
        
        
  String merkkiJono = Pop.kysyString("Palindromintesti" + "\n" + "Anna merkkijono!(tyhjä merkkijono lopettaa");
  String vertailtava = "";
  String sana = merkkiJono;
  
  String merkit = merkkiJono;
  int pituus = merkkiJono.length();
  
  
  for (int i = pituus-1; i >= 0; i--) {
      if (merkkiJono.charAt(i) != ' ' && merkkiJono.charAt(i) != ',' && merkkiJono.charAt(i) != '.') {
          vertailtava = vertailtava + merkit.charAt(i);
      }
  }
  
      vertailtava = vertailtava.toLowerCase();

      
      merkkiJono = "";
      
  for (int i = 0; i <= pituus-1; i++) {
      if (sana.charAt(i) != ' ' && sana.charAt(i) != ',' && sana.charAt(i) != '.') {
          merkkiJono = merkkiJono + merkit.charAt(i);
      }
      
  }
     merkkiJono = merkkiJono.toLowerCase();
   
    
     if (vertailtava.equals(merkkiJono)) {
         Pop.ilmoita(merkkiJono + " on palindromi.");
     }
     else 
         Pop.ilmoita(merkkiJono + " ei ole palindromi.");

     
     }
  }