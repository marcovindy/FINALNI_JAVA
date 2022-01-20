package logika;

/**
 * Class PrikazCarymaryfuk - Třída slouží k ....
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PrikazCarymaryfuk implements IPrikaz {

    private static final String NAZEV = "čárymáryfuk";
    private final HerniPlan plan;
    public boolean prostorVytvoren = false;
    public Prostor carymaryfukProstor;
    public Prostor puvodniProstor;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazCarymaryfuk(HerniPlan plan) {
        this.plan = plan;

    }

    /**
     * Po zavolání příkazu se vypíše obsah batohu
     *
     * @param parametry - vola se bez parametru
     * @return - Vrací obsah batohu
     */

    public String provedPrikaz(String... parametry) {
        String text = "";
        puvodniProstor = plan.puvodniProstor;

        if ( parametry.length == 0 ) {
            if ( plan.getAktualniProstor().getNazev().equals("kouzelná_místnost_abraka") ) {
                if ( !(prostorVytvoren) ) {
                    prostorVytvoren = true;
                    carymaryfukProstor = plan.vytvorDruhaKouzelnaMistnost();
                }
                plan.setAktualniProstor(carymaryfukProstor);
                text += "Zamlží se ti před očima a přeneseš se z kouzelná_místnost_abraka do " + carymaryfukProstor.getNazev();
                text += "\n" + plan.getAktualniProstor().dlouhyPopis(plan.getVydrz());
            } else if ( plan.getAktualniProstor().getNazev().equals("kouzelná_místnost_čárymáry") ) {
                plan.setAktualniProstor(puvodniProstor);
                text += "Zamlží se ti před očima a přeneseš se z kouzelná_místnost_čárymáry do " + puvodniProstor.getNazev();
                text += "\n" + plan.getAktualniProstor().dlouhyPopis(plan.getVydrz());
            } else {
                text += "Příkaz zde vykonat nemůžeš";
            }
        } else {
            text += "Příkaz napsat jen samotný, jako čárymáryfuk";
        }


        return text;
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

