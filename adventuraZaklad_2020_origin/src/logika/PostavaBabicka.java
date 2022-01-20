package logika;

/**
 * Class PostavaBabicka - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PostavaBabicka extends Postava {

    HerniPlan plan;

    public PostavaBabicka(String jmeno,
                           boolean probehlDulezityRozhovor,
                           boolean dostalaSvouVec,
                           String hlavniRec,
                           String dalsiRec,
                           String vecKterouPotrebuje,
                           Vec vecKterouNabidne,
                           HerniPlan plan) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
        this.plan = plan;
    }





    public HerniPlan getPlan() {
        return plan;
    }

    public void setPlan(HerniPlan plan) {
        this.plan = plan;
    }
}
