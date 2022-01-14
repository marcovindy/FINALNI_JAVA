package logika;

import java.util.HashSet;
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

    private Hra hra;
    private Prostor aktualniProstor;
    private Batoh batoh;
    private int vydrz;

    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
      * @param hra
      */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        batoh = new Batoh(10, 0);
        this.hra = hra;
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // === vytvářejí se jednotlivé prostory ===
        Prostor hospodaHlavniMistnost = new Prostor("hospoda_hlavní_místnost","Žižkovská hospoda plná ožralých lidí");
        Prostor zachodMuzi = new Prostor("záchod_muži","Staré poblité záchody s hnědými věcmi na stěnách a stropu");
        Prostor zachodZeny = new Prostor("záchod_ženy","Né tak moc zapáchající záchody plné červenobílých kusů papíru okolo");
        Prostor hospodaKuchyn = new Prostor("hospoda_kuchyň","Zaplivaná kuchyň, kde pobíhají šváby a krysy");
        Prostor predHospodou = new Prostor("před_hospodou","Ulice před hospodou, kde slyšíš policejní sirény a \"zpěv\" opilců");
        Prostor uliceZizkovska = new Prostor("ulice_žizkovská","Ulice Žižkovská, zaplivaná ulice, kterou docela znáš.");
        Prostor uliceVinohradska = new Prostor("ulice_vinohradská","Ulice před hospodou, kde slyšíš policejní sirény a \"zpěv\" opilců");
        Prostor uliceKonevova = new Prostor("ulice_koněvova","Ulice Koněvova, dlouhá ulice plná temných zákoutí.");
        Prostor obchod = new Prostor("obchod","Tady bych si něco mohl koupit.");
        Prostor ulicePodLipami = new Prostor("ulice_pod_lipami","Ulice Pod Lipami, tady už to znáš moc dobře, jsi blízko!");
        Prostor smradlavaUlicka = new Prostor("smradlavá_ulička","");
        Prostor kanal = new Prostor("kanál","");
        Prostor predKoleji = new Prostor("před_kolejí","Před kolejí, už vidíš vrátnici, ale nikdo tam není. Snad se konečně dostaneš dovnitř.");
        Prostor uliceSpojovaci = new Prostor("ulice_spojovací","");


        // === přiřazují se průchody mezi prostory (sousedící prostory) ===
        hospodaHlavniMistnost.setVychod(zachodMuzi);
        hospodaHlavniMistnost.setVychod(zachodZeny);
        hospodaHlavniMistnost.setVychod(hospodaKuchyn);
        hospodaHlavniMistnost.setVychod(predHospodou);

        zachodMuzi.setVychod(hospodaHlavniMistnost);
        zachodZeny.setVychod(hospodaHlavniMistnost);
        hospodaKuchyn.setVychod(hospodaHlavniMistnost);

        predHospodou.setVychod(hospodaHlavniMistnost);
        predHospodou.setVychod(uliceZizkovska);
        predHospodou.setVychod(uliceVinohradska);

        uliceZizkovska.setVychod(predHospodou);
        uliceZizkovska.setVychod(uliceKonevova);
        uliceZizkovska.setVychod(obchod);

