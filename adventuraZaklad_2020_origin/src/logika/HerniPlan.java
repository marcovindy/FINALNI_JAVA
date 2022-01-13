package logika;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Postava> postavy;
    private Batoh batoh;
    public int vydrz;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        batoh = new Batoh(10, 0);
        postavy = new HashSet<>();
        vydrz = 11;

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor hospodaHlavniMistnost = new Prostor("hospoda_hlavní_místnost","Žižkovská hospoda plná ožralých lidí");
        Prostor predKoleji = new Prostor("pred_koleji", "U vchodu před kolejí, pro tebe známé místo, kde na tebe pořád civí vrátný přes okno");
        Prostor zachodMuzi = new Prostor("záchod_muži","Staré poblité záchody s hnědými věcmi na stěnách a stropu");
        Prostor zachodZeny = new Prostor("záchod_ženy","Né tak moc zapáchající záchody plné červenobílých kusů papíru okolo");
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

        // === Naplnění místnosti - Záchod muži ===

        zachodMuzi.vlozVec(new Vec("hadr", true, "Sakra ušpiněný hadr."));
        zachodMuzi.vlozVec(new Vec("hnědá_hmota", true, "Fůj, co to je za strašnou věc, roztéká se po ruce."));
        zachodMuzi.vlozVec(new Vec("záchod", false, "Sem se koná poptřeba."));

        // === Naplnění místnosti - Záchod ženy ===

        zachodZeny.vlozVec(new Vec("hodinky", true, "Kdo to tu asi nechal? Uvidíme."));
        zachodZeny.vlozVec(new Vec("záchod", false, "Sem se koná poptřeba."));
        zachodZeny.vlozVec(new Vec("peněženka", true, "Tu jsem hledal!"));
        PostavaHolkaZeZachodu holkaZeZachodu = new PostavaHolkaZeZachodu("holka_ze_záchodů",
                false, false,
                "[Holka ze záchodů]: Blééééé.! #$%^@ Grk. Ueeee!\n" +
                "[Ja]: No teda... A pak že já vypadám, že mám dost.\n" +
                "[Holka ze záchodů]: Co tady děláš. Nech mě být, odejdi.\n" +
                "[Ja]: Myslím, že potřebuješ trochu vody, počkej.\n",
                "[Hospodský]: Teď nemám čas\n",
                "voda",
                "");
        zachodZeny.vlozPostavu(holkaZeZachodu);

        // === Naplnění místnosti - Hospoda hlavní místnost ===

        hospodaHlavniMistnost.vlozVec(new Vec("voda", true, "Voda, proč ne."));
        hospodaHlavniMistnost.vlozVec(new Vec("pivo", true, "Pivo zdarma, to mám ale štěstí!"));
        hospodaHlavniMistnost.vlozVec(new Vec("stůl", false, "Velký stůl."));
        hospodaHlavniMistnost.vlozVec(new Vec("židle", false, "Na tomto se sedí."));
        hospodaHlavniMistnost.vlozVec(new Vec("podtácek", true, "Pěkně mokrej podtácek."));
        PostavaHospodsky hospodsky = new PostavaHospodsky("hospodský", false, false,
                "[Hospodský]: No ty ale vypadáš! Měl bys jít domů, tady spát nemůžeš.\n" +
                "[Já]: Hned půjdu, akorát si nemůžu vzpomenout, kde mám své věci....\n" +
                "[Hospodský]: Podívej se na záchody, tam jsi strávil pomalu celej večer.\n" +
                "[Já]: Hmm dík.\n",
                "[Hospodský]: Teď nemám čas\n",
                "parek",
                "mobil");
        hospodaHlavniMistnost.vlozPostavu(hospodsky);
        PostavaHospodsky opilec = new PostavaHospodsky("opilec", false, false,
                "Co chceš?\n", "xx", "parek", "mobil");
        hospodaHlavniMistnost.vlozPostavu(opilec);
        PostavaHospodsky servirka = new PostavaHospodsky("servírka", false, false,
                "Co chceš?\n", "xx", "parek", "mobil");
        hospodaHlavniMistnost.vlozPostavu(servirka);


        hospodaKuchyn.vlozVec(new Vec("sporák", false, "Au."));
        hospodaKuchyn.vlozVec(new Vec("nůž", true, "Assissin's Creed Mode Activated!"));
        hospodaKuchyn.vlozVec(new Vec("špagety", true, "Dobrota."));
        hospodaKuchyn.vlozVec(new Vec("obracečka", true, "K čemu je asi tato věc?"));
        PostavaHospodsky kuchar = new PostavaHospodsky("servírka", false, false,
                "Co chceš?\n", "xx", "parek", "mobil");
        hospodaHlavniMistnost.vlozPostavu(kuchar);




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

    public Set<Postava> getPostavy() {
        return postavy;
    }

}
