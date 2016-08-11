package com.nonobank.ui.activity.Utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by edison on 16/4/5.
 */
public class ReadCode {

    //读取短信内容
    public String read(String str) throws IOException {
        File file=new File(str);
        FileInputStream fis=new FileInputStream(file);
        StringBuffer sb=new StringBuffer();

        BufferedInputStream bis=new BufferedInputStream(fis);
        BufferedReader read = new BufferedReader (new InputStreamReader(bis));
        int c=0;
        while ((c=read.read())!=-1) {
            sb.append((char) c);
        }
        read.close();
        bis.close();
        fis.close();
//        System.out.println("code= "+sb.toString());
        String verify=sb.toString();
        return verify;
    }
}
