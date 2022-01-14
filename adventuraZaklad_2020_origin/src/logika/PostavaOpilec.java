package logika;

/**
 * Class PostavaOpilec - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

public class PostavaOpilec extends Postava {
    public PostavaOpilec(String jmeno,
                         boolean probehlDulezityRozhovor,
                         boolean dostalaSvouVec,
                         String hlavniRec,
                         String dalsiRec,
                         String vecKterouPotrebuje,
                         Vec vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }
}
