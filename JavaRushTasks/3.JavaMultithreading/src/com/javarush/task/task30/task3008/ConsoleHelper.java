package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString(){
        String result = "";
        while (true){
            try{
                result = bufferedReader.readLine();
                return result;
            } catch (IOException e){
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
    }

    public static int readInt(){
        int result = -1;
        try {
            result = Integer.parseInt(readString());
        } catch (NumberFormatException e){
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            try{
                result = Integer.parseInt(readString());
            } catch (NumberFormatException e1){
                System.out.println(e1.getMessage());
            }
        }
        return result;
    }
}
