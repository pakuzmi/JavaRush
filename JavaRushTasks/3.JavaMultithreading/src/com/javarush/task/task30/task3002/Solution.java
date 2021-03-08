package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        String result;
        if (s.startsWith("0x")) {
            s = s.substring(2);
            result = ((Integer) Integer.parseInt(s, 16)).toString();
        } else if (s.startsWith("0b")) {
            s = s.substring(2);
            result = ((Integer) Integer.parseInt(s, 2)).toString();
        } else if (s.startsWith("0")){
            s = s.substring(1);
            result = ((Integer) Integer.parseInt(s, 8)).toString();
        } else {
            result = ((Integer) Integer.parseInt(s)).toString();
        }
        return result;
    }
}
