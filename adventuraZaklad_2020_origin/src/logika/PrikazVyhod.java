package logika;

public class PrikazVyhod implements IPrikaz{
    private static final String NAZEV = "vyhod";
    private HerniPlan plan;

    public PrikazVyhod(HerniPlan plan) {
        this.plan = plan;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš mi říct, co mám vyhodit";
        } else if (parametry.length > 1) {
            return "Nemůžeš vyhodit více věcí zároveň.";
        } else {
            String nazevVeci = parametry[0];
            if(!(plan.getBatoh().obsahujeVec(nazevVeci))){
                return nazevVeci + " nemáš u sebe, takže ji nemůžeš vyhodit.";
            } else {
                Vec pozadovanaVec = plan.getBatoh().vyberVec(nazevVeci);
                if(pozadovanaVec == null){
                    return nazevVeci + " se nedá vyhodit";
                }else{
                    boolean povedloSeVyhodit = plan.getBatoh().vyhodVec(pozadovanaVec);
                    if(povedloSeVyhodit){
                        plan.getAktualniProstor().vlozVec(pozadovanaVec);
                        return nazevVeci + " jsi vzal z batohu a dal do místnosti";
                    }
                }
            }
        }

        return "Vyhozeno!";
    }

    public String getNazev() {
        return NAZEV;
    }
}
