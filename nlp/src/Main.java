import edu.stanford.nlp.ling.Word;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main {
    public static void main(String[] args) {

        var m = new MapBuilder();
        System.out.println("test");
        System.out.println(m.wordMap.get("the").get("903.txt")); // 0, 39, 77


/*
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







    * 1. Formatter le fichier + HashMap<Word, HashMap<File, Int[]>>
    * 2. Autocorrection d'erreur du query
    * 3. Recherche fichier pertinent
    * 4. Bigrammes et autocompletion
    *
    * */

    }
}