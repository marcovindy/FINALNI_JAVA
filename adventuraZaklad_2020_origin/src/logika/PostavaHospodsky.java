package logika;

/**
 * Class PostavaHospodsky - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

import java.util.ArrayList;
import java.util.List;

public class PostavaHospodsky extends Postava {

    List<Vec> veciPostavy;

    public PostavaHospodsky(String jmeno,
                            boolean probehlDulezityRozhovor,
                            boolean dostalaSvouVec,
                            String hlavniRec,
                            String dalsiRec,
                            String vecKterouPotrebuje,
                            String vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
        veciPostavy = new ArrayList<>();
    }

    public void vlozVec(Vec vec) {
        veciPostavy.add(vec);
    }


}
