package logika;

/**
 * Class PostavaBandaOpilcu - konkrétní postava
 *
 * NOTE: U této postavy jsem chtěl, aby mě mohli postal do bezvědomí, když je budu pořád otravovat,
 * ale už jsem to bohužel nestihl
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PostavaBandaOpilcu extends Postava {

    public PostavaBandaOpilcu(String jmeno,
                              boolean probehlDulezityRozhovor,
                              boolean dostalaSvouVec,
                              String hlavniRec,
                              String dalsiRec,
                              String vecKterouPotrebuje,
                              Vec vecKterouNabidne ) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
    }

}
