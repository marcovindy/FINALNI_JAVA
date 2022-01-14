package logika;

/**
 * Class PostavaKuchar - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

public class PostavaKuchar extends Postava {
    public PostavaKuchar(String jmeno,
                         boolean probehlDulezityRozhovor,
                         boolean dostalaSvouVec,
                         String hlavniRec,
                         String dalsiRec,
                         String vecKterouPotrebuje,
                         String vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }
}
