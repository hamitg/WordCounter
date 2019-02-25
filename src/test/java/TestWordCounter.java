
import WordCounter.WordCounter;
import WordCounter.WordCounterImp;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.io.FileUtils;

public class TestWordCounter {

    public static void main(String args[]) throws IOException { // Create check file to test the programme
        createFile();
    }

    final static int UNIQUE_WORD_AMOUNT = 70;
    final static int MAX_REPETATION = 4;
    final static int WORD_SIZE = 4;


    @Test
    public void testWordCounterPrintAllCounts() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream file1 = classloader.getResourceAsStream("Dictionary2.txt");
        InputStream file2 = classloader.getResourceAsStream("DictionaryTest.txt");
        WordCounter wordcounter = new WordCounterImp();
        wordcounter.registerFile("Dictionary2.txt", true);
        createTestFile(wordcounter.getHashMap().entrySet());
        sortFile("/Users/mac/IdeaProjects/WordCounter/src/main/resources/Dictionary2.txt");
        sortFile("/Users/mac/IdeaProjects/WordCounter/src/main/resources/DictionaryTest.txt");
        assertTrue(checkFilesEquality());
    }

    public static boolean checkFilesEquality() throws IOException {
        File f1 = new File("/Users/mac/IdeaProjects/WordCounter/src/main/resources/Dictionary2.txt");
        File f2 = new File("/Users/mac/IdeaProjects/WordCounter/src/main/resources/DictionaryTest.txt");
        boolean result = FileUtils.contentEquals(f1, f2);
        return result;
    }


    public void createTestFile(Set<Map.Entry<String, Integer>> map) throws IOException {
        PrintWriter writerTest = new PrintWriter("/Users/mac/IdeaProjects/WordCounter/src/main/resources/DictionaryTest.txt", "UTF-8");
        for (Map.Entry<String, Integer> entry : map) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            for (int i = 0; i < value; i++)
                writerTest.println(key);
        }
        writerTest.close();
    }

    public static void createFile() throws IOException {
        PrintWriter writer = null;
        writer = new PrintWriter("/Users/mac/IdeaProjects/WordCounter/src/main/resources/Dictionary2.txt", "UTF-8");
        for (int i = 0; i < UNIQUE_WORD_AMOUNT; i++) {
            String generatedString = RandomStringUtils.randomAlphanumeric(WORD_SIZE);
            Random rn = new Random();
            int a = Math.abs(rn.nextInt()) % MAX_REPETATION;
            for (int j = 0; j < a; j++) {
                writer.println(generatedString);
            }
        }
        writer.close();
    }

    public void sortFile(String file) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        ArrayList<String> lines = new ArrayList<String>();

        try {

            reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                lines.add(currentLine);

                currentLine = reader.readLine();
            }
            Collections.sort(lines);
            writer = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                writer.write(line);

                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }

                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
