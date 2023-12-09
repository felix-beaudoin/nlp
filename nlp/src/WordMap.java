
import java.util.*;

public class WordMap implements Map {

    private int size = 0;
    private int maxSize;

    public double maxFacteurCharge = 3.0/4;

    private LinkedList<WordMapEntry>[] map;

    WordMap() {
        maxSize = 15;                   // est ce que maxSize devrait etre premier pour eviter les collisions
        map = new LinkedList[maxSize];

        for (int i = 0; i < maxSize; i++) {
            map[i] = new LinkedList<>();
        }
    }

    public int size() {
        return size;
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






    public FileMap get(Object mot) {            // Object pourrait etre String?

        int index = mot.hashCode() % maxSize;
        if (index<0) { index += maxSize; }

        for (WordMapEntry entry : map[index]) {
            if (mot.equals(entry.mot())) { return entry.fileMap(); }
        }

        return null;
    }

    public Object put(String mot, FileMap fileMap) {

        size++;

        if (size >= maxSize * maxFacteurCharge) {


            Set<WordMapEntry> entrySet = entrySet();

            this.maxSize = maxSize*2 + 1;
            map = new LinkedList[maxSize];

            for (int i = 0; i < maxSize; i++) {
                map[i] = new LinkedList<>();
            }

            // est ce qu'on devrait delete l'ancienne map pour pas surcharger la memoire

            for (WordMapEntry entry : entrySet) {
                put(entry.mot(), entry.fileMap());
                System.out.println("resizing!");
            }
        }

        WordMapEntry newEntry = new WordMapEntry(mot, fileMap);

        int index = mot.hashCode() % maxSize;
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
    public Set<String> keySet() {
        HashSet<String> set = new HashSet<>();

        for (LinkedList<WordMapEntry> entryList : map) {
            for (WordMapEntry entry : entryList) {
                set.add(entry.mot());
            }
        }

        return set;
    }

    public Collection values() {
        HashSet<FileMap> set = new HashSet<>();

        for (LinkedList<WordMapEntry> entryList : map) {
            for (WordMapEntry entry : entryList) {
                set.add(entry.fileMap());
            }
        }

        return set;
    }

    public Set<WordMapEntry> entrySet() {
        HashSet<WordMapEntry> set = new HashSet<>();

        for (LinkedList<WordMapEntry> entryList : map) {
            set.addAll(entryList);
        }
        return set;
    }
}
