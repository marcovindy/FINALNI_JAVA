package logika;

/**
 *  Class Vec - popisuje atributy a chovani veci
 *  @author Marek Vaníček
 *  @version školní rok 2021/2022
 */
public class Vec {
    private String nazev;
    private Boolean prenositelna;
    private String popis;
    private int cena = 0;

    public Vec(String nazev, Boolean prenositelna, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
    }

    /**
     * vrati nazev veci
     * @return textu nazvu veci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * vrati popis veci
     * @return textu popis veci
     */
    public String getPopis() {
        return popis;
    }

    /**
     * vrati, zda lze predmet sebrat
     * @return true pokud lze sebrat, false pokud nelze sebrat
     */
    public Boolean getPrenositelna() {
        return prenositelna;
    }

    /**
     * nastavi cenu veci
     * @param cena - cena, kterou vec bude mit
     */
    public void setCena(int cena) {
        cena = cena;
    }

    /**
     * vrati cenu veci
     * @return cena veci
     */
    public int getCena() {
        return cena;
    }
}