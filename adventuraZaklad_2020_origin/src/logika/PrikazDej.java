package logika;

/**
 *  Třída PrikazDej implementuje pro hru příkaz dej.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Marek Vaníček
 *@version    1.0 pro školní rok 2021/2022
 */

public class PrikazDej implements IPrikaz{
    private static final String NAZEV = "dej";
    private HerniPlan plan;

    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        String text = "";
        if ( parametry.length <= 1 ) {
            text = "Musíš mi říct, co a komu dát";
            return text;
        } else {
            String pozadovanaVec = parametry[0];
            String pozadovanaPostava = parametry[1];
            if( !(plan.getBatoh().obsahujeVec(pozadovanaVec)) ){
                text = pozadovanaVec + " nemáš u sebe";
            } else if ( plan.getAktualniProstor().vratPostavu(pozadovanaPostava) == null ){
                text = pozadovanaPostava + " zde není";
            } else {
                Vec vec = plan.getBatoh().vyberVec(pozadovanaVec);
                Postava postava = plan.getAktualniProstor().vratPostavu(pozadovanaPostava);
                boolean povedloSe = postava.dej(vec);
                if ( !(povedloSe) ){
                    text = pozadovanaPostava + " nechce " + pozadovanaVec;
                } else {
                    plan.getBatoh().vyhodVec(vec);
                    text = pozadovanaVec + " předána postavě";
                }
            }
        }
        return text;
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
