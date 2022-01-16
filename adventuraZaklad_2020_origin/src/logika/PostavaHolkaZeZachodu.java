package logika;

/**
 * Class PostavaHolkaZeZachodu - konkrétní postava
 * Toto je důležitá postava, jeden ze způsobů, jak vyhrát hru je jí pomoc na záchodech
 * Díky tomu, že ji dá hráč vodu, tak postava odejde před kolej, kde si od ní můžeme půjši ISIC
 * nebo-li vstupenku na kolej
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class PostavaHolkaZeZachodu extends Postava {

    private boolean dostalsVec;
    private boolean jePredKoleji;
    private Batoh batoh;
    private Prostor aktualniProstor;
    private Prostor predKoleji;

    public PostavaHolkaZeZachodu(String jmeno,
                                 boolean probehlDulezityRozhovor,
                                 boolean dostalaSvouVec,
                                 String hlavniRec,
                                 String dalsiRec,
                                 String vecKterouPotrebuje,
                                 Vec vecKterouNabidne, Prostor aktualniProstor, Prostor predKoleji, Batoh batoh,
                                 boolean jePredKoleji,
                                 boolean dostalsVec) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
        this.aktualniProstor = aktualniProstor;
        this.batoh = batoh;
        this.jePredKoleji = jePredKoleji;
        this.dostalsVec = dostalsVec;
        this.predKoleji = predKoleji;
    }

    public void presunHolku(){
        if ( isDostalaSvouVec() && !(jePredKoleji) ) {
            Prostor prostorProPresun = this.getPredKoleji();
            if(prostorProPresun != null){
                prostorProPresun.vlozPostavu(new PostavaHolkaZeZachodu("holka_ze_záchodů",
                        false, false,
                        """
                                [Holka ze záchodů]: Blééééé.! #$%^@ Grk. Ueeee!
                                [Ja]: No teda... A pak že já vypadám, že mám dost.
                                [Holka ze záchodů]: Co tady děláš. Nech mě být, odejdi.
                                [Ja]: Myslím, že potřebuješ trochu vody, počkej.
                                """,
                        "[Hospodský]: Teď nemám čas\n",
                        "voda",
                        new Vec("ISIC", true, "VŠE ISIC", false),
                        this.getAktualniProstor(), prostorProPresun, this.getBatoh(), true, false));
                this.getAktualniProstor().smazPostavu("holka_ze_záchodů");
            }
        }
    }


    @Override
    public String mluv() {
        if ( jePredKoleji ) {
            setVecKterouPotrebuje("zapalovač");
            if( batoh.vlezeSeDoBatohu() && !(isDostalsVec()) ){
                setDostalsVec(true);
                batoh.vlozVec(getVecKterouNabidne());
               if ( batoh.obsahujeVec("zapalovač") ) {
                   return """
                    
                    [Ja]: Ahoj.
                    [Holka ze záchodů]: To jsi ty! Co mi dal tu vodu v hospodě na záchodech. *Cítí se trapně*
                    [Ja]: No vypadá to, že už ti je líp.
                    [Holka ze záchodů]: Už je to lepší, takže jsi taky tady z kolejí.
                    [Ja]: Jsem, ale asi jsem ztratil ISIC, nepůjčíš mi ho náhodou?
                    [Holka ze záchodů]: Otevřu ti, počkej.
                    *Píp*
                    *Blokuju dveře, aby se nezavřeli*
                    [Ja]: Díky moc.
                    [Holka ze záchodů]: Nemáš náhodou oheň?
                    [Ja]: Mám, tady si vem zapalovač, já ho potřebovat nebudu.
                    """;
               } else {
                   return """
                    
                    [Ja]: Ahoj.
                    [Holka ze záchodů]: To jsi ty! Co mi dal tu vodu v hospodě na záchodech. *Cítí se trapně*
                    [Ja]: No vypadá to, že už ti je líp.
                    [Holka ze záchodů]: Už je to lepší, takže jsi taky tady z kolejí.
                    [Ja]: Jsem, ale asi jsem ztratil ISIC, nepůjčíš mi ho náhodou?
                    [Holka ze záchodů]: Otevřu ti, počkej.
                    *Píp*
                    *Blokuju dveře, aby se nezavřeli*
                    [Ja]: Díky moc.
                    """;
               }
            } else if (!(batoh.vlezeSeDoBatohu() && !(isDostalsVec()))) {
                return """
                    
                    [Ja]: Ahoj.
                    [Holka ze záchodů]: To jsi ty! Co mi dal tu vodu v hospodě na záchodech. *Cítí se trapně*
                    [Ja]: No vypadá to, že už ti je líp.
                    [Holka ze záchodů]: Už je to lepší, takže jsi taky tady z kolejí.
                    [Ja]: Jsem, ale asi jsem ztratil ISIC, nepůjčíš mi ho náhodou?
                    [Holka ze záchodů]: Jasně.
                    
                    Nemáš místo v batohu!
                    """;
            } else {
                return """
                    
                    [Holka ze záchodů]: Počky, musím si zavolat
                    """;
            }
        } else {
            if (!(isProbehlDulezityRozhovor())) {
                setProbehlDulezityRozhovor(true);
                return "\n" + getHlavniRec();
            } else {
                return "\n" + getDalsiRec();
            }
        }
    }

    @Override
    public boolean dej(Vec vec) {
        if (vec.getNazev().equals(getVecKterouPotrebuje())) {
            setDostalaSvouVec(true);
            presunHolku();
            return true;
        }
        return false;
    }

    public boolean isDostalsVec() {
        return dostalsVec;
    }

    public void setDostalsVec(boolean dostalsVec) {
        this.dostalsVec = dostalsVec;
    }

    public Batoh getBatoh() {
        return batoh;
    }


    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }


    public Prostor getPredKoleji() {
        return predKoleji;
    }
}
