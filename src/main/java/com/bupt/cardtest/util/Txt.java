package com.bupt.cardtest.util;

import com.bupt.cardtest.model.bean.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class Txt {
    //private static final Logger logger=LoggerFactory.getLogger(Txt.class);


    public  List<Record> readTxt(String filePath,String deviceId,String file_name)throws Exception{
        List<Record> records=new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(
                        new FileInputStream(file), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt = null; // 读取文件的方法

                int head=15;
                int row=0;
                int s=1;

                while ((lineTxt = br.readLine()) != null) {
                    row++;
                    if (row<=head) continue;

                    String[] arrStrings = lineTxt.split("\\|");// 用于把一个字符串分割成字符串数组

                    if (arrStrings.length!=6) throw new Exception("文件格式不正确");//行数组格式不对抛异常


                    if (arrStrings[0].trim().length()==0){
                        Record pre=records.get(records.size()-1);
                        if (arrStrings[3]!=""){
                            pre.addData(arrStrings[3].trim());
                        }
                        if (arrStrings[4]!="") {
                            pre.setCrc(arrStrings[4].trim());
                        }
                        if (arrStrings[5]!=""){
                            pre.setAnnotation(arrStrings[5].trim());
                        }

                    }else {
                        Record cur=new Record();
                        cur.setSid(s++);
                        cur.setDeviceId(deviceId);
                        cur.setFileName(file_name);
                        cur.setStart(Integer.parseInt(arrStrings[0].trim()));
                        cur.setEnd(Integer.parseInt(arrStrings[1].trim()));
                        cur.setSrc(arrStrings[2].trim());
                        cur.setData(arrStrings[3].trim());
                        cur.setCrc(arrStrings[4].trim());
                        cur.setAnnotation(arrStrings[5].trim());

                        records.add(cur);
                    }
                }

                br.close();
                /*
                for (int i = 0; i < records.size(); i++) {
                    System.out.println(records.get(i));
                }
                */

            } else {

                throw new Exception("文件不存在");
            }
        } catch (Exception e) {

            throw e;
        }
        return records;
    }


}
