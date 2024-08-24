package org.example;
import java.io.*;

public class WordCount {
    public static void main(String[] args) {

        // Check if at least one argument is provided
        if (args.length < 1) {
            System.out.println("Usage: java FileByteCounter [-c] <file_path>");
            return;
        }

        // Get the file path from the first command-line argument
        String filePath = (args.length==2)? args[1]:args[0];
        File file = new File(filePath);

        if (!file.exists() || file.isDirectory())  {
            System.out.println("The file does not exist or is a directory.");
        }

        switch(args[0]){
            case "-c" -> FileStatsHelper.size(file);
            case "-l" -> FileStatsHelper.lines(file);
            case "-w" -> FileStatsHelper.words(file);
            case "-m" -> FileStatsHelper.chars(file);
            case "default" -> FileStatsHelper.calculateStats(file);
        }
    }

}