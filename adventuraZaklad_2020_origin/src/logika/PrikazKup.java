package logika;

/**
 *  Třída PrikazKup implementuje pro hru příkaz kup.
 *  Díky příkazu si lze zakoupit v obchodě určitý předmět za peníze
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class PrikazKup implements IPrikaz {
    private static final String NAZEV = "kup";
    private final HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazKup(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "kup". Řeší koupení jedné věci, zkouší zda je věc
     *  vůbec v obchodě, či jestli má hráč místo v batohu, dále
     *  také zkouší, jestli má dostatek peněz pro koupení věci
     *
     *
     *  NOTE: Také hráče již nenechá si "množit věci", tento bug se mi stával,
     *  když hráč v obchodě vyhodil věc a kupoval si ji za 0 pořád dokola,
     *  vyřešeno tím, že poté co si hráč předmět z prostoru koupí, tak se smaže.
     *
     *
     *@param parametry - jako  parametr obsahuje jméno věci,
     *                         kterou si chce hráč koupit.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        String text;
        if ( parametry.length > 1 ) {
            text = "Můžeš koupit jen jednu věc současně";
        } else if ( parametry.length == 0 ) {
            text = "Musíš mi říct, co chceš koupit";
        } else {
            if ( plan.getAktualniProstor().getNazev().equals("obchod") ) {
                if ( plan.getAktualniProstor().obsahujeVec(parametry[0]) ) {
                    Vec pozadovanaVec = plan.getAktualniProstor().vyberVec(parametry[0]);
                    if( plan.getBatoh().getPenize() < pozadovanaVec.getCena() ) {
                        text = "Na tohle nemáš dostatek peněz.";
                    } else {
                        if ( plan.getBatoh().vlezeSeDoBatohu() ) {
                            plan.getBatoh().vlozVec(pozadovanaVec);
                            text = "Vložil jsi do batohu: " + parametry[0] + ".";
                            plan.getBatoh().setPenize(plan.getBatoh().getPenize() - pozadovanaVec.getCena());
                            plan.getAktualniProstor().odeberVec(pozadovanaVec);
                        } else {
                            text = "Bohužel, nemáš místo v batohu, takže si nic nekoupíš";
                        }
                    }
                } else {
                    text = "Taková věc zde není";
                }
            } else if ( plan.getAktualniProstor().getNazev().equals("hospoda_hlavní_místnost") ) {
                text = "Bohužel, už se nedá nic koupit, hospoda zavírá #COVID";
            } else {
                text = "Zde nejde nic koupit";
            }
        }
        return text;
    }



    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
