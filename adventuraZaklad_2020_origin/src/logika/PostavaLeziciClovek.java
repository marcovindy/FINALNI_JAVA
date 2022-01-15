package logika;

/**
 * Class PostavaLeziciClovek - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

public class PostavaLeziciClovek extends Postava {

    private Prostor aktualniProstor;
    private Batoh batoh;
    public boolean dostalsVec = false;

    public PostavaLeziciClovek(String jmeno,
                               boolean probehlDulezityRozhovor,
                               boolean dostalaSvouVec,
                               String hlavniRec,
                               String dalsiRec,
                               String vecKterouPotrebuje,
                               Vec vecKterouNabidne, Prostor aktualniProstor, Batoh batoh) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
        this.batoh = batoh;
        this.aktualniProstor = aktualniProstor;
    }

    @Override
    public boolean dej(Vec vec) {
        if (vec.getLzeKonzumovat()) {
            setDostalaSvouVec(true);
            getAktualniProstor().smazPostavu(getJmeno());
            return true;
        }
        return false;
    }

    @Override
    public String mluv() {
        String text = "\n";
        if (!(isProbehlDulezityRozhovor())) {
            setProbehlDulezityRozhovor(true);
            if (getBatoh().vlozVec(getVecKterouNabidne()) && !(dostalsVec)) {
                dostalsVec = true;
            }
            text += getHlavniRec();
        } else {
            if (getBatoh().vlozVec(getVecKterouNabidne()) && !(dostalsVec)) {
                dostalsVec = true;
            }
            text += getDalsiRec();
        }
        return text;
    }

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    public Batoh getBatoh() {
        return batoh;
    }
}
