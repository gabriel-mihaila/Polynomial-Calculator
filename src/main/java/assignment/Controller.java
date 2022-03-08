package assignment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller extends Component {
    private View gui;

    public Controller(View gui) {
        this.gui = gui;
        OperationsListeners();
    }

    public ArrayList<Monom> getMonoame(String polynom){
        ArrayList<Monom> monoame = new ArrayList<>();
        double coeficient;
        int putere;
        if(polynom == null)
            monoame.add(new Monom(0.0, 0));
        else {
            String template = "([+-]?)([[0-9].]*)([x]?[\\^]?)?([0-9]?)";
            Pattern pattern = Pattern.compile(template);
            Matcher matcher = pattern.matcher(polynom);
            while(matcher.find()) {
                coeficient = 1;
                if(matcher.group(1).equals("-"))
                    coeficient = -1;
                if(matcher.group(2) != null && !matcher.group(2).equals(""))
                    coeficient *= Double.parseDouble(matcher.group(2));

                if(matcher.group(3).equals("x^")) {
                    if(matcher.group(4) != null) putere = Integer.parseInt(matcher.group(4));
                    else putere = 0;
                }
                else
                    if(matcher.group(3).equals("x"))  putere = 1;
                    else putere =0;
                monoame.add(new Monom(coeficient, putere));
            }
            monoame.remove(monoame.size() - 1);

        }
        return monoame;
    }

    private void OperationsListeners()
    {
        gui.getAddButton().addActionListener(e ->{

                Polinom polinom1 = new Polinom(this.getMonoame(gui.getFirstPolynomField()));
                Polinom polinom2 = new Polinom(this.getMonoame(gui.getSecondPolynomField()));
                ArrayList<Monom> monomRezultat = polinom1.add(polinom2);
                Polinom polinomRezultat = new Polinom(monomRezultat);
                gui.setResultPolynomField1(polinomRezultat.toString());
                gui.setResultPolynomField2(null);
        });

        gui.getSubstractButton().addActionListener(e ->{

                Polinom polinom1 = new Polinom(this.getMonoame(gui.getFirstPolynomField()));
                Polinom polinom2 = new Polinom(this.getMonoame(gui.getSecondPolynomField()));
                ArrayList<Monom> monomRezultat = polinom1.sub(polinom2);
                Polinom polinomRezultat = new Polinom(monomRezultat);
                gui.setResultPolynomField1(polinomRezultat.toString());
                gui.setResultPolynomField2(null);
        });

        gui.getMultiplyButton().addActionListener(e ->{

                Polinom polinom1 = new Polinom(this.getMonoame(gui.getFirstPolynomField()));
                Polinom polinom2 = new Polinom(this.getMonoame(gui.getSecondPolynomField()));
                ArrayList<Monom> monomRezultat = polinom1.multiply(polinom2);
                Polinom polinomRezultat = new Polinom(monomRezultat);
                gui.setResultPolynomField1(polinomRezultat.toString());
                gui.setResultPolynomField2(null);

        });

        gui.getDiffButton().addActionListener(e ->{

                Polinom polinom1 = new Polinom(this.getMonoame(gui.getFirstPolynomField()));
                Polinom polinom2 = new Polinom(this.getMonoame(gui.getSecondPolynomField()));
                ArrayList<Monom> rezultatMonom = polinom1.diff();
                Polinom rezultatPolinom = new Polinom(rezultatMonom);
                gui.setResultPolynomField1("P1': " + rezultatPolinom.toString());
                rezultatMonom = polinom2.diff();
                rezultatPolinom = new Polinom(rezultatMonom);
                gui.setResultPolynomField2("P2': " + rezultatPolinom.toString());

        });

        gui.getIntegrateButton().addActionListener(e ->{

                Polinom polinom1 = new Polinom(this.getMonoame(gui.getFirstPolynomField()));
                Polinom polinom2 = new Polinom(this.getMonoame(gui.getSecondPolynomField()));
                ArrayList<Monom> rezultatMonom = polinom1.integrate();
                Polinom rezultatPolinom = new Polinom(rezultatMonom);
                gui.setResultPolynomField1("∫P1: " + rezultatPolinom.toString());
                rezultatMonom = polinom2.integrate();
                rezultatPolinom = new Polinom(rezultatMonom);
                gui.setResultPolynomField2("∫P2: " + rezultatPolinom.toString());
        });

        gui.getDivideButton().addActionListener(e ->{

                Polinom polinom1 = new Polinom(this.getMonoame(gui.getFirstPolynomField()));
                Polinom polinom2 = new Polinom(this.getMonoame(gui.getSecondPolynomField()));
                try{
                    DividePolinom dividePolinom = polinom1.divide(polinom2);
                    gui.setResultPolynomField1("Cat: " + dividePolinom.getCat().toString());
                    gui.setResultPolynomField2("Rest: " + dividePolinom.getRest().toString());
                }
                catch (ExceptieImpartire ex){
                    JOptionPane.showMessageDialog(this,"<html><font color=#ff0000>Impartire cu 0</font> ","Alert",JOptionPane.WARNING_MESSAGE);
                    gui.setResultPolynomField1(null);
                    gui.setResultPolynomField2(null);
                }

        });
    }

    public View getGui() {
        return gui;
    }

    public void setGui(View gui) {
        this.gui = gui;
    }
}
