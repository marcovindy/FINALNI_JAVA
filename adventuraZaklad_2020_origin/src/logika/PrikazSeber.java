package logika;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  @author     Marek Vaníček
 *  @version    školní rok 2021/2022
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private HerniPlan plan;

    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš mi říct, co mám sebrat.";
        }
        else if (parametry.length > 1) {
            return "Nemůžeš sebrat více věcí zároveň.";
        }
        else {
            String nazevVeci = parametry[0];
            if ( plan.getAktualniProstor().obsahujeVec(nazevVeci) ) {
                Vec pozadovanaVec = plan.getAktualniProstor().vyberVec(nazevVeci);
                if ( pozadovanaVec == null || !(pozadovanaVec.getPrenositelna()) ) {
                    plan.getAktualniProstor().vlozVec(pozadovanaVec);
                    return nazevVeci + " se nedá přenášet";
                }else{

                    boolean povedloSeVlozit = plan.getBatoh().vlozVec(pozadovanaVec);
                    if(povedloSeVlozit){
                        plan.getAktualniProstor().odeberVec(pozadovanaVec);
                        return nazevVeci + " jsi vzal z mistnosti a dal do batohu";
                    }

                    plan.getAktualniProstor().vlozVec(pozadovanaVec);
                    return nazevVeci + " se snazis nacpat do plneho batohu";
                }
            }else{
                return nazevVeci + " neni v mistnosti";
            }
        }
    }

    public String getNazev() {
        return NAZEV;
    }
}
