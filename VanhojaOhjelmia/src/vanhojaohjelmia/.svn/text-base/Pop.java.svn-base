package vanhojaohjelmia;

import java.util.ArrayList;
import javax.swing.*;

public class Pop {

    private abstract class valueParser<T> {
        public abstract T parseValue(String tarjokas);
        public abstract String message();
    }

    private class intParser extends valueParser<Integer> {
        @Override
        public Integer parseValue(String tarjokas) {
            return Integer.parseInt(tarjokas);
        }
        @Override
        public String message() {
            return " is not an integer!";
        }
    }

    private class doubleParser extends valueParser<Double> {
        @Override
        public Double parseValue(String tarjokas) {
            return Double.parseDouble(tarjokas);
        }
        @Override
        public String message() {
            return " is not an double!";
        }
    }
    private static Pop instanssi = new Pop();
    private ArrayList<String> tulostukset;
    private ArrayList<String> syotteet;
    private int mones;

    private Pop() {
        tulostukset = new ArrayList<String>();
    }

    private void notify(String viesti) {
        tulostukset.add(viesti);
        if (syotteet == null) {
            JOptionPane.showMessageDialog(null, viesti, "", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private String getString(String kysymys) {
        if (syotteet == null) {
            return JOptionPane.showInputDialog(kysymys);
        }

        return syotteet.get(mones++);
    }

    private int getInt(String kysymys) {
        return getValue(kysymys, new intParser());
    }

    private double getDouble(String kysymys) {
        return getValue(kysymys, new doubleParser());
    }

    private <T> T getValue(String kysymys, valueParser<T> parser) {
        String tarjokas = "";

        while (true) {
            try {
                if (syotteet == null) {
                    tarjokas = JOptionPane.showInputDialog(kysymys);
                } else {
                    tarjokas = syotteet.get(mones);
                    mones++;
                }
                return parser.parseValue(tarjokas);
            } catch (Exception e) {
                String message = tarjokas == null ? "Cancel" : tarjokas;
                notify(message + parser.message());
            }
        }
    }

    private int getButtonSelection(String kysymys, String... valinnat) {
        if (valinnat.length == 0) {
            throw new IllegalArgumentException("No values supplied.");
        }

        if (syotteet != null) {
            String valinta = syotteet.get(mones);
            mones++;
            return Integer.parseInt(valinta);
        }

        return JOptionPane.showOptionDialog(null,
                kysymys,
                "",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                valinnat,
                valinnat[0]);
    }

    public String getStringSelection(String kysymys, String... valinnat) {
        if (valinnat.length == 0) {
            throw new IllegalArgumentException("No values supplied.");
        }

        if (syotteet != null) {
            return syotteet.get(mones++);
        }

        Object valinta = JOptionPane.showInputDialog(null,
                kysymys,
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                valinnat,
                valinnat[0]);
        
        if (valinta == null) return null;
        
        return "" + valinta;
    }

    private ArrayList<String>  notifications() {
        return tulostukset;
    }

    private void setInputs(ArrayList<String> syotteet) {
        this.syotteet = syotteet;
        mones = 0;
        tulostukset = new ArrayList<String>();
    }

    /* helpers for tests */
    public static void asetaSyotteet(ArrayList<String> syotteet) {
        instanssi.setInputs(syotteet);
    }

    public static void asetaSyotteet(String[] syotteet) {
        ArrayList<String> lista = new ArrayList<String>();
        for (String syote : syotteet) {
            lista.add(syote);
        }
        asetaSyotteet(lista);
    }

    public static ArrayList<String> tulosteet() {
        return instanssi.notifications();
    }

    /* static */
    public static void ilmoita(String viesti) {
        instanssi.notify(viesti);
    }

    public static void ilmoita(int viesti) {
        instanssi.notify("" + viesti);
    }

    public static void ilmoita(double viesti) {
        instanssi.notify("" + viesti);
    }

    public static void ilmoita(boolean viesti) {
        instanssi.notify("" + viesti);
    }

    public static void tell(String message) {
        instanssi.notify(message);
    }

    public static void tell(int message) {
        instanssi.notify("" + message);
    }

    public static void tell(double message) {
        instanssi.notify("" + message);
    }

    public static void tell(boolean message) {
        instanssi.notify("" + message);
    }

//------------------------------------------------------------------
//  kysyString(String kysymys)
//-----------------------------------------------------------------
    public static String kysyString(String kysymys) {
        return instanssi.getString(kysymys);
        // MYÖS NULL JA TYHJÄ KELPAAVAT! NIITÄ ON HYVÄ ITSE TESTATA.     
    }

    public static String askString(String question) {
        return kysyString(question);
    }

//------------------------------------------------------------------
//  kysyInt(String kysymys)
//-----------------------------------------------------------------
    public static int kysyInt(String kysymys) {
        return instanssi.getInt(kysymys);
    }

    public static int askInt(String question) {
        return kysyInt(question);
    }

//-----------------------------------------------------------------
//  kysyDouble(String kysymys)
//------------------------------------------------------------------
    public static double kysyDouble(String kysymys) {
        return instanssi.getDouble(kysymys);
    }

    public static double askDouble(String question) {
        return kysyDouble(question);
    }

//-----------------------------------------------------------------
//  valitseNappula(String kysymys, String... valinnat)
//------------------------------------------------------------------
    public static int valitseNappula(String kysymys, String... valinnat) {
        return instanssi.getButtonSelection(kysymys, valinnat);
    }

    public static int selectButton(String question, String... selections) {
        return valitseNappula(question, selections);
    }

//-----------------------------------------------------------------
//  valitseString(String kysymys, String... valinnat)
//------------------------------------------------------------------
    public static String valitseString(String kysymys, String... valinnat) {
        return instanssi.getStringSelection(kysymys, valinnat);
    }

    public static String selectString(String question, String... selections) {
        return valitseString(question, selections);
    }
}
