package io;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class FileIOTest {
    private FileIO fileIO;

    @Before
    public void setUp() {
        fileIO = new FileIO();
        System.out.println("Setting up FileIOTest");
    }

    @After
    public void tearDown() {
        fileIO = null;
        System.out.println("Tearing down FileIOTest");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadFileNonExistent() {
        fileIO.readFile("nonexistent.txt");
    }

    @Test
    public void testReadEmptyFile() {
        int[] numbers = fileIO.readFile("src/test/resources/empty_file.txt");
        assertEquals(0, numbers.length);
    }

    @Test
    public void testReadValidFile() {
        int[] numbers = fileIO.readFile("src/test/resources/grades_valid.txt");
        assertNotNull(numbers);
        assertTrue(numbers.length > 0);
    }

    @Test
    public void testReadInvalidFile() {
        // This should skip invalid entries and only return valid numbers
        int[] numbers = fileIO.readFile("src/test/resources/grades_invalid.txt");
        assertNotNull(numbers);
        // The actual assertions would depend on the content of grades_invalid.txt
    }
}
