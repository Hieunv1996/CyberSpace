package cluster;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hieunv on 03/04/2017.
 */
public class StopWords {
    private String url;

    public StopWords(String url) {
        this.url = url;
    }

    public Set<String> getStopWords(){
        Set<String> stop = new HashSet<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null){
                stop.add(line.trim().toLowerCase());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stop;
    }

}
