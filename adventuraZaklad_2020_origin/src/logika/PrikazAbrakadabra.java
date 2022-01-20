package logika;

/**
 * Class PrikazAbrakadabra - Třída slouží k ....
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PrikazAbrakadabra implements IPrikaz {

    private static final String NAZEV = "abrakadabra";
    private final HerniPlan plan;
    public boolean prostorVytvoren = false;
    public Prostor minulyProstor;
    public Prostor abrakadabraProstor;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazAbrakadabra(HerniPlan plan) {
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
        if (parametry.length == 0) {
            if (plan.getAktualniProstor().getNazev().equals("kouzelná_místnost_čárymáry")) {
                text += "Hehe, odsud se jen tak nedostaneš, musíš za babičkou";
            } else {

                if (!(prostorVytvoren)) {
                    prostorVytvoren = true;
                    abrakadabraProstor = plan.vytvorPrvniKouzelnaMistnost();
                }

                if (plan.getAktualniProstor().getNazev().equals("kouzelná_místnost_abraka")) {
                    plan.setAktualniProstor(minulyProstor);
                    text += "Zamlží se ti před očima a přeneseš se z " + abrakadabraProstor.getNazev() + " do " + minulyProstor.getNazev();
                } else {
                    minulyProstor = plan.getAktualniProstor();
                    plan.setAktualniProstor(abrakadabraProstor);
                    text += "Zamlží se ti před očima a přeneseš se z " + minulyProstor.getNazev() + " do " + abrakadabraProstor.getNazev();
                }

                text += plan.getAktualniProstor().dlouhyPopis(plan.getVydrz());
            }
        } else {
            text += "Musíš napsat pouze abrakadabra";
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

