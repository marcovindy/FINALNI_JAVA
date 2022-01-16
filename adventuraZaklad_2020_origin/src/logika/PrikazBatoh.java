package logika;

/**
 * Class PostavaBandaOpilcu - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PrikazBatoh implements IPrikaz {

    private static final String NAZEV = "batoh";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazBatoh(HerniPlan plan) {
        this.plan = plan;

    }

    /**
     * Po zavolání příkazu se vypíše obsah batohu
     *
     * @param parametry - vola se bez parametru
     * @return - Vrací obsah batohu
     */

    public String provedPrikaz(String... parametry) {
        return plan.getBatoh().vypisBatohu();
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}

