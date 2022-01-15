package logika;

/**
 * Class PostavaHospodsky - konkrétní postava
 *
 * @author Marek Vaníček
 * @version 1.0 pro školní rok 2021/2022
 */


public class PostavaHospodsky extends Postava {

    private Batoh batoh;
    private Postava servirka;
    public boolean dostalsVec = false;

    public PostavaHospodsky(String jmeno,
                            boolean probehlDulezityRozhovor,
                            boolean dostalaSvouVec,
                            String hlavniRec,
                            String dalsiRec,
                            String vecKterouPotrebuje,
                            Vec vecKterouNabidne,
                            Batoh batoh,
                            PostavaServirka servirka) {
        super(jmeno, probehlDulezityRozhovor, dostalaSvouVec, hlavniRec, dalsiRec, vecKterouPotrebuje, vecKterouNabidne);
        this.batoh = batoh;
        this.servirka = servirka;
    }


    @Override
    public String mluv() {
        if(servirka.isProbehlDulezityRozhovor()){
            if(batoh.vlezeSeDoBatohu() && !(dostalsVec) && servirka.isProbehlDulezityRozhovor()){
                dostalsVec = true;
                batoh.vlozVec(getVecKterouNabidne());
                return """
                    
                    [Ja]: Zdravím, nenašel se tu náhodou mobil?
                    [Hospodsky]: Jaký byl?
                    [Ja]: Modrý značka Wakasaki, průhledné pouzdro.
                    [Hospodsky]: Tak ten tu čirou náhodou mám.
                    *Hospodsky předává telefon*
                    [Ja]: Děkuju, naschle.
                    """;
            }
        }
        return super.mluv();
    }
}
