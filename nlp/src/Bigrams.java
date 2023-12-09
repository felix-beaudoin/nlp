import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Bigrams {

    private String[][] words;
    private WordMap wordMap;
    private List<File> arrayOfFiles;

    public Bigrams(String[][] words, WordMap wordMap, List<File> arrayOfFiles) {
        this.words = words;
        this.wordMap = wordMap;
        this.arrayOfFiles = arrayOfFiles;
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


    public String getMostProbableBigramOf(String wordToComplete) {
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

}