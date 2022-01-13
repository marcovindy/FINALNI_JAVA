package logika;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Postava - obecna trida pro vsechny postavy
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */

public class Postava implements IPostava {

    private String jmeno;
    private boolean probehlDulezityRozhovor;
    private String hlavniRec;
    private String dalsiRec;
    private String vecKterouPotrebuje;
    private String vecKterouNabidne;

    public Postava(String jmeno, boolean probehlDulezityRozhovor, String hlavniRec, String dalsiRec, String vecKterouPotrebuje, String vecKterouNabidne) {
        this.jmeno = jmeno;
        this.probehlDulezityRozhovor = probehlDulezityRozhovor;
        this.hlavniRec = hlavniRec;
        this.dalsiRec = dalsiRec;
        this.vecKterouPotrebuje = vecKterouPotrebuje;
        this.vecKterouNabidne = vecKterouNabidne;
    }




    public String mluv(){

        if( !(probehlDulezityRozhovor) ) {
            probehlDulezityRozhovor = true;
            return hlavniRec;
        } else {
            return dalsiRec;
        }
    }






    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
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
