package logika;

/**
 * Class PostavaServirka - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

public class PostavaServirka extends Postava {
    public PostavaServirka(String jmeno,
                           boolean probehlDulezityRozhovor,
                           boolean dostalaSvouVec,
                           String hlavniRec,
                           String dalsiRec,
                           String vecKterouPotrebuje,
                           String vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }

}

