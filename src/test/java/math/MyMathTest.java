package math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class MyMathTest {
    private MyMath math;

    @BeforeEach
    public void setUp() {
        math = new MyMath();
        System.out.println("Setting up MyMathTest");
    }

    @AfterEach
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

    @Test
    public void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> math.factorial(-1));
    }

    @Test
    public void testFactorialTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> math.factorial(13));
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

    @Test
    public void testIsPrimeBelowTwo() {
        assertThrows(IllegalArgumentException.class, () -> math.isPrime(1));
    }

    @Test
    public void testFactorialBoundaryCondition() {
    
        int result = math.factorial(12);
        assertEquals(479001600, result); 

    
        assertEquals(2, math.factorial(2));
    }
}
