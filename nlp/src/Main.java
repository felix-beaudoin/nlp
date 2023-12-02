import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;

import java.net.CookieHandler;
import java.util.HashMap;
import java.util.Properties;
import java.util.Queue;

import static edu.stanford.nlp.util.StringUtils.editDistance;

public class Main {
    public static void main(String[] args) {


        // For testing correction function on hashmap keys
        String sentence1 = new String("The discovery of other solar system wanderers rivaling Pluto in " +
                "size suddenly had scientists asking what wasn’t a planet");
        String[] sentence = sentence1.split(" ");

        HashMap<String, Integer> map = new HashMap<>();
        for (String word : sentence) {
                map.put(word, 0);
        }

        Correction correction = new Correction();
        System.out.println(correction.closestWord("plonat", map));





    }
}

/*
 * 1. Formatter le fichier + HashMap<Word, HashMap<File, Int[]>>
 * 2. Autocorrection d'erreur du query
 * 3. Recherche fichier pertinent
 * 4. Bigrammes et autocompletion
 *
 * */
