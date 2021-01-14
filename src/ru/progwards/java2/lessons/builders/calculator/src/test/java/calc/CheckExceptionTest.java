package calc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CheckExceptionTest {
    static SimpleCalculator calc;

    @BeforeClass
    public static void init() {
        calc = new SimpleCalculator();
    }

    @Test(expected = ArithmeticException.class)
    public void sumException() {
        assertEquals(calc.sum(Integer.MAX_VALUE, 1), Integer.MAX_VALUE + 1);
    }

    @Test(expected = ArithmeticException.class)
    public void diffException() {
        assertEquals(calc.diff(Integer.MAX_VALUE, Integer.MIN_VALUE), Integer.MAX_VALUE - Integer.MIN_VALUE);
    }

    @Test(expected = ArithmeticException.class)
    public void multException() {
        assertEquals(calc.mult(Integer.MAX_VALUE, 2), Integer.MAX_VALUE * 2);
    }

    @Test(expected = ArithmeticException.class)
    public void divException() {
        assertEquals(calc.sum(Integer.MAX_VALUE, 0), Integer.MAX_VALUE / 0);
    }

    @AfterClass
    public static void destroy() {
        calc = null;
    }
}
