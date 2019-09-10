package com.soft863.salary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOUtilImp implements Util{

    public void writeLog(String path, String content){
        try {
            FileWriter fileWriter = new FileWriter (path);
            char[] chars = content.toCharArray ();
            fileWriter.write (chars,0,chars.length);
            fileWriter.close ();

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public List<String> readLines(String path) throws IOException {
        FileReader fileReader = new FileReader (path);
        List<String> list = new ArrayList<> ();
        BufferedReader bufferedReader = new BufferedReader (fileReader);
        String line;
        String[] str;

        while ((line = bufferedReader.readLine ()) != null) {
            str = line.split(",");

            if(line.contains("面议")||line.contains("不限经验")||str.length!=8)
            {
                continue;
            }
            String strNew = str[1]+","+str[2]+","+str[3]+","+str[4]+","+str[6]+","+str[7];
            if(!list.contains(strNew))
            list.add(strNew);
        }

        bufferedReader.close ();
        return list;
    }

}