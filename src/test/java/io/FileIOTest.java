package io;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.io.IOException;

public class FileIOTest {
    private FileIO fileIO;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUp() {
        fileIO = new FileIO();
        System.out.println("Setting up FileIOTest");
        System.setErr(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        fileIO = null;
        System.out.println("Tearing down FileIOTest");
        System.setOut(originalOut);
        System.setErr(originalErr);
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

    @Test
    public void testReadFileIOException() throws IOException {
        // Create a directory instead of a file to force an IOException when trying to
        // read
        File tempDir = new File("temp_test_dir");
        tempDir.mkdir();

        try {
            // This should trigger IOException and execute the printStackTrace() line
            int[] result = fileIO.readFile("temp_test_dir");
            assertNotNull(result);
            assertEquals(0, result.length);

            // Verify that printStackTrace was called (exception was caught and printed)
            assertTrue(outContent.toString().contains("java.io.FileNotFoundException") ||
                    outContent.toString().length() > 0);
        } finally {
            // Clean up
            tempDir.delete();
        }
    }
}
