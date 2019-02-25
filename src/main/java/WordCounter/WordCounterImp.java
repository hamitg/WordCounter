package WordCounter;

import java.util.*;
import java.io.*;

public class WordCounterImp implements WordCounter {
    Scanner scanner;
    private HashMap<String, Integer> wordCounts = new HashMap<String, Integer>();


    public HashMap<String, Integer> getHashMap() {
        HashMap<String, Integer> copyHashMap = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            copyHashMap.put(key, value);
        }
        return copyHashMap;
    }

    public int getWordCount(String word) {
        return wordCounts.get(word);
    }


    public void registerFile(String nameOfFile, boolean b) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(nameOfFile);
            scanner = new Scanner(is);
        } catch (Exception e) {
            System.out.println("Could not find file: " + e.getMessage());
        }

        while (scanner.hasNext()) {
            String a = scanner.next();
            int count = wordCounts.containsKey(a) ? wordCounts.get(a) : 0;
            wordCounts.put(a, count + 1);
        }
    }


    public void printAllCounts() {
        for (Map.Entry<String, Integer> entry : getHashMap().entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ": " + value);
        }

    }
}
