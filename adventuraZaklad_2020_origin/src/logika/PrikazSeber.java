package logika;

/**
 * Třída PrikazSeber implementuje pro hru příkaz seber.
 * Tato třída je součástí jednoduché textové hry.
 * Je určená k tomu, aby mohl hráč zvedat předměty (sebrat).
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private final HerniPlan plan;

    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    public String provedPrikaz(String... parametry) {
        String text;
        if (parametry.length == 0) {
            text = "Musíš mi říct, co mám sebrat.";
            return text;
        } else if (parametry.length > 1) {
            text = "Nemůžeš sebrat více věcí zároveň.";
            return text;
        } else {
            String nazevVeci = parametry[0];
            if (plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
                Vec pozadovanaVec = plan.getAktualniProstor().vyberVec(nazevVeci);
                if (pozadovanaVec == null || !(pozadovanaVec.getPrenositelna())) {
                    plan.getAktualniProstor().vlozVec(pozadovanaVec);
                    text = nazevVeci + " se nedá přenášet";
                    return text;
                } else if (pozadovanaVec.getNazev().equals("peníze")) {
                    plan.getAktualniProstor().odeberVec(pozadovanaVec);
                    int randomPenize = getRandomNumber(5, 25);
                    plan.getBatoh().setPenize(plan.getBatoh().getPenize() + randomPenize);
                    text = "Vložil jsi do batohu: " + nazevVeci + " " + randomPenize + ".";
                    return text;
                } else {

                    boolean povedloSeVlozit = plan.getBatoh().vlozVec(pozadovanaVec);
                    if (povedloSeVlozit) {
                        plan.getAktualniProstor().odeberVec(pozadovanaVec);
                        text = pozadovanaVec.getPopis() + "\n";
                        return text + "Vložil jsi do batohu: " + nazevVeci + ".";
                    }

                    plan.getAktualniProstor().vlozVec(pozadovanaVec);
                    text = nazevVeci + " se snazis nacpat do plneho batohu";
                    return text;
                }
            } else {
                return nazevVeci + " neni v mistnosti";
            }
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getNazev() {
        return NAZEV;
    }
}
