import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class QueryReader {

    private ArrayList<String> bigramQueries = new ArrayList<>();
    private ArrayList<String[]> searchQueries = new ArrayList<>();
    private String filePath;
    private WordMap wordMap;
    private Bigrams bigrams;
    private DocumentSearch documentSearch;

    public QueryReader(String filePath, WordMap wordMap, Bigrams bigrams, DocumentSearch documentSearch) {
        this.filePath = filePath;
        this.wordMap = wordMap;
        this.bigrams = bigrams;
        this.documentSearch = documentSearch;
    }

    public ArrayList<String> getBigramQueries() {return this.bigramQueries;}

    public ArrayList<String[]> getSearchQueries() {return searchQueries;}

    public void readFile() throws FileNotFoundException {
        File textFile = new File(filePath);
        Scanner reader = new Scanner(textFile);
        Correction correction = new Correction(this.wordMap);

        while (reader.hasNextLine()) {
            String[] queryLine = reader.nextLine().split(" ");

            if (queryLine[0].toLowerCase().equals("search")) {

                // create a new String[] of words to search
                String[] wordsToSearch = new String[queryLine.length - 1];
                for (int i = 1; i < queryLine.length; i++) {
                    wordsToSearch[i - 1] = correction.closestWord(queryLine[i]);
                }

                System.out.println(documentSearch.bestDocument(wordsToSearch));

                // this.searchQueries.add(wordsToSearch);

            } else if (queryLine[0].toLowerCase().equals("the")) {
                String wordToComplete = correction.closestWord(queryLine[5]);
                System.out.println(wordToComplete + " " + bigrams.getMostProbableBigramOf(wordToComplete));

                //bigramQueries.add(queryLine[5]);                // the word to get the query of is always at this position
            }

        }

    }

    public void correctQueries() {
        Correction correction = new Correction(this.wordMap);

        // Replace words in query by their correction
        for (int i=0; i<bigramQueries.size(); i++) {
            bigramQueries.set(i, correction.closestWord(bigramQueries.get(i)));
        }
        for (int i=0; i<searchQueries.size(); i++) {
            for (int j=0; j<searchQueries.get(i).length; j++) {
                searchQueries.get(i)[j] = correction.closestWord(searchQueries.get(i)[j]);
            }
        }
    }


}