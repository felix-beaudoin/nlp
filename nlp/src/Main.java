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


        Bigrams textBigrams = new Bigrams(m.words, m.wordMap, m.arrayOfFiles);
        System.out.println(textBigrams.getBigrams()[2][m.words[2].length-2].w2);
        textBigrams.getMostProbableBigramOf("planet");

        QueryReader queryReader = new QueryReader("src/ressources/query1.txt", m.wordMap);
        try {
            queryReader.readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        queryReader.correctQueries();
        System.out.println(queryReader.getSearchQueries().get(1)[1]);
        System.out.println(queryReader.getBigramQueries().get(0));












/*
    * 1. Formatter le fichier + HashMap<Word, HashMap<File, Int[]>>
    * 2. Autocorrection d'erreur du query
    * 3. Recherche fichier pertinent
    * 4. Bigrammes et autocompletion
    *
    * */

    }
}