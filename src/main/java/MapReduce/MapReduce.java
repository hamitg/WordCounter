package MapReduce;

import WordCounter.WordCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class MapReduce implements WordCounter {

    // TODO :: replace this with absolute path.
    String BASE_PATH = "/Users/mac/IdeaProjects/WordCounter/src/main/resources/";


    HashMap<String, Integer> counts = new HashMap<>();

    @Override
    public void printAllCounts() {

    }

    @Override
    public int getWordCount(String word) {
        return counts.get(word);
    }

    @Override
    public void registerFile(String nameOfFile, boolean b) throws FileNotFoundException {
        File f = new File(BASE_PATH + nameOfFile);
        List<File> files = null;
        try {
            files = FileSplit.splitFile(f);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        try {
            processFiles(files);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processFiles(List<File> files) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (final File file : files) {
            Thread thread = new Thread() {
                public void run() {
                    try {
                        addSubCounts(processFile(file));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            };
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        deleteFiles(files);
    }

    private void deleteFiles(final List<File> files) {
        for (File file : files) {
            file.delete();
        }
    }

    private void addSubCounts(HashMap<String, Integer> subCounts) {
        for (Map.Entry<String, Integer> e : subCounts.entrySet()) {
            synchronized (this) {
                int count = counts.containsKey(e.getKey()) ? counts.get(e.getKey()) : 0;
                counts.put(e.getKey(), count + e.getValue());
            }
        }
    }

    private HashMap<String, Integer> processFile(File f) throws FileNotFoundException {
        HashMap<String, Integer> currentCounts = new HashMap<>();
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String current = scanner.next();
            int count = currentCounts.containsKey(current) ? currentCounts.get(current) : 0;
            currentCounts.put(current, count + 1);
        }
        return currentCounts;
    }

    @Override
    public HashMap<String, Integer> getHashMap() {
        return counts;
    }
}
