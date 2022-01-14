package logika;

import java.util.HashSet;
import java.util.Set;

/**
 * Class Batoh - v batohu se ukládají věci, které má postava u sebe
 * do batohu se lze kdykoliv podívat pomocí příkazu batoh
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Černý, Marek Vaníček
 * @version 5.0
 * @created Leden 2022
 */

public class Batoh {

    private Set<Vec> obsahBatohu;
    private int omezeniBatohu;
    private int penize;

    /**
     * konstruktor batohu
     */

    public Batoh(int omezeniBatohu, int penize) {
        this.omezeniBatohu = omezeniBatohu;
        this.penize = penize;
        obsahBatohu = new HashSet<Vec>();
    }

    /**
     * zobrazí obsah batohu a stav peněz, pokud hráč vlastní peněženku
     *
     * @return obsah batohu
     */

    public String seznamVeci() {
        String seznam = "";
        for (Vec vec : obsahBatohu) {
            seznam = seznam + vec.getNazev() + ", ";
        }
        if (obsahujeVec("peněženka")) {
            seznam += stavPenez();
        }
        return seznam;
    }

    /**
     * předává stav peněz
     *
     * @return text se stavem peněz
     */


    public String stavPenez() {
        return "Peníze: " + getPenize() + "\n";
    }

    /**
     * výpis obsahu batohu pro příkaz batoh
     *
     * @return vrácí text s výpisem batohu
     */

    public String vypisBatohu() {
        String text = "Pracně otevírám batoh\n";
        if (obsahBatohu.size() <= 0) {
            text += "Obsah batohu: \n" + "Nic jsem v batohu nenašel";
        } else {
            text += "Obsah batohu: \n" + seznamVeci();
        }
        return text;
    }

    /**
     * zjistí, zda batoh obsahuje konkrétní věc
     *
     * @param nazevVeci název věci, kterou hledáme
     * @return zasílá true pokud věc v batohu je a false pokud tam není
     */

    public boolean obsahujeVec(String nazevVeci) {
        for (Vec vec : obsahBatohu) {
            if (vec.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }

    /**
     * pouze zjistí, jestli není inventář plný
     *
     * @return zasílá true pokud je batoh plný a false pokud není plný
     */

    public boolean vlezeSeDoBatohu() {
        if (this.obsahBatohu.size() < this.omezeniBatohu) {
            return true;
        }
        return false;
    }

    /**
     * zkontoluje, zda je batoh plný, jinak vloží věc do batohu
     *
     * @param vec obsahuje vec kterou vkládáme do batohu
     * @return vraci true pokud se věc povedlo dát do batohu, false pokud je batoh již plný
     */

    public boolean vlozVec(Vec vec) {
        if (vlezeSeDoBatohu()) {
            obsahBatohu.add(vec);
            return true;
        }
        return false;
    }

    /**
     * vybere věc podle jejího názvu
     *
     * @param nazevVeci obsahuje název věci kterou chceme vybrat
     * @return vrátí věc, pokud ji nenašel, tak vrátí null
     */

    public Vec vyberVec(String nazevVeci) {
        Vec vybranaVec = null;
        for (Vec vec : obsahBatohu) {
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
     * vyhodí věc z batohu
     *
     * @param vec je věc, kterou chceme vyhodit
     * @return vrátí true pokud je vyhození provedlo a false pokud se vyhození nepovedlo
     */

    public boolean vyhodVec(Vec vec) {
        if (vec != null) {
            obsahBatohu.remove(vec);
            return true;
        }
        return false;
    }

    /**
     * vrací hodnotu peněz
     *
     * @return vrátí hodnotu peněz
     */

    public int getPenize() {
        return penize;
    }

    /**
     * nastaví novou hodnotu peněz
     *
     * @param penize je nová hodnota peněz, která se do batohu nastaví
     */

    public void setPenize(int penize) {
        this.penize = penize;
    }
}
