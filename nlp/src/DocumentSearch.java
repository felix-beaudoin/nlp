import java.util.ArrayList;
import java.util.Set;

public class DocumentSearch {

    private WordMap wordMap;
    private int numberOfFiles;
    private Text[] texts;
    private ArrayList<String> titles = new ArrayList<>();

    public void listTitles () {
        for (Text text : this.texts) {
            this.titles.add(text.getTitle());
        }
    }

    /**
     * Constructor for DocumentSearch
     * @param wordMap
     * @param numberOfFiles number of files in dataset
     * @param texts list of dataset texts
     * Produces a list of the titles.
     */
    public DocumentSearch(WordMap wordMap, int numberOfFiles, Text[] texts) {
        this.wordMap = wordMap;
        this.numberOfFiles = numberOfFiles;
        this.texts = texts;
        listTitles();
    }

    /**
     * Count a word occurrences in one document
     * @param wordToSearch
     * @param document
     * @return number of times wordToSearch appears in the document
     */
    public int countFrequency(String wordToSearch, String document) {
        int count = 0;
        FileMap fileMap = this.wordMap.get(wordToSearch);

            if (fileMap.containsKey(document)) {
                count = fileMap.get(document).size();           // Size of the list of positions in document
            }

        return count;
    }

    public int countDocuments (String wordToSearch) {
        FileMap fileMap = this.wordMap.get(wordToSearch);
        int number = fileMap.keySet().size();
        return number;
    }

    /**
     * Calculate TF-IDF score of one document
     * @param wordsToSearch list of words to search
     * @param document
     * @return Score based on frequency of the words to search in this document
     */
    public double score (String[] wordsToSearch, String document) {
        double score = 0;
        for (String word : wordsToSearch) {
            Set<String> documents = wordMap.get(word).keySet();         // Documents that contain this word

            // IDF
            double idf = 1 + Math.log((1 + numberOfFiles) / (1 + documents.size()));

            // TF
            double count = countFrequency(word, document);
            double totalW = 0;
            // number of words in the document
            for (Text text : texts) {
                if (text.getTitle().equals(document)) {
                    totalW = text.getWords().length;
                    break;
                }
            }
            double tf = count / totalW;

            // TF-IDF
            double tfIdf = tf * idf;

            score += tfIdf;
        }
        return score;

    }

    /**
     * Compare scores between documents
     * @param wordsToSearch
     * @return the most relevant document related to the wordsToSearch
     */
    public String bestDocument(String[] wordsToSearch) {
        double bestScore = 0;
        String bestDocument = "joe";
        for (String document : titles) {
            double score = score(wordsToSearch, document);
            if (score > bestScore) {                        // document has a better score
                bestScore = score;
                bestDocument = document;

            } else if (score == bestScore) {
                if (document.compareTo(bestDocument) < 0) {      // Take first document in lexical order
                    bestDocument = document;
                }
            }
        }
        return bestDocument;
    }



}
