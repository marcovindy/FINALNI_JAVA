package logika;

/**
 * Class PrikazAbrakadabra - Třída slouží k teleportu hráče do nové místnosti, kde dostane úkol
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
     * Po zavolání příkazu se teleportuje do prostoru abraka nebo zpět posledního prostoru ve hře
     *
     * @param parametry - vola se bez parametru
     * @return - Vrací text o tom, jestli se kouzlo vydařilo a hráč se teleportoval, či proč ne
     */

    public String provedPrikaz(String... parametry) {
        String text = "";
        if (parametry.length == 0) {
            if (!(prostorVytvoren)) {
                prostorVytvoren = true;
                abrakadabraProstor = plan.vytvorPrvniKouzelnaMistnost();
            }
            if (plan.getAktualniProstor().getNazev().equals("kouzelná_místnost_abraka")) {
                plan.setAktualniProstor(minulyProstor);
                text += "Zamlží se ti před očima a přeneseš se z " + abrakadabraProstor.getNazev() + " do " + minulyProstor.getNazev();
            } else {
                if (!(plan.getAktualniProstor().getNazev().equals("kouzelná_místnost_čárymáry"))) {
                    minulyProstor = plan.getAktualniProstor();
                    plan.puvodniProstor = plan.getAktualniProstor();
                }
                plan.setAktualniProstor(abrakadabraProstor);
                text += "Zamlží se ti před očima a přeneseš se z " + minulyProstor.getNazev() + " do " + abrakadabraProstor.getNazev();
            }
            text += "\n" + plan.getAktualniProstor().dlouhyPopis(plan.getVydrz());
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

