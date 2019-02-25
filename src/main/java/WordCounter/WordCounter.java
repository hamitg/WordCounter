package WordCounter;

import java.io.FileNotFoundException;
import java.util.HashMap;

public interface WordCounter {
    void printAllCounts();

    int getWordCount(String word);

    void registerFile(String nameOfFile, boolean b) throws FileNotFoundException;

    HashMap<String, Integer> getHashMap();

}
