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
        assertEquals(0, arithmetic.multiply(0, 0));
    }

    @Test
    public void testMultiplyZeroFirstOperand() {
        // Test when x is 0 but y is not 0 - this covers the x <= Integer.MAX_VALUE / y
        // branch
        assertEquals(0, arithmetic.multiply(0, 10));
        assertEquals(0, arithmetic.multiply(0, 1));
        assertEquals(0, arithmetic.multiply(0, Integer.MAX_VALUE));
    }

    @Test
    public void testMultiplyBothNegative() {
        // Test both operands negative to cover all branches of x < 0 || y < 0
        try {
            arithmetic.multiply(-1, -1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testMultiplyEdgeCases() {
        // Test the boundary condition in the overflow check
        // This tests the condition x <= Integer.MAX_VALUE / y more thoroughly
        assertEquals(1, arithmetic.multiply(1, 1));
        assertEquals(2, arithmetic.multiply(1, 2));

        // Test a case right at the boundary of overflow
        int maxSqrt = (int) Math.sqrt(Integer.MAX_VALUE);
        arithmetic.multiply(maxSqrt, maxSqrt); // Should not overflow
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplySecondOperandNegative() {
        // Test when second operand is negative - covers y < 0 part of the condition
        arithmetic.multiply(3, -2);
    }

    @Test
    public void testMultiplyExactBoundaryCondition() {
        int y = 1000;
        int x = Integer.MAX_VALUE / y; 


        int result = arithmetic.multiply(x, y);
        assertTrue("Result should be positive", result > 0);
        assertTrue("Result should be close to MAX_VALUE", result >= Integer.MAX_VALUE - y);
    }
}
