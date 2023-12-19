public class Text {
    private String title;
    private String[] words;

    /**
     * Constructor for Text
     * @param title title of the document
     * @param words list of words in the document, after processing
     */
    public Text (String title, String[] words) {
        this.title = title;
        this.words = words;
    }
    public String getTitle() {
        return this.title;
    }

    public String[] getWords() {
        return this.words;
    }


}
