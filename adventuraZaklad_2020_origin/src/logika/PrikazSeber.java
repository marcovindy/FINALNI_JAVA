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
        String text;
        if (parametry.length == 0) {
            text = "Musíš mi říct, co mám sebrat.";
            return text;
        }
        else if (parametry.length > 1) {
            text = "Nemůžeš sebrat více věcí zároveň.";
            return text;
        }
        else {
            String nazevVeci = parametry[0];
            if ( plan.getAktualniProstor().obsahujeVec(nazevVeci) ) {
                Vec pozadovanaVec = plan.getAktualniProstor().vyberVec(nazevVeci);
                if ( pozadovanaVec == null || !(pozadovanaVec.getPrenositelna()) ) {
                    plan.getAktualniProstor().vlozVec(pozadovanaVec);
                    text = nazevVeci + " se nedá přenášet";
                    return text;
                }else{

                    boolean povedloSeVlozit = plan.getBatoh().vlozVec(pozadovanaVec);
                    if(povedloSeVlozit){
                        plan.getAktualniProstor().odeberVec(pozadovanaVec);
                        text = pozadovanaVec.getPopis() + "\n";
                        return  text + "Vložil jsi do batohu: " + nazevVeci + ".";
                    }

                    plan.getAktualniProstor().vlozVec(pozadovanaVec);
                    text = nazevVeci + " se snazis nacpat do plneho batohu";
                    return text;
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
