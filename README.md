# Text processing and document search tool
## Overview

A text processing tool and document search engine built able to do:

    Tokenization
    Lemmatization
    Bigrams prediction
    TFIDF calculator
    Auto-correction based on Levenshtein distance

 
## Functionalities
   
On input "search x", outputs the file from the dataset that has the highest relevance concerning x.

On input "the most probable bigram of y", outputs yz, where z is the most common word following the word y

When processing x or y, the program will correct it if it contains spelling error based on Levenshtein distance and TFIDF. (see plonet and cofee exemples below)


On input:

    search plonet
    the most probable bigram of cofee
    search coffe importe
    search graveety
    the most probable bigram of dwarf

It outputs:

    902.txt
    coffee import
    903.txt
    902.txt
    dwarf planet


## Data structures
Hash maps were manually implemented in `WordMap.java` and `FileMap.java`
