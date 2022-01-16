import logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída VyhraPrvniMoznostTest slouží k otestování, zda hra může být zakončena dle scénáře
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class VyhraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje podle scénáře, zda může být hra vyhrána. (dokončena)
     */
    @Test
    public void testVyhra1() {

        // Úvod

        assertEquals("záchod_muži", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Jsi v mistnosti/prostoru Staré poblité záchody s hnědými věcmi na stěnách a stropu.\n" +
                "Věci v prostoru: hadr, hnědá_hmota, záchod, \n" +
                "Lidé v prostoru: čůrající_týpek, \n" +
                "Východy: hospoda_hlavní_místnost\n" +
                "Výdrž: 11", hra1.getHerniPlan().getAktualniProstor().dlouhyPopis(hra1.getHerniPlan().getVydrz()));

        // 0. krok

        assertEquals("Pracně otevírám batoh\n" +
                "Obsah batohu: \n" +
                "Nic jsem v batohu nenašel\n", hra1.zpracujPrikaz("batoh"));

        // 1. krok

        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");
        assertEquals(false, hra1.konecHry());
        assertEquals("Jsi v mistnosti/prostoru Žižkovská hospoda plná ožralých lidí.\n" +
                "Věci v prostoru: voda, pivo, podtácek, stůl, židle, \n" +
                "Lidé v prostoru: servírka, hospodský, opilec, \n" +
                "Východy: před_hospodou záchod_muži záchod_ženy hospoda_kuchyň\n" +
                "Výdrž: 10", hra1.getHerniPlan().getAktualniProstor().dlouhyPopis(hra1.getHerniPlan().getVydrz()));

        // 2. krok

        assertEquals("\n" +
                "[Hospodský]: No ty ale vypadáš! Měl bys jít domů, tady spát nemůžeš.\n" +
                "[Já]: Hned půjdu, akorát si nemůžu vzpomenout, kde mám své věci....\n" +
                "[Hospodský]: Podívej se na záchody, tam jsi strávil pomalu celej večer.\n" +
                "[Já]: Hmm dík.\n", hra1.zpracujPrikaz("mluv hospodský"));

        // 3. krok

        assertEquals("Pivo zdarma, to mám ale štěstí!\n" +
                "Vložil jsi do batohu: pivo.", hra1.zpracujPrikaz("seber pivo"));

        // 4. krok

        assertEquals("Voda, proč ne.\n" +
                "Vložil jsi do batohu: voda.", hra1.zpracujPrikaz("seber voda"));

        // 5. krok

        assertEquals("Jsi v mistnosti/prostoru Né tak moc zapáchající záchody plné červenobílých kusů papíru okolo.\n" +
                "Věci v prostoru: hodinky, záchod, peněženka, \n" +
                "Lidé v prostoru: holka_ze_záchodů, \n" +
                "Východy: hospoda_hlavní_místnost\n" +
                "Výdrž: 9", hra1.zpracujPrikaz("jdi záchod_ženy"));

        // 6. krok

        assertEquals("Tu jsem hledal!\n" +
                "Vložil jsi do batohu: peněženka.", hra1.zpracujPrikaz("seber peněženka"));

        // 7. krok

        assertEquals("Kdo to tu asi nechal? Uvidíme.\n" +
                "Vložil jsi do batohu: hodinky.", hra1.zpracujPrikaz("seber hodinky"));

        // 8. krok

        assertEquals("\n" +
                "[Holka ze záchodů]: Blééééé.! #$%^@ Grk. Ueeee!\n" +
                "[Ja]: No teda... A pak že já vypadám, že mám dost.\n" +
                "[Holka ze záchodů]: Co tady děláš. Nech mě být, odejdi.\n" +
                "[Ja]: Myslím, že potřebuješ trochu vody, počkej.\n", hra1.zpracujPrikaz("mluv holka_ze_záchodů"));

        // 9. krok

        assertEquals("voda předána postavě holka_ze_záchodů", hra1.zpracujPrikaz("dej voda holka_ze_záchodů"));

        // 10. krok

        assertEquals("Jsi v mistnosti/prostoru Žižkovská hospoda plná ožralých lidí.\n" +
                "Věci v prostoru: podtácek, stůl, židle, \n" +
                "Lidé v prostoru: servírka, hospodský, opilec, \n" +
                "Východy: před_hospodou záchod_muži záchod_ženy hospoda_kuchyň\n" +
                "Výdrž: 8", hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost"));

        // 11. krok

        assertEquals("Jsi v mistnosti/prostoru Zaplivaná kuchyň, kde pobíhají šváby a krysy.\n" +
                "Věci v prostoru: sporák, špagety, nůž, obracečka, \n" +
                "Lidé v prostoru: kuchař, \n" +
                "Východy: hospoda_hlavní_místnost\n" +
                "Výdrž: 7", hra1.zpracujPrikaz("jdi hospoda_kuchyň"));

        // 12. krok

        assertEquals("To vypadá pěkně nebezpečně.\n" +
                "Vložil jsi do batohu: nůž.", hra1.zpracujPrikaz("seber nůž"));

        // 13. krok

        assertEquals("Voní suprově, snad tak budou i chutnat. Už jsem nějaký jídlo potřeboval. Teď to stačí jen zkonzumovat.\n" +
                "Vložil jsi do batohu: špagety.", hra1.zpracujPrikaz("seber špagety"));

        // 14. krok

        assertEquals("Jsi v mistnosti/prostoru Žižkovská hospoda plná ožralých lidí.\n" +
                "Věci v prostoru: podtácek, stůl, židle, \n" +
                "Lidé v prostoru: servírka, hospodský, opilec, \n" +
                "Východy: před_hospodou záchod_muži záchod_ženy hospoda_kuchyň\n" +
                "Výdrž: 6", hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost"));

        // 15. krok

        assertEquals("\n" +
                "[Ja]: Můžu zaplatit?\n" +
                "[Servirka]: V pořádku už to udělali kamarádi s kterýma jsi tu byl\n" +
                "[Ja]: Aah super a neviděla jste tu mobil a klíče, nějak jsem je vytratil.\n" +
                "[Servirka]: Nějaký muž našel mobil, klíče bohužel ne, ale zeptej se na mobil hospodského.\n" +
                "[Ja]: Super, díky. Tak se mějte.\n", hra1.zpracujPrikaz("mluv servírka"));

        // 16. krok

        assertEquals("\n" +
                "[Ja]: Zdravím, nenašel se tu náhodou mobil?\n" +
                "[Hospodsky]: Jaký byl?\n" +
                "[Ja]: Modrý značka Wakasaki, průhledné pouzdro.\n" +
                "[Hospodsky]: Tak ten tu čirou náhodou mám.\n" +
                "*Hospodsky předává telefon*\n" +
                "[Ja]: Děkuju, naschle.\n", hra1.zpracujPrikaz("mluv hospodský"));

        // 17. krok

        assertEquals("Jsi v mistnosti/prostoru Ulice před hospodou, kde slyšíš policejní sirény a \"zpěv\" opilců.\n" +
                "Věci v prostoru: bota, \n" +
                "Lidé v prostoru: banda_opilců, bezdomovec, \n" +
                "Východy: ulice_vinohradská ulice_žižkovská hospoda_hlavní_místnost\n" +
                "Výdrž: 5", hra1.zpracujPrikaz("jdi před_hospodou"));

        // 18. krok

        assertEquals("Jsi v mistnosti/prostoru Ulice Žižkovská, zaplivaná ulice, kterou docela znáš.\n" +
                "Věci v prostoru: lavička, popelnice, lampa, nakouslá_bageta, \n" +
                "Lidé v prostoru: ležící_člověk, \n" +
                "Východy: před_hospodou obchod ulice_koněvova\n" +
                "Výdrž: 4", hra1.zpracujPrikaz("jdi ulice_žižkovská"));

        // 19. krok

        assertEquals("Mňam, tak to bylo sakra dobrý! Hned se cítím líp.\n" +
                "Výdrž: 11", hra1.zpracujPrikaz("konzumuj špagety"));

        // 20. krok

        assertEquals("\n" +
                "[Ja]: Petře to jsi ty?! Proč sakra ležíš na lavičce.\n" +
                "[Petr]: Trochu jsem usnul...\n" +
                "[Ja]: Zvládneš dojít domů?\n" +
                "[Petr]: Jasný, jasný, nemáš něco na pití nebo na jídlo?\n" +
                "[Ja]: Zrovna teď ne hele, každopádně zkus dojít domů\n" +
                "[Petr]: Hele nechal sis u mě klíče, tady je máš.\n" +
                "[Ja]: Super, ty jsem hledal!\n" +
                "*Petr ti předává klíče*\n", hra1.zpracujPrikaz("mluv ležící_člověk"));

        // 21. krok

        assertEquals("Jsi v mistnosti/prostoru Ulice Koněvova, dlouhá ulice plná temných zákoutí.\n" +
                "Věci v prostoru: lavička, popelnice, lampa, nakouslá_bageta, zapalovač, \n" +
                "Lidé v prostoru: random_týpek, \n" +
                "Východy: ulice_pod_lipami kanál smradlavá_ulička ulice_žižkovská\n" +
                "Výdrž: 10", hra1.zpracujPrikaz("jdi ulice_koněvova"));

        // 22. krok

        assertEquals("Hm, zapalovač.\n" +
                "Vložil jsi do batohu: zapalovač.", hra1.zpracujPrikaz("seber zapalovač"));


        // 23. krok

        assertEquals("Jsi v mistnosti/prostoru Ulice Pod Lipami, tady už to znáš moc dobře, jsi blízko!\n" +
                "Věci v prostoru: auto, patník, strom, zápalky, kniha, \n" +
                "Lidé v prostoru: starý_muž, \n" +
                "Východy: před_kolejí ulice_koněvova ulice_spojovací\n" +
                "Výdrž: 9", hra1.zpracujPrikaz("jdi ulice_pod_lipami"));

        // 22. krok

        assertEquals("Jsi v mistnosti/prostoru Před kolejí, už vidíš vrátnici, ale nikdo tam není. Snad se konečně dostaneš dovnitř.\n" +
                "Věci v prostoru: lavička, bota, \n" +
                "Lidé v prostoru: holka_ze_záchodů, \n" +
                "Východy: ulice_pod_lipami kolej\n" +
                "Výdrž: 8", hra1.zpracujPrikaz("jdi před_kolejí"));

        // 22. krok
        assertEquals("před_kolejí", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("\n" +
                "[Ja]: Ahoj.\n" +
                "[Holka ze záchodů]: To jsi ty! Co mi dal tu vodu v hospodě na záchodech. *Cítí se trapně*\n" +
                "[Ja]: No vypadá to, že už ti je líp.\n" +
                "[Holka ze záchodů]: Už je to lepší, takže jsi taky tady z kolejí.\n" +
                "[Ja]: Jsem, ale asi jsem ztratil ISIC, nepůjčíš mi ho náhodou?\n" +
                "[Holka ze záchodů]: Otevřu ti, počkej.\n" +
                "*Píp*\n" +
                "*Blokuju dveře, aby se nezavřeli*\n" +
                "[Ja]: Díky moc.\n" +
                "[Holka ze záchodů]: Nemáš náhodou oheň?\n" +
                "[Ja]: Mám, tady si vem zapalovač, já ho potřebovat nebudu.\n", hra1.zpracujPrikaz("mluv holka_ze_záchodů"));

        // 22. krok

        assertEquals("zapalovač předána postavě holka_ze_záchodů", hra1.zpracujPrikaz("dej zapalovač holka_ze_záchodů"));
        // 22. krok

        assertEquals("Tvé ztracené věci co jsi našel: peněženka, telefon, \n" +
                "\n" +
                "Máš štěstí, že máš alespoň telefon, zavolal jsi spolubydlicímu, aby ti otevřel, protože nemáš klíče,\n" +
                "ten ti nechtěl telefon zvednout, ale když jsi slyšel za dveřmi, že mu vyzvání, tak jsi zakřičel >Vstávej Pepků!< a on ti otevřel.\n" +
                "\n" +
                "Výhra!!! Dostal ses na kolej bez toho, aniž bys usnul někde na chodníku, gratuluji!!!", hra1.zpracujPrikaz("jdi kolej"));

    }
}
