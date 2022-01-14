package logika;

/**
 * Class HerniPlan - tato třída představuje mapu adventury
 * inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * včetně postav a věcí v prostoru
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class HerniPlan {

    private Hra hra;
    private Prostor aktualniProstor;
    private Batoh batoh;
    private int vydrz;


    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *
     * @param hra logika hry
     */

    public HerniPlan(Hra hra) {
        batoh = new Batoh(10, 0);
        zalozProstoryHry();
        this.hra = hra;
    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví záchod_muži.
     * Do prostorů vytvoří postavy a věci.
     * <p>
     * Dále nastavuje také hodnotu výdrže
     */
    private void zalozProstoryHry() {

        // === vytvářejí se jednotlivé prostory ===

        Prostor hospodaHlavniMistnost = new Prostor("hospoda_hlavní_místnost", "Žižkovská hospoda plná ožralých lidí");
        Prostor zachodMuzi = new Prostor("záchod_muži", "Staré poblité záchody s hnědými věcmi na stěnách a stropu");
        Prostor zachodZeny = new Prostor("záchod_ženy", "Né tak moc zapáchající záchody plné červenobílých kusů papíru okolo");
        Prostor hospodaKuchyn = new Prostor("hospoda_kuchyň", "Zaplivaná kuchyň, kde pobíhají šváby a krysy");
        Prostor predHospodou = new Prostor("před_hospodou", "Ulice před hospodou, kde slyšíš policejní sirény a \"zpěv\" opilců");
        Prostor uliceZizkovska = new Prostor("ulice_žizkovská", "Ulice Žižkovská, zaplivaná ulice, kterou docela znáš.");
        Prostor uliceVinohradska = new Prostor("ulice_vinohradská", "Ulice před hospodou, kde slyšíš policejní sirény a \"zpěv\" opilců");
        Prostor uliceKonevova = new Prostor("ulice_koněvova", "Ulice Koněvova, dlouhá ulice plná temných zákoutí.");
        Prostor obchod = new Prostor("obchod", "Tady bych si něco mohl koupit.");
        Prostor ulicePodLipami = new Prostor("ulice_pod_lipami", "Ulice Pod Lipami, tady už to znáš moc dobře, jsi blízko!");
        Prostor smradlavaUlicka = new Prostor("smradlavá_ulička", "Ulička s pochybným individuem.");
        Prostor kanal = new Prostor("kanál", "Temnota, proč by sem někdo chodil?");
        Prostor predKoleji = new Prostor("před_kolejí", "Před kolejí, už vidíš vrátnici, ale nikdo tam není. Snad se konečně dostaneš dovnitř.");
        Prostor uliceSpojovaci = new Prostor("ulice_spojovací", "Konečná, odsud už dál asi nepůjdeš.");
        Prostor sklad = new Prostor("sklad", "Hmmm, sklad obchodu.");
        Prostor kino = new Prostor("kino", "");
        Prostor hrbitov = new Prostor("hrbitov", "");


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

        obchod.setVychod(uliceZizkovska);

        obchod.setVychod(sklad);

        sklad.setVychod(obchod);

        uliceVinohradska.setVychod(predHospodou);
        uliceVinohradska.setVychod(kino);
        uliceVinohradska.setVychod(hrbitov);

        uliceKonevova.setVychod(uliceZizkovska);
        uliceKonevova.setVychod(ulicePodLipami);
        uliceKonevova.setVychod(smradlavaUlicka);
        uliceKonevova.setVychod(kanal);

        smradlavaUlicka.setVychod(uliceKonevova);

        kanal.setVychod(uliceKonevova);

        ulicePodLipami.setVychod(predKoleji);
        ulicePodLipami.setVychod(uliceKonevova);
        ulicePodLipami.setVychod(uliceSpojovaci);

        uliceSpojovaci.setVychod(ulicePodLipami);


        // === Nastavení začátku na záchody_muži ===

        aktualniProstor = zachodMuzi;

        // === Naplnění místnosti - Záchod muži ===

        zachodMuzi.vlozVec(new Vec("hadr", true, "Sakra ušpiněný hadr.", false));
        zachodMuzi.vlozVec(new Vec("hnědá_hmota", true, "Fůj, co to je za strašnou věc, roztéká se po ruce.", false));
        zachodMuzi.vlozVec(new Vec("záchod", false, "Sem se koná poptřeba.", false));

        PostavaCurajiciTypek curajiciTypek = new PostavaCurajiciTypek("čůrající_týpek", false, false,
                "[Čůrající týpek]: Nech mě bejt!\n", "[Čůrající týpek]: Chčiju ty vole, né? Kam to čumíš!\n",
                "<NIC>", null);
        zachodMuzi.vlozPostavu(curajiciTypek);

        // === Naplnění místnosti - Záchod ženy ===

        zachodZeny.vlozVec(new Vec("hodinky", true, "Kdo to tu asi nechal? Uvidíme.", false));
        zachodZeny.vlozVec(new Vec("záchod", false, "Sem se koná poptřeba.", false));
        zachodZeny.vlozVec(new Vec("peněženka", true, "Tu jsem hledal!", false));

        PostavaHolkaZeZachodu holkaZeZachodu = new PostavaHolkaZeZachodu("holka_ze_záchodů",
                false, false,
                """
                        [Holka ze záchodů]: Blééééé.! #$%^@ Grk. Ueeee!
                        [Ja]: No teda... A pak že já vypadám, že mám dost.
                        [Holka ze záchodů]: Co tady děláš. Nech mě být, odejdi.
                        [Ja]: Myslím, že potřebuješ trochu vody, počkej.
                        """,
                "[Hospodský]: Teď nemám čas\n",
                "voda",
                new Vec("ISIC", true, "VŠE ISIC", false));
        zachodZeny.vlozPostavu(holkaZeZachodu);

        // === Naplnění místnosti - Hospoda hlavní místnost ===

        hospodaHlavniMistnost.vlozVec(new Vec("voda", true, "Voda, proč ne.", true));
        hospodaHlavniMistnost.vlozVec(new Vec("pivo", true, "Pivo zdarma, to mám ale štěstí!", true));
        hospodaHlavniMistnost.vyberVec("voda").setBodyVydrze(-100);
        hospodaHlavniMistnost.vyberVec("pivo").setBodyVydrze(3);
        hospodaHlavniMistnost.vlozVec(new Vec("stůl", false, "Velký stůl.", false));
        hospodaHlavniMistnost.vlozVec(new Vec("židle", false, "Na tomto se sedí.", false));
        hospodaHlavniMistnost.vlozVec(new Vec("podtácek", true, "Pěkně mokrej podtácek.", false));

        PostavaServirka servirka = new PostavaServirka("servírka", false, false,
                """
                        [Ja]: Můžu zaplatit?
                        [Servirka]: V pořádku už to udělali kamarádi s kterýma jsi tu byl
                        [Ja]: Aah super a neviděla jste tu mobil a klíče, nějak jsem je vytratil.
                        [Servirka]: Nějaký muž našel mobil, klíče bohužel ne, ale zeptej se na mobil hospodského.
                        [Ja]: Super, díky. Tak se mějte.
                        """,
                """
                        [Servirka]: Ano? Ještě něco?
                        [Já]: Nene
                        """,
                "hodinky",
                null);
        hospodaHlavniMistnost.vlozPostavu(servirka);

        PostavaHospodsky hospodsky = new PostavaHospodsky("hospodský", false, false,
                """
                        [Hospodský]: No ty ale vypadáš! Měl bys jít domů, tady spát nemůžeš.
                        [Já]: Hned půjdu, akorát si nemůžu vzpomenout, kde mám své věci....
                        [Hospodský]: Podívej se na záchody, tam jsi strávil pomalu celej večer.
                        [Já]: Hmm dík.
                        """,
                "[Hospodský]: Teď nemám čas\n",
                "parek",
                new Vec("mobil", true, "Hledaný mobil", false), this.getBatoh(), servirka);
        hospodaHlavniMistnost.vlozPostavu(hospodsky);

        PostavaOpilec opilec = new PostavaOpilec("opilec", false, false,
                "Co chceš?\n", "xx", "pivo", null);
        hospodaHlavniMistnost.vlozPostavu(opilec);



        // === Naplnění místnosti - Hospoda kuchyň ===

        hospodaKuchyn.vlozVec(new Vec("sporák", false, "Au.", false));
        hospodaKuchyn.vlozVec(new Vec("špagety", true,
                "Voní suprově, snad tak budou i chutnat. Už jsem nějaký jídlo potřeboval. Teď to stačí jen zkonzumovat."
                , true));
        hospodaHlavniMistnost.vyberVec("špagety").setBodyVydrze(7);
        hospodaKuchyn.vlozVec(new Vec("nůž", true, "To vypadá pěkně nebezpečně.", false));
        hospodaKuchyn.vlozVec(new Vec("obracečka", true, "K čemu je asi tato věc?", false));

        PostavaKuchar kuchar = new PostavaKuchar("kuchař", false, false,
                "[Kuchař]: Co chceš?\n", "[Kuchař]: Nech mě.\n", "paprika", null);
        hospodaKuchyn.vlozPostavu(kuchar);

        // === Naplnění místnosti - Před hospodou ===

        predHospodou.vlozVec(new Vec("bota", true, "Nějaká pochozená bota.", false));

        PostavaBandaOpilcu bandaOpilcu = new PostavaBandaOpilcu("banda_opilců", false, false,
                "[Kuchař]: Co chceš?\n", "[Kuchař]: Nech mě.\n", "pivo", null);
        predHospodou.vlozPostavu(bandaOpilcu);

        PostavaBezdomovec bezdomovec = new PostavaBezdomovec("bezdomovec", false, false,
                "[Kuchař]: Co chceš?\n", "[Kuchař]: Nech mě.\n", "hodinky", null);
        predHospodou.vlozPostavu(bezdomovec);

        // === Nastavení počátční výdrže ===

        setVydrz(11);


    }

    /**
     * Metoda kontroluje zda hráči nedošla výdrž
     *
     * @param operace pro zjištění z jakého příkazu nám kontrola přichází
     */

    public void zkontrolujVydrz(int operace) {
        if (operace == 1) {
            if (getVydrz() <= 0) {
                String nazevAktualnihoProstoru = getAktualniProstor().getNazev();

                if ((nazevAktualnihoProstoru.equals("hospoda_hlavní_místnost"))
                        || (nazevAktualnihoProstoru.equals("záchod_muži"))
                        || (nazevAktualnihoProstoru.equals("záchod_ženy"))
                        || (nazevAktualnihoProstoru.equals("hospoda_kuchyň"))) {
                    ukoncitHru("""
                            Bohužel jsi se úplně vyčerpal a usnul v hospodě.
                            V hospodě tě zamknuli a ráno nikdo nepřichází.
                            Kvůli tomu jsi nestihl přijít na zkoušku.
                            Prohra!""");
                } else {
                    ukoncitHru("""
                            Bohužel jsi se úplně vyčerpal a usnul mimo postel.
                            Když jsi spal, tak tě někdo přepadl a ukradl ti všechno oblečení
                            Nahej jít na zkoušku nemůžeš a tak jsi ji nestihl.
                            Prohra!""");
                }
            }
        } else if (operace == 2) {
            if (getVydrz() <= 0) {
                ukoncitHru("""
                        Bohužel jsi snědl něco co jsi neměl a usnul mimo postel.
                        Když jsi spal, tak tě někdo přepadl a ukradl ti všechno oblečení
                        Nahej jít na zkoušku nemůžeš a tak jsi ji nestihl.
                        Prohra!""");
            }
        }

    }

    /**
     * Metoda kontroluje zda hráči nedošla výdrž
     *
     * @param zaverecnyText pro text, který se vypíše na konci hry
     */

    public void ukoncitHru(String zaverecnyText) {
        hra.setText(zaverecnyText);
        hra.setKonecHry(true);
    }


    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     * @param prostor nový aktuální prostor
     */

    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /**
     * Metoda vrací odkaz na batoh postavy, ve kterém hřáč nosí věci.
     *
     * @return batoh
     */

    public Batoh getBatoh() {
        return this.batoh;
    }

    /**
     * Metoda vrací výdrž postavy.
     *
     * @return vydrz
     */

    public int getVydrz() {
        return vydrz;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     * @param vydrz nová aktuální výdrž
     */

    public void setVydrz(int vydrz) {
        this.vydrz = vydrz;
    }
}
