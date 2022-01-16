package logika;

/**
 * Class PostavaServirka - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PostavaServirka extends Postava {

    public PostavaServirka(String jmeno,
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
        if( isProbehlDulezityRozhovor() ){
            if (vec.getNazev().equals(getVecKterouPotrebuje())) {
                if( isProbehlDulezityRozhovor() ){
                    setDostalaSvouVec(true);
                    setDalsiRec("""

                            [Já]: Líbí se vám ta kytice?
                            [Servírka]: Ano moc. *úsměv*\s
                            [Já]: To jsem rád jen jsem vám chtěl udělat radost. *úsměv*

                            """);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }
}

