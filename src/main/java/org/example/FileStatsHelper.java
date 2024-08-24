package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class FileStatsHelper {

    public static long size(File file) {
        long fileSizeInBytes = file.length();
        System.out.println(fileSizeInBytes + " "+ file.getName());
        return fileSizeInBytes;
    }

    public static long lines(File file) {
        long lineCount = 0;

        try {
            lineCount = Files.lines(Paths.get(file.getPath())).count();
            System.out.println(lineCount + " "+ file.getName());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return lineCount;
    }

    public static long words(File file) {
        long wordCount = 0;

        try {
            wordCount = Files.lines(Paths.get(file.getPath()))
                    .mapToInt(line -> line.trim().split("\\s+").length)
                    .sum();
            System.out.println(wordCount + " "+ file.getName());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        return wordCount;
    }

    public static long chars(File file) {
        long charCount = 0;

        try {
            charCount = Files.lines(Paths.get(file.getPath()))
                    .mapToLong(String::length)
                    .sum();

            System.out.println(charCount+" "+ file.getName());

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return charCount;
    }

    public static long[] calculateStats(File file) {
        long byteCount =0;
        long[] counts = new long[2];
        try {
            // Read all bytes to get the byte count of the file
             byteCount = Files.readAllBytes(Paths.get(file.getPath())).length;

            try (Stream<String> lines = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
                // Use an array to store counts [lines, words]
                counts = lines.map(line -> new long[]{
                        1, // Each line contributes one to the line count
                        Pattern.compile("\\s+").splitAsStream(line.trim()).filter(word -> !word.isEmpty()).count() // Count words in line
                }).reduce(new long[2], (a, b) -> new long[]{
                        a[0] + b[0], // Sum of lines
                        a[1] + b[1]  // Sum of words
                });
                System.out.println(counts[0]+" "+counts[1]+" "+byteCount+" "+file.getName());
            }catch (IOException e){
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return new long[]{byteCount, counts[0], counts[1]};
    }
}
