package math;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import io.FileIO;
import java.io.File;
import java.io.PrintWriter;

public class ArrayOperationsTest {
    private ArrayOperations arrayOps;
    private FileIO fileIO;
    private MyMath myMath;
    private String testFilePath;

    @Before
    public void setUp() {
        arrayOps = new ArrayOperations();
        fileIO = new FileIO();
        myMath = new MyMath();
        testFilePath = "src/test/resources/test_primes.txt";
    }

    @Test
    public void testFindPrimesInFile() throws Exception {
        // Create a test file with numbers
        try (PrintWriter writer = new PrintWriter(testFilePath)) {
            writer.println("2");
            writer.println("3");
            writer.println("4");
            writer.println("5");
            writer.println("6");
            writer.println("7");
        }

        int[] result = arrayOps.findPrimesInFile(fileIO, testFilePath, myMath);
        int[] expected = {2, 3, 5, 7};
        assertArrayEquals(expected, result);

        // Clean up
        new File(testFilePath).delete();
    }

    @Test
    public void testFindPrimesInEmptyFile() throws Exception {
        // Create an empty test file
        try (PrintWriter writer = new PrintWriter(testFilePath)) {
            // intentionally empty
        }

        int[] result = arrayOps.findPrimesInFile(fileIO, testFilePath, myMath);
        assertEquals(0, result.length);

        // Clean up
        new File(testFilePath).delete();
    }

    @Test
    public void testFindPrimesWithNoPrimes() throws Exception {
        // Create a test file with non-prime numbers
        try (PrintWriter writer = new PrintWriter(testFilePath)) {
            writer.println("4");
            writer.println("6");
            writer.println("8");
            writer.println("9");
        }

        int[] result = arrayOps.findPrimesInFile(fileIO, testFilePath, myMath);
        assertEquals(0, result.length);

        // Clean up
        new File(testFilePath).delete();
    }
}
