import logika.Hra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída KouzelneZadaniTest slouží ke komplexnímu otestování
 * třídy PrikazAbrakadabra, PrikazCarymaryfuk a s třídami jími spojenymi
 *
 * @author Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class KouzelneZadaniTest {
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
     * Testuje, zda se hráč může objevit v kouzelných místnostech a pak se vrátit zpět do hry
     */
    @Test
    public void TestZmenyProstoru() {

        assertEquals("Zamlží se ti před očima a přeneseš se z záchod_muži do kouzelná_místnost_abraka\n" +
                        "Jsi v mistnosti/prostoru Kouzelná místnost Abrakadabra, kde lze vidět kouzelného dědečka.\n" +
                        "Věci v prostoru: \n" +
                        "Lidé v prostoru: kouzelný_dědeček, \n" +
                        "Východy:\n" +
                        "Výdrž: 11",
                hra1.zpracujPrikaz("abrakadabra"));
        // Když postava znovu vykoná příkaz abrakadabra, tak by měla se objevit zpět v předchozí místnosti
        assertEquals("Zamlží se ti před očima a přeneseš se z kouzelná_místnost_abraka do záchod_muži\n" +
                        "Jsi v mistnosti/prostoru Staré poblité záchody s hnědými věcmi na stěnách a stropu.\n" +
                        "Věci v prostoru: hadr, hnědá_hmota, záchod, \n" +
                        "Lidé v prostoru: čůrající_týpek, \n" +
                        "Východy: hospoda_hlavní_místnost\n" +
                        "Výdrž: 11",
                hra1.zpracujPrikaz("abrakadabra"));

        hra1.zpracujPrikaz("abrakadabra");
        // Když postava vykoná příkaz čárymáryfuk, tak by měla se objevit v další kouzelné místnosti
        assertEquals("Zamlží se ti před očima a přeneseš se z kouzelná_místnost_abraka do kouzelná_místnost_čárymáry\n" +
                        "Jsi v mistnosti/prostoru Kouzelná místnost čárymáryfuk, kde lze vidět starou babičku.\n" +
                        "Věci v prostoru: \n" +
                        "Lidé v prostoru: kouzelná_babička, \n" +
                        "Východy:\n" +
                        "Výdrž: 11",
                hra1.zpracujPrikaz("čárymáryfuk"));
        // Když postava vykoná příkaz čárymáryfuk znovu, tak by měla se objevit v minulé místnosti hry, což je záchod_muži
        assertEquals("Zamlží se ti před očima a přeneseš se z kouzelná_místnost_čárymáry do záchod_muži\n" +
                        "Jsi v mistnosti/prostoru Staré poblité záchody s hnědými věcmi na stěnách a stropu.\n" +
                        "Věci v prostoru: hadr, hnědá_hmota, záchod, \n" +
                        "Lidé v prostoru: čůrající_týpek, \n" +
                        "Východy: hospoda_hlavní_místnost\n" +
                        "Výdrž: 11",
                hra1.zpracujPrikaz("čárymáryfuk"));
    }

    /***************************************************************************
     * Testuje, jestli se dá vyhrát hra pomocí splněného úkolu pro dědečka
     */
    @Test
    public void TestUkol() {
        hra1.zpracujPrikaz("abrakadabra");
        // Konverzace s 1.sobou z kouzelné místnosti
        assertEquals("\n" +
                        "[Já]: Dobrý den, kde jsem se to ocitl? To snad musí být sen, nebo jsem zase se opil a někam dostal?\n" +
                        "[Kouzelný dědeček]: Nazdar holomku, já jsem kouzelník Abraka.\n" +
                        "[Já]: Co to říkáte? Jakože umíte nějaký supr, čupr triky?\n" +
                        "[Kouzelný dědeček]: To si piš. Ale abych udělal supr tríček, tak potřebuju brýle, které má u sebe moje stará.\n" +
                        "[Já]: Já vám je klidně přinesu, kde vaší starou najdu?\n" +
                        "[Kouzelný dědeček]: Stačí říct slovíčko čárymáryfuk a budeš u ní. Ale pozor, je hrozná, jen tak ti je nedá.\n" +
                        "[Já]: Tak já to tedy zkusím.\n" +
                        "(Odemkl sis příkaz čárymáryfuk, tento příkaz lze vykonat jen v této místnosti)\n",
                hra1.zpracujPrikaz("mluv kouzelný_dědeček"));


        hra1.zpracujPrikaz("čárymáryfuk");
        // Konverzace s 2.sobou z kouzelné místnosti
        assertEquals("\n" +
                        "[Já]: Dobrý den babičko, neměla byste brýle pro vašeho muže?\n" +
                        "[Kouzelná babička]: No jooo, ten dědek si to tu zase nechal!\n" +
                        "[Já]: Tak já mu to přinesu.\n" +
                        "[Kouzelná babička]: Hehe pokud jsi toho hoden! Vyzkouším si tě...\n" +
                        "[Kouzelná babička]: Měříte můj život v hodinách a sloužím vám tím, že zanikám. Jsem rychlá, když jsem hubená a pomalá, když jsem tlustá. Vítr je můj nepřítel.\n" +
                        "(Teď napiš příkaz: odpověz kouzelná_babička [tvoje_odpověď])\n",
                hra1.zpracujPrikaz("mluv kouzelná_babička"));
        // Odpověď 2.osobě
        assertEquals("Super, uhodl jsi\n" +
                        "Babka ti dá brejle.",

                hra1.zpracujPrikaz("odpověz kouzelná_babička svíčka"));
        assertFalse(hra1.konecHry());
        hra1.zpracujPrikaz("abrakadabra");
        // Předání předmětu 1.osobě + výhra
        assertEquals("brýle předána postavě kouzelný_dědeček\n" +
                        "[Kouzelný dědeček]: Díky mladej a teď musíš do postele, abys dal statistiku! Abrakadabra!\n" +
                        "Dokončil jsi easter egg! Vyhráváš! Děda tě přenesl na kolej do postele.",
                hra1.zpracujPrikaz("dej brýle kouzelný_dědeček"));
        assertTrue(hra1.konecHry());
    }

    /***************************************************************************
     * Testuje, jestli se dá Prohrát, pokud hrář neuhádne hádanku
     */
    @Test
    public void TestUkol2() {
        hra1.zpracujPrikaz("abrakadabra");

        hra1.zpracujPrikaz("čárymáryfuk");
        // Konverzace s 2.sobou z kouzelné místnosti
        assertEquals("\n" +
                        "[Já]: Dobrý den babičko, neměla byste brýle pro vašeho muže?\n" +
                        "[Kouzelná babička]: No jooo, ten dědek si to tu zase nechal!\n" +
                        "[Já]: Tak já mu to přinesu.\n" +
                        "[Kouzelná babička]: Hehe pokud jsi toho hoden! Vyzkouším si tě...\n" +
                        "[Kouzelná babička]: Měříte můj život v hodinách a sloužím vám tím, že zanikám. Jsem rychlá, když jsem hubená a pomalá, když jsem tlustá. Vítr je můj nepřítel.\n" +
                        "(Teď napiš příkaz: odpověz kouzelná_babička [tvoje_odpověď])\n",
                hra1.zpracujPrikaz("mluv kouzelná_babička"));
        // Odpověď 2.osobě - špatná
        assertEquals("Špatná odpověď\n" +
                        "Prohráls! Babička tě poslala do jiné dimenze.",

                hra1.zpracujPrikaz("odpověz kouzelná_babička Velbloud"));

        assertTrue(hra1.konecHry());
    }
}
