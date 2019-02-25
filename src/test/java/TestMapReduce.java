import MapReduce.MapReduce;
import WordCounter.WordCounter;
import WordCounter.WordCounterImp;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import static org.junit.Assert.*;


public class TestMapReduce {
    @Test
    public void testMapReduce() throws FileNotFoundException {
        WordCounter wordCounter = new MapReduce();
        wordCounter.registerFile("Dictionary2.txt", false);

//        WordCounter w = new WordCounterImp();
//        w.registerFile("Dictionary2.txt", false);
//        HashMap<String, Integer> counts = wordCounter.getHashMap();
    }
}
