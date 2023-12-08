//import static edu.stanford.nlp.util.StringUtils.editDistance;
import java.util.HashMap;
public class Correction {
    private Query query;
    private HashMap<String, Integer> text = new HashMap<>();

    // TODO: add constructor, get word to correct from query

    public String closestWord(String wordToCorrect, HashMap<String,Integer> text1) {
        int leastEditDistance = wordToCorrect.length();
        String closestWord = wordToCorrect;

        for (String word : text1.keySet()) {
            //int editDistance = editDistance(wordToCorrect, word);

            //if (leastEditDistance > editDistance) {     // a word in text is closer to the word we are correcting
             //   leastEditDistance = editDistance;
            //    closestWord = word;
           // }
        }
        return closestWord;
    }


}
