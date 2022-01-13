package logika;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Batoh {

    private Set<Vec> obsahBatohu;
    private int omezeniBatohu;
    private int penize;


    public Batoh(int omezeniBatohu, int penize){
        this.omezeniBatohu = omezeniBatohu;
        this.penize = penize;
        obsahBatohu = new HashSet<Vec>();
    }

    public String seznamVeci(){
        String seznam = "";
        for( Vec vec : obsahBatohu ){
            seznam = seznam + vec.getNazev() + ", ";

        }
        return seznam;
    }

    public String dlouhyPopis(){
        String text = "Pracně otevírám batoh\n";
        if( obsahBatohu.size() <= 0 ){
            text += "Obsah batohu: \n" + "Nic jsem v batohu nenašel";
        } else {
            text += "Obsah batohu: \n" + seznamVeci();
        }
        return text;
    }

    public boolean obsahujeVec(String nazevVeci){
        for( Vec vec : obsahBatohu ){
            if(vec.getNazev().equals(nazevVeci)){
                return true;
            }
        }
        return false;
    }

    public boolean vlezeSeDoBatohu(){
        if( this.obsahBatohu.size() < this.omezeniBatohu ){
            return true;
        }
        return false;
    }

    public boolean vlozVec( Vec vec ){
        if( vlezeSeDoBatohu() ){
            obsahBatohu.add(vec);
            return true;
        }
        return false;
    }

    public Vec vyberVec(String nazevVeci){
        Vec vybranaVec = null;
        for(Vec vec : obsahBatohu){
            vybranaVec = vec;
            if ( vybranaVec != null ){
                if(nazevVeci.equals(vybranaVec.getNazev())){
                    return vybranaVec;
                }
            }
        }
        return vybranaVec;
    }

    public boolean vyhodVec( Vec vec ){
        if(vec != null){
            obsahBatohu.remove(vec);
            return true;
        }
        return false;
    }


}
