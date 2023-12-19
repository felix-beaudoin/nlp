import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bigrams {

   // private String[][] words;
   //private List<File> arrayOfFiles;
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


    // get all the bigrams out of the text
    // array of arrays for each text
   // public Pair[][] getBigrams() {
   //     Pair[][] bigrams = new Pair[words.length][];
   //     for (int j = 0; j<words.length; j++) {
   //         bigrams[j] = new Pair[words[j].length - 1];
//
   //         for (int i = 0; i < words[j].length - 1; i++) {
   //             Pair bigram = new Pair(words[j][i], words[j][i + 1]);
   //             bigrams[j][i] = bigram;
   //         }
   //     }
   //     return bigrams;
   // }


  // public String getMostProbableBigramOf1(String wordToComplete) {
  //     FileMap fileMap =  wordMap.get(wordToComplete);
  //     System.out.println("fileMap.keySet() = " + fileMap.keySet());
  //     System.out.println("fileMap.entrySet() = " + fileMap.entrySet());

  //     for (FileMapEntry entry : fileMap.entrySet()) {
  //         int i = arrayOfFiles.indexOf(entry.fichier());      // la position du fichier dans la liste pour pouvoir recuperer les mots au bon index de bigram[]
  //         ArrayList<Integer> positions = entry.positions();
  //         //mots
  //         for (int position : positions) {
  //             Pair bigram =  getBigrams()[i][position];
  //             String nextWord = bigram.w2;                    // on a trouvé le mot suivant!
  //                                                             // il faut mettre les mots suivants dans une structure qui
  //                                                             // va permettre de les compter facilement(hashmap?). Puis faire les
  //                                                             // fréquences relatives.
  //         }

  //     }

  //     return "";
  // }

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