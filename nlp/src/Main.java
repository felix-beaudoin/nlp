import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main {
    public static void main(String[] args) {

   /*     var m = new MapBuilder();
        System.out.println("test");
        System.out.println(m.wordMap.get("the").get("900.txt"));



/*
        FileMap fm1 = new FileMap();
        fm1.put("f1", 2);
        fm1.put("f1", 4);
        fm1.put("f1", 6);

        fm1.put("f1", new int[]{1, 3, 5});
        fm1.put("f2", new int[]{9, 8, 7});

        FileMap fm2 = new FileMap();
        fm2.put("f1", 10);
        fm2.put("f1", 11);
        fm2.put("f1",12);

        fm2.put("f2", new int[]{-4, -12, -36});

        FileMap fm3 = new FileMap();
        fm3.put("f1", new int[]{13, 14, 15});
        fm3.put("f2", new int[]{-45, 90, -180});

        WordMap wordMap = new WordMap();
        wordMap.put("a", fm1);
        wordMap.put("b", fm3);
        wordMap.put("b", fm2);
*/






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