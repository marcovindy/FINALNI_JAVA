import logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída CenaTest slouží ke komplexnímu otestování
 * třídy PrikazCena
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class BatohTest {
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
     * Testuje, zda lze předmět vzít, vyhodit
     */
    @Test
    public void batohTest() {

        // test sebrani veci, uspesne

        assertEquals("Sakra ušpiněný hadr.\n" +
                        "Vložil jsi do batohu: hadr.",
                hra1.zpracujPrikaz("seber hadr"));

        // test sebrani veci, ktera neni v prostoru

        assertEquals("blbost neni v mistnosti",
                hra1.zpracujPrikaz("seber blbost"));

        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");

        // test prenositelnosti

        assertEquals("stůl se nedá přenášet",
                hra1.zpracujPrikaz("seber stůl"));

        // test prikazu batoh

        assertEquals("Pracně otevírám batoh\n" +
                        "Obsah batohu: \n" +
                        "hadr, ",
                hra1.zpracujPrikaz("batoh"));

        // test vyhozeni

        assertEquals("Nevím co tím myslíš? Tento příkaz neznám. ",
                hra1.zpracujPrikaz("vyhoď hadr"));

    }
}
