package logika;

/**
 * Class PrikazCena - Tímto příkazem, lze zjistit kolik co stojí, příkaz se hodí do obchodu.
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PrikazCena implements IPrikaz {

    private static final String NAZEV = "cena";
    private HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazCena(HerniPlan plan) {
        this.plan = plan;

    }

    /**
     * Po zavolání příkazu se vypíše obsah batohu
     *
     * @param parametry - Věc od které chci vědět cenu
     * @return - Vrací cenu věci, pokud je vše zadáno správně a je hráč o bchodě
     */

    public String provedPrikaz(String... parametry) {
        if (plan.getAktualniProstor().getNazev().equals("obchod")) {
            if( parametry.length == 0 ){
                return "Musíš mi říct, od které věci chceš vědět cenu";
            } else if (parametry.length > 1){
                return "Můžeš se podívat na cenu jen jedné věci současně";
            } else {
                if(plan.getAktualniProstor().obsahujeVec(parametry[0])){
                    return parametry[0] + " stojí " + plan.getAktualniProstor().vyberVec(parametry[0]).getCena();
                } else {
                    return parametry[0] + " zde není, takže její cenu nezjistíš";
                }
            }
        } else {
            return "Na cenu se můžeš podívat jen v obchodě";
        }
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

