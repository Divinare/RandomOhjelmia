package ohJa;

////////////////////////////////////////////////////////////////////////
// Helsingin yliopisto, tktl, Arto Wikla 27.8.2011
//
// Ponnahdusikkunakirjasto, versio 0.9.
//
// Kursseille Ohjelmoinnin perusteet ja Ohjelmoinnin jatkokurssi
//
// Tarjolla olevat kirjastorutiinit on kuvailtu sivulla
// http://www.cs.helsinki.fi/u/wikla/ohjelmointi/materiaali/01_algoritmeja/
//
////////////////////////////////////////////////////////////////////////

import javax.swing.*;

public class Pop {

//-----------------------------------------------------------------
//  ilmoita(String viesti)
//-----------------------------------------------------------------
  public static void ilmoita(String viesti) {
    JOptionPane.showMessageDialog(null,
        viesti, "", JOptionPane.PLAIN_MESSAGE);
  }
  public static void ilmoita(int viesti)     {ilmoita(""+viesti);}
  public static void ilmoita(double viesti)  {ilmoita(""+viesti);}
  public static void ilmoita(boolean viesti) {ilmoita(""+viesti);}

  public static void tell(String message) {ilmoita(message);}
  public static void tell(int message)     {ilmoita(""+message);}
  public static void tell(double message)  {ilmoita(""+message);}
  public static void tell(boolean message) {ilmoita(""+message);}

//------------------------------------------------------------------
//  kysyString(String kysymys)
//-----------------------------------------------------------------
  public static String kysyString(String kysymys) {
    // MYÖS NULL JA TYHJÄ KELPAAVAT! NIITÄ ON HYVÄ ITSE TESTATA.
    return JOptionPane.showInputDialog(kysymys);  
  }
  public static String askString(String question) {return kysyString(question);}

//------------------------------------------------------------------
//  kysyInt(String kysymys)
//-----------------------------------------------------------------
  public static int kysyInt(String kysymys) {
    String tarjokas = "";
    int arvo = 0;
    boolean kunnossa = false;
    do 
      try {
        tarjokas = JOptionPane.showInputDialog(kysymys);  
        arvo = Integer.parseInt(tarjokas);
        kunnossa = true;
      } catch (Exception e) {
        if (tarjokas==null)
          ilmoita("Cancel is not an integer!");
        else
          ilmoita(tarjokas + " is not an integer!");
      }
    while (!kunnossa);

    return arvo;
  }
  public static int askInt(String question) {return kysyInt(question);}

//-----------------------------------------------------------------
//  kysyDouble(String kysymys)
//------------------------------------------------------------------
  public static double kysyDouble(String kysymys) {
    String tarjokas = "";
    double arvo = 0;
    boolean kunnossa = false;
    do 
      try {
        tarjokas = JOptionPane.showInputDialog(kysymys);  
        arvo = Double.parseDouble(tarjokas);
        kunnossa = true;
      } catch (Exception e) {
        if (tarjokas==null)
          ilmoita("Cancel is not a double!");
        else
          ilmoita(tarjokas + " is not a double!");
      }
    while (!kunnossa);

    return arvo;
  }
  public static double askDouble(String question) {return kysyDouble(question);}

//-----------------------------------------------------------------
//  valitseNappula(String kysymys, String... valinnat)
//------------------------------------------------------------------
  public static int valitseNappula(String kysymys, String... valinnat) {
    if (valinnat.length == 0) {
      throw new IllegalArgumentException("No values supplied.");
    }
    return  JOptionPane.showOptionDialog(null,
                                         kysymys,
                                         "",
                                         JOptionPane.DEFAULT_OPTION,
                                         JOptionPane.QUESTION_MESSAGE,
                                         null,
                                         valinnat,
                                         valinnat[0]);
  }
  public static int selectButton(String question, String... selections)
    {return valitseNappula(question, selections);}

//-----------------------------------------------------------------
//  valitseString(String kysymys, String... valinnat)
//------------------------------------------------------------------

