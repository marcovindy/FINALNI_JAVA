package logika;

/**
 * Class PostavaServirka - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PostavaServirka extends Postava {
    public PostavaServirka(String jmeno,
                           boolean probehlDulezityRozhovor,
                           boolean dostalaSvouVec,
                           String hlavniRec,
                           String dalsiRec,
                           String vecKterouPotrebuje,
                           Vec vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }

}

