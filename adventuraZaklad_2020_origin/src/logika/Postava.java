package logika;

/**
 * Class Postava - obecna trida pro vsechny postavy, další postavy se extendují, zde je jejich basic logika
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class Postava implements IPostava {

    private final String jmeno;
    private boolean probehlDulezityRozhovor;
    private boolean dostalaSvouVec;
    private String hlavniRec;
    private String dalsiRec;
    private String vecKterouPotrebuje;
    private Vec vecKterouNabidne;

    /**
     * Vytvoření věci se zadaným popisem, jestli se dá přenášet a jestli lze sníst
     *
     * @param jmeno                   jmeno postavy.
     * @param probehlDulezityRozhovor jestli již s postavou se dříve mluvilo.
     * @param dostalaSvouVec          jestli dostala věc kterou potřebuje.
     * @param hlavniRec               prvni rozhovor.
     * @param dalsiRec                dalsi rozhovory s postavou.
     * @param vecKterouPotrebuje      právě konkrétní věc, kterou bychom jí měli dát.
     * @param vecKterouNabidne        věc kterou nám ona může dát, pokud je to součástí nějakého úkolu.
     */


    public Postava(String jmeno, boolean probehlDulezityRozhovor, boolean dostalaSvouVec, String hlavniRec, String dalsiRec, String vecKterouPotrebuje, Vec vecKterouNabidne) {
        this.jmeno = jmeno;
        this.probehlDulezityRozhovor = probehlDulezityRozhovor;
        this.hlavniRec = hlavniRec;
        this.dalsiRec = dalsiRec;
        this.vecKterouPotrebuje = vecKterouPotrebuje;
        this.vecKterouNabidne = vecKterouNabidne;
        this.dostalaSvouVec = dostalaSvouVec;
    }

    /**
     * určuje zda hráč prvně mluví s postavou či ne, podle toho přiřadí určitou konverzaci
     *
     * @return zasílá text, který postava řekne
     */


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

    /**
     * předání věcí mezi hráčem a postavou, pokud postava potřebuje (chce) předmět, tak se uloží, že jej dostala
     *
     * @param vec věc, kterou chceme dát postave
     * @return posílá, zda předmět postava si vzala od hráče, nebo ne
     */


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

    public Vec getVecKterouNabidne() {
        return vecKterouNabidne;
    }

    public void setVecKterouNabidne(Vec vecKterouNabidne) {
        this.vecKterouNabidne = vecKterouNabidne;
    }

}
