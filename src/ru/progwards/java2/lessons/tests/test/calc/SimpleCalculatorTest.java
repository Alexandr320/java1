package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;
import java.util.Arrays;
import static junit.framework.TestCase.*;

@RunWith(Enclosed.class)
public class SimpleCalculatorTest {

    @RunWith(Parameterized.class)
    public static class SimpleCalculatorSumTest {  // ТЕСТ СУММЫ
        static SimpleCalculator calc;

        @BeforeClass
        public static void init() {
            calc = new SimpleCalculator();
        }
        public int val1;
        public int val2;
        public int expected;

        public SimpleCalculatorSumTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][] {
                    {0, 0, 0},
                    {5, 0, 5},
                    {-5, -5, -10},
                    {34, 55, 89},
                    {-34, -55, -89}
            });
        }

        @Test
        public void testWithParams() {
            assertEquals(expected, calc.sum(val1, val2));
        }

        @AfterClass
        public static void destroy() {
            calc = null;
        }
    }

    @RunWith(Parameterized.class)
    public static class SimpleCalculatorDiffTest {  // ТЕСТ РАЗНОСТИ
        static SimpleCalculator calc;

        @BeforeClass
        public static void init() {
            calc = new SimpleCalculator();
        }
        public int val1;
        public int val2;
        public int expected;

        public SimpleCalculatorDiffTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][] {
                    {0, 0, 0},
                    {5, 2, 3},
                    {-5, -5, 0},
                    {89, 55, 34},
                    {34, -55, 89}
            });
        }

        @Test
        public void testWithParams() {
            assertEquals(expected, calc.diff(val1, val2));
        }

        @AfterClass
        public static void destroy() {
            calc = null;
        }
    }

    @RunWith(Parameterized.class)
    public static class SimpleCalculatorMultTest {  // ТЕСТ УМНОЖЕНИЯ
        static SimpleCalculator calc;

        @BeforeClass
        public static void init() {
            calc = new SimpleCalculator();
        }
        public int val1;
        public int val2;
        public int expected;

        public SimpleCalculatorMultTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][] {
                    {0, 0, 0},
                    {5, 2, 10},
                    {-5, -5, 25},
                    {10, 10, 100},
                    {-10, -55, 550}
            });
        }

        @Test
        public void testWithParams() {
            assertEquals(expected, calc.mult(val1, val2));
        }

        @AfterClass
        public static void destroy() {
            calc = null;
        }
    }

    @RunWith(Parameterized.class)
    public static class SimpleCalculatorDivTest {  // ТЕСТ ДЕЛЕНИЯ
        static SimpleCalculator calc;

        @BeforeClass
        public static void init() {
            calc = new SimpleCalculator();
        }
        public int val1;
        public int val2;
        public int expected;

        public SimpleCalculatorDivTest(int val1, int val2, int expected) {
            this.val1 = val1;
            this.val2 = val2;
            this.expected = expected;
        }

        @Parameterized.Parameters(name = "Тест {index}: ({0}) + ({1}) = {2}")
        public static Iterable<Object[]> dataForTest() {
            return Arrays.asList(new Object[][] {
                    {0, 1, 0},
                    {5, 5, 1},
                    {-5, -5, 1},
                    {150, 50, 3},
                    {-150, 50, -3}
            });
        }

        @Test
        public void testWithParams() {
            assertEquals(expected, calc.div(val1, val2));
        }

        @AfterClass
        public static void destroy() {
            calc = null;
        }
    }

}
