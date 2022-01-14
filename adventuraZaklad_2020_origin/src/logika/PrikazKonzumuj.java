package logika;

/*******************************************************************************
 *
 *
 * @author    Marek Vaníček
 * @version   školní rok 2021/2022
 */
public class PrikazKonzumuj implements IPrikaz {

    private static final String NAZEV = "konzumuj";
    private HerniPlan plan;
    private Hra hra;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazKonzumuj(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     * Po zavolání příkazu se vypíše obsah batohu????
     *
     * @param   - vola se bez parametru
     * @return  - Vrací obsah batohu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        String text;
        if( parametry.length == 0 ){
            return "Musíš říct co chceš konzumovat";
        } else if (parametry.length > 1){
            return "Můžeš konzumovat pouze jednu věc současně";
        } else {
            String nazevVeci = parametry[0];
            if ( plan.getBatoh().obsahujeVec(nazevVeci) ){
                Vec pozadovanaVec = plan.getBatoh().vyberVec(nazevVeci);
                if ( pozadovanaVec.getLzeKonzumovat() ) {

                    plan.setVydrz(plan.getVydrz() + pozadovanaVec.getBodyVydrze());
                    if ( pozadovanaVec.getBodyVydrze() < 0 ) {
                        text = "Bléééé, tak tohle mi fakt nepomohlo.\n" + "Výdrž: " + plan.getVydrz();
                        plan.zkontrolujVydrz(2);
                    } else if ( pozadovanaVec.getBodyVydrze() >= 4 ) {
                        text = "Mňam, tak to bylo sakra dobrý! Hned se cítím líp.\n" + "Výdrž: " + plan.getVydrz() ;
                    } else {
                        text = "To mi moc nedalo *ach*.\n" + "Výdrž: " + plan.getVydrz() ;
                    }
                } else {
                    text = nazevVeci + " sníst nemůžeš";
                }
            } else {
                text = nazevVeci + " nemáš v batohu";
            }
        }
        return text;
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}

