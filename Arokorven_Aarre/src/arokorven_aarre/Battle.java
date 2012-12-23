package arokorven_aarre;

public class Battle {

    static String vihollisenNimi;
    int vihollisenVoima;
    int vihollisenHP;

    public Battle(String vihollisenNimi, int vihollisenVoima, int vihollisenHP) {
        this.vihollisenNimi = vihollisenNimi;
        this.vihollisenVoima = vihollisenVoima;
        this.vihollisenHP = vihollisenHP;
    }

    public void NormaaliTaisteluKierros() {
        int omahyokkays = Hero.getVoima() + (Dice.heitaNoppaa() + Dice.heitaNoppaa());
        int vastahyokkays = vihollisenVoima + (Dice.heitaNoppaa() + Dice.heitaNoppaa());

        if (omahyokkays < vastahyokkays) {
            Hero.vahennaHP(vastahyokkays - omahyokkays);
        } else {
            vihollisenHP = omahyokkays - vastahyokkays;
        }
    }

    public void OnniTaisteluKierros() {
        if (Hero.checkOnni()) {
            int omahyokkays = Hero.getVoima() + (Dice.heitaNoppaa());
            int vastahyokkays = vihollisenVoima + (Dice.heitaNoppaa() + Dice.heitaNoppaa());

            if (omahyokkays < vastahyokkays) {
                Hero.vahennaHP(vastahyokkays - omahyokkays);
            } else {
                vihollisenHP = omahyokkays - vastahyokkays;
            }
        } else {
            int omahyokkays = Hero.getVoima() + (Dice.heitaNoppaa() + Dice.heitaNoppaa());
            int vastahyokkays = vihollisenVoima + (Dice.heitaNoppaa() + Dice.heitaNoppaa());

            if (omahyokkays < vastahyokkays) {
                Hero.vahennaHP(vastahyokkays - omahyokkays);
            } else {
                vihollisenHP = omahyokkays - vastahyokkays;
            }
        }
    }
}