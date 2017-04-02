package cluster;

import java.util.ArrayList;

/**
 * Created by hieunv on 01/04/2017.
 */
public class Main {
    public static void main(String[] args) {
        Process process = new Process(5);
        process.readCfs("/home/hieunv/Desktop/CyberSpace/ConfessionsClassify/data/part-00000");
        process.getFeatures();
        process.cfs_Vectors();
        process.cluster();
        process.result("result.dat");

    }
}
