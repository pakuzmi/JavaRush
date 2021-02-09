package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString() throws IOException {
        String result;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        result = br.readLine();
        br.close();
        return result;
    }
    public static int readInt() throws IOException{
        int result;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        result = Integer.parseInt(br.readLine());
        return result;
    }
}
