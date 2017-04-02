package cluster;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Created by hieunv on 02/04/2017.
 */
public class Cluster {
    private Vector<Double> point;
    private Set<Integer> items;

    public Cluster(Vector<Integer> point) {
        Vector<Double> v = new Vector<Double>();
        for (Integer i : point){
            v.add(Double.valueOf(i));
        }
        items = new HashSet<Integer>();
    }

    public Cluster(Vector<Integer> point, Set<Integer> items) {
        Vector<Double> v = new Vector<Double>();
        for (Integer i : point){
            v.add(Double.valueOf(i));
        }
        this.point = v;
        this.items = items;
    }

    public Vector<Double> getPoint() {
        return point;
    }

    public void setPoint(Vector<Double> point) {
        this.point = point;
    }

    public Set<Integer> getItems() {
        return items;
    }

    public void setItems(Set<Integer> items) {
        this.items = items;
    }

    public double euclidean(Vector<Integer> v){
        double ans = 0;
        for (int i = 0;i < v.size();i++){
            ans += Math.pow((v.elementAt(i) - point.elementAt(i)),2);
        }
        return  Math.sqrt(ans);
    }
}
