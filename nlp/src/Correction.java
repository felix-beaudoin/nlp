import static edu.stanford.nlp.util.StringUtils.editDistance;
public class Correction {

    private WordMap wordMap;


    public Correction( WordMap map) {
        this.wordMap = map;
    }


    /**
     * Find word in texts with the smallest Levenshtein distance
     * @param wordToCorrect
     * @return the word that corrects the word to correct
     */
    public String closestWord(String wordToCorrect) {
        int leastEditDistance = wordToCorrect.length();         // Initialize leastEditDistance to its maximum
        String closestWord = wordToCorrect;

        for (String word : this.wordMap.keySet()) {
            int editDistance = editDistance(wordToCorrect, word);   // CoreNLP tool

            if (leastEditDistance > editDistance) {             // a word in text is closer to the word we are correcting
               leastEditDistance = editDistance;
                closestWord = word;
             }
        }


        return closestWord;
    }


}
