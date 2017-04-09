package cluster;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hieunv on 01/04/2017.
 */
public class ConfessionReader {
    private String url;
    private ArrayList<String> nl_confess;
    private ArrayList<String> confess;
    private final String re_url = "/home/hieunv/Desktop/CyberSpace/ConfessionsClassify/data/replace.txt";
    private Map<String, String> replace = new HashMap<String, String>();

    public ConfessionReader(String url, ArrayList<String> nl_confess, ArrayList<String> confess) {
        this.url = url;
        this.nl_confess = nl_confess;
        this.confess = confess;
    }

    public ConfessionReader() {
    }

    public void read() {
//        getReplace();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
//                   line = _replace(line);
                    nl_confess.add(normalizer(line));
                    confess.add(line);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String normalizer(String s) {
        s = s.replaceAll(".+\"message\":","")
        .replaceAll("\\\\n"," ")
        .replaceAll("\\\\","")
        .replaceAll("\\\\u[0-9a-zA-Z]{4}","")
        .replaceAll("[^a-z_\\\\nắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ]","")
        .replaceAll("\\n+","\\n")
        .replaceAll(" +"," ")
        .toLowerCase();
        return s;
    }

    public ArrayList<String> splitText(String s) {
        ArrayList<String> rs = new ArrayList<String>();
        String[] arr = s.split("\\s+");
        for (String a : arr) {
            if (a.length() > 1) rs.add(a.trim());
            if (a.contains("_")) {
                String[] subarr = a.split("_");
                for (String b : subarr) if (b.length() > 1) rs.add(b.trim());
            }
        }
        return rs;
    }

    private String _replace(String s) {
        for (String key : replace.keySet()) {
            s = s.replace(key, replace.get(key));
        }
        return s;
    }

//    private void getReplace() {
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(re_url));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String arr[] = line.trim().split(",");
//                replace.put(arr[0].trim(), arr[1].trim());
//            }
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
