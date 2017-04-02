package cluster;

import com.sun.org.apache.bcel.internal.generic.DUP;

import java.util.*;

/**
 * Created by hieunv on 02/04/2017.
 */
public class Feature {
    private ArrayList<String> confessions;
    private Set<String> features;
    private ArrayList<ArrayList<String>> cfs_words;
    private final int DUPLICATE = 10;

    public Feature(ArrayList<String> confessions, Set<String> features, ArrayList<ArrayList<String>> cfs_words) {
        this.confessions = confessions;
        this.features = features;
        this.cfs_words = cfs_words;
    }

    public void takeFeatures() {
        Map<String, Integer> map = toMap();
        for (String key : map.keySet()){
            if(map.get(key) >= DUPLICATE) features.add(key);
        }
    }

    private Map<String, Integer> toMap() {
        ConfessionReader obj = new ConfessionReader();
        Map<String, Integer> map = new TreeMap<String, Integer>();
        for (String con : confessions) {
            ArrayList<String> words = obj.splitText(con);
            cfs_words.add(words);
            for (String word : words) {
                if (map.containsKey(word)) map.put(word, map.get(word) + 1);
                else map.put(word, 1);
            }
        }
        return map;
    }
}
