
import java.util.*;

public class FileMap implements Map {

    private int size = 0;
    public int maxSize;
    public double maxFacteurCharge = 3.0/4;
    private LinkedList<FileMapEntry>[] map;

    FileMap() {
        maxSize = 15;
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

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    public ArrayList<Integer> get(String fichier) {

        int index = fichier.hashCode() % maxSize;
        if (index<0) { index += maxSize; }

        for (FileMapEntry entry : map[index]) {
            if (fichier.equals(entry.fichier())) { return entry.positions(); }
        }

        return null;
    }


    public ArrayList<Integer> put(String fichier, int position) {

        size++;

        if (size >= maxSize * maxFacteurCharge) {

            Set<FileMapEntry> entrySet = entrySet();

            this.maxSize = maxSize*2 + 1;
            map = new LinkedList[maxSize];

            for (int i = 0; i < maxSize; i++) {
                map[i] = new LinkedList<>();
            }

            for (FileMapEntry entry : entrySet) {
                put(entry.fichier(), entry.positions());
            }
        }

        int index = fichier.hashCode() % maxSize;

        for (int i = 0; i < map[index].size(); i++) {

            if (fichier.equals(map[index].get(i).fichier())) {
                map[index].get(i).positions().add(position);

                return map[index].get(i).positions();
            }

        }

        var positionArray = new ArrayList<Integer>();
        positionArray.add(position);
        FileMapEntry newEntry = new FileMapEntry(fichier, positionArray);

        map[index].push(newEntry);
        return null;
    }

    private ArrayList<Integer> put(String fichier, ArrayList<Integer> positions) {

        int index = fichier.hashCode() % maxSize;
        if (index<0) { index += maxSize; }

        for (FileMapEntry entry : map[index]) {
            if (entry.fichier().equals(fichier)) {
                map[index].push(new FileMapEntry(fichier, positions));
                return map[index].remove(map[index].indexOf(entry)).positions();
            }
        }
        map[index].push(new FileMapEntry(fichier, positions));
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
        HashSet<String> set = new HashSet<>();

        for (LinkedList<FileMapEntry> entryList : map) {
            for (FileMapEntry entry : entryList) {
                set.add(entry.fichier());
            }
        }

        return set;
    }

    public Collection values() {
        HashSet<ArrayList<Integer>> set = new HashSet<>();

        for (LinkedList<FileMapEntry> entryList : map) {
            for (FileMapEntry entry : entryList) {
                set.add(entry.positions());
            }
        }

        return set;
    }

    public Set<FileMapEntry> entrySet() {
        HashSet<FileMapEntry> set = new HashSet<>();

        for (LinkedList<FileMapEntry> entryList : map) {
            set.addAll(entryList);
        }

        return set;
    }
}
