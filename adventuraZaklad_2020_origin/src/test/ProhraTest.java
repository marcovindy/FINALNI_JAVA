import logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída ProhraTest testuje, zda lze prohrát
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class ProhraTest {
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
     * Testuje zda hráč může usnout po snězení špatného jídla
     *
     */
    @Test
    public void VydrzTest1() {


        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");
        hra1.zpracujPrikaz("seber pivo");
        hra1.getHerniPlan().getBatoh().vyberVec("pivo").setBodyVydrze(-20);

        assertEquals("Bléééé, tak tohle mi fakt nepomohlo.\n" +
                        "Výdrž: -10\n" +
                        "Bohužel jsi snědl něco co jsi neměl a usnul mimo postel.\n" +
                        "Když jsi spal, tak tě někdo přepadl a ukradl ti všechno oblečení\n" +
                        "Nahej jít na zkoušku nemůžeš a tak jsi ji nestihl.\n" +
                        "Prohra!",

                hra1.zpracujPrikaz("konzumuj pivo"));
    }

    /***************************************************************************
     * Testuje zda hráč může usnout, když se vyčerpá
     *
     */

    @Test
    public void VydrzTest2() {
        hra1.getHerniPlan().setVydrz(0);


        assertEquals("Bohužel jsi se úplně vyčerpal a usnul v hospodě.\n" +
                "V hospodě tě zamknuli a ráno nikdo nepřichází.\n" +
                "Kvůli tomu jsi nestihl přijít na zkoušku.\n" +
                "Prohra!", hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost"));


    }
}
