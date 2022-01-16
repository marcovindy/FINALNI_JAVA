package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     * existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        } else {

            if (priselNaKolej(sousedniProstor.getNazev())) {
                if (plan.getBatoh().obsahujeVec("ISIC")) {
                    plan.ukoncitHru();
                    return plan.moznostiKonceKolej();
                } else {
                    return "Bohužel nemáš ISIC, aby ses dostal na kolej.\n";
                }
            }

            plan.setVydrz(plan.getVydrz() - 1);
            if (plan.getVydrz() < 0) {
                plan.ukoncitHru();
                return plan.zkontrolujVydrz(1);
            } else {
                plan.setAktualniProstor(sousedniProstor);
                return sousedniProstor.dlouhyPopis(plan.getVydrz());
            }


        }
    }

    public boolean priselNaKolej(String prostor) {

        return prostor.equals("kolej");
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
