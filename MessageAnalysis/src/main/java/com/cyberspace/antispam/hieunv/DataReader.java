package com.cyberspace.antispam.hieunv;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hieunv on 27/03/2017.
 */
public class DataReader {
    private final String hostName = "localhost";
    private int[] startDate;
    private int[] endDate;
    private ArrayList<Message> messages = new ArrayList<Message>();
    public DataReader(int[] startDate, int[] endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ArrayList<Message> getMessages() {
        readData();
        return messages;
    }

    public DataReader() {
        startDate = new int[3];
        endDate = new int[3];
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date dateobj = new Date();
        String[] date = df.format(dateobj).split("[^0-9]");
        startDate[0] = endDate[0] = Integer.parseInt(date[0]);
        startDate[1] = endDate[1] = Integer.parseInt(date[1]);
        startDate[2] = endDate[2] = Integer.parseInt(date[2]);
        dateReduce(this.endDate,1);
        dateReduce(startDate,16);
    }

    public void readData(){
        StringBuilder host = new StringBuilder();
        while (isStop() == false){
            host.setLength(0);
            host.append(hostName).append("/"+endDate[0]+"/"+endDate[1]+"/"+endDate[2]);

            ArrayList<String> paths = getListFile(host.toString());
            for (String path : paths){
                read(path);
            }

            dateReduce(endDate,1);
        }
    }

    private void read(String path){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null){
                line = line.toLowerCase().trim();
                int i = 0,j = line.length() -1;
                while (i != ' ') i++;
                while (j != ' ') j--;
                messages.add(new Message(line.substring(0,i),line.substring(i,j).trim(),Integer.parseInt(line.substring(j+1))));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getListFile(String path){
        ArrayList<String> filepath = new ArrayList<String>();
        ArrayList<String> lstFile = new ArrayList<String>();
        filepath.add(path);
        while (filepath.size() > 0){
            File[] list = new File(filepath.get(0)).listFiles();
            for (File file : list){
                if(file.isFile()) lstFile.add(file.getAbsolutePath());
                else filepath.add(file.getAbsolutePath());
            }
            filepath.remove(0);
        }
        return lstFile;
    }

    private boolean isStop(){
        if(startDate[0] > endDate[0]) return true;
        if(startDate[0] == endDate[0] && startDate[1] > endDate[1]) return true;
        if(startDate[0] == endDate[0] && startDate[1] == endDate[1] && startDate[2] > endDate[2]) return true;
        return false;
    }

    private void dateReduce(int[] date,int value) {
        for (int i = 0;i < value;i++){
            if (date[2] > 1) date[2]--;
            else if (date[1] > 1) {
                date[1]--;
                date[2] = dayReduce(date[1], date[0]);
            } else {
                date[0]--;
                date[1] = 12;
                date[2] = 31;
            }
        }
    }

    private int dayReduce(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
                    return 29;
                } else return 28;
        }
        throw null;
    }
}
