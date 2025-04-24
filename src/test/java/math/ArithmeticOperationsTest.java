package math;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class ArithmeticOperationsTest {
    private ArithmeticOperations arithmetic;

    @Before
    public void setUp() {
        arithmetic = new ArithmeticOperations();
        System.out.println("Setting up ArithmeticOperationsTest");
    }

    @After
    public void tearDown() {
        arithmetic = null;
        System.out.println("Tearing down ArithmeticOperationsTest");
    }

    @Test
    public void testDivideNormalCase() {
        assertEquals(2.0, arithmetic.divide(4.0, 2.0), 0.0001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        arithmetic.divide(5.0, 0.0);
    }

    @Test
    public void testMultiplyNormalCase() {
        assertEquals(6, arithmetic.multiply(2, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyNegativeNumbers() {
        arithmetic.multiply(-2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyOverflow() {
        arithmetic.multiply(Integer.MAX_VALUE, 2);
    }

    @Test
    public void testMultiplyWithZero() {
        assertEquals(0, arithmetic.multiply(5, 0));
        assertEquals(0, arithmetic.multiply(0, 5));
    }
}
