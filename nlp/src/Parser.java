import java.io.*;
import java.util.*;
public class Parser {

    // https://docs.oracle.com/javase/8/docs/api/java/util/zip/ZipFile.html
    try {
        ZipFile zipFile = new ZipFile("src/dataset.zip");
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        // https://docs.oracle.com/javase/8/docs/api/java/util/zip/ZipEntry.html
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            // https://www.baeldung.com/java-read-zip-files
            try (InputStream inputStream = zipFile.getInputStream(entry)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
    } catch (IOException e) {
            System.out.println(e);
    }
}




