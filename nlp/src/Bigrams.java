public class Bigrams {

    private String[] words;


    public Bigrams(String[] words) {
        this.words = words;
    }


    // get all the bigrams out of the text
    public Pair[] getBigrams() {
        Pair[] bigrams = new Pair[words.length-1];
        for (int i = 0; i < words.length-1; i++) {
            Pair bigram = new Pair(words[i], words[i+1]);
            bigrams[i] = bigram;
        }
        return bigrams;
    }


    // query "the most probable bigram of__"
}