//        uliceVinohradska.setVychod(predHospodou);

        uliceKonevova.setVychod(uliceZizkovska);
        uliceKonevova.setVychod(ulicePodLipami);
        uliceKonevova.setVychod(smradlavaUlicka);
        uliceKonevova.setVychod(kanal);

        ulicePodLipami.setVychod(predKoleji);
        ulicePodLipami.setVychod(uliceKonevova);
        ulicePodLipami.setVychod(uliceSpojovaci);
                
        aktualniProstor = zachodMuzi;  // hra začíná na pánských záchodech

        // === Naplnění místnosti - Záchod muži ===

        zachodMuzi.vlozVec(new Vec("hadr", true, "Sakra ušpiněný hadr.", false));
        zachodMuzi.vlozVec(new Vec("hnědá_hmota", true, "Fůj, co to je za strašnou věc, roztéká se po ruce.", false));
        zachodMuzi.vlozVec(new Vec("záchod", false, "Sem se koná poptřeba.", false));
        PostavaCurajiciTypek curajiciTypek = new PostavaCurajiciTypek("čůrající_týpek", false, false,
                "[Čůrající týpek]: Nech mě bejt!\n", "[Čůrající týpek]: Chčiju ty vole, né? Kam to čumíš!", "<NIC>", "<NIC>");
        zachodMuzi.vlozPostavu(curajiciTypek);

        // === Naplnění místnosti - Záchod ženy ===

        zachodZeny.vlozVec(new Vec("hodinky", true, "Kdo to tu asi nechal? Uvidíme.", false));
        zachodZeny.vlozVec(new Vec("záchod", false, "Sem se koná poptřeba.", false));
        zachodZeny.vlozVec(new Vec("peněženka", true, "Tu jsem hledal!", false));
        PostavaHolkaZeZachodu holkaZeZachodu = new PostavaHolkaZeZachodu("holka_ze_záchodů",
                false, false,
                "[Holka ze záchodů]: Blééééé.! #$%^@ Grk. Ueeee!\n" +
                "[Ja]: No teda... A pak že já vypadám, že mám dost.\n" +
                "[Holka ze záchodů]: Co tady děláš. Nech mě být, odejdi.\n" +
                "[Ja]: Myslím, že potřebuješ trochu vody, počkej.\n",
                "[Hospodský]: Teď nemám čas\n",
                "voda",
                "<NIC>");
        zachodZeny.vlozPostavu(holkaZeZachodu);

        // === Naplnění místnosti - Hospoda hlavní místnost ===

        hospodaHlavniMistnost.vlozVec(new Vec("voda", true, "Voda, proč ne.", true));
        hospodaHlavniMistnost.vlozVec(new Vec("pivo", true, "Pivo zdarma, to mám ale štěstí!", true));
        hospodaHlavniMistnost.vyberVec("voda").setBodyVydrze(-100);
        hospodaHlavniMistnost.vyberVec("pivo").setBodyVydrze(3);
        hospodaHlavniMistnost.vlozVec(new Vec("stůl", false, "Velký stůl.", false));
        hospodaHlavniMistnost.vlozVec(new Vec("židle", false, "Na tomto se sedí.", false));
        hospodaHlavniMistnost.vlozVec(new Vec("podtácek", true, "Pěkně mokrej podtácek.", false));
        PostavaHospodsky hospodsky = new PostavaHospodsky("hospodský", false, false,
                "[Hospodský]: No ty ale vypadáš! Měl bys jít domů, tady spát nemůžeš.\n" +
                "[Já]: Hned půjdu, akorát si nemůžu vzpomenout, kde mám své věci....\n" +
                "[Hospodský]: Podívej se na záchody, tam jsi strávil pomalu celej večer.\n" +
                "[Já]: Hmm dík.\n",
                "[Hospodský]: Teď nemám čas\n",
                "parek",
                "mobil");
        hospodaHlavniMistnost.vlozPostavu(hospodsky);
        PostavaOpilec opilec = new PostavaOpilec("opilec", false, false,
                "Co chceš?\n", "xx", "pivo", "<NIC>");
        hospodaHlavniMistnost.vlozPostavu(opilec);
        PostavaServirka servirka = new PostavaServirka("servírka", false, false,
                "[Ja]: Můžu zaplatit?\n" +
                        "[Servirka]: V pořádku už to udělali kamarádi s kterýma jsi tu byl\n" +
                        "[Ja]: Aah super a neviděla jste tu mobil a klíče, nějak jsem je vytratil.\n" +
                        "[Servirka]: Nějaký muž našel mobil, klíče bohužel ne, ale zeptej se na mobil hospodského.\n" +
                        "[Ja]: Super, díky. Tak se mějte.\n",
                "[Servirka]: Ano? Ještě něco?\n" +
                "[Já]: Nene\n",
                "hodinky",
                "<NIC>");
        hospodaHlavniMistnost.vlozPostavu(servirka);

        // === Naplnění místnosti - Hospoda kuchyň ===

        hospodaKuchyn.vlozVec(new Vec("sporák", false, "Au.", false));
        hospodaKuchyn.vlozVec(new Vec("špagety", true,
                "Voní suprově, snad tak budou i chutnat. Už jsem nějaký jídlo potřeboval. Teď to stačí jen zkonzumovat."
                , true));
        hospodaHlavniMistnost.vyberVec("špagety").setBodyVydrze(7);
        hospodaKuchyn.vlozVec(new Vec("nůž", true, "To vypadá pěkně nebezpečně.", false));
        hospodaKuchyn.vlozVec(new Vec("obracečka", true, "K čemu je asi tato věc?", false));
        PostavaKuchar kuchar = new PostavaKuchar("kuchař", false, false,
                "[Kuchař]: Co chceš?\n", "[Kuchař]: Nech mě.", "paprika", "<NIC>");
        hospodaKuchyn.vlozPostavu(kuchar);

        // === Naplnění místnosti - Před hospodou ===

        predHospodou.vlozVec(new Vec("bota", true, "Nějaká pochozená bota.", false));
        PostavaBandaOpilcu bandaOpilcu = new PostavaBandaOpilcu("banda_opilců", false, false,
                "[Kuchař]: Co chceš?\n", "[Kuchař]: Nech mě.", "pivo", "<NIC>");
        predHospodou.vlozPostavu(bandaOpilcu);
        PostavaBezdomovec bezdomovec = new PostavaBezdomovec("bezdomovec", false, false,
                "[Kuchař]: Co chceš?\n", "[Kuchař]: Nech mě.", "hodinky", "<NIC>");
        predHospodou.vlozPostavu(kuchar);



        setVydrz(11);




    }

    public void zkontrolujVydrz(int operace){
        if(operace == 1) {
            if (getVydrz() <= 0 ){
                String nazevAktualnihoProstoru = getAktualniProstor().getNazev();

                if ((nazevAktualnihoProstoru.equals("hospoda_hlavní_místnost"))
                        || (nazevAktualnihoProstoru.equals("záchod_muži"))
                        || (nazevAktualnihoProstoru.equals("záchod_ženy"))
                        || (nazevAktualnihoProstoru.equals("hospoda_kuchyň"))) {
                    ukoncitHru("Bohužel jsi se úplně vyčerpal a usnul v hospodě.\n"
                            + "V hospodě tě zamknuli a ráno nikdo nepřichází.\n"
                            + "Kvůli tomu jsi nestihl přijít na zkoušku.\n"
                            + "Prohra!");
                }else {
                    ukoncitHru("Bohužel jsi se úplně vyčerpal a usnul mimo postel.\n"
                            + "Když jsi spal, tak tě někdo přepadl a ukradl ti všechno oblečení\n"
                            + "Nahej jít na zkoušku nemůžeš a tak jsi ji nestihl.\n"
                            + "Prohra!");
                }
            }
        } else if (operace == 2) {
            if ( getVydrz() <= 0 ) {
                ukoncitHru("Bohužel jsi snědl něco co jsi neměl a usnul mimo postel.\n"
                        + "Když jsi spal, tak tě někdo přepadl a ukradl ti všechno oblečení\n"
                        + "Nahej jít na zkoušku nemůžeš a tak jsi ji nestihl.\n"
                        + "Prohra!");
            }
        }

    }

    public void ukoncitHru(String zaverecnyText) {
        hra.setText(zaverecnyText);
        hra.setKonecHry(true);
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

    public int getVydrz() {
        return vydrz;
    }

    public void setVydrz(int vydrz) {
        this.vydrz = vydrz;
    }
}
