package logika;

/**
 * Class PostavaCurajiciTypek - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

public class PostavaCurajiciTypek extends Postava {

    public PostavaCurajiciTypek(String jmeno,
                                boolean probehlDulezityRozhovor,
                                boolean dostalaSvouVec,
                                String hlavniRec,
                                String dalsiRec,
                                String vecKterouPotrebuje,
                                Vec vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }


}
