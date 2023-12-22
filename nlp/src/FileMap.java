
import java.util.*;


public class FileMap implements Map {

    private int size = 0;
    public int maxSize;
    public double maxFacteurCharge = 3.0/4;
    private LinkedList<FileMapEntry>[] map;



    FileMap() {
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

    @Override
    public boolean containsKey(Object key) {
        for (LinkedList<FileMapEntry> entries : map) {
            for (FileMapEntry entry : entries) {
                if ( key.equals(entry.fichier()) ) { return true; }
            }
        }
        return false;
    }

    public boolean containsKey(String fichier) {
        int index = fichier.hashCode() % maxSize;
        if (index<0) { index += maxSize; }

        for (FileMapEntry entry : map[index]) {
            if (fichier.equals(entry.fichier())) { return true; }
        }

        return false;
    }

    public boolean containsValue(Object value) {
        for (LinkedList<FileMapEntry> entries : map) {
            for (FileMapEntry entry : entries) {
                if ( value.equals(entry.positions()) ) { return true; }
            }
        }
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


        // on regarde l'index
        int index = fichier.hashCode() % maxSize;
        if (index<0) { index += maxSize; }

        // dans le bucket, on va à travers le bucket pour trouver le entry
        for (FileMapEntry entry : map[index]) {
            if (fichier.equals(entry.fichier())) { return entry.positions(); }
        }

        return null;
    }


    public ArrayList<Integer> put(String fichier, int position) {

        size++;

        // si le facteur de charge est atteint
        if (size >= maxSize * maxFacteurCharge) {

            Set<FileMapEntry> entrySet = entrySet();

            this.maxSize = maxSize*2 + 1;
            map = new LinkedList[maxSize];

            for (int i = 0; i < maxSize; i++) {
                map[i] = new LinkedList<>();
            }

            // on resize et on rehash tout les entries
            for (FileMapEntry entry : entrySet) {
                put(entry.fichier(), entry.positions());
            }
        }

        int index = fichier.hashCode() % maxSize;
        if (index<0) { index += maxSize; }



        // parcourir le bucket pour la bonne valeur
        for (int i = 0; i < map[index].size(); i++) {

            if (fichier.equals(map[index].get(i).fichier())) {
                map[index].get(i).positions().add(position);

                return map[index].get(i).positions();
            }

        }

        ArrayList<Integer> positionArray = new ArrayList<>();
        positionArray.add(position);
        FileMapEntry newEntry = new FileMapEntry(fichier, positionArray);

        // si aucun élément du bucket est le bon, on le rajoute
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
