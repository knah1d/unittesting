package math;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class MyMathTest {
    private MyMath math;

    @Before
    public void setUp() {
        math = new MyMath();
        System.out.println("Setting up MyMathTest");
    }

    @After
    public void tearDown() {
        math = null;
        System.out.println("Tearing down MyMathTest");
    }

    @Test
    public void testFactorialZero() {
        assertEquals(1, math.factorial(0));
    }

    @Test
    public void testFactorialOne() {
        assertEquals(1, math.factorial(1));
    }

    @Test
    public void testFactorialNormal() {
        assertEquals(120, math.factorial(5));
        assertEquals(3628800, math.factorial(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        math.factorial(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialTooLarge() {
        math.factorial(13);
    }

    @Test
    public void testIsPrimeTrue() {
        assertTrue(math.isPrime(2));
        assertTrue(math.isPrime(3));
        assertTrue(math.isPrime(17));
        assertTrue(math.isPrime(19));
    }

    @Test
    public void testIsPrimeFalse() {
        assertFalse(math.isPrime(4));
        assertFalse(math.isPrime(6));
        assertFalse(math.isPrime(8));
        assertFalse(math.isPrime(9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsPrimeBelowTwo() {
        math.isPrime(1);
    }
}
