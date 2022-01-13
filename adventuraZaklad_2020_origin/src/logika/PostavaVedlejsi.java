package logika;

public class PostavaVedlejsi implements IPostava {

    private String jmeno;
    private String hlavniRec;


    public PostavaVedlejsi(String jmeno, String hlavniRec) {
        this.jmeno = jmeno;
        this.hlavniRec = hlavniRec;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getHlavniRec() {
        return hlavniRec;
    }

    public void setHlavniRec(String hlavniRec) {
        this.hlavniRec = hlavniRec;
    }

    @Override
    public String mluv() {
        return null;
    }

    @Override
    public boolean dej(Vec vec) {
        return false;
    }
}
