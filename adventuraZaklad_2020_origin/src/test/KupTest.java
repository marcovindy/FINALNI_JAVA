import logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy PrikazKup
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class KupTest {
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
     * Testuje koupení předmětu v obchodě
     *
     */
    @Test
    public void KoupeniVeci() {
        hra1.getHerniPlan().getBatoh().setPenize(100);

        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");
        hra1.zpracujPrikaz("jdi před_hospodou");
        hra1.zpracujPrikaz("jdi ulice_žižkovská");
        hra1.zpracujPrikaz("jdi obchod");

        assertEquals("Jsi v mistnosti/prostoru Obchod, tady bych si něco mohl koupit.\n" +
                "Věci v prostoru: mléko, rohlík, kytka, zapalovač, \n" +
                "Lidé v prostoru: prodavač, \n" +
                "Východy: ulice_žižkovská\n" +
                "Výdrž: "+hra1.getHerniPlan().getVydrz(),

                hra1.getHerniPlan().getAktualniProstor().dlouhyPopis(hra1.getHerniPlan().getVydrz()));

        assertEquals("Vložil jsi do batohu: rohlík.",
                hra1.zpracujPrikaz("kup rohlík"));

        assertEquals("Taková věc zde není",
                hra1.zpracujPrikaz("kup rohlík"));


    }
}
