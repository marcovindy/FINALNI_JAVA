package logika;

/**
 * Class PostavaHolkaZeZachodu - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

public class PostavaHolkaZeZachodu extends Postava {

    private HerniPlan plan;

    public PostavaHolkaZeZachodu(String jmeno,
                                 boolean probehlDulezityRozhovor,
                                 boolean dostalaSvouVec,
                                 String hlavniRec,
                                 String dalsiRec,
                                 String vecKterouPotrebuje,
                                 Vec vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }


}
