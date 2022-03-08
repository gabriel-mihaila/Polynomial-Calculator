package assignment;

public class DividePolinom {
    private Polinom cat;
    private Polinom rest;

    public DividePolinom(Polinom cat, Polinom rest) {
        this.cat = cat;
        this.rest = rest;
    }

    public Polinom getCat() {
        return cat;
    }

    public void setCat(Polinom cat) {
        this.cat = cat;
    }

    public Polinom getRest() {
        return rest;
    }

    public void setRest(Polinom rest) {
        this.rest = rest;
    }
}