  public static String valitseString(String kysymys, String... valinnat) {
    if (valinnat.length == 0) {
      throw new IllegalArgumentException("No values supplied.");
    }
    Object valinta =   JOptionPane.showInputDialog(null,
                                kysymys,
                                "",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                valinnat,
                                valinnat[0]);
    if (valinta == null)   // Temppuilua... 
      return null;         // koska tämä versio showInputDialogista
    else                   // on Object! (nulli nullina)
      return ""+valinta; 
  }
  public static String selectString(String question, String... selections)
    {return valitseString(question, selections);}

//------------------------------------------------------------------
//  Käyttöesimerkki:
//-----------------------------------------------------------------

  public static void main(String[] args) {

     Pop.ilmoita("Ilmoitus on tällainen." + "\n" +
                 "Se on kuitattava ok-nappulalla.");

     Pop.ilmoita("Ilmoituksessa voi olla useitakin rivejä" + "\n" +
             "Rivinvaidot pitää itse ilmaista \\n-merkillä."  + "\n" +
             "Pitkiäkin rivejä voi kirjoittaa. " +
             "Pitkiäkin rivejä voi kirjoittaa. " +
             "Pitkiäkin rivejä voi kirjoittaa. " + "\n" +
             "Tuohon tyyliin.."
     );

     String vastaus = Pop.kysyString("Anna jokin merkkijono!");
     Pop.ilmoita("Se oli: " + vastaus);
     if (vastaus==null) 
       Pop.ilmoita("Vastaaminen keskeytettiin cancel-nappulalla.");
     else 
     if (vastaus.length() ==0 ) 
       Pop.ilmoita("Annettiin tyhjä merkkijono.");

     int vast = Pop.valitseNappula("Kyllä vai ei?", "kyllä", "ei");
     if (vast == 0)
       Pop.ilmoita("Vastaus oli kyllä.");
     else
       Pop.ilmoita("Vastaus oli ei.");

     vastaus = Pop.kysyString("Myös kysymyksessä voi olla useita rivejä." + "\n" +
             "Rivinvaihdot pitää itse ilmaista \\n-merkillä."  + "\n" +
             "Samoin kuin ilmoituksessa.");
     if (vastaus==null) 
       Pop.ilmoita("Vastaus keskeytettiin cancel-nappulalla.");
     else 
     if (vastaus.length() ==0 ) 
       Pop.ilmoita("Vastaus oli tyhjä merkkijono.");
     else
       Pop.ilmoita("Se oli: " + vastaus);

     int luku = Pop.kysyInt("Anna kokonaisluku.");
     Pop.ilmoita("Luku oli " + luku);

     double liuku = Pop.kysyDouble("Anna desimaaliluku.");
     Pop.ilmoita("Luku oli " + liuku);

     int lemmikki = Pop.valitseNappula("Mikä on lemmikkisi?",
                                       "kissa", "hiiri", "koira");
     Pop.ilmoita("Valintasi indeksi on " + lemmikki);
     if (lemmikki==-1) 
       Pop.ilmoita("Vastaaminen keskeytettiin cancel-nappulalla.");

     String lemmik = Pop.valitseString("Mikä on lemmikkisi?",
                                       "kissa", "hiiri", "koira");
     Pop.ilmoita("Valintasi on " + lemmik);
     if (lemmik==null) 
       Pop.ilmoita("Vastaaminen keskeytettiin cancel-nappulalla.");

     int oikea = Pop.valitseNappula("Vaihtoehdoton \"valinta\"", "Ainoa oikea");
     Pop.ilmoita("Valintasi indeksi on " + oikea + ". Kuinkas muutenkaan.");

     int maa = Pop.valitseNappula("Mikä maa?", 
       "Suomi", "Ruotsi", "Venäjä", "Norja", "Tanska", "Islanti",
       "Saksa", "Ranska", "Hollanti", "Belgia", "Puola", "Itävalta",
       "Sveitsi", "Italia", "Espanja");
     Pop.ilmoita("Valintasi indeksi on " + maa);

     String v = Pop.valitseString("Mikä maa?", 
       "Suomi", "Ruotsi", "Venäjä", "Norja", "Tanska", "Islanti",
       "Saksa", "Ranska", "Hollanti", "Belgia", "Puola", "Itävalta",
       "Sveitsi", "Italia", "Espanja");
     Pop.ilmoita("Valintasi on " + v);
  }
}
