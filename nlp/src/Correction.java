import static edu.stanford.nlp.util.StringUtils.editDistance;
public class Correction {
    private Query query;
    private WordMap wordMap = new WordMap();


    // TODO:  get word to correct from query

    public Correction( WordMap map) {
        this.wordMap = map;
    }


    public String closestWord(String wordToCorrect) {
        int leastEditDistance = wordToCorrect.length();
        String closestWord = wordToCorrect;

        for (String word : this.wordMap.keySet()) {
            int editDistance = editDistance(wordToCorrect, word);

            if (leastEditDistance > editDistance) {     // a word in text is closer to the word we are correcting
               leastEditDistance = editDistance;
                closestWord = word;
             }
        }


        return closestWord;
    }


}
