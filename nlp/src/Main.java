import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;

import java.util.HashMap;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        map.put(5, "salut");
        map.put(2, "deux");
        map.put(3, "three");
        System.out.println(map.get(5));
        map.put(5, "five");
        System.out.println(map.get(5));

    /*
    * 1. Formatter le fichier + HashMap<Word, HashMap<File, Int[]>>
    * 2. Autocorrection d'erreur du query
    * 3. Recherche fichier pertinent
    * 4. Bigrammes et autocompletion
    *
    * */

    }
}