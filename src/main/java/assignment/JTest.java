package assignment;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class JTest {

    Controller controller = new Controller(new View("Test"));
    Polinom polinom1 = new Polinom(controller.getMonoame("x^3-2x+1"));
    Polinom polinom2 = new Polinom(controller.getMonoame("x+2"));
    ArrayList<Monom> monomRezultat = new ArrayList<>();
    Polinom rezultat;
    DividePolinom dividePolinom;

    @Test
    public void add() throws Exception
    {
        monomRezultat = polinom1.add(polinom2);
        rezultat = new Polinom(monomRezultat);
        assertEquals("+x^3-x+3", rezultat.toString());
    }

    @Test
    public void sub() throws Exception
    {
        monomRezultat = polinom1.sub(polinom2);
        rezultat = new Polinom(monomRezultat);
        assertEquals("+x^3-3x-1", rezultat.toString());
    }

    @Test
    public void multiply() throws Exception
    {
        monomRezultat = polinom1.multiply(polinom2);
        rezultat = new Polinom(monomRezultat);
        assertEquals("+x^4+2x^3-2x^2-3x+2", rezultat.toString());
    }

    @Test
    public void diff() throws Exception
    {
        monomRezultat = polinom1.diff();
        rezultat = new Polinom(monomRezultat);
        assertEquals("+3x^2-2", rezultat.toString());
    }

    @Test
    public void integrate() throws Exception
    {
        monomRezultat = polinom1.integrate();
        rezultat = new Polinom(monomRezultat);
        assertEquals("+0.25x^4-x^2+x", rezultat.toString());
    }

    @Test
    public void divide() throws Exception
    {
        dividePolinom = polinom1.divide(polinom2);
        rezultat = new Polinom(monomRezultat);
        assertEquals("+x^2-2x+2", dividePolinom.getCat().toString());
        assertEquals("-3", dividePolinom.getRest().toString());
    }
}