import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class QueryReader {

    private ArrayList<String> bigramQueries = new ArrayList<>();
    private ArrayList<String[]> searchQueries = new ArrayList<>();
    private String filePath;
    private WordMap wordMap;
    private Bigrams bigrams;
    private DocumentSearch documentSearch;
    private String solutionPath;



/**
 * Constructor for QueryReader
 * @param filePath path of query file
 * @param solutionPath path of solution file
 * @param wordMap
 * @param bigrams
 * @param documentSearch
 */
    public QueryReader(String filePath, String solutionPath, WordMap wordMap, Bigrams bigrams, DocumentSearch documentSearch) {
        this.filePath = filePath;
        this.solutionPath = solutionPath;
        this.wordMap = wordMap;
        this.bigrams = bigrams;
        this.documentSearch = documentSearch;
    }

    //public ArrayList<String> getBigramQueries() {return this.bigramQueries;}

   // public ArrayList<String[]> getSearchQueries() {return searchQueries;}

    /**
     * Read query file, correct it and get answers to queries
     * @throws FileNotFoundException
     */
    public void answerQueries() {

        try {
            File textFile = new File(filePath);
            Scanner reader = new Scanner(textFile);
            Correction correction = new Correction(this.wordMap);

            File solutionFile = new File(solutionPath);
            solutionFile.createNewFile();
            FileWriter fileWriter = new FileWriter(solutionFile);


            while (reader.hasNextLine()) {
                String[] queryLine = reader.nextLine().split(" ");

                // Search query
                if (queryLine[0].toLowerCase().equals("search")) {

                    // create a new String[] of words to search
                    String[] wordsToSearch = new String[queryLine.length - 1];
                    // correct words to search
                    for (int i = 1; i < queryLine.length; i++) {
                        wordsToSearch[i - 1] = correction.closestWord(queryLine[i]);
                    }
                    // Print answer to search query
                    System.out.println(documentSearch.bestDocument(wordsToSearch));
                    fileWriter.write(documentSearch.bestDocument(wordsToSearch) + "\n");


                    // Bigram query
                } else if (queryLine[0].toLowerCase().equals("the")) {          // bigram query starts with "the"
                    String wordToComplete = correction.closestWord(queryLine[5]);

                    // Print answer to bigram query
                    System.out.println(wordToComplete + " " + bigrams.getMostProbableBigramOf(wordToComplete));
                    fileWriter.write(wordToComplete + " " + bigrams.getMostProbableBigramOf(wordToComplete) + "\n");

                }

            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
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