public class Query {
    private String bigramWord;
    private String[] searchWords;
    private boolean queryType;
        // queryType = true => the most probable bigram of _
        // queryType = false => search _

    public Query(boolean queryType, String bigramWord) throws IllegalArgumentException {
        if (queryType == true) {
            this.bigramWord = bigramWord;
            this.queryType = true;
        }
        else {
            throw new IllegalArgumentException("The query 'The most probable bigram of' requires a String");
        }
    }
    public Query(boolean queryType, String[] searchWords) throws IllegalArgumentException {
        if (queryType == false) {
            this.searchWords = searchWords;
            this.queryType = false;
        }
        else {
            throw new IllegalArgumentException("The query 'Search' requires a list of String");
        }
    }


}

// TODO: parse query file and create Query objects from it