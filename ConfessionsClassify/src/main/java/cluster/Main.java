package cluster;

import java.util.ArrayList;

/**
 * Created by hieunv on 01/04/2017.
 */
public class Main {
    public static void main(String[] args) {
        Process process = new Process(5);
        String fileName = "test.txt";
        process.readCfs("/home/hieunv/Desktop/CyberSpace/ConfessionsClassify/data/"+fileName);
        process.getFeatures();
        process.cfs_Vectors();
        process.cluster();
        process.result("/home/hieunv/Desktop/"+fileName);

    }
}
