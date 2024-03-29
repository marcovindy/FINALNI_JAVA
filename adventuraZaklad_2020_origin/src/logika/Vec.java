package logika;

/**
 * Class Vec - popisuje atributy a chovani veci
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Černý, Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class Vec {
    private final String nazev;
    private final boolean prenositelna;
    private final String popis;
    private int cena;
    private final boolean lzeKonzumovat;
    private int bodyVydrze;

    /**
     * Vytvoření věci se zadaným popisem, jestli se dá přenášet a jestli lze sníst
     *
     * @param nazev         nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     *                      víceslovný název bez mezer.
     * @param popis         Popis prostoru.
     * @param prenositelna  jestli je věc přenositelná.
     * @param lzeKonzumovat jestli věc lze sníst.
     */

    public Vec(String nazev, boolean prenositelna, String popis, boolean lzeKonzumovat) {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.lzeKonzumovat = lzeKonzumovat;
    }

    /**
     * vrati nazev veci
     *
     * @return textu nazvu veci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * vrati popis veci
     *
     * @return textu popis veci
     */
    public String getPopis() {
        return popis;
    }

    /**
     * vrati, zda lze predmet sebrat
     *
     * @return true pokud lze sebrat, false pokud nelze sebrat
     */
    public Boolean getLzeKonzumovat() {
        return lzeKonzumovat;
    }

    /**
     * vrati, zda lze predmet sebrat
     *
     * @return true pokud lze sebrat, false pokud nelze sebrat
     */
    public Boolean getPrenositelna() {
        return prenositelna;
    }

    /**
     * nastavi cenu veci
     *
     * @param cena - cena, kterou vec bude mit
     */
    public void setCena(int cena) {
        this.cena = cena;
    }

    /**
     * vrati cenu veci
     *
     * @return cena veci
     */
    public int getCena() {
        return cena;
    }

    /**
     * nastavi body výdrže
     *
     * @param bodyVydrze - vydrz, ktera se nastavi na vec
     */
    public void setBodyVydrze(int bodyVydrze) {
        this.bodyVydrze = bodyVydrze;
    }

    /**
     * vrati body výdrže konkrétní věci
     *
     * @return body výdrže po konzumaci
     */
    public int getBodyVydrze() {
        return bodyVydrze;
    }


}
