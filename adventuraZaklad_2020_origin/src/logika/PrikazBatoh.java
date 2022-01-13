package logika;

/*******************************************************************************
 *
 *
 * @author    Marek Vaníček
 * @version   školní rok 2021/2022
 */
public class PrikazBatoh implements IPrikaz {

    private static final String NAZEV = "batoh";
    private HerniPlan plan;
    private Batoh batoh;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazBatoh(HerniPlan plan) {
        this.plan = plan;

    }

    /**
     * Po zavolání příkazu se vypíše obsah batohu
     *
     * @param   - vola se bez parametru
     * @return  - Vrací obsah batohu
     */

    public String provedPrikaz(String... parametry) {
        return "Pracně otevírám batoh\n" + plan.getBatoh().seznamVeci();
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}

