package logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 * Tato třída je součástí jednoduché textové hry.
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */
public class Prostor {

    private final String nazev;
    private final String popis;
    private final Set<Prostor> vychody;
    private final List<Vec> veci;
    private final List<Postava> postavy;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     *              víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new ArrayList<>();
        postavy = new ArrayList<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     * <p>
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor druhy)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }


    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis(int vydrz) {
        return "Jsi v mistnosti/prostoru " + popis + "\n"
                + "Věci v prostoru: " + seznamVeci() + "\n"
                + "Lidé v prostoru: " + seznamPostav() + "\n"
                + popisVychodu() + "\n"
                + "Výdrž: " + vydrz;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        StringBuilder vracenyText = new StringBuilder("Východy:");
        for (Prostor sousedni : vychody) {
            vracenyText.append(" ").append(sousedni.getNazev());
        }
        return vracenyText.toString();
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor> hledaneProstory =
                vychody.stream()
                        .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                        .collect(Collectors.toList());
        if (hledaneProstory.isEmpty()) {
            return null;
        } else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vkládá věci do prostoru
     *
     * @param vec pro věc kterou chci přidat do prostoru
     */

    public void vlozVec(Vec vec) {
        veci.add(vec);
    }

    /**
     * Maže věci z prostoru
     *
     * @param vec pro věc kterou chci odebrat z prostoru
     */

    public void odeberVec(Vec vec) {
        veci.remove(vec);
    }

    /**
     * Ptá se, zda v prostoru je konkrétní věc
     *
     * @param nazevVeci pro věc kterou chci přidat do prostoru
     * @return zašle true, pokud je věc v prostoru a false pokud ne
     */

    public boolean obsahujeVec(String nazevVeci) {
        for (Vec vec : veci) {
            if (vec.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vybere konkrétní věc podle jména
     *
     * @param nazevVeci pro věc kterou chci vybrat
     * @return pošle věc, kterou jsme chtěli vybrat a nebo null
     */

    public Vec vyberVec(String nazevVeci) {
        Vec vybranaVec = null;
        for (Vec vec : veci) {
            vybranaVec = vec;
            if (vybranaVec != null) {
                if (nazevVeci.equals(vybranaVec.getNazev())) {
                    return vybranaVec;
                }
            }
        }
        return vybranaVec;
    }

    /**
     * Vytvoří seznam věcí a dá je do stringu na vypsání
     *
     * @return zašle seznam věcí ve stringu
     */

    public String seznamVeci() {
        StringBuilder seznam = new StringBuilder();
        for (Vec vec : veci) {
            seznam.append(vec.getNazev()).append(", ");
        }
        return seznam.toString();
    }

    /**
     * vloží postavu do prostoru
     *
     * @param postava pro postavu kterou chci přidat do prostoru
     */

    public void vlozPostavu(Postava postava) {
        postavy.add(postava);

    }

    /**
     * vloží postavu do prostoru
     *
     * @param jmeno podle jmena vymaže postavu
     */


    public void smazPostavu(String jmeno) {
        Postava postava = vratPostavu(jmeno);
        postavy.remove(postava);
    }

    /**
     * vytvoří seznam postav do stringu a pošle ho na vypsání
     *
     * @return zašle ve stringu seznam postav
     */


    public String seznamPostav() {
        StringBuilder seznam = new StringBuilder();
        for (Postava postava : postavy) {
            seznam.append(postava.getJmeno()).append(", ");
        }
        return seznam.toString();
    }

    /**
     * Vybere postavu podle jména a zašle dále jako objekt
     *
     * @return zašle postavu již jako objekt
     */

    public Postava vratPostavu(String jmenoPostavy) {
        Postava potrebnaPostava = null;
        for (Postava postava : postavy) {
            if (jmenoPostavy.equals(postava.getJmeno())) {
                potrebnaPostava = postava;
            }
        }
        return potrebnaPostava;
    }


    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
}
