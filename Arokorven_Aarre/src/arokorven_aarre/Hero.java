package arokorven_aarre;

public class Hero {

    private static int MaxHP;
    private static int HP;
    private static int voima;
    private static int onni;

    public Hero() {
        voima = 6 + Dice.heitaNoppaa();
        HP = 12 + Dice.heitaNoppaa() + Dice.heitaNoppaa();
        MaxHP = HP;
        onni = 6 + Dice.heitaNoppaa();
    }

    public static void lisaaHP(int Hp) {
        if (getHP() + Hp > MaxHP) {
            HP = MaxHP;
        } else {
            HP += HP;
        }
    }

    public static void lisaaMaxHP(int HP) {
        MaxHP += HP;
    }

    public static void vahennaHP(int Hp) {
        HP -= Hp;
        if (HP < 1) {
            Roads.GameOver();
        }
    }

    public static void vahennaMaxHP(int HP) {
        MaxHP -= HP;
    }

    public static void lisaaVoima(int strena) {
        voima += strena;
    }

    public static void vahennaVoima(int strena) {
        voima -= strena;
    }

    public static int getHP() {
        return HP;
    }

    public static int getMaxHP() {
        return MaxHP;
    }

    public static int getVoima() {
        return voima;
    }

    public static int getOnni() {
        return onni;
    }

    public static boolean checkOnni() {
        int testiluku = Dice.heitaNoppaa() + Dice.heitaNoppaa();
        if (testiluku <= onni) {
            onni--;
            return true;
        } else {
            onni--;
            return false;
        }
    }

    public static void lisaaOnni(int tuuri) {
        onni += tuuri;
    }

    public static void vahennaOnni(int tuuri) {
        onni -= tuuri;
    }
}