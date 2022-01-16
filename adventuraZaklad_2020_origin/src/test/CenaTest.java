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
public class CenaTest {
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
     * Testuje, zda se hráč v obchodě, může podívat na cenu předmětu, který v tomto prostoru je
     */
    @Test
    public void CenaVeci() {


        hra1.zpracujPrikaz("jdi hospoda_hlavní_místnost");
        hra1.zpracujPrikaz("jdi před_hospodou");
        hra1.zpracujPrikaz("jdi ulice_žižkovská");
        hra1.zpracujPrikaz("jdi obchod");


        assertEquals(":rohlík stojí 6",
                hra1.zpracujPrikaz("cena rohlík"));



    }
}
