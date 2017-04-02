package cluster;

import java.util.*;

/**
 * Created by hieunv on 02/04/2017.
 */
public class VSMConfession {
    private Set<String> features;
    private ArrayList<ArrayList<String>> cfs_words;

    public VSMConfession(Set<String> features, ArrayList<ArrayList<String>> cfs_words) {
        this.features = features;
        this.cfs_words = cfs_words;
    }

    public ArrayList<Vector<Integer>> toVector(){
        ArrayList<Vector<Integer>> cfs = new ArrayList<Vector<Integer>>();
        for (ArrayList<String> item : cfs_words){
            Map<String,Integer> mItem = toMap(item);
            Vector<Integer> v = new Vector<Integer>();
            for (String key : features){
                if(mItem.containsKey(key)){
                    v.add(mItem.get(key));
                }else v.add(0);
            }
            cfs.add(v);
        }
        return cfs;
    }

    private Map<String,Integer> toMap(ArrayList<String> s){
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (String key : s){
            if(map.containsKey(key)) map.put(key,map.get(key)+1);
            else map.put(key,1);
        }
        return map;
    }
}
