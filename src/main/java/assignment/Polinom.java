package assignment;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Polinom {

   private ArrayList<Monom> monom;
   private int gradPolinom;

    public Polinom(ArrayList<Monom> monom) {
        this.monom = new ArrayList<>();
        this.monom = monom;
        if(!monom.isEmpty())
            this.gradPolinom = monom.get(0).getPutere();
        else
            this.gradPolinom = -1;
    }

    public ArrayList<Monom> add(Polinom polynom) {
        ArrayList<Monom> rezultat = new ArrayList<>();
        double coefecient;
        int i = 0, j = 0, putere;
        while(i < this.monom.size() && j < polynom.monom.size()) {
            if(this.monom.get(i).getPutere() == polynom.monom.get(j).getPutere()){
                coefecient = this.monom.get(i).getCoeficient() + polynom.monom.get(j).getCoeficient();
                putere = this.monom.get(i).getPutere();
                i++; j++;
            }
            else if(this.monom.get(i).getPutere() > polynom.monom.get(j).getPutere()){
                coefecient = this.monom.get(i).getCoeficient();
                putere = this.monom.get(i).getPutere();
                i++;
            }
            else{
                coefecient = polynom.monom.get(j).getCoeficient();
                putere = polynom.monom.get(j).getPutere();
                j++;
            }
            rezultat.add(new Monom(coefecient, putere));
        }
        while(i < this.monom.size()){
            rezultat.add(new Monom(this.monom.get(i).getCoeficient(), this.monom.get(i).getPutere()));
            i++;
        }
        while(j < polynom.monom.size()){
            rezultat.add(new Monom(polynom.monom.get(j).getCoeficient(), polynom.monom.get(j).getPutere()));
            j++;
        }
        return rezultat;
    }

    public ArrayList<Monom> sub (Polinom polynom){
        ArrayList<Monom> rezultat = new ArrayList<>();
        for(Monom monom : polynom.monom){
            monom.setCoeficient(-1 * monom.getCoeficient());
        }
        rezultat = this.add(polynom);

        return rezultat;
    }

    public ArrayList<Monom> multiply (Polinom polynom){
        ArrayList<Monom> rezultat = new ArrayList<>();
        double coeficient;
        int putere, putereDuplicat;
        for(Monom monom1: this.monom) {
            for(Monom monom2: polynom.monom){
                coeficient = monom1.getCoeficient() * monom2.getCoeficient();
                putere = monom1.getPutere() + monom2.getPutere();
                putereDuplicat = 0;
                for(Monom monomRes : rezultat){
                    if(monomRes.getPutere() == putere){
                        monomRes.setCoeficient(monomRes.getCoeficient() + coeficient);
                        putereDuplicat = 1;
                    }
                }
                if(putereDuplicat == 0)
                    rezultat.add(new Monom(coeficient, putere));
            }
        }
        return rezultat;
    }

    public ArrayList<Monom> diff(){
        ArrayList<Monom> rezultat = new ArrayList<>();
        double coeficient;
        int putere;
        for(Monom monom : this.monom) {
            if(monom.getPutere() != 0){
                coeficient = monom.getCoeficient() * monom.getPutere();
                putere = monom.getPutere() - 1;
                rezultat.add(new Monom(coeficient, putere));
            }
            else
                rezultat.add(new Monom(0.0, 0));
        }
        return rezultat;
    }

    public ArrayList<Monom> integrate(){
        ArrayList<Monom> rezultat = new ArrayList<>();
        double coeficient;
        int putere;
        for(Monom monom : this.monom){
            putere = monom.getPutere() + 1;
            coeficient = monom.getCoeficient() / putere;
            rezultat.add(new Monom(coeficient, putere));
        }
        return rezultat;
    }

    public DividePolinom divide(Polinom impartitor) throws ExceptieImpartire {
        ArrayList<Monom> cat = new ArrayList<>();
        ArrayList<Monom> rest = new ArrayList<>();
        ArrayList<Monom> monomCat = new ArrayList<>();
        double coeficient;
        int putere;
        if(impartitor.gradPolinom == 0 && impartitor.monom.get(0).getCoeficient() == 0)
            throw new ExceptieImpartire("Impartire la 0");
        else{
            while(this.gradPolinom >= impartitor.gradPolinom){
                coeficient = this.monom.get(0).getCoeficient() / impartitor.monom.get(0).getCoeficient();
                putere = this.monom.get(0).getPutere() - impartitor.monom.get(0).getPutere();
                monomCat.add(new Monom(coeficient, putere));
                this.monom = (new Polinom(this.monom)).sub(new Polinom(impartitor.multiply(new Polinom(monomCat))));
                monomCat.remove(0);
                this.monom.remove(0);
                if(this.monom.size() == 0)
                    this.gradPolinom = -1;
                else
                    this.gradPolinom = this.monom.get(0).getPutere();

                cat.add(new Monom(coeficient, putere));
            }
            for(Monom demp: this.monom){
                rest.add(new Monom(demp.getCoeficient(), demp.getPutere()));
            }
            return new DividePolinom(new Polinom(cat), new Polinom(rest));
        }
    }

    public String toString(){
        StringBuilder polinom = new StringBuilder();
        int special0 = 0;
        for(Monom monom : this.monom) {
            if(Math.abs(monom.getCoeficient()) < 0.0001){special0++;
                System.out.println("0_test");
            }
            else {
                if(monom.getCoeficient() < 0){
                    if(Math.abs(monom.getCoeficient() + 1.0 ) < 0.0001 && monom.getPutere() != 0)
                        polinom.append("-");
                    else if(Math.abs(monom.getCoeficient() + monom.getCoeficient().intValue()) < 0.0001)
                        polinom.append(monom.getCoeficient().intValue());
                    else polinom.append(new DecimalFormat("#.##").format(monom.getCoeficient()));
                }
                else if(Math.abs(monom.getCoeficient() - 1.0) < 0.0001 && monom.getPutere() != 0)
                        polinom.append("+");
                    else if(Math.abs(monom.getCoeficient() - monom.getCoeficient().intValue()) < 0.0001)
                    polinom.append("+").append(monom.getCoeficient().intValue());
                else polinom.append("+").append(new DecimalFormat("#.##").format(monom.getCoeficient()));
                if(monom.getPutere() != 0){
                    if(monom.getPutere() == 1)
                        polinom.append("x");
                    else
                        polinom.append("x^").append(monom.getPutere());
                }
            }

        }
        if(special0 == this.monom.size()) polinom.append("0");
        return polinom.toString();
    }

    public ArrayList<Monom> getMonomul1() {
        return monom;
    }

    public void setMonomul1(ArrayList<Monom> monom) {
        this.monom = monom;
    }

    public int getGradPolinom() {
        return gradPolinom;
    }

    public void setGradPolinom(int gradPolinom) {
        this.gradPolinom = gradPolinom;
    }
}
