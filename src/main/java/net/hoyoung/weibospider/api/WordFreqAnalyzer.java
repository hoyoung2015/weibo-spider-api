package net.hoyoung.weibospider.api;

import org.aspectj.util.FileUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/25.
 */
public class WordFreqAnalyzer {
    public static void main(String[] args) throws IOException {
        String str = FileUtil.readAsString(new File("E:\\tmp\\hah.txt"));
        System.out.println(str);
        String[] arr = str.split(" ");
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (String word : arr){
            Integer count = map.get(word);
            if (count==null){
                count = 1;
            }else {
                count++;
            }
            map.put(word,count);
        }
        map.remove("　");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\tmp\\hah_freq.txt")));
        bw.write("word freq");
        for (Map.Entry<String, Integer> entry:map.entrySet()){
            bw.newLine();
            bw.write(entry.getKey()+" "+entry.getValue());
        }

        bw.flush();
        bw.close();
        System.out.println("successful");
    }
}
