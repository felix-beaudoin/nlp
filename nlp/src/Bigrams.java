import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bigrams {

    private String[][] words;
    private WordMap wordMap;
    private List<File> arrayOfFiles;
    //private ArrayList<Text> texts;
    private Text[] texts;

    public Bigrams(String[][] words, WordMap wordMap, List<File> arrayOfFiles, Text[] texts) {
        this.words = words;
        this.wordMap = wordMap;
        this.arrayOfFiles = arrayOfFiles;
        this.texts = texts;
    }


    // get all the bigrams out of the text
    // array of arrays for each text
    public Pair[][] getBigrams() {
        Pair[][] bigrams = new Pair[words.length][];
        for (int j = 0; j<words.length; j++) {
            bigrams[j] = new Pair[words[j].length - 1];

            for (int i = 0; i < words[j].length - 1; i++) {
                Pair bigram = new Pair(words[j][i], words[j][i + 1]);
                bigrams[j][i] = bigram;
            }
        }
        return bigrams;
    }


    public String getMostProbableBigramOf1(String wordToComplete) {
        FileMap fileMap =  wordMap.get(wordToComplete);
        System.out.println("fileMap.keySet() = " + fileMap.keySet());
        System.out.println("fileMap.entrySet() = " + fileMap.entrySet());

        for (FileMapEntry entry : fileMap.entrySet()) {
            int i = arrayOfFiles.indexOf(entry.fichier());      // la position du fichier dans la liste pour pouvoir recuperer les mots au bon index de bigram[]
            ArrayList<Integer> positions = entry.positions();
            //mots
            for (int position : positions) {
                Pair bigram =  getBigrams()[i][position];
                String nextWord = bigram.w2;                    // on a trouvé le mot suivant!
                                                                // il faut mettre les mots suivants dans une structure qui
                                                                // va permettre de les compter facilement(hashmap?). Puis faire les
                                                                // fréquences relatives.
            }

        }

        return "";
    }

    public String getMostProbableBigramOf(String wordToComplete) {
        FileMap fileMap =  wordMap.get(wordToComplete);     // on trouve le fileMap associé au mot

        HashMap<String, Integer> nextwords = new HashMap<>();

        for (FileMapEntry entry : fileMap.entrySet()) {
            String title = entry.fichier();
            for (Text text : texts) {
                if (text.getTitle().equals(title)) {
                    for (Integer position : entry.positions()) {
                        if (!position.equals(text.getWords().length-1)) {           // pour etre sur de pas avoir index out of bound
                            String word = text.getWords()[position + 1];            // on trouve le mot suivant

                            if (nextwords.containsKey(word)) {
                                Integer count = nextwords.get(word);
                                nextwords.put(word, count+1);                   // on monte le compteur de +1
                            } else {
                                nextwords.put(word, 1);
                            }
                        }

                    }

                    break;
                }
            }
        }
        Integer max = 0;
        String wordMax = "joe";

        for (Map.Entry<String, Integer> entry : nextwords.entrySet()) {
            if (entry.getValue().compareTo(max) > 0) {
                max = entry.getValue();
                wordMax = entry.getKey();
            } else if  (entry.getValue().compareTo(max) == 0) {         //prendre le premier dans l'ordre lexicographique
                if (entry.getKey().compareTo(wordMax) < 0 ) {
                    wordMax = entry.getKey();
                }

            }
        }
        return wordMax;
    }
}