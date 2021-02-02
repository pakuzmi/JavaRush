package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* 
Смена кодировки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String filePath1 = args[0];
        String filePath2 = args[1];
        String text = null;
        try(BufferedInputStream is = new BufferedInputStream( new FileInputStream(filePath1))){
            byte[] buff = new byte[is.available()];
            is.read(buff);
            text = new String(buff, "Windows-1251");
        }
        try(BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(filePath2))){
            os.write(text.getBytes("UTF-8"));
        }
    }
}
