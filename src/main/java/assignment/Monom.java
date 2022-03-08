package assignment;

public class Monom {
    private Double coeficient;
    private int putere;

    public Monom(Double coeficient, int putere) {
        this.coeficient = coeficient;
        this.putere = putere;
    }

    public Double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(Double coeficient) {
        this.coeficient = coeficient;
    }

    public int getPutere() {
        return putere;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }
}
