package logika;

/**
 * Class PostavaRandomTypek - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

public class PostavaVedlejsi extends Postava {
    public PostavaVedlejsi(String jmeno,
                           boolean probehlDulezityRozhovor,
                           boolean dostalaSvouVec,
                           String hlavniRec,
                           String dalsiRec,
                           String vecKterouPotrebuje,
                           Vec vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }
}
