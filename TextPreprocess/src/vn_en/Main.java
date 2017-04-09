package vn_en;

import jdk.nashorn.internal.ir.VarNode;

/**
 * Created by hieunv on 08/04/2017.
 */
public class Main {
    public static void main(String[] args) {
        new VnToEn("/home/hieunv/Desktop/two_label.arff","/home/hieunv/Desktop/rs_two_label.arff").resolve();
    }
}