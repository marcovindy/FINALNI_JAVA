package logika;

/**
 * Class PostavaStaryMuz - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PostavaStaryMuz extends Postava {
    public PostavaStaryMuz(String jmeno,
                              boolean probehlDulezityRozhovor,
                              boolean dostalaSvouVec,
                              String hlavniRec,
                              String dalsiRec,
                              String vecKterouPotrebuje,
                              Vec vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }
}
