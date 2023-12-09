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

    public QueryReader(String filePath, WordMap wordMap) {
        this.filePath = filePath;
        this.wordMap = wordMap;
    }

    public ArrayList<String> getBigramQueries() {return this.bigramQueries;}

    public ArrayList<String[]> getSearchQueries() {return searchQueries;}

    public void readFile() throws FileNotFoundException {
        File textFile = new File(filePath);
        Scanner reader = new Scanner(textFile);

        while (reader.hasNextLine()) {
            String[] queryLine = reader.nextLine().split(" ");

            if (queryLine[0].toLowerCase().equals("search")) {

                // create a new String[] of words to search
                String[] wordsToSearch = new String[queryLine.length - 1];
                for (int i = 1; i < queryLine.length; i++) {
                    wordsToSearch[i - 1] = queryLine[i];
                }
                this.searchQueries.add(wordsToSearch);
            } else if (queryLine[0].toLowerCase().equals("the")) {
                bigramQueries.add(queryLine[5]);                // the word to get the query of is always at this position
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