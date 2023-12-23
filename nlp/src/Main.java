/**
 * IFT2015 - TP2
 * Félix Beaudoin 20244864
 * Viviane Binet 20244728
 */

import java.io.*;

public class Main {
    public static void main(String[] args) {

        /**
         * Dataset, query and solution files path
         */
        String datasetDir = "src/ressources/dataset";
        String queryFile = "src/ressources/query1.txt";
        String solutionFile = "src/ressources/solution.txt";



        File folder = new File(datasetDir);
        int numberOfFiles = folder.listFiles().length;

        MapBuilder m = new MapBuilder(datasetDir);

        Bigrams textBigrams = new Bigrams(m.wordMap,  m.texts);

        DocumentSearch documentSearch = new DocumentSearch(m.wordMap, numberOfFiles, m.texts);

        QueryReader queryReader = new QueryReader(queryFile, solutionFile, m.wordMap, textBigrams, documentSearch);
        queryReader.answerQueries();

    }
}