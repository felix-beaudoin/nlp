
import java.util.*;

public class FileMap implements Map {

    private int size = 0;
    private int maxSize = 15;
    private LinkedList<FileMapEntry>[] map;

    FileMap() {
        map = new LinkedList[maxSize];

        for (int i = 0; i < maxSize; i++) {
            map[i] = new LinkedList<>();
        }
    }

    FileMap(int maxSize, FileMap fileMap) {
        map = new LinkedList[maxSize];

        for (int i = 0; i < maxSize; i++) {
            map[i] = new LinkedList<>();
        }

        this.maxSize = maxSize;

        //fileMap.getMapEntry();
        //TODO à finir
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

        for (FileMapEntry entry : map[index]) {
            if (fichier.equals(entry.fichier())) { return entry.positions(); }
        }

        return null;
    }


    public ArrayList<Integer> put(String fichier, int position) {
        // Mis à part quelques détails le plan était presque sans faille

        int index = fichier.hashCode() % maxSize;

        size++;

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
