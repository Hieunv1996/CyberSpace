package com.hieunv;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by hieunv on 09/04/2017.
 */
public class Reader {
    private String url;
    private ArrayList<String> data = new ArrayList<>();

    public Reader(String url, ArrayList<String> data) {
        this.url = url;
        this.data = data;
    }

    private void readData(){
        String line;
        ArrayList<String> files = getAllFile();
        for (String file : files){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null){

                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<String> getAllFile(){
        ArrayList<String> lstUrl = new ArrayList<>();
        File folder = new File(url);
        if(folder.isFile()) lstUrl.add(folder.getAbsolutePath());
        else{
            for (File file : folder.listFiles()){
                if(file.getName().endsWith(".txt")){
                    lstUrl.add(file.getAbsolutePath());
                }
            }
        }
        return lstUrl;
    }
}
