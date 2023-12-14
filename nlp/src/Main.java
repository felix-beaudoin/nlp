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
        String datasetDir = "src/ressources/dataset";
        String queryFile = "src/ressources/query1.txt";

        File folder = new File(datasetDir);
        int numberOfFiles = folder.listFiles().length;


        var m = new MapBuilder();
        System.out.println("test");
        System.out.println(m.wordMap.get("the").get("903.txt")); // 0, 39, 77


        Bigrams textBigrams = new Bigrams(m.words, m.wordMap, m.arrayOfFiles, m.texts);
        System.out.println(textBigrams.getBigrams()[2][m.words[2].length-2].w2);
        System.out.println(textBigrams.getMostProbableBigramOf("say"));


        System.out.println("-------------- TFIDF ----------------");

        String[] bla = {"coffee", "import"};
        DocumentSearch documentSearch = new DocumentSearch(bla, m.wordMap, numberOfFiles, m.texts);
        System.out.println(documentSearch.countFrequency("coffee", "903.txt"));
        System.out.println(documentSearch.countDocuments("the"));
        System.out.println(documentSearch.score(bla, "903.txt"));
        System.out.println(documentSearch.bestDocument(bla));

        System.out.println("--------- Answers to queries ------------");
        QueryReader queryReader = new QueryReader("src/ressources/query1.txt", m.wordMap, textBigrams, documentSearch);
        try {
            queryReader.readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
















        /*
    * 1. Formatter le fichier + HashMap<Word, HashMap<File, Int[]>>
    * 2. Autocorrection d'erreur du query
    * 3. Recherche fichier pertinent
    * 4. Bigrammes et autocompletion
    *
    *
    *
    *
    * */

    }
}