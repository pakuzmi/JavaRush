package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        String result = null;
        try {
            int a = string.indexOf('\t');
            int b = string.indexOf('\t', a + 1);
            result = string.substring(a + 1, b);
        } catch (RuntimeException e){
            throw new TooShortStringException();
        }
        return result;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
        //System.out.println(getPartOfString(null));
    }
}
