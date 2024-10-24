# NLP Tool 
Overview

A simple NLP tool built in Java using Stanford CoreNLP. It processes text from datasets to perform:

    Tokenization
    Lemmatization
    Bigrams prediction
    TFIDF calculator
    Auto-correction based on Levenshtein distance


On input "search x", outputs the file from the dataset that has the highest relevance concerning x.

On input "the most probable bigram of y", outputs yz, where z is the most common word following the word y

When processing x or y, it will correct it if it contains spelling error.


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
    
