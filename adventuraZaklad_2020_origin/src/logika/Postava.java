package logika;

/**
 * Class Postava - obecna trida pro vsechny postavy
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

public class Postava implements IPostava{

    private final String jmeno;
    private boolean probehlDulezityRozhovor;
    private boolean dostalaSvouVec;
    private String hlavniRec;
    private String dalsiRec;
    private String vecKterouPotrebuje;
    private String vecKterouNabidne;

    public Postava(String jmeno, boolean probehlDulezityRozhovor, boolean dostalaSvouVec, String hlavniRec, String dalsiRec, String vecKterouPotrebuje, String vecKterouNabidne) {
        this.jmeno = jmeno;
        this.probehlDulezityRozhovor = probehlDulezityRozhovor;
        this.hlavniRec = hlavniRec;
        this.dalsiRec = dalsiRec;
        this.vecKterouPotrebuje = vecKterouPotrebuje;
        this.vecKterouNabidne = vecKterouNabidne;
        this.dostalaSvouVec = dostalaSvouVec;
    }

    public String mluv() {
        String text = "\n";
        if (!(probehlDulezityRozhovor)) {
            probehlDulezityRozhovor = true;
            text += hlavniRec;
        } else {
            text += dalsiRec;
        }
        return text;
    }

    public boolean dej(Vec vec) {
        if (vec.getNazev().equals(vecKterouPotrebuje)) {
            dostalaSvouVec = true;
            return true;
        }
        return false;
    }

    public boolean isDostalaSvouVec() {
        return dostalaSvouVec;
    }

    public void setDostalaSvouVec(boolean dostalaSvouVec) {
        this.dostalaSvouVec = dostalaSvouVec;
    }

    public String getJmeno() {
        return jmeno;
    }

    public boolean isProbehlDulezityRozhovor() {
        return probehlDulezityRozhovor;
    }

    public void setProbehlDulezityRozhovor(boolean probehlDulezityRozhovor) {
        this.probehlDulezityRozhovor = probehlDulezityRozhovor;
    }

    public String getHlavniRec() {
        return hlavniRec;
    }

    public void setHlavniRec(String hlavniRec) {
        this.hlavniRec = hlavniRec;
    }

    public String getDalsiRec() {
        return dalsiRec;
    }

    public void setDalsiRec(String dalsiRec) {
        this.dalsiRec = dalsiRec;
    }

    public String getVecKterouPotrebuje() {
        return vecKterouPotrebuje;
    }

    public void setVecKterouPotrebuje(String vecKterouPotrebuje) {
        this.vecKterouPotrebuje = vecKterouPotrebuje;
    }

    public String getVecKterouNabidne() {
        return vecKterouNabidne;
    }

    public void setVecKterouNabidne(String vecKterouNabidne) {
        this.vecKterouNabidne = vecKterouNabidne;
    }

}
