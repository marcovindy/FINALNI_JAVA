package logika;

/*******************************************************************************
 *
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class PrikazMluv implements IPrikaz {

    private static final String NAZEV = "mluv";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;

    }

    /**
     * Po zavolání příkazu se hráč snaží mluvit s postavou
     *
     * @param parametry - jmeno postavy s kterou by chtěl hřáč mluvit uloženo v parametru 0
     * @return - Vrací dialog s postavou, či neúspěšnou konverzaci
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš říct s kým chceš mluvit";
        } else if (parametry.length > 1) {
            return "Můžeš mluvit jen s jednou postavou současně";
        } else {
            if (plan.getAktualniProstor().vratPostavu(parametry[0]) != null) {
                return plan.getAktualniProstor().vratPostavu(parametry[0]).mluv();
            }
        }
        return parametry[0] + " zde není";
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}

