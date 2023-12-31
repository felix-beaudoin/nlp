import java.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;

import java.util.*;

public class MapBuilder {

    public WordMap wordMap = new WordMap();
    private String datasetDir;

    public Text[] texts;

    /**
     * Constructor for MapBuilder. Process texts and put the words in wordMaps
     * @param datasetDir directory of dataset
     */
    public MapBuilder(String datasetDir) {
        this.datasetDir = datasetDir;

        try {
            String dir = datasetDir;
            File folder = new File(dir);
            File[] list = folder.listFiles();

            int dimension = list.length;

            texts = new Text[dimension];


            // ouvrir chaque fichier du repertoire
            // transformer les mots
            // remplir les maps
            for (int j=0; j<list.length; j++) {
               File file = list[j];



                if(file.isFile())
                {
                    BufferedReader br=new BufferedReader(new FileReader(new
                            File(dir+"/"+file.getName())));
                    StringBuffer word=new StringBuffer();
                    String line;
                    while((line=br.readLine())!=null)
                    {
                        String newline=line.replaceAll("[^’'a-zA-Z0-9]"," ");
                        String finalline=newline.replaceAll("\\s+"," ").trim();
                        Properties props=new Properties();
                        props.setProperty("annotators","tokenize,pos,lemma");
                        props.setProperty("coref.algorithm","neural");

                        RedwoodConfiguration.current().clear().apply();
                        StanfordCoreNLP pipeline=new StanfordCoreNLP(props);
                        CoreDocument document=new CoreDocument(finalline);
                        pipeline.annotate(document);
                        for(CoreLabel tok:document.tokens()){
                            String str=String.valueOf(tok.lemma());
                            if(!(str.contains("'s")||str.contains("’s"))){
                                word.append(str).append(" ");
                            }
                        }


                    }
                    String str=String.valueOf(word);
                    str=str.replaceAll("[^a-zA-Z0-9]"," ").replaceAll("\\s+"," ").trim();


                    String[] mots = str.split(" ");


                    this.texts[j] = new Text(file.getName(), mots);

                    // Put words in wordMap
                    for (int i=0; i < mots.length; i++) {
                        String mot = mots[i];
                        if (wordMap.get(mot) == null) {

                            FileMap fileMap = new FileMap();
                            fileMap.put(word, i);
                            wordMap.put(mot, fileMap);
                        }

                        wordMap.get(mot).put(file.getName(), i);

                    }

                }
            }
        } catch (IOException joe) {
            System.out.println(joe);
        }
    }
}