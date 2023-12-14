import java.util.ArrayList;
import java.util.Set;

public class DocumentSearch {
     /* TFIDF:
     * compter la fréquence d'un mot dans chaque document
     * compter le nombre de documents qui contiennent le mot
     * compter le nombre de documents
     */


    private String[] wordsToSearch;
    private WordMap wordMap;

    private int numberOfFiles;
    private Text[] texts;
    private ArrayList<String> titles = new ArrayList<>();

    public void listTitles () {
        for (Text text : this.texts) {
            this.titles.add(text.getTitle());
        }
    }

    public DocumentSearch(String[] wordsToSearch, WordMap wordMap, int numberOfFiles, Text[] texts) {
        this.wordsToSearch = wordsToSearch;
        this.wordMap = wordMap;
        this.numberOfFiles = numberOfFiles;
        this.texts = texts;
        listTitles();
    }

    // Count the word frequency in 1 document
    public int countFrequency(String wordToSearch, String document) {
        int count = 0;
        FileMap fileMap = this.wordMap.get(wordToSearch);
        //System.out.println(fileMap.entrySet());
            if (fileMap.containsKey(document)) {
                count = fileMap.get(document).size();
            }

        return count;
    }

    public int countDocuments (String wordToSearch) {
        FileMap fileMap = this.wordMap.get(wordToSearch);
        int number = fileMap.keySet().size();
        return number;
    }

    public double score (String[] wordsToSearch, String document) {
        double score = 0;
        for (String word : wordsToSearch) {
            Set<String> documents = wordMap.get(word).keySet();

            // IDF
            double idf = 1 + Math.log((1 + numberOfFiles) / (1 + documents.size()));
            //System.out.println("idf = "+idf);

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
           // System.out.println("tf= "+tf);

            // TF-IDF
            double tfIdf = tf * idf;
           // System.out.println("tfidf= "+tfIdf);
            score += tfIdf;
        }
        return score;

    }

    // Compare the scores between documents
    public String bestDocument(String[] wordsToSearch) {
        double bestScore = 0;
        String bestDocument = "joe";
        for (String document : titles) {
            double score = score(wordsToSearch, document);
            if (score > bestScore) {
                bestScore = score;
                bestDocument = document;

            } else if (score == bestScore) {
                if (document.compareTo(bestDocument) < 0) {         // Lexical order
                    bestDocument = document;
                }
            }
        }
        return bestDocument;
    }



}
