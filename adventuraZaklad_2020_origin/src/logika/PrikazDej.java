package logika;

/**
 * Třída PrikazDej implementuje pro hru příkaz dej.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PrikazDej implements IPrikaz {
    private static final String NAZEV = "dej";
    private final HerniPlan plan;

    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Po zavolání příkazu hráč zkouší dát někdo nějakou věc
     *
     * @param parametry - Věc kterou chci někomu dát
     * @return - Vrací text co hráč postavě dává a jaké, pokud postava věc nechce (viz. Postava Class), tak napíše
     * proč nemůže/nechce
     */

    @Override
    public String provedPrikaz(String... parametry) {
        String text;
        if (parametry.length <= 1) {
            text = "Musíš mi říct, co a komu dát";
            return text;
        } else {
            String pozadovanaVec = parametry[0];
            String pozadovanaPostava = parametry[1];
            if (!(plan.getBatoh().obsahujeVec(pozadovanaVec))) {
                text = pozadovanaVec + " nemáš u sebe";
            } else if (plan.getAktualniProstor().vratPostavu(pozadovanaPostava) == null) {
                text = pozadovanaPostava + " zde není";
            } else {
                Vec vec = plan.getBatoh().vyberVec(pozadovanaVec);
                Postava postava = plan.getAktualniProstor().vratPostavu(pozadovanaPostava);
                boolean povedloSe = postava.dej(vec);
                if (!(povedloSe)) {
                    text = pozadovanaPostava + " nechce předmet " + pozadovanaVec;
                } else {
                    plan.getBatoh().vyhodVec(vec);
                    text = pozadovanaVec + " předána postavě " + pozadovanaPostava;
                    if ( jeKouzelnyDedecek(pozadovanaPostava) ) {
                        text += "\n[Kouzelný dědeček]: Díky mladej a teď musíš do postele, abys dal statistiku! Abrakadabra!";
                        text += "\nDokončil jsi easter egg! Vyhráváš! Děda tě přenesl na kolej do postele.";
                        plan.ukoncitHru();
                    }
                }
            }
        }
        return text;
    }

    public boolean jeKouzelnyDedecek(String jmeno){
        if ( jmeno.equals("kouzelný_dědeček") ) {
            return true;
        }
        return false;
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
