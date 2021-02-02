package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        try{
            String[] strings = string.split(" ");
            while (i <= 4){
                sb.append(strings[i] + " ");
                i++;
            }
        } catch (RuntimeException e) {
            throw new TooShortStringException();
        }
        return sb.toString().trim();
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
