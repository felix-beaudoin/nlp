
import java.util.*;

public class WordMap implements Map {

    private int size = 0;
    private int maxSize;

    public double maxFacteurCharge = 3.0/4;

    private LinkedList<WordMapEntry>[] map;

    WordMap() {
        maxSize = 17;

        //chaque linkedlist représente un bucket
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

    public boolean containsKey(Object word) {
        for (LinkedList<WordMapEntry> entries : map) {
            for (WordMapEntry entry : entries) {
                if ( word.equals(entry.mot()) ) { return true; }
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (LinkedList<WordMapEntry> entries : map) {
            for (WordMapEntry entry : entries) {
                if ( value.equals(entry.fileMap()) ) { return true; }
            }
        }
        return false;
    }


    public FileMap get(Object mot) {

        // on regarde l'index
        int index = mot.hashCode() % maxSize;
        if (index<0) { index += maxSize; }


        // dans le bucket, on va à travers le bucket pour trouver le entry
        for (WordMapEntry entry : map[index]) {
            if (mot.equals(entry.mot())) { return entry.fileMap(); }
        }

        return null;
    }


    public Object put(String mot, FileMap fileMap) {

        size++;

        // si le facteur de charge est atteint
        if (size >= maxSize * maxFacteurCharge) {

            // on resize et on rehash tout les entries
            Set<WordMapEntry> entrySet = entrySet();

            this.maxSize = maxSize*2 + 1;
            map = new LinkedList[maxSize];

            for (int i = 0; i < maxSize; i++) {
                map[i] = new LinkedList<>();
            }

            for (WordMapEntry entry : entrySet) {
                put(entry.mot(), entry.fileMap());
            }
        }

        WordMapEntry newEntry = new WordMapEntry(mot, fileMap);

        int index = mot.hashCode() % maxSize; // -maxSize <= index < maxSize
        if (index<0) { index += maxSize; } // 0 <= index < maxSize


        // parcourir le bucket pour la bonne valeur
        for (int i = 0; i < map[index].size(); i++) {

            if (mot.equals(map[index].get(i).mot())) {
                return map[index].set(i, newEntry).fileMap();
            }

        }
        // si aucun élément du bucket est le bon, on le rajoute
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
