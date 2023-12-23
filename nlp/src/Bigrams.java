import java.util.HashMap;
import java.util.Map;

public class Bigrams {

    private WordMap wordMap;
    private Text[] texts;

    /**
     * Constructor for Bigrams
     * @param wordMap
     * @param texts list of texts in dataset
     */
    public Bigrams(WordMap wordMap, Text[] texts) {
        this.wordMap = wordMap;

        this.texts = texts;
    }


    /**
     * Find the most probable next word given the first word.
     * @param wordToComplete first word
     * @return the most probable next word
     */
    public String getMostProbableBigramOf(String wordToComplete) {
        FileMap fileMap =  wordMap.get(wordToComplete);     // fileMap associated with the word

        HashMap<String, Integer> nextWords = new HashMap<>();

        for (FileMapEntry entry : fileMap.entrySet()) {
            String title = entry.fichier();
            for (Text text : texts) {
                if (text.getTitle().equals(title)) {                                // Find text in list of texts
                    for (Integer position : entry.positions()) {
                        if (!position.equals(text.getWords().length-1)) {           // make sure index is not out of bound
                            String word = text.getWords()[position + 1];            // this is the next word

                            // Count frequency of this word
                            if (nextWords.containsKey(word)) {
                                Integer count = nextWords.get(word);
                                nextWords.put(word, count+1);
                            } else {
                                nextWords.put(word, 1);
                            }
                        }
                    }
                    break;
                }
            }
        }

        // Find the word with the highest count

        Integer max = 0;
        String wordMax = "joe";

        for (Map.Entry<String, Integer> entry : nextWords.entrySet()) {
            if (entry.getValue().compareTo(max) > 0) {                  // Word with higher count
                max = entry.getValue();
                wordMax = entry.getKey();

            } else if  (entry.getValue().compareTo(max) == 0) {         //take first word in alphabetical order
                if (entry.getKey().compareTo(wordMax) < 0 ) {
                    wordMax = entry.getKey();
                }

            }
        }
        return wordMax;
    }

}