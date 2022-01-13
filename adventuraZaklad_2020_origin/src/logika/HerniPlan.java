package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private Batoh batoh;
    public int vydrz;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        batoh = new Batoh(10, 0);
        vydrz = 10;

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor hospodaHlavniMistnost = new Prostor("Hospoda_hlavní_místnost","Žižkovská hospoda plná ožralých lidí");
        Prostor predKoleji = new Prostor("pred_koleji", "U vchodu před kolejí, pro tebe známé místo, kde na tebe pořád civí vrátný přes okno");
        Prostor zachodMuzi = new Prostor("zachod_muzi","Staré poblité záchody s hnědými věcmi na stěnách a stropu");
        Prostor zachodZeny = new Prostor("zachod_zeny","Né tak moc zapáchající záchody plné červenobílých kusů papíru okolo");
        Prostor hospodaKuchyn = new Prostor("hospoda_kuchyn","Zaplivaná kuchyň, kde pobíhají šváby a krysy");
        Prostor predHospodou = new Prostor("pred_hospodou","Ulice před hospodou, kde slyšíš policejní sirény a \"zpěv\" opilců");
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        hospodaHlavniMistnost.setVychod(zachodMuzi);
        hospodaHlavniMistnost.setVychod(zachodZeny);
        hospodaHlavniMistnost.setVychod(hospodaKuchyn);
        hospodaHlavniMistnost.setVychod(predHospodou);

        zachodMuzi.setVychod(hospodaHlavniMistnost);
        zachodZeny.setVychod(hospodaHlavniMistnost);
        hospodaKuchyn.setVychod(hospodaHlavniMistnost);

        predHospodou.setVychod(hospodaHlavniMistnost);
                
        aktualniProstor = zachodMuzi;  // hra začíná v domečku


        zachodMuzi.vlozVec(new Vec("hneda_hmota", true, "Fůj, co to je za strašnou věc, roztéká se po ruce."));
        zachodMuzi.vlozVec(new Vec("hadr", true, "Sakra ušpiněný hadr."));
        zachodMuzi.vlozVec(new Vec("zachod", false, "Sem se koná poptřeba."));

        zachodZeny.vlozVec(new Vec("zachod", false, "Sem se koná poptřeba."));
        zachodZeny.vlozVec(new Vec("penezenka", true, "Peněženka se vším, yes!"));
        zachodZeny.vlozVec(new Vec("hodinky", true, "Supr, hodinky, škoda že jsou ty čísla rozmazané, nepřečtu nic."));

        hospodaHlavniMistnost.vlozVec(new Vec("stůl", false, "Velký stůl."));
        hospodaHlavniMistnost.vlozVec(new Vec("židle", false, "Na tomto se sedí."));
        hospodaHlavniMistnost.vlozVec(new Vec("krýgl", true, "Krýgl od Braníčka, to se mi líbí."));
        hospodaHlavniMistnost.vlozVec(new Vec("podtácek", true, "Pěkně mokrej podtácek."));

        hospodaKuchyn.vlozVec(new Vec("sporák", false, "Au."));
        hospodaKuchyn.vlozVec(new Vec("nůž", true, "Assissin's Creed Mode Activated!"));
        hospodaKuchyn.vlozVec(new Vec("špagety", true, "Dobrota."));
        hospodaKuchyn.vlozVec(new Vec("obracečka", true, "K čemu je asi tato věc?"));

        // Vec houba = new Vec("houba", true)
        // Vec strom = new Vec("strom", false)

        //louka.vlozVec(houba);
        //louka.vlozVec(strom);
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    public Batoh getBatoh() {
        return this.batoh;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }


}
