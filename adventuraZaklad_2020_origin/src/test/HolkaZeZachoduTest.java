import logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída HolkaZeZachoduTest slouží ke komplexnímu otestování
 * postavy holka_ze_záchodů
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class HolkaZeZachoduTest {
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
     * Testuje zda se u postaví mění typ rozhovoru.
     */
    @Test
    public void testRozhovorHolka() {


        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");
        hra1.zpracujPrikaz("seber voda");
        hra1.zpracujPrikaz("jdi záchod_ženy");


        // Kontrola zda rozhovor změní boolean zda proběhl rozhovor
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().vratPostavu("holka_ze_záchodů").isProbehlDulezityRozhovor());
        hra1.zpracujPrikaz("mluv holka_ze_záchodů");
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().vratPostavu("holka_ze_záchodů").isProbehlDulezityRozhovor());

    }

    /***************************************************************************
     * Testuje zda postava může dostat předmět, který chce.
     */
    @Test
    public void testPredaniVeci() {


        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");
        hra1.zpracujPrikaz("seber voda");
        hra1.zpracujPrikaz("jdi záchod_ženy");


        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().vratPostavu("holka_ze_záchodů").isDostalaSvouVec());
        hra1.zpracujPrikaz("dej voda holka_ze_záchodů");
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().vratPostavu("holka_ze_záchodů").isDostalaSvouVec());

    }

    /***************************************************************************
     * Testuje zda se může postava přesunout do jiné místnosti po splnění úkolu.
     */
    @Test
    public void presunHolky() {
        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");
        hra1.zpracujPrikaz("seber voda");
        hra1.zpracujPrikaz("jdi záchod_ženy");
        hra1.zpracujPrikaz("dej voda holka_ze_záchodů");
        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");
        hra1.zpracujPrikaz("jdi před_hospodou");
        hra1.zpracujPrikaz("jdi ulice_žižkovská");
        hra1.zpracujPrikaz("jdi ulice_koněvova");
        hra1.zpracujPrikaz("jdi ulice_pod_lipami");
        hra1.zpracujPrikaz("jdi před_kolejí");
        assertEquals("Jsi v mistnosti/prostoru Před kolejí, už vidíš vrátnici, ale nikdo tam není. Snad se konečně dostaneš dovnitř.\n" +
                        "Věci v prostoru: lavička, bota, \n" +
                        "Lidé v prostoru: holka_ze_záchodů, \n" +
                        "Východy: ulice_pod_lipami kolej\n" +
                        "Výdrž: 3",
                hra1.getHerniPlan().getAktualniProstor().dlouhyPopis(hra1.getHerniPlan().getVydrz()));

        assertEquals("\n" +
                        "[Ja]: Ahoj.\n" +
                        "[Holka ze záchodů]: To jsi ty! Co mi dal tu vodu v hospodě na záchodech. *Cítí se trapně*\n" +
                        "[Ja]: No vypadá to, že už ti je líp.\n" +
                        "[Holka ze záchodů]: Už je to lepší, takže jsi taky tady z kolejí.\n" +
                        "[Ja]: Jsem, ale asi jsem ztratil ISIC, nepůjčíš mi ho náhodou?\n" +
                        "[Holka ze záchodů]: Otevřu ti, počkej.\n" +
                        "*Píp*\n" +
                        "*Blokuju dveře, aby se nezavřeli*\n" +
                        "[Ja]: Díky moc.\n",
                hra1.zpracujPrikaz("mluv holka_ze_záchodů"));
    }


    /***************************************************************************
     * Testuje zda se postava smaže z aktuálního prostoru po přesunu postavy.
     */
    @Test
    public void testSmazaniPostavy() {

        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");
        hra1.zpracujPrikaz("seber voda");
        hra1.zpracujPrikaz("jdi záchod_ženy");
        hra1.zpracujPrikaz("dej voda holka_ze_záchodů");
        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");


        assertEquals("Jsi v mistnosti/prostoru Né tak moc zapáchající záchody plné červenobílých kusů papíru okolo.\n" +
                "Věci v prostoru: hodinky, záchod, peněženka, \n" +
                "Lidé v prostoru: \n" +
                "Východy: hospoda_hlavní_místnost\n" +
                "Výdrž: 7", hra1.zpracujPrikaz("jdi záchod_ženy"));
         }
}
