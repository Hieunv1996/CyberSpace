package vn_en;

import java.io.*;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by hieunv on 08/04/2017.
 */
public class VnToEn {
    private String url_in;
    private String url_out;

    private String stop_url = "/home/hieunv/Desktop/CyberSpace/TextPreprocess/dat/stopwords.txt";
    private Set<String> stopwords = new HashSet<>();

    public VnToEn(String url_in, String url_out) {
        this.url_in = url_in;
        this.url_out = url_out;
        getStopwords();
    }

    public void resolve() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url_in));
            BufferedWriter writer = new BufferedWriter(new FileWriter(url_out));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                line = removeStopwords(line);

                writer.write(line);
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String removeStopwords(String s) {
        String[] arr = s.split("\\s+");
        s = "";
        for (String i : arr) {
            if (!stopwords.contains(i.trim())) {
                s += i.trim() + " ";
            }
        }
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").trim();
    }

    private void getStopwords() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(stop_url));
            String line;
            while ((line = reader.readLine()) != null) {
                stopwords.add(line.trim().toLowerCase());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
