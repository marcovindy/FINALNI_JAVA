package logika;

/**
 * Class PostavaHospodsky - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */


public class PostavaHospodsky extends Postava {

    public PostavaHospodsky(String jmeno,
                            boolean probehlDulezityRozhovor,
                            boolean dostalaSvouVec,
                            String hlavniRec,
                            String dalsiRec,
                            String vecKterouPotrebuje,
                            Vec vecKterouNabidne) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }

    @Override
    public boolean dej(Vec vec) {



        return super.dej(vec);
    }
}
