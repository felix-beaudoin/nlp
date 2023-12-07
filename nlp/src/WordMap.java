
import java.util.*;

public class WordMap implements Map {

    private int size = 0;
    private int maxSize = 15;
    private LinkedList<WordMapEntry>[] map;

    WordMap() {
        map = new LinkedList[maxSize];

        for (int i = 0; i < maxSize; i++) {
            map[i] = new LinkedList<>();
        }
    }

    WordMap(int maxSize, WordMap mapImplementation) {
        map = new LinkedList[maxSize];

        for (int i = 0; i < maxSize; i++) {
            map[i] = new LinkedList<>();
        }

        this.maxSize = maxSize;

        //mapImplementation.get();
    }



    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object key) {
        return false;
    }

    public boolean containsValue(Object value) {
        return false;
    }






    public FileMap get(Object mot) {
        int index = mot.hashCode() % maxSize;

        if (index<0) { index += maxSize; }

        for (WordMapEntry entry : map[index]) {
            if (mot.equals(entry.mot())) { return entry.fileMap(); }
        }

        return null;
    }

    public Object put(String mot, FileMap fileMap) {

        WordMapEntry newEntry = new WordMapEntry(mot, fileMap);

        int index = mot.hashCode() % maxSize;

        size++;

        if (index<0) { index += maxSize; }

        for (int i = 0; i < map[index].size(); i++) {

            if (mot.equals(map[index].get(i).mot())) {
                return map[index].set(i, newEntry).fileMap();
            }

        }

        map[index].push(newEntry);
        return null;

    }

    public Object put(Object key, Object value) {
        return null;
    }


    public Object remove(Object key) {
        return null;
    }

    public void putAll(Map m) {

    }

    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    public Collection values() {
        return null;
    }

    public Set<FileMapEntry> entrySet() {
        HashSet<Object> set = new HashSet<>();
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < map[i].size(); i++) {
                map[i].get(j);

            }
        }
        return null;
    }
}
