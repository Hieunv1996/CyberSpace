package cluster;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Created by hieunv on 02/04/2017.
 */
public class Process {
    private int k;
    private final ArrayList<Cluster> clusters = new ArrayList<Cluster>();
    private ArrayList<String> confessions = new ArrayList<String>();
    private Set<String> features = new HashSet<String>();
    private ArrayList<ArrayList<String>> cfs_words = new ArrayList<ArrayList<String>>();
    private ArrayList<Vector<Integer>> cfs_vector = new ArrayList<Vector<Integer>>();
    public Process(int k) {
        this.k = k;
    }

    public void readCfs(String url){
        ConfessionReader confessionReader = new ConfessionReader(url,confessions);
        confessionReader.read();
        System.out.println("data size: "+confessions.size());
    }

    public void getFeatures(){
        Feature feature = new Feature(confessions,features,cfs_words);
        feature.takeFeatures();
        System.out.println("features size: "+features.size());
    }

    public void cfs_Vectors(){
        VSMConfession vsmConfession = new VSMConfession(features,cfs_words);
        cfs_vector = vsmConfession.toVector();
    }

    private void init(){
        for (int i = 0;i < k;i++){
            clusters.add(new Cluster(cfs_vector.get(i)));
        }
    }

    public void cluster(){
        init();
        boolean check = false;
        while (!check){
            check = computeDistance();
            updateCentros();
        }
    }

    private void updateCentros(){
        for (Cluster cluster : clusters){
            Vector<Double> sum = new Vector<Double>();
            Set<Integer> itemlst = cluster.getItems();
            for (Integer i : itemlst){
                Vector<Integer> v = cfs_vector.get(i);
                for (int j = 0;j < v.size();j++){
                    if(sum.size() == j) sum.add(0.0);
                    sum.set(j,sum.elementAt(j)+v.elementAt(j));
                }
            }
            for (int i = 0;i < sum.size();i++){
                sum.set(i,sum.elementAt(i)/itemlst.size());
            }
            cluster.setPoint(sum);
        }
    }

    private boolean computeDistance(){
        int count = 0;
        for (int i = 0; i < cfs_vector.size(); i++) {
            Vector<Integer> v = cfs_vector.get(i);
            double val, minDis = Double.MAX_VALUE;
            int l1 = -1, l2 = -1;
            for (Cluster cluster : clusters) {
                val = cluster.euclidean(v);
                if (val < minDis){
                    minDis = val;
                    l1 = i;
                }
                if(cluster.getItems().contains(i)){
                    l2 = i;
                }
            }
            if(l1 != l2){
                count++;
                clusters.get(l1).getItems().add(i);
                if(l2 >= 0) clusters.get(l2).getItems().remove(i);
            }
        }
        System.out.println(count);
        return count == 0;
    }

    public void result(String url){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(url));
            for (Cluster cluster : clusters){
                Set<Integer> set = cluster.getItems();
                for (Integer i : set){
                    writer.write(cluster + ": " +confessions.get(i));
                    writer.newLine();
                }
                writer.write("\n-----------------------------------------\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
