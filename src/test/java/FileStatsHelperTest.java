package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileStatsHelperTest {

    private File testFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary test file
        testFile = new File("testFile.txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Hello World\n");
            writer.write("This is a test file.\n");
            writer.write("It contains multiple lines and words.");
        }
    }

    @AfterEach
    public void tearDown() {
        // Delete the temporary test file after each test
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testSize() {
        long expectedSize = 70;
        assertEquals(expectedSize, FileStatsHelper.size(testFile), "File size should match the expected value.");
    }

    @Test
    public void testLines() {
        int expectedLineCount = 3; // We have 3 lines in the setUp() content
        assertEquals(expectedLineCount, FileStatsHelper.lines(testFile), "Line count should match the expected value.");
    }

    @Test
    public void testWords() {
        int expectedWordCount = 13; // 13 words in the setUp() content
        assertEquals(expectedWordCount, FileStatsHelper.words(testFile), "Word count should match the expected value.");
    }

    @Test
    public void testChars() {
        long expectedCharCount = 68; // Count the number of Unicode code points
        assertEquals(expectedCharCount, FileStatsHelper.chars(testFile), "Character count should match the expected value.");
    }

    @Test
    public void testCalculateStats() {
        long expectedCharCount = 70; // Count the number of Unicode code points
        long expectedLineCount = 3;
        long expectedWordCount = 13;
        long[] counts = FileStatsHelper.calculateStats(testFile);
        assertEquals(expectedCharCount, counts[0], "Character count should match the expected value.");
        assertEquals(expectedLineCount, counts[1], "Line count should match the expected value.");
        assertEquals(expectedWordCount, counts[2], "Word count should match the expected value.");
    }
}
