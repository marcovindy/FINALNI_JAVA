package logika;

import java.util.Locale;

/**
 * Třída PrikazOdpovez implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author  Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class PrikazOdpovez implements IPrikaz {

    private static final String NAZEV = "odpověz";
    private final HerniPlan plan;


    /**
     * Konstruktor třídy
     *
     * @param plan herní svět
     */
    public PrikazOdpovez(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Vrací text o tom, zda odpověď byla správná nebo také ne, v případě, že odpovídáme kouzelné babičce špatně, tak také
     * volá konec hry pro prohru, pokud byla správná, babička vám dá předmět pro dědečka
     *
     * @return text s tím, zda byla odpověď správná, či ne
     */
    @Override
    public String provedPrikaz(String... parametry) {
        String text = "";
        if ( parametry.length < 2 ) {
            text += "Musíš říct komu a co chceš odpovědět";
        } else if ( parametry.length == 2 ) {
            if ( parametry[0].equals("kouzelná_babička") ) {
                String slovo = parametry[1].toLowerCase(Locale.ROOT);
                if (slovo.equals("svíčka")){
                    plan.getBatoh().vlozVec(new Vec("brýle", true, "Brýle pro kouzelnýho dědka.", false));
                    text += "Super, uhodl jsi\n" +
                            "Babka ti dá brejle.";
                } else {
                    text += "Špatná odpověď\n" +
                            "Prohráls! Babička tě poslala do jiné dimenze.";
                    plan.ukoncitHru();
                }
            } else {
                text += "Pro tuto postavu nemůžeš použít příkaz odpověz";
            }
        } else {
            text += "První napiš odpověz, pak komu a pak co, odpovědět můžeš jen jedním slovem";
        }
        return text;
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
