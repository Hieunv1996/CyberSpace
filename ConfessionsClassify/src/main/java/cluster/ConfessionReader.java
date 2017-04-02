package cluster;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hieunv on 01/04/2017.
 */
public class ConfessionReader {
    private String url;
    private ArrayList<String> confessions;

    public ConfessionReader(String url, ArrayList<String> confessions) {
        this.url = url;
        this.confessions = confessions;
    }

    public ConfessionReader() {
    }

    public void read(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null){
                confessions.add(normalizer(line));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(confessions);
    }
    private String normalizer(String s){
        s = s.toLowerCase().replaceAll("[0-9]+","")
                .replaceAll("[+!#-%&,()/]+","")
        .replaceAll("[*'\".]+","")
        .replaceAll("[-]+","")
        .replaceAll("[_]_","");
        return s;
    }

    public ArrayList<String> splitText(String s){
        ArrayList<String> rs = new ArrayList<String>();
        String[] arr = s.split("\\s+");
        for (String a : arr){
            if(a.length() > 1) rs.add(a.trim());
            if(a.contains("_")){
                String[] subarr = a.split("_");
                for (String b : subarr) if(b.length() > 1) rs.add(b.trim());
            }
        }
        return rs;
    }
}
