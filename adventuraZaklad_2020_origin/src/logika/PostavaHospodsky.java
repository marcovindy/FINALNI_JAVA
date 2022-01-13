package logika;

import java.util.ArrayList;
import java.util.List;

public class PostavaHospodsky extends Postava {

    List<Vec> veciPostavy;

    public PostavaHospodsky( String jmeno,
            boolean probehlDulezityRozhovor,
            String hlavniRec,
            String dalsiRec,
            String vecKterouPotrebuje,
            String vecKterouNabidne ) {
        super(jmeno, probehlDulezityRozhovor, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
        veciPostavy = new ArrayList<>();
    }

    public void vlozVec(Vec vec){
        veciPostavy.add(vec);
    }





}