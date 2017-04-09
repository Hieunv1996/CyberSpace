package fixfonterror;

import java.io.*;
import java.util.*;

/**
 * Created by hieunv on 07/04/2017.
 */
public class Fix_Shuffle {
    private final String root = "/home/hieunv/Desktop/data";
    private final String dataPath = "/home/hieunv/Desktop/CyberSpace/ConfessionsClassify/data/";
    private final Map<String, String> replace = new HashMap<String, String>();

    public Fix_Shuffle() {
        getReplace();
    }

    public void read_write_shuffle() {
        ArrayList<String> data = new ArrayList<String>();
        File[] files = (new File(root + "/in")).listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(root + "/out/" + file.getName()));
                    data.clear();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.length() > 0) {
                            line = myReplace(line);
                            writer.write(line + "\n\n");
                        }
                    }
                    reader.close();
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String myReplace(String s) {
        for (String key : replace.keySet()) {
            s = s.replace(key, replace.get(key));
        }
//        s = s.replaceAll(".+,\"message\":", "")
//                .toLowerCase()
//                .replaceAll("\\\\n", " ")
//                .replaceAll("\\\\\"+", "")
//                .replaceAll("\\\\u[0-9A-Za-z]{4}", "")
//                .replaceAll(".+\"story\":.+","")
//                .replaceAll("[^a-z\\nắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ\"]", " ")
//                .replaceAll(" +", " ");
        return s;
    }

    private void getReplace() {
        String url = dataPath + "/replace.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                String arr[] = line.trim().split(",");
                replace.put(arr[0].trim(), arr[1].trim());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
