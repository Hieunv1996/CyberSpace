package shuffle;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hieunv on 05/04/2017.
 */
public class Shuffle {
    private String url;
    public Shuffle(String url) {
        this.url = url;
    }

    public void solve(){
        File[] files = (new File(url)).listFiles();
        for (File file : files){
            if(file.getName().endsWith(".txt")){
                ArrayList<String> data = readData(file.getAbsolutePath());
                System.out.println(file.getName() + ": " +data.size());
            }
        }
    }
    private ArrayList<String> readData(String url){
        ArrayList<String> collects = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null){
                if(!line.equals("")){
                    collects.add(line);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(collects);
        return collects;
    }

    private void writeData(ArrayList<String> data,String url){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(url));
            for (String d : data){
                writer.write(d);
                writer.write("\n\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